package io.teacheck.jdbc;

import io.teacheck.service.DatabaseServiceAlumno;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import rx.Single;

public class JDBCAlumno implements DatabaseServiceAlumno {

    private static final String GET_ALUMNO_BY_ID = "SELECT * FROM alumno WHERE alumnoId=?";
    private static final String GET_ALUMNOS = "SELECT * FROM alumno";
    private static final String GET_ALUMNO_ASIGNATURAS = "SELECT al.nombre,al.apellido,al.segundo_apellido,al.email," +
            "cu.nombre AS nombre_curso,asig.nombre AS nombre_asignatura FROM ((asignatura asig JOIN matricula ma " +
            "ON asig.matriculaID=ma.matriculaID) JOIN curso cu ON ma.cursoID=cu.cursoID) JOIN alumno al " +
            "ON ma.alumnoID=al.alumnoID WHERE al.alumnoID=?";
    private static final String GET_ALUMNO_ASIG_ESTADISTICAS = "SELECT asig.nombre AS asignatura, ex.nombre AS examen," +
            " ex.nota AS nota_examen,ex.descripcion AS desc_examen, en.nombre AS entregable, en.nota AS nota_entregable, " +
            "en.descripcion AS desc_entregable FROM ((((alumno al JOIN matricula ma ON al.alumnoid=ma.alumnoid) JOIN " +
            "asignatura asig ON asig.matriculaid=ma.matriculaid) JOIN examen ex ON asig.asignaturaid=ex.asignaturaid) " +
            "JOIN entregable en ON en.asignaturaid=asig.asignaturaid) WHERE al.alumnoid=?";

    private JDBCClient client;

    public JDBCAlumno(JDBCClient client) {
        this.client = client;
    }

    @Override
    public Single<JsonObject> getAlumno(int alumnoID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ALUMNO_BY_ID, new JsonArray().add(alumnoID))
                        .doAfterTerminate(conn::close))
                .map(resultSet -> resultSet.getRows().get(0));
    }

    @Override
    public Single<JsonArray> getAlumnos() {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQuery(GET_ALUMNOS).doAfterTerminate(conn::close))
                .map(ResultSet::toJson)
                .map(entries -> entries.getJsonArray("rows"));
    }

    @Override
    public Single<JsonObject> getAsignaturasAlumno(int alumnoID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ALUMNO_ASIGNATURAS, new JsonArray().add(alumnoID))
                        .doAfterTerminate(conn::close))
                .map(ResultSet::getRows)
                .map(jsonObjects -> {

                    JsonObject alumno = new JsonObject()
                            .put("nombre", jsonObjects.get(0).getString("nombre"))
                            .put("apellido", jsonObjects.get(0).getString("apellido"))
                            .put("segundo_apellido", jsonObjects.get(0).getString("segundo_apellido"))
                            .put("nombre_curso", jsonObjects.get(0).getString("nombre_curso"))
                            .put("email", jsonObjects.get(0).getString("email"));
                    JsonArray asignaturas = new JsonArray();
                    for (JsonObject obj : jsonObjects) {
                        JsonObject asignatura = new JsonObject()
                                .put("nombre", obj.getString("nombre_asignatura"));
                        asignaturas.add(asignatura);
                    }
                    return alumno.put("asignaturas", asignaturas);
                });

    }

    @Override
    public Single<JsonArray> getAlumnoAsigEstadisticas(int alumnoID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ALUMNO_ASIG_ESTADISTICAS, new JsonArray().add(alumnoID))
                        .doAfterTerminate(conn::close))
                .map(ResultSet::getRows)
                .map(JsonArray::new);
    }
}

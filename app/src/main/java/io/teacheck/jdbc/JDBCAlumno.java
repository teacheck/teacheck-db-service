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
    private static final String GET_ALUMNO_ASIG_ESTADISTICAS = "SELECT al.nombre,al.apellido,al.segundo_apellido," +
            "al.email,cu.nombre AS nombre_curso,asig.asignaturaid AS asignaturaid FROM (((asignatura asig JOIN matricula ma " +
            "ON asig.matriculaID=ma.matriculaID) JOIN curso cu ON ma.cursoID=cu.cursoID) JOIN alumno al ON ma.alumnoID=al.alumnoID) " +
            "WHERE al.alumnoid=?";
    private static final String GET_ALUMNO_ASIG_ESTADISTICAS_EXAMEN = "SELECT pf.nombre, ex.nombre, ex.nota " +
            "FROM ((asignatura asig JOIN profesor pf ON asig.profesorid=pf.profesorid) JOIN examen ex " +
            "ON ex.asignaturaid=asig.asignaturaid) WHERE asig.asignaturaid=?";

    private JDBCClient client;

    public JDBCAlumno(JDBCClient client) {
        this.client = client;
    }

    @Override
    public Single<JsonObject> getAlumno(int alumnoID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ALUMNO_BY_ID, new JsonArray().add(alumnoID))
                        .doAfterTerminate(conn::close))
                .map(ResultSet::toJson);
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
                .map(ResultSet::toJson);

    }

    @Override
    public Single<JsonArray> getAlumnoAsigEstadisticas(int alumnoID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ALUMNO_ASIG_ESTADISTICAS,new JsonArray().add(alumnoID))
                .map(ResultSet::getRows)
                .map(jsonObjects -> jsonObjects.get(0).getString("asignaturaid"))
                .flatMap(s -> conn.rxQueryWithParams(GET_ALUMNO_ASIG_ESTADISTICAS_EXAMEN, new JsonArray().add(Integer.valueOf(s)))
                .doAfterTerminate(conn::close))
                .map(ResultSet::getRows)
                .map(JsonArray::new));
    }
}

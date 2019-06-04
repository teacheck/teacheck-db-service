package io.teacheck.jdbc;

import com.sun.org.apache.regexp.internal.RE;
import io.teacheck.service.DatabaseServiceProfesor;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import rx.Single;

import java.util.ArrayList;
import java.util.List;

public class JDBCProfesor implements DatabaseServiceProfesor {


    private static final String GET_PROFESOR_BY_ID = "SELECT pf.nombre,pf.apellido,pf.email,asig.nombre AS asignatura " +
            "FROM asignatura asig JOIN profesor pf ON asig.profesorID=pf.profesorID WHERE pf.profesorID=? LIMIT 1";
    private static final String GET_PROFESOR_COORDINADOR = "SELECT pf.nombre,pf.apellido,pf.email,cu.nombre " +
            "AS nombre_curso,asig.nombre AS nombre_asignatura FROM (curso cu JOIN profesor pf ON cu.profesorID=pf.profesorID)" +
            " JOIN asignatura asig ON asig.profesorID=pf.profesorID WHERE pf.profesorID=? LIMIT 1";
    private static final String GET_ALUMNOS_POR_ASIGNATURA = "SELECT cu.nombre AS curso, asig.nombre AS asignatura, " +
            "al.nombre AS nombre_alumno, al.apellido AS apellido_alumno, al.segundo_apellido AS sa_alumno FROM " +
            "((((profesor pf JOIN asignatura asig ON pf.profesorid=asig.profesorid) JOIN matricula ma ON " +
            "asig.matriculaid=ma.matriculaid) JOIN alumno al ON al.alumnoid=ma.alumnoid) JOIN curso cu ON " +
            "cu.cursoid=ma.cursoid) WHERE pf.profesorid=?";
    private static final String GET_ALARMAS_ALUMNOS_POR_ASIGNATURA = "SELECT DISTINCT cu.nombre AS curso, al.nombre AS " +
            "nombre_alumno, al.apellido AS apellido_alumno, al.segundo_apellido AS sa_alumno, al.email AS " +
            "email_alumno,ala.tipo AS tipo_alarma ,ala.descripcion AS desc_alarma FROM ((((((profesor pf JOIN " +
            "asignatura asig ON pf.profesorid=asig.profesorid) JOIN matricula ma ON asig.matriculaid=ma.matriculaid) " +
            "JOIN alumno al ON al.alumnoid=ma.alumnoid) JOIN curso cu ON cu.cursoid=ma.cursoid) JOIN alarma_semanal als " +
            "ON als.alumnoid=al.alumnoid) JOIN alarma ala ON ala.alarmaid=als.alarmaid) WHERE pf.profesorid=? ORDER BY desc_alarma";
    private static final String GET_ALARMAS_ALUMNOS_COORDINADOR = "SELECT DISTINCT cu.nombre AS curso, al.nombre AS " +
            "nombre_alumno, al.apellido AS apellido_alumno, al.segundo_apellido AS sa_alumno, al.email AS " +
            "email_alumno,ala.tipo AS tipo_alarma ,ala.descripcion AS desc_alarma FROM ((((((profesor pf JOIN " +
            "asignatura asig ON pf.profesorid=asig.profesorid) JOIN matricula ma ON asig.matriculaid=ma.matriculaid) " +
            "JOIN alumno al ON al.alumnoid=ma.alumnoid) JOIN curso cu ON cu.cursoid=ma.cursoid) JOIN alarma_semanal als " +
            "ON als.alumnoid=al.alumnoid) JOIN alarma ala ON ala.alarmaid=als.alarmaid) WHERE cu.cursoid=? ORDER BY desc_alarma";
    private static final String GET_ASIGNATURAS = "SELECT DISTINCT pf.nombre,pf.apellido,pf.email,asig.nombre AS " +
            "nombre_asignatura FROM (profesor pf JOIN asignatura asig ON pf.profesorid=asig.profesorid) WHERE pf.profesorid=?";
    private static final String GET_CURSO_COORD = "select DISTINCT pf.nombre,pf.apellido,pf.email,cu.nombre AS " +
            "nombre_curso, asig.nombre AS nombre_asignatura FROM (((profesor pf JOIN curso cu ON pf.profesorid=cu.profesorid) " +
            "JOIN matricula ma ON ma.cursoid=cu.cursoid) JOIN asignatura asig ON asig.matriculaid=ma.matriculaid) WHERE pf.profesorid=?";
    private JDBCClient client;

    public JDBCProfesor(JDBCClient client) {
        this.client = client;
    }

    @Override
    public Single<JsonObject> getProfesor(int profesorID) {
        return getJsonObjectSingle(profesorID, GET_PROFESOR_BY_ID);
    }

    @Override
    public Single<JsonObject> getCoordinador(int profesorID) {
        return getJsonObjectSingle(profesorID, GET_PROFESOR_COORDINADOR);
    }

    @Override
    public Single<JsonObject> getAlumnosPorAsignatura(int profesorID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ALUMNOS_POR_ASIGNATURA, new JsonArray().add(profesorID))
                        .doAfterTerminate(conn::close))
                .map(resultSet -> {
                    JsonObject obj = new JsonObject()
                            .put("curso", resultSet.getRows().get(0).getString("curso"))
                            .put("asignatura", resultSet.getRows().get(0).getString("asignatura"));
                    JsonArray alumnos = new JsonArray();
                    for (JsonObject alumno : resultSet.getRows()) {
                        alumnos.add(new JsonObject()
                                .put("nombre", alumno.getString("nombre_alumno"))
                                .put("apellido", alumno.getString("apellido_alumno"))
                                .put("segundo_apellido", alumno.getString("sa_alumno")));
                    }
                    obj.put("alumnos", alumnos);
                    return obj;
                });
    }

    @Override
    public Single<JsonArray> getAlarmaPorAsignatura(int profesorID) {
        return getJsonArraySingle(profesorID, GET_ALARMAS_ALUMNOS_POR_ASIGNATURA);
    }

    @Override
    public Single<JsonArray> getAlarmaAlumnosCoordinador(int cursoID) {
        return getJsonArraySingle(cursoID, GET_ALARMAS_ALUMNOS_COORDINADOR);
    }

    @Override
    public Single<JsonObject> getAsignaturas(int profesorID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ASIGNATURAS, new JsonArray().add(profesorID))
                        .doAfterTerminate(conn::close))
                .map(resultSet -> {
                    JsonObject profesor = new JsonObject()
                            .put("nombre", resultSet.getRows().get(0).getString("nombre"))
                            .put("apellido", resultSet.getRows().get(0).getString("apellido"))
                            .put("email", resultSet.getRows().get(0).getString("email"));
                    JsonArray asignaturas = new JsonArray();
                    for (JsonObject object : resultSet.getRows()) {
                        asignaturas.add(new JsonObject()
                                .put("nombre", object.getString("nombre_asignatura")));
                    }
                    profesor.put("asignaturas", asignaturas);
                    return profesor;
                });
    }

    @Override
    public Single<JsonObject> getCursoCoord(int profesorID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_CURSO_COORD, new JsonArray().add(profesorID))
                .doAfterTerminate(conn::close))
                .map(resultSet -> {
                    JsonObject profesor = new JsonObject()
                            .put("curso", resultSet.getRows().get(0).getString("nombre_curso"))
                            .put("nombre", resultSet.getRows().get(0).getString("nombre"))
                            .put("apellido", resultSet.getRows().get(0).getString("apellido"))
                            .put("email", resultSet.getRows().get(0).getString("email"));
                    JsonArray asignaturas = new JsonArray();
                    for (JsonObject object : resultSet.getRows()) {
                        asignaturas.add(new JsonObject()
                                .put("nombre", object.getString("nombre_asignatura")));
                    }
                    profesor.put("asignaturas", asignaturas);
                    return profesor;
                });
    }

    private Single<JsonArray> getJsonArraySingle(int id, String sql) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(sql, new JsonArray().add(id))
                        .doAfterTerminate(conn::close))
                .map(resultSet -> {
                    JsonArray asignaturas = new JsonArray();
                    for (String asig : getNombreAsignaturas(resultSet.getRows())) {
                        JsonObject asignatura = new JsonObject()
                                .put("asignatura", asig);
                        JsonArray alumnos = new JsonArray();
                        for (JsonObject obj : resultSet.getRows()) {
                            if (asig.equals(obj.getString("desc_alarma"))) {
                                alumnos.add(new JsonObject()
                                        .put("nombre", obj.getString("nombre_alumno"))
                                        .put("apellido", obj.getString("apellido_alumno"))
                                        .put("segundo_apellido", obj.getString("sa_alumno"))
                                        .put("alarma", obj.getString("tipo_alarma")));
                                asignatura.put("curso", obj.getString("curso"));
                            }
                        }
                        asignatura.put("alumnos", alumnos);
                        asignaturas.add(asignatura);
                    }
                    return asignaturas;
                });
    }

    private List<String> getNombreAsignaturas(List<JsonObject> jsonObjects) {
        List<String> asignaturas = new ArrayList<>();
        for (JsonObject entries : jsonObjects) {
            if (!asignaturas.contains(entries.getString("desc_alarma"))) {
                asignaturas.add(entries.getString("desc_alarma"));
            }
        }
        return asignaturas;
    }

    private Single<JsonObject> getJsonObjectSingle(int profesorID, String sql) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(sql, new JsonArray().add(profesorID))
                        .doAfterTerminate(conn::close))
                .map(ResultSet::toJson)
                .map(entries -> entries.getJsonArray("rows").getJsonObject(0));
    }
}

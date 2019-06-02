package io.teacheck.jdbc;

import io.teacheck.service.DatabaseServiceProfesor;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import rx.Single;

public class JDBCProfesor implements DatabaseServiceProfesor {


    private static final String GET_PROFESOR_BY_ID = "SELECT pf.nombre,pf.apellido,pf.email,asig.nombre AS asignatura " +
            "FROM asignatura asig JOIN profesor pf ON asig.profesorID=pf.profesorID WHERE pf.profesorID=? LIMIT 1";
    private static final String GET_PROFESOR_COORDINADOR = "SELECT pf.nombre,pf.apellido,pf.email,cu.nombre " +
            "AS nombre_curso,asig.nombre AS nombre_asignatura FROM (curso cu JOIN profesor pf ON cu.profesorID=pf.profesorID)" +
            " JOIN asignatura asig ON asig.profesorID=pf.profesorID WHERE pf.profesorID=? LIMIT 1";

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

    private Single<JsonObject> getJsonObjectSingle(int profesorID, String sql) {
        return client.rxGetConnection()
                .flatMap(conn -> {
                    return conn.rxQueryWithParams(sql, new JsonArray().add(profesorID)).doAfterTerminate(conn::close);
                })
                .map(ResultSet::toJson)
                .map(entries -> entries.getJsonArray("rows").getJsonObject(0));
    }
}

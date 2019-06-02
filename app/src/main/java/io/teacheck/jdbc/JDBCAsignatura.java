package io.teacheck.jdbc;

import io.teacheck.service.DatabaseServiceAsignatura;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import rx.Single;

public class JDBCAsignatura implements DatabaseServiceAsignatura {

    private static final String GET_ASIGNATURA_ALUMNOS = "SELECT al.nombre, al.apellido FROM ((asignatura asig JOIN matricula ma " +
            "ON asig.matriculaid=ma.matriculaid) JOIN alumno al ON ma.alumnoid=al.alumnoid) WHERE asig.nombre=?";

    private JDBCClient client;

    public JDBCAsignatura(JDBCClient client) {
        this.client = client;
    }

    @Override
    public Single<JsonArray> getAsignaturaAlumnos(String nombreAsignatura) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_ASIGNATURA_ALUMNOS, new JsonArray().add(nombreAsignatura))
                        .doAfterTerminate(conn::close))
                .map(ResultSet::getRows)
                .map(JsonArray::new);
    }
}

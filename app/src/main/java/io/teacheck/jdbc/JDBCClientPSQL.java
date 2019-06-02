package io.teacheck.jdbc;

import io.teacheck.constants.Constants;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.jdbc.JDBCClient;

public class JDBCClientPSQL {

    private JDBCClient client;
    private Vertx vertx;

    private final JsonObject config = new JsonObject()
            .put("url", Constants.JDBC_DATABASE_URL)
            .put("user", Constants.POSTGRES_USER)
            .put("password", Constants.POSTGRES_PASSWORD)
            .put("driver_class", "org.postgresql.Driver");

    public JDBCClientPSQL(Vertx vertx) {
        this.vertx = vertx;
        client = JDBCClient.createShared(this.vertx, config);
    }

    public JDBCClient getClient() {
        return this.client;
    }
}

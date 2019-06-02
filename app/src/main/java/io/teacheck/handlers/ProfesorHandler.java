package io.teacheck.handlers;

import io.teacheck.jdbc.JDBCProfesor;
import io.teacheck.service.DatabaseServiceProfesor;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

public class ProfesorHandler {

    private DatabaseServiceProfesor serviceProfesor;

    public ProfesorHandler(JDBCClient client) {
        serviceProfesor = new JDBCProfesor(client);
    }

    public void registerProfesorHandlers(Router router) {
        router.get("/api/profesor/:profesorID").handler(this::getProfesorHandler);
        router.get("/api/profesor/:profesorID/coordinador").handler(this::getCoordinadorHandler);
    }

    private void getCoordinadorHandler(RoutingContext ctx) {
        int profesorID = Integer.parseInt(ctx.request().getParam("profesorID"));
        serviceProfesor.getProfesor(profesorID)
                .subscribe(entries -> ctx.response()
                        .putHeader("Content-Type", "application/json")
                        .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }

    private void getProfesorHandler(RoutingContext ctx) {
        int profesorID = Integer.parseInt(ctx.request().getParam("profesorID"));
        serviceProfesor.getProfesor(profesorID)
                .subscribe(entries -> ctx.response()
                        .putHeader("Content-Type", "application/json")
                        .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }
}

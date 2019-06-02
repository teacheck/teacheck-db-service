package io.teacheck.handlers;

import io.teacheck.jdbc.JDBCAsignatura;
import io.teacheck.service.DatabaseServiceAsignatura;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

public class AsignaturaHandler {

    private DatabaseServiceAsignatura serviceAsignatura;

    public AsignaturaHandler(JDBCClient client) {
        serviceAsignatura = new JDBCAsignatura(client);
    }

    public void registerAsignaturaHandlers(Router router) {
        router.get("/api/asignatura/:nombre/alumnos").handler(this::getAsignaturaAlumnos);
    }

    private void getAsignaturaAlumnos(RoutingContext ctx) {
        String nombreAsig = ctx.request().getParam("nombre");
        serviceAsignatura.getAsignaturaAlumnos(nombreAsig)
                .subscribe(jsonArray -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(jsonArray.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }
}

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
        router.get("/api/profesor/:profesorID/alumnos-por-asignatura").handler(this::getAlumnosPorAsignatura);
        router.get("/api/profesor/:profesorID/alarma-por-asignatura").handler(this::getAlarmaPorAsignatura);
        router.get("/api/profesor/curso/:cursoID/alarma-alumnos-coord").handler(this::getAlarmaAlumnosCoord);
        router.get("/api/profesor/:profesorID/asignaturas").handler(this::getAsignaturas);
        router.get("/api/profesor/:profesorID/curso-coord").handler(this::getCursoCoord);
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

    private void getAlumnosPorAsignatura(RoutingContext ctx) {
        int profesorID = Integer.parseInt(ctx.request().getParam("profesorID"));
        serviceProfesor.getAlumnosPorAsignatura(profesorID)
                .subscribe(entries -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }


    private void getAlarmaPorAsignatura(RoutingContext ctx) {
        int profesorID = Integer.parseInt(ctx.request().getParam("profesorID"));
        serviceProfesor.getAlarmaPorAsignatura(profesorID)
                .subscribe(entries -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }


    private void getAlarmaAlumnosCoord(RoutingContext ctx) {
        int cursoID = Integer.parseInt(ctx.request().getParam("cursoID"));
        serviceProfesor.getAlarmaAlumnosCoordinador(cursoID)
                .subscribe(entries -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }

    private void getAsignaturas(RoutingContext ctx) {
        int profesorID = Integer.parseInt(ctx.request().getParam("profesorID"));
        serviceProfesor.getAsignaturas(profesorID)
                .subscribe(entries -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }


    private void getCursoCoord(RoutingContext ctx) {
        int profesorID = Integer.parseInt(ctx.request().getParam("profesorID"));
        serviceProfesor.getCursoCoord(profesorID)
                .subscribe(entries -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }
}

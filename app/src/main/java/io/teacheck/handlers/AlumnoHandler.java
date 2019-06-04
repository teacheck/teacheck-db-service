package io.teacheck.handlers;

import io.teacheck.jdbc.JDBCAlumno;
import io.teacheck.service.DatabaseServiceAlumno;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

public class AlumnoHandler {

    private DatabaseServiceAlumno serviceAlumno;

    public AlumnoHandler(JDBCClient client) {
        this.serviceAlumno = new JDBCAlumno(client);
    }

    public void registerAlumnoHandlers(Router router) {
        router.get("/api/alumnos").handler(this::getAllStudents);
        router.get("/api/alumnos/:alumnoID").handler(this::getStudent);
        router.get("/api/alumnos/:alumnoID/asignaturas").handler(this::getAsignaturasAlumno);
        router.get("/api/alumnos/:alumnoID/asignaturas-only").handler(this::getAsignaturasOnly);
        router.get("/api/alumnos/:alumnoID/asignatura-estadisticas").handler(this::getAsignaturaEstadisticas);
    }

    private void getStudent(RoutingContext ctx) {
        int alumnoID = Integer.parseInt(ctx.request().getParam("alumnoID"));
        serviceAlumno.getAlumno(alumnoID)
                .subscribe((entries) -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));

    }

    private void getAllStudents(RoutingContext ctx) {
        serviceAlumno.getAlumnos()
                .subscribe(jsonArray -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(jsonArray.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }

    private void getAsignaturasAlumno(RoutingContext ctx) {
        int alumnoID = Integer.parseInt(ctx.request().getParam("alumnoID"));
        serviceAlumno.getAsignaturasAlumno(alumnoID)
                .subscribe(entries -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }

    private void getAsignaturaEstadisticas(RoutingContext ctx) {
        int alumnoID = Integer.parseInt(ctx.request().getParam("alumnoID"));
        serviceAlumno.getAlumnoAsigEstadisticas(alumnoID)
                .subscribe(jsonArray -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(jsonArray.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }

    private void getAsignaturasOnly(RoutingContext ctx) {
        int alumnoID = Integer.parseInt(ctx.request().getParam("alumnoID"));
        serviceAlumno.getOnlyAsignaturas(alumnoID)
                .subscribe(entries -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(entries.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }
}

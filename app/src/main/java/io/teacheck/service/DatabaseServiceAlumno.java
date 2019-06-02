package io.teacheck.service;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import rx.Single;

public interface DatabaseServiceAlumno {

    Single<JsonObject> getAlumno(int alumnoID);

    Single<JsonArray> getAlumnos();

    Single<JsonObject> getAsignaturasAlumno(int alumnoID);

    Single<JsonArray> getAlumnoAsigEstadisticas(int alumnoID);
}

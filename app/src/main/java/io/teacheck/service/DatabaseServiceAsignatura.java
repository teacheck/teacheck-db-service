package io.teacheck.service;

import io.vertx.core.json.JsonArray;
import rx.Single;

public interface DatabaseServiceAsignatura {

    Single<JsonArray> getAsignaturaAlumnos(String nombreAsignatura);
}

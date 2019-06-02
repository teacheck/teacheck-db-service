package io.teacheck.service;

import io.vertx.core.json.JsonObject;
import rx.Single;

public interface DatabaseServiceProfesor {

    Single<JsonObject> getProfesor(int profesorID);
    Single<JsonObject> getCoordinador(int profesorID);
}

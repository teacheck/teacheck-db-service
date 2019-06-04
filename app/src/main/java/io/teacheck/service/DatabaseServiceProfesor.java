package io.teacheck.service;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import rx.Single;

public interface DatabaseServiceProfesor {

    Single<JsonObject> getProfesor(int profesorID);

    Single<JsonObject> getCoordinador(int profesorID);

    Single<JsonObject> getAlumnosPorAsignatura(int profesorID);

    Single<JsonObject> getAlarmaPorAsignatura(int profesorID);

    Single<JsonObject> getAlarmaAlumnosCoordinador(int cursoID);

    Single<JsonObject> getAsignaturas(int profesorID);

    Single<JsonObject> getCursoCoord(int profesorID);
}

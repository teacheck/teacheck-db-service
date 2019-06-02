package io.teacheck.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.teacheck.model.Comportamiento}.
 * NOTE: This class has been automatically generated from the {@link io.teacheck.model.Comportamiento} original class using Vert.x codegen.
 */
public class ComportamientoConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Comportamiento obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "asignaturaID":
          if (member.getValue() instanceof Number) {
            obj.setAsignaturaID(((Number)member.getValue()).intValue());
          }
          break;
        case "atencion":
          if (member.getValue() instanceof String) {
            obj.setAtencion(io.teacheck.model.Evaluacion.valueOf((String)member.getValue()));
          }
          break;
        case "comportamientoID":
          if (member.getValue() instanceof Number) {
            obj.setComportamientoID(((Number)member.getValue()).intValue());
          }
          break;
        case "motivacion":
          if (member.getValue() instanceof String) {
            obj.setMotivacion(io.teacheck.model.Evaluacion.valueOf((String)member.getValue()));
          }
          break;
      }
    }
  }

  public static void toJson(Comportamiento obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Comportamiento obj, java.util.Map<String, Object> json) {
    json.put("asignaturaID", obj.getAsignaturaID());
    if (obj.getAtencion() != null) {
      json.put("atencion", obj.getAtencion().name());
    }
    json.put("comportamientoID", obj.getComportamientoID());
    if (obj.getMotivacion() != null) {
      json.put("motivacion", obj.getMotivacion().name());
    }
  }
}

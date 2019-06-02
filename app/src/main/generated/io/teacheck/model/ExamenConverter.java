package io.teacheck.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.teacheck.model.Examen}.
 * NOTE: This class has been automatically generated from the {@link io.teacheck.model.Examen} original class using Vert.x codegen.
 */
public class ExamenConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Examen obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "asignaturaID":
          if (member.getValue() instanceof Number) {
            obj.setAsignaturaID(((Number)member.getValue()).intValue());
          }
          break;
        case "competencia":
          if (member.getValue() instanceof String) {
            obj.setCompetencia((String)member.getValue());
          }
          break;
        case "descripcion":
          if (member.getValue() instanceof String) {
            obj.setDescripcion((String)member.getValue());
          }
          break;
        case "examenID":
          if (member.getValue() instanceof Number) {
            obj.setExamenID(((Number)member.getValue()).intValue());
          }
          break;
        case "nombre":
          if (member.getValue() instanceof String) {
            obj.setNombre((String)member.getValue());
          }
          break;
        case "nota":
          if (member.getValue() instanceof Number) {
            obj.setNota(((Number)member.getValue()).floatValue());
          }
          break;
        case "peso":
          if (member.getValue() instanceof Number) {
            obj.setPeso(((Number)member.getValue()).intValue());
          }
          break;
      }
    }
  }

  public static void toJson(Examen obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Examen obj, java.util.Map<String, Object> json) {
    json.put("asignaturaID", obj.getAsignaturaID());
    if (obj.getCompetencia() != null) {
      json.put("competencia", obj.getCompetencia());
    }
    if (obj.getDescripcion() != null) {
      json.put("descripcion", obj.getDescripcion());
    }
    json.put("examenID", obj.getExamenID());
    if (obj.getNombre() != null) {
      json.put("nombre", obj.getNombre());
    }
    json.put("nota", obj.getNota());
    json.put("peso", obj.getPeso());
  }
}

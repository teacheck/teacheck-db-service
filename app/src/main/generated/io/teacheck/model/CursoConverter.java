package io.teacheck.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.teacheck.model.Curso}.
 * NOTE: This class has been automatically generated from the {@link io.teacheck.model.Curso} original class using Vert.x codegen.
 */
public class CursoConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Curso obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "cursoID":
          if (member.getValue() instanceof Number) {
            obj.setCursoID(((Number)member.getValue()).intValue());
          }
          break;
        case "descripcion":
          if (member.getValue() instanceof String) {
            obj.setDescripcion((String)member.getValue());
          }
          break;
        case "nombre":
          if (member.getValue() instanceof String) {
            obj.setNombre((String)member.getValue());
          }
          break;
        case "profesorID":
          if (member.getValue() instanceof Number) {
            obj.setProfesorID(((Number)member.getValue()).intValue());
          }
          break;
      }
    }
  }

  public static void toJson(Curso obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Curso obj, java.util.Map<String, Object> json) {
    json.put("cursoID", obj.getCursoID());
    if (obj.getDescripcion() != null) {
      json.put("descripcion", obj.getDescripcion());
    }
    if (obj.getNombre() != null) {
      json.put("nombre", obj.getNombre());
    }
    json.put("profesorID", obj.getProfesorID());
  }
}

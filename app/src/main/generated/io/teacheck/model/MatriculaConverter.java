package io.teacheck.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.teacheck.model.Matricula}.
 * NOTE: This class has been automatically generated from the {@link io.teacheck.model.Matricula} original class using Vert.x codegen.
 */
public class MatriculaConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Matricula obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "alumnoID":
          if (member.getValue() instanceof Number) {
            obj.setAlumnoID(((Number)member.getValue()).intValue());
          }
          break;
        case "asignaturaID":
          if (member.getValue() instanceof Number) {
            obj.setAsignaturaID(((Number)member.getValue()).intValue());
          }
          break;
        case "cursoID":
          if (member.getValue() instanceof Number) {
            obj.setCursoID(((Number)member.getValue()).intValue());
          }
          break;
        case "matriculaID":
          if (member.getValue() instanceof Number) {
            obj.setMatriculaID(((Number)member.getValue()).intValue());
          }
          break;
      }
    }
  }

  public static void toJson(Matricula obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Matricula obj, java.util.Map<String, Object> json) {
    json.put("alumnoID", obj.getAlumnoID());
    json.put("asignaturaID", obj.getAsignaturaID());
    json.put("cursoID", obj.getCursoID());
    json.put("matriculaID", obj.getMatriculaID());
  }
}

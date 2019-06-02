package io.teacheck.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.teacheck.model.FeedbackSemanal}.
 * NOTE: This class has been automatically generated from the {@link io.teacheck.model.FeedbackSemanal} original class using Vert.x codegen.
 */
public class FeedbackSemanalConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, FeedbackSemanal obj) {
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
        case "feedbackID":
          if (member.getValue() instanceof Number) {
            obj.setFeedbackID(((Number)member.getValue()).intValue());
          }
          break;
        case "semanalID":
          if (member.getValue() instanceof Number) {
            obj.setSemanalID(((Number)member.getValue()).intValue());
          }
          break;
      }
    }
  }

  public static void toJson(FeedbackSemanal obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(FeedbackSemanal obj, java.util.Map<String, Object> json) {
    json.put("alumnoID", obj.getAlumnoID());
    json.put("asignaturaID", obj.getAsignaturaID());
    json.put("feedbackID", obj.getFeedbackID());
    json.put("semanalID", obj.getSemanalID());
  }
}

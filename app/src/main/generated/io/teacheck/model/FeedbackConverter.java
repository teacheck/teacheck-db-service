package io.teacheck.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.teacheck.model.Feedback}.
 * NOTE: This class has been automatically generated from the {@link io.teacheck.model.Feedback} original class using Vert.x codegen.
 */
public class FeedbackConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Feedback obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "evaluacion":
          if (member.getValue() instanceof String) {
            obj.setEvaluacion(io.teacheck.model.Evaluacion.valueOf((String)member.getValue()));
          }
          break;
        case "feedbackID":
          if (member.getValue() instanceof Number) {
            obj.setFeedbackID(((Number)member.getValue()).intValue());
          }
          break;
        case "horasTrabajadas":
          if (member.getValue() instanceof Number) {
            obj.setHorasTrabajadas(((Number)member.getValue()).intValue());
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

  public static void toJson(Feedback obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Feedback obj, java.util.Map<String, Object> json) {
    if (obj.getEvaluacion() != null) {
      json.put("evaluacion", obj.getEvaluacion().name());
    }
    json.put("feedbackID", obj.getFeedbackID());
    json.put("horasTrabajadas", obj.getHorasTrabajadas());
    if (obj.getMotivacion() != null) {
      json.put("motivacion", obj.getMotivacion().name());
    }
  }
}

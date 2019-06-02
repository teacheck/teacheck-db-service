package io.teacheck.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter for {@link io.teacheck.model.Alumno}.
 * NOTE: This class has been automatically generated from the {@link io.teacheck.model.Alumno} original class using Vert.x codegen.
 */
public class AlumnoConverter {

  public static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, Alumno obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "alumnoID":
          if (member.getValue() instanceof Number) {
            obj.setAlumnoID(((Number)member.getValue()).intValue());
          }
          break;
        case "apellido":
          if (member.getValue() instanceof String) {
            obj.setApellido((String)member.getValue());
          }
          break;
        case "email":
          if (member.getValue() instanceof String) {
            obj.setEmail((String)member.getValue());
          }
          break;
        case "nombre":
          if (member.getValue() instanceof String) {
            obj.setNombre((String)member.getValue());
          }
          break;
        case "repetidor":
          if (member.getValue() instanceof Boolean) {
            obj.setRepetidor((Boolean)member.getValue());
          }
          break;
        case "segundoApellido":
          if (member.getValue() instanceof String) {
            obj.setSegundoApellido((String)member.getValue());
          }
          break;
        case "telefono":
          if (member.getValue() instanceof String) {
            obj.setTelefono((String)member.getValue());
          }
          break;
      }
    }
  }

  public static void toJson(Alumno obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

  public static void toJson(Alumno obj, java.util.Map<String, Object> json) {
    json.put("alumnoID", obj.getAlumnoID());
    if (obj.getApellido() != null) {
      json.put("apellido", obj.getApellido());
    }
    if (obj.getEmail() != null) {
      json.put("email", obj.getEmail());
    }
    if (obj.getNombre() != null) {
      json.put("nombre", obj.getNombre());
    }
    json.put("repetidor", obj.isRepetidor());
    if (obj.getSegundoApellido() != null) {
      json.put("segundoApellido", obj.getSegundoApellido());
    }
    if (obj.getTelefono() != null) {
      json.put("telefono", obj.getTelefono());
    }
  }
}

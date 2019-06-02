package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

@DataObject(generateConverter = true)
public class Alumno implements Serializable {

    private int alumnoID;
    private String nombre;
    private String apellido;
    private String segundoApellido;
    private String email;
    private String telefono;
    private boolean repetidor;
    private int alarmaID;

    public Alumno(int alumnoID, String nombre, String apellido, String segundoApellido, String email, String telefono, boolean repetidor, int alarmaID) {
        this.alumnoID = alumnoID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.telefono = telefono;
        this.repetidor = repetidor;
        this.alarmaID = alarmaID;
    }

    public Alumno(JsonObject jsonObject) {
        AlumnoConverter.fromJson(jsonObject, this);
    }

    public JsonObject toJson() {
        JsonObject alumno = new JsonObject();
        AlumnoConverter.toJson(this, alumno);
        return alumno;
    }

    public int getAlumnoID() {
        return alumnoID;
    }

    public void setAlumnoID(int alumnoID) {
        this.alumnoID = alumnoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isRepetidor() {
        return repetidor;
    }

    public void setRepetidor(boolean repetidor) {
        this.repetidor = repetidor;
    }

    public int getAlarmaID() {
        return alarmaID;
    }

    public void setAlarmaID(int alarmaID) {
        this.alarmaID = alarmaID;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "alumnoID=" + alumnoID +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", repetidor=" + repetidor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;

        Alumno alumno = (Alumno) o;

        return getAlumnoID() == alumno.getAlumnoID();

    }

    @Override
    public int hashCode() {
        return getAlumnoID();
    }
}

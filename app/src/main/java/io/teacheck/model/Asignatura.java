package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

@DataObject(generateConverter = true)
public class Asignatura implements Serializable {
    private int asignaturaID;
    private int matriculaID;
    private String nombre;
    private String descripcion;
    private int peso;
    private int profesorID;
    private int alarmaID;

    public Asignatura(int asignaturaID, int matriculaID, String nombre, String descripcion, int peso, int profesorID, int alarmaID) {
        this.asignaturaID = asignaturaID;
        this.matriculaID = matriculaID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.profesorID = profesorID;
        this.alarmaID = alarmaID;
    }

    public Asignatura(JsonObject jsonObject) {

    }

    public int getAsignaturaID() {
        return asignaturaID;
    }

    public void setAsignaturaID(int asignaturaID) {
        this.asignaturaID = asignaturaID;
    }

    public int getMatriculaID() {
        return matriculaID;
    }

    public void setMatriculaID(int matriculaID) {
        this.matriculaID = matriculaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(int profesorID) {
        this.profesorID = profesorID;
    }

    public int getAlarmaID() {
        return alarmaID;
    }

    public void setAlarmaID(int alarmaID) {
        this.alarmaID = alarmaID;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "asignaturaID=" + asignaturaID +
                ", matriculaID=" + matriculaID +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", peso=" + peso +
                ", profesorID=" + profesorID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asignatura)) return false;

        Asignatura that = (Asignatura) o;

        return getAsignaturaID() == that.getAsignaturaID();

    }

    @Override
    public int hashCode() {
        return getAsignaturaID();
    }
}

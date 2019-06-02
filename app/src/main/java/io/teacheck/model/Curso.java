package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Curso {
    private int cursoID;
    private String nombre;
    private String descripcion;
    private int profesorID;

    public Curso(int cursoID, String nombre, String descripcion, int profesorID) {
        this.cursoID = cursoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesorID = profesorID;
    }

    public Curso(JsonObject jsonObject) {

    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
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

    public int getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(int profesorID) {
        this.profesorID = profesorID;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "cursoID=" + cursoID +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", profesorID=" + profesorID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;

        Curso curso = (Curso) o;

        return getCursoID() == curso.getCursoID();

    }

    @Override
    public int hashCode() {
        return getCursoID();
    }
}

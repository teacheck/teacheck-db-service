package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.Date;

@DataObject(generateConverter = true)
public class Examen implements Serializable {
    private int examenID;
    private String nombre;
    private String descripcion;
    private String competencia;
    private int peso;
    private float nota;
    private Date fecha;
    private int asignaturaID;

    public Examen(int examenID, String nombre, String descripcion, String competencia, int peso, float nota, Date fecha, int asignaturaID) {
        this.examenID = examenID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.competencia = competencia;
        this.peso = peso;
        this.nota = nota;
        this.fecha = fecha;
        this.asignaturaID = asignaturaID;
    }

    public Examen(JsonObject jsonObject) {

    }

    public int getExamenID() {
        return examenID;
    }

    public void setExamenID(int examenID) {
        this.examenID = examenID;
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

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAsignaturaID() {
        return asignaturaID;
    }

    public void setAsignaturaID(int asignaturaID) {
        this.asignaturaID = asignaturaID;
    }

    @Override
    public String toString() {
        return "Examen{" +
                "examenID=" + examenID +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", competencia='" + competencia + '\'' +
                ", peso=" + peso +
                ", nota=" + nota +
                ", fecha=" + fecha +
                ", asignaturaID=" + asignaturaID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examen)) return false;

        Examen examen = (Examen) o;

        return getExamenID() == examen.getExamenID();

    }

    @Override
    public int hashCode() {
        return getExamenID();
    }
}

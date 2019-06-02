package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.Date;

@DataObject(generateConverter = true)
public class Comportamiento implements Serializable {
    private int comportamientoID;
    private Evaluacion atencion;
    private Evaluacion motivacion;
    private Date fecha;
    private int asignaturaID;

    public Comportamiento(int comportamientoID, Evaluacion atencion, Evaluacion motivacion, Date fecha, int asignaturaID) {
        this.comportamientoID = comportamientoID;
        this.atencion = atencion;
        this.motivacion = motivacion;
        this.fecha = fecha;
        this.asignaturaID = asignaturaID;
    }

    public Comportamiento(JsonObject jsonObject) {

    }

    public int getComportamientoID() {
        return comportamientoID;
    }

    public void setComportamientoID(int comportamientoID) {
        this.comportamientoID = comportamientoID;
    }

    public Evaluacion getAtencion() {
        return atencion;
    }

    public void setAtencion(Evaluacion atencion) {
        this.atencion = atencion;
    }

    public Evaluacion getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(Evaluacion motivacion) {
        this.motivacion = motivacion;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comportamiento)) return false;

        Comportamiento that = (Comportamiento) o;

        return getComportamientoID() == that.getComportamientoID();

    }

    @Override
    public int hashCode() {
        return getComportamientoID();
    }
}

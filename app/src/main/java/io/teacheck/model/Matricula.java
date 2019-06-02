package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.Date;

@DataObject(generateConverter = true)
public class Matricula implements Serializable {
    private int matriculaID;
    private int alumnoID;
    private int cursoID;
    private Date fechaInit;

    public Matricula(int matriculaID, int alumnoID, int cursoID, Date fechaInit) {
        this.matriculaID = matriculaID;
        this.alumnoID = alumnoID;
        this.cursoID = cursoID;
        this.fechaInit = fechaInit;
    }

    public Matricula(JsonObject jsonObject){

    }

    public int getMatriculaID() {
        return matriculaID;
    }

    public void setMatriculaID(int matriculaID) {
        this.matriculaID = matriculaID;
    }

    public int getAlumnoID() {
        return alumnoID;
    }

    public void setAlumnoID(int alumnoID) {
        this.alumnoID = alumnoID;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public Date getFechaInit() {
        return fechaInit;
    }

    public void setFechaInit(Date fechaInit) {
        this.fechaInit = fechaInit;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "matriculaID=" + matriculaID +
                ", alumnoID=" + alumnoID +
                ", cursoID=" + cursoID +
                ", fechaInit=" + fechaInit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula)) return false;

        Matricula matricula = (Matricula) o;

        return getMatriculaID() == matricula.getMatriculaID();

    }

    @Override
    public int hashCode() {
        return getMatriculaID();
    }
}

package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

@DataObject(generateConverter = true)
public class FeedbackSemanal implements Serializable {
    private int semanalID;
    private int asignaturaID;
    private int alumnoID;
    private int feedbackID;

    public FeedbackSemanal(int semanalID, int asignaturaID, int alumnoID, int feedbackID) {
        this.semanalID = semanalID;
        this.asignaturaID = asignaturaID;
        this.alumnoID = alumnoID;
        this.feedbackID = feedbackID;
    }

    public FeedbackSemanal(JsonObject jsonObject) {
        FeedbackSemanalConverter.fromJson(jsonObject,this);
    }

    public int getSemanalID() {
        return semanalID;
    }

    public void setSemanalID(int semanalID) {
        this.semanalID = semanalID;
    }

    public int getAsignaturaID() {
        return asignaturaID;
    }

    public void setAsignaturaID(int asignaturaID) {
        this.asignaturaID = asignaturaID;
    }

    public int getAlumnoID() {
        return alumnoID;
    }

    public void setAlumnoID(int alumnoID) {
        this.alumnoID = alumnoID;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    @Override
    public String toString() {
        return "FeedbackSemanal{" +
                "semanalID=" + semanalID +
                ", asignaturaID=" + asignaturaID +
                ", alumnoID=" + alumnoID +
                ", feedbackID=" + feedbackID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedbackSemanal)) return false;

        FeedbackSemanal that = (FeedbackSemanal) o;

        return getSemanalID() == that.getSemanalID();

    }

    @Override
    public int hashCode() {
        return getSemanalID();
    }
}

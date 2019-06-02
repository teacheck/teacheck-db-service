package io.teacheck.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

@DataObject(generateConverter = true)
public class Feedback implements Serializable {
    private int feedbackID;
    private Evaluacion evaluacion;
    private Evaluacion motivacion;
    private int horasTrabajadas;

    public Feedback(int feedbackID, Evaluacion evaluacion, Evaluacion motivacion, int horasTrabajadas) {
        this.feedbackID = feedbackID;
        this.evaluacion = evaluacion;
        this.motivacion = motivacion;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Feedback(JsonObject jsonObject) {

    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Evaluacion getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(Evaluacion motivacion) {
        this.motivacion = motivacion;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackID=" + feedbackID +
                ", evaluacion=" + evaluacion +
                ", motivacion=" + motivacion +
                ", horasTrabajadas=" + horasTrabajadas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;

        Feedback feedback = (Feedback) o;

        return getFeedbackID() == feedback.getFeedbackID();

    }

    @Override
    public int hashCode() {
        return getFeedbackID();
    }
}

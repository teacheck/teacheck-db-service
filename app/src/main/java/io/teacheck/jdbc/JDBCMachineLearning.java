package io.teacheck.jdbc;

import io.teacheck.service.DatabaseServiceML;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import rx.Single;

import java.util.ArrayList;
import java.util.List;

public class JDBCMachineLearning implements DatabaseServiceML {

    private static final String GET_DATA_REPORT_SEMANAL = "SELECT al.alumnoid, feeds.semana AS semana,comp.asistencia AS asistencia_comp, ex.nota AS nota_examen,en.nombre AS nombre_entregable," +
            "en.nota AS nota_entregable,feed.horas_trabajadas,comp.atencion AS atencion_comp,comp.motivacion AS motivacion_comp,feed.atencion AS atencion_feed,feed.motivacion AS motivacion_feed FROM " +
            "((((((((alumno al JOIN matricula ma ON ma.alumnoid=al.alumnoid) JOIN asignatura asig ON asig.matriculaid=ma.matriculaid) " +
            "JOIN examen ex ON ex.asignaturaid=asig.asignaturaid) JOIN entregable en ON en.asignaturaid=asig.asignaturaid) " +
            "JOIN comportamiento_semanal comps ON comps.asignaturaid=asig.asignaturaid) JOIN comportamiento comp " +
            "ON comp.comportamientoid=comps.comportamientoid) JOIN feedback_semanal feeds ON feeds.asignaturaid=asig.asignaturaid) " +
            "JOIN feedback feed ON feed.feedbackid=feeds.feedbackid) WHERE feeds.semana=? and comps.semana=?";

    private JDBCClient client;

    public JDBCMachineLearning(JDBCClient client) {
        this.client = client;
    }

    @Override
    public Single<JsonArray> getDataReportSemanal(int semanaID) {
        return client.rxGetConnection()
                .flatMap(conn -> conn.rxQueryWithParams(GET_DATA_REPORT_SEMANAL,
                        new JsonArray().add(semanaID).add(semanaID))
                        .doAfterTerminate(conn::close))
                .map(resultSet -> {
                    JsonArray report = new JsonArray();
                    for (String e : getEntregables(resultSet.getRows())) {
                        JsonArray data = new JsonArray();
                        for (JsonObject object : resultSet.getRows()) {
                            if (e.equals(object.getString("nombre_entregable"))) {
                                data.add(new JsonObject()
                                        .put("AlumnoID", object.getInteger("alumnoid"))
                                        .put("Semana", object.getInteger("semana"))
                                        .put("Asistencia", object.getDouble("asistencia_comp"))
                                        .put("NotaC11", object.getDouble("nota_examen"))
                                        .put("EntregableC11", object.getString("nombre_entregable"))
                                        .put("NotaEntregableC11", object.getDouble("nota_entregable"))
                                        .put("Horas1", object.getDouble("horas_trabajadas"))
                                        .put("AtenciónP1", object.getString("atencion_comp"))
                                        .put("MotivaciónP1", object.getString("motivacion_comp"))
                                        .put("AtenciónA1", object.getString("atencion_feed"))
                                        .put("MotivaciónA1", object.getString("motivacion_feed"))
                                        .put("Criterios", ""));
                                System.out.println("Mhmmm....");
                            }

                        }
                        report.add(new JsonObject().put("data", data));
                    }

                    return report;
                });
    }

    private List<String> getEntregables(List<JsonObject> rows) {
        List<String> entregables = new ArrayList<>();
        for (JsonObject object : rows) {
            if (!entregables.contains(object.getString("nombre_entregable"))) {
                entregables.add(object.getString("nombre_entregable"));
            }
        }
        return entregables;
    }
}

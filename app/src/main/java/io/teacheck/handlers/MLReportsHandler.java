package io.teacheck.handlers;

import io.teacheck.jdbc.JDBCMachineLearning;
import io.teacheck.service.DatabaseServiceML;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

public class MLReportsHandler {
    private DatabaseServiceML serviceML;

    public MLReportsHandler(JDBCClient client) {
        this.serviceML = new JDBCMachineLearning(client);
    }

    public void registerMLReportsHandlers(Router router) {
        router.get("/api/ml-reports/:semanaID").handler(this::getDataReportSemanal);
    }

    private void getDataReportSemanal(RoutingContext ctx) {
        int semanaID = Integer.parseInt(ctx.request().getParam("semanaID"));
        serviceML.getDataReportSemanal(semanaID)
                .subscribe(report -> ctx.response()
                                .putHeader("Content-Type", "application/json")
                                .end(report.encodePrettily()),
                        throwable -> ctx.fail(throwable.getCause()));
    }
}

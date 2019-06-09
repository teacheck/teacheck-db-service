package io.teacheck.handlers;

import io.teacheck.jdbc.JDBCMachineLearning;
import io.teacheck.service.DatabaseServiceML;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.codec.BodyCodec;
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
        router.post("/api/scaneo-semanal").handler(this::postWeeklyScanner);
    }

    private void postWeeklyScanner(RoutingContext ctx) {
        Vertx vertx = Vertx.currentContext().owner();
        WebClientOptions webClientOptions = new WebClientOptions()
                .setDefaultPort(8080)
                .setDefaultHost("ml-service-middleware");
        WebClient client = WebClient.create(vertx, webClientOptions);
        client.post("/predict")
                .as(BodyCodec.jsonObject())
                .sendJsonObject(new JsonObject().put("data", ctx.getBodyAsJsonArray()), httpResponseAsyncResult -> {
                    if (httpResponseAsyncResult.succeeded()) {
                        ctx.response().putHeader("Content-Type", "application/json")
                                .end(httpResponseAsyncResult.result().bodyAsJsonObject().encodePrettily());
                    } else {
                        ctx.response().setStatusCode(500).end("Something is wrong");
                    }
                });
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

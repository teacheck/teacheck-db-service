package io.teacheck.service;

import io.vertx.core.json.JsonArray;
import rx.Single;

public interface DatabaseServiceML {

    Single<JsonArray> getDataReportSemanal(int semanaID);
}

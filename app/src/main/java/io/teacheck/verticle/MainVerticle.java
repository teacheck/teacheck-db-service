package io.teacheck.verticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.rxjava.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        DeploymentOptions options = new DeploymentOptions().setInstances(20);
        vertx.deployVerticle(new DBMigrationVerticle());
        vertx.deployVerticle(HttpVerticle.class.getName(), options);

    }
}
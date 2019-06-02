package io.teacheck.verticle;

import io.teacheck.constants.Constants;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import org.flywaydb.core.Flyway;

public class DBMigrationVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBMigrationVerticle.class);

    @Override
    public void start(Future<Void> startFuture) {
        vertx.executeBlocking(future -> {
            Flyway flyway = Flyway.configure()
                    .dataSource(Constants.JDBC_DATABASE_URL, Constants.POSTGRES_USER, Constants.POSTGRES_PASSWORD)
                    .load();
            flyway.migrate();
            future.complete();
        }, ar -> {
            if (ar.succeeded()) {
                LOGGER.info("Migrations applied successfully.");
                startFuture.complete();
            } else {
                LOGGER.error("Could not apply migrations.");
                startFuture.fail(ar.cause());
            }
        });
    }
}

package io.teacheck.verticle;


import io.teacheck.constants.Constants;
import io.teacheck.handlers.AlumnoHandler;
import io.teacheck.handlers.AsignaturaHandler;
import io.teacheck.handlers.ProfesorHandler;
import io.teacheck.jdbc.JDBCClientPSQL;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import io.vertx.rxjava.ext.web.handler.CookieHandler;
import io.vertx.rxjava.ext.web.handler.CorsHandler;
import rx.Completable;

public class HttpVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(HttpVerticle.class);
    private Router router;
    private JDBCClientPSQL clientPSQL;

    @Override
    public Completable rxStart() {
        getJDBCClientPSQL();
        buildRoutes();
        enableCORS();
        return vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(Constants.HTTP_SERVER_PORT)
                .toCompletable();

    }

    private void buildRoutes() {
        router = Router.router(vertx);
        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());

        router.route("/").handler(res -> res.response().putHeader("Content-Type", "text/plain")
                .end("Teacheck database microservice API version " + Constants.API_VERSION));

        (new AlumnoHandler(clientPSQL.getClient())).registerAlumnoHandlers(router);
        (new ProfesorHandler(clientPSQL.getClient())).registerProfesorHandlers(router);
        (new AsignaturaHandler(clientPSQL.getClient())).registerAsignaturaHandlers(router);
    }

    private void getJDBCClientPSQL() {
        clientPSQL = new JDBCClientPSQL(vertx);
    }

    private void enableCORS() {
        router.route().handler(CorsHandler.create("http://" + Constants.HTTP_SERVER_HOST + ":" + Constants.HTTP_SERVER_PORT)
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
                .allowCredentials(true)
                .allowedHeader("Access-Control-Allow-Headers")
                .allowedHeader("Authorization")
                .allowedHeader("Access-Control-Allow-Method")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Content-Type"));
    }

}

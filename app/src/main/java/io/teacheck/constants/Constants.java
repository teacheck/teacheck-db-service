package io.teacheck.constants;

public final class Constants {

  public static final String JDBC_DATABASE_URL = System.getenv("JDBC_DATABASE_URL");
  public static final String POSTGRES_USER = System.getenv("POSTGRES_USER");
  public static final String POSTGRES_PASSWORD = System.getenv("POSTGRES_PASSWORD");
  public static final int HTTP_SERVER_PORT = Integer.parseInt(System.getenv("HTTP_SERVER_PORT"));
  public static final String HTTP_SERVER_HOST = System.getenv("HTTP_SERVER_HOST");
  public static final String API_VERSION = System.getenv("API_VERSION");
}

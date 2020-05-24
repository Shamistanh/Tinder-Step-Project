package app;

public class HerokuEnv {

  public static int port() {
    try {
      return Integer.parseInt(System.getenv("PORT"));
    } catch (NumberFormatException ex) {
      return 5000;
    }
  }
  public static String jdbc_url() {
    String url = System.getenv("postgres://nyataccyehwxrh:d7d0b02ba6051fee490b31b679376390f5121a6fbed7ed67d7ba50c34ed3cece@ec2-34-200-72-77.compute-1.amazonaws.com:5432/d6t2ihomrv0cr3");
    if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_URL is empty!!!");
    return url;
  }

  public static String jdbc_username() {
    String url = System.getenv("nyataccyehwxrh");
    if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_USERNAME is empty!!!");
    return url;
  }

  public static String jdbc_password() {
    String url = System.getenv("d7d0b02ba6051fee490b31b679376390f5121a6fbed7ed67d7ba50c34ed3cece");
    if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_PASSWORD is empty!!!");
    return url;
  }

}

package app;


import db.ConnDetails;
import db.DbConn;
import db.DbSetup;
import filters.LoginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.*;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.EnumSet;

public class ServerApp {
    public static void main(String[] args) throws Exception {

        // temporary
   // DbSetup.migrate(ConnDetails.url, ConnDetails.username, ConnDetails.password);
      //  DbSetup.migrate(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());
        // temporary
   // Connection conn = DbConn.create(ConnDetails.url, ConnDetails.username, ConnDetails.password);
       // Connection conn = DbConn.createFromURL(HerokuEnv.jdbc_url());
        Class.forName(ConnDetails.dbDriver);
        Connection conn = DriverManager.getConnection(ConnDetails.url,
                ConnDetails.username,
                ConnDetails.password);



        Server server = new Server(HerokuEnv.port());
        TemplateEngine engine = TemplateEngine.folder("content/templates");
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new LikeServlet(engine,conn)), "/liked");
        handler.addServlet(new ServletHolder(new UserServlet(engine,conn)), "/user-list");
        handler.addServlet(new ServletHolder(new RegisterServlet(conn)), "/*");
        handler.addServlet(new ServletHolder(new MessageServlet(engine,conn)), "/messages");
        handler.addServlet(new ServletHolder(new RegisterServlet(conn)), "/register/*");
        handler.addServlet(new ServletHolder(new LoginServlet(conn)), "/login");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        handler.addServlet(new ServletHolder(new StaticServlet("images")), "/images");
        handler.addServlet(new ServletHolder(new StaticServlet("js")), "/js/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");

        // primary security
        handler.addFilter(LoginFilter.class,"/liked", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(LoginFilter.class,"/user-list", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(LoginFilter.class,"/messages", EnumSet.of(DispatcherType.REQUEST));


        server.setHandler(handler);
        conn.close();
        server.start();
        server.join();

    }
}

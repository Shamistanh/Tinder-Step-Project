package app;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9000);
        TemplateEngine engine = TemplateEngine.folder("content/templates");
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new LikeServlet(engine)), "/users");
        handler.addServlet(new ServletHolder(new CookieGet()), "/cg/*");
        handler.addServlet(new ServletHolder(new CookieRemove()), "/cr/*");
        handler.addServlet(new ServletHolder(new UserServlet(engine)), "/user-list");
        //handler.addServlet(new ServletHolder(new RedirectServlet("/register")), "/");
        handler.addServlet(new ServletHolder(new MessageServlet(engine)), "/messages");
        handler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        handler.addServlet(new ServletHolder(new StaticServlet("images")), "/images");
        handler.addServlet(new ServletHolder(new StaticServlet("js")), "/js/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");
//
//        handler.addFilter(LoginFilter.class, "/messages", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(handler);

        server.start();
        server.join();
    }
}

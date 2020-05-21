package web;

import beans.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MessageServlet  extends HttpServlet {


    private TemplateEngine engine;


    public MessageServlet(TemplateEngine engine) {
        this.engine = engine;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        List<Message> messages = Arrays.asList(
                new Message(1,2,3,req.getParameter("msj"), "2019")

        );
        data.put("messages", messages);

        engine.render("chat.ftl", data,resp);
    }
}

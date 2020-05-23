package web;

import beans.Message;
import service.MessageHandling;
import service.MessageService;
import service.MyID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MessageServlet  extends HttpServlet {
    MyID myID = new MyID();

    String sender_id= "sinama";
    private TemplateEngine engine;

    public MessageServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    String msg_text="deyer";
    List<String> messages = new ArrayList<>();
    MessageHandling isent  = new MessageHandling();
    MessageService messageService  = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        Cookie[] cookies = req.getCookies();
        String cookiess = Arrays.stream(cookies)
                .map(c -> String.format("%s-%s", c.getName(), c.getValue()))
                .collect(Collectors.joining());

        sender_id = cookiess.split("-")[1];
        List<Message> all_sent  = isent.allMessages(myID.id(), sender_id);
        List<Message> all_reveived  = isent.allMessages(sender_id,myID.id());
        data.put("sents", all_sent);
        data.put("receivings", all_reveived);
        engine.render("chat.ftl", data,resp);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        msg_text = req.getParameter("msj");
        messageService.insertToDB(myID.id(), sender_id, msg_text,java.time.LocalDate.now());
        messages.add(msg_text);
        System.out.println(msg_text);
        resp.sendRedirect("/messages");
    }
}
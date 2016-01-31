package com.netcracker.edu.airagency;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Жасулан on 21.01.2016.
 */
public class Listener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session id: "+se.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        Socket socket = (Socket) se.getSession().getServletContext().getAttribute("socket");
        System.out.println("context: "+se.getSession().getServletContext());
        System.out.println(se.getSession().getId());
        try {
            socket.close();
            System.out.println("socket closed");
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
    }
}

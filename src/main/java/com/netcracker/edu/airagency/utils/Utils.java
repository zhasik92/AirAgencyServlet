package com.netcracker.edu.airagency.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

/**
 * Created by Жасулан on 23.01.2016.
 */
public class Utils {
    public static String[] parseServerResponse(String resp) {
        System.out.println(resp);
        return resp.split(Pattern.quote("#?"));
    }

    public static void doRoutine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=utf-8");
       session.setMaxInactiveInterval(70);
        Socket socket = (Socket) session.getServletContext().getAttribute("socket");
        if (socket == null || socket.isClosed()) {
            socket = new Socket("localhost", 4444);
            session.getServletContext().setAttribute("socketInput", new BufferedReader(new InputStreamReader(socket.getInputStream())));
            session.getServletContext().setAttribute("socketOut", new PrintWriter(socket.getOutputStream(), true));
            session.getServletContext().setAttribute("socket", socket);
        }
    }
}

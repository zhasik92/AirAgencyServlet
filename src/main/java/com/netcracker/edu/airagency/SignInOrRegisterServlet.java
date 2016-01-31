package com.netcracker.edu.airagency;

import com.netcracker.edu.airagency.utils.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * Created by Жасулан on 23.01.2016.
 */
public class SignInOrRegisterServlet extends HttpServlet {

    /* @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         registerOrSignIn(req, resp);

     }
 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registerOrSignIn(req, resp);
    }

    protected static void registerOrSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.doRoutine(req, resp);
        ServletContext sessionServletContext=req.getSession().getServletContext();
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String command=req.getParameter("command");
        BufferedReader in = (BufferedReader) sessionServletContext.getAttribute("socketInput");
        ((PrintWriter) sessionServletContext.getAttribute("socketOut")).println(command + " " + login + " " + pass);
        String[] serverResp = Utils.parseServerResponse(in.readLine());
        if (serverResp[1].equals("0")) {
            resp.setStatus(HttpServletResponse.SC_OK);
            if ("sign_in".equals(command)) {
                sessionServletContext.setAttribute("status", "authorized");
                sessionServletContext.setAttribute("login", login);
                System.out.println("redirecting to jquery");
                resp.sendRedirect("jquery.html");
            }
            if ("register".equals(command)) {
                resp.sendRedirect("index.html");
            }
            return;
        }
        if (serverResp[1].equals("1")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("login password fail");
            resp.sendRedirect("index.html");
        }
        resp.getWriter().println(serverResp[1]);
    }
}

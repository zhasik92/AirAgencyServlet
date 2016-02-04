package com.netcracker.edu.airagency;

import com.netcracker.edu.airagency.utils.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Жасулан on 31.01.2016.
 */
public class BuyTicketStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.doRoutine(req,resp);
        ServletContext sessionServletContext=req.getSession().getServletContext();
        PrintWriter out=(PrintWriter) sessionServletContext.getAttribute("socketOut");
        BufferedReader in=(BufferedReader) sessionServletContext.getAttribute("socketInput");
        resp.getWriter().println("added passenger");
    }
}

package com.netcracker.edu.airagency;

import com.netcracker.edu.airagency.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Жасулан on 30.01.2016.
 */
public class BuyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.doRoutine(req,resp);
        while(req.getParameterNames().hasMoreElements()){
            System.out.println(req.getParameterNames().nextElement());
        }
        resp.getWriter().println("buycommand");
    }
}
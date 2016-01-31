package com.netcracker.edu.airagency.sandbox;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netcracker.edu.airagency.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Жасулан on 29.01.2016.
 */
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        doPost(req, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Utils.doRoutine(req, response);
        String command=req.getParameter("command");
       // System.out.println("depCity= " + depCity + "; arrcity= " + arrCity);
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        BufferedReader in = (BufferedReader) req.getServletContext().getAttribute("socketInput");
        PrintWriter out = (PrintWriter) req.getSession().getServletContext().getAttribute("socketOut");
        out.println(command);
        System.out.println(command);
        String[] fromServ = Utils.parseServerResponse(in.readLine());
        JsonObject myObj = new JsonObject();
        if (fromServ[1] == null) {
            myObj.addProperty("success", false);
        } else {
            myObj.addProperty("success", true);
        }
        JsonParser parser = new JsonParser();
        myObj.add("flight", parser.parse(fromServ[0]));
        System.out.println(myObj.toString());
        response.getWriter().println(myObj.toString());
    }
}

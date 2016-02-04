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
        JsonParser parser = new JsonParser();
        // JsonObject reqParsed=parser.parse()
        // System.out.println("depCity= " + depCity + "; arrcity= " + arrCity);
        /*response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");*/
        BufferedReader in = (BufferedReader) req.getServletContext().getAttribute(Utils.SOCKET_INPUT);
        PrintWriter out = (PrintWriter) req.getSession().getServletContext().getAttribute(Utils.SOCKET_OUTPUT);
        if ("find_routes".equals(req.getParameter("command"))) {
            out.println(req.getParameter("command") + " " + req.getParameter("depCity") + " " + req.getParameter("arrCity"));
        }
        if("find_short_route".equals(req.getParameter("command"))){
            out.println(req.getParameter("command") + " " + req.getParameter("depCity") + " " + req.getParameter("arrCity")+" 00:00");
        }
        JsonObject serverParsed = parser.parse(in.readLine()).getAsJsonObject();
        JsonObject myObj = new JsonObject();
        if ("0".equals(serverParsed.get("commandStatus").toString())) {
            myObj.addProperty("success", true);
        } else {
            myObj.addProperty("success", false);
        }
        myObj.add("flight", serverParsed.get("value"));
        System.out.println(myObj.toString());
        response.getWriter().println(myObj.toString());
    }
}

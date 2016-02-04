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
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.doRoutine(req, resp);
        String depCity = req.getParameter("depCity");
        String arrCity = req.getParameter("arrCity");
        String flightDate = req.getParameter("date");
        resp.getWriter().println("<html>" +
                "<head>" +
              /*  "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js\" type=\"text/javascript\"></script>"+
                " <script type=\"text/javascript\" src=\"addpass.js\"></script>"+*/
                "<title>Airagency</title>" +
                "</head>" +
                "<body>" +
                "<h1>Welcome to Defaulter Transaero!</h1>" +
                "<p>Add passenger's information:</p>" +
                "<form id=\"passenger\" action=\"add_passenger\" method=\"post\">" +
                "<input type=\"hidden\" name=\"depCity\" value=" + depCity + ">" +
                "<input type=\"hidden\" name=\"arrCity\" value=" + arrCity + ">" +
                "<input type=\"hidden\" name=\"fightDate\" value=" + flightDate + ">" +
                "Passport:   <input type=\"text\" name=\"passport\" required/>" +
                "<p/>Citizenship: <input type=\"text\" name=\"citizenship\" required/>" +
                "<p/>First name:  <input type=\"text\" name=\"name\" required/>" +
                "<p/>Last name:  <input type=\"text\" name=\"last_name\" required/>" +
                "<p/>Date of birth:<input type=\"date\" name=\"date_of_birth\" required/>" +
                "<p/><input type=\"hidden\" name=\"email\" value=\"stub@mail.ru\"/>" +
                "<input id=\"myButton\" type=\"submit\" value=\"confirm\">" +
                "</form>" +
                /*"<div id=\"anotherSection\">" +
                "    <fieldset>" +
                "        <legend>Flights:</legend>" +
                "        <div id=\"ajaxResponse\"></div>" +
                "    </fieldset>" +*/
                "</div>" +
                "</body>" +
                "</html>");
    }
}
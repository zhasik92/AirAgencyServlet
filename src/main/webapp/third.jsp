<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*, java.net.*,java.io.*" %>

<html>
<head>
    <title>Простейшая страница JSP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

</body>
</html>

<%
    Socket socket = (Socket) session.getServletContext().getAttribute("socket");
    PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
    pw.println("find_routes " + request.getParameter("from") + " " + request.getParameter("to"));
%>

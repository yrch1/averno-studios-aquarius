<%-- 
    Document   : errorLogin
    Created on : 28-abr-2010, 10:01:21
    Author     : yrch
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error </h1>
        <form method="POST" action="j_security_check">
    User Name : <input type="text" name="j_username">
    Password  : <input type="password" name="j_password">
    <input type="submit">
</form>

    </body>
</html>

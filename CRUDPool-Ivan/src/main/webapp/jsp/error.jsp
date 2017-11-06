<%-- 
    Document   : error
    Created on : 05-nov-2017, 17:40:31
    Author     : Ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1><%=request.getAttribute("error")%></h1>
        <a href="<%=request.getContextPath()%>/index.html">Volver</a>
    </body>
</html>

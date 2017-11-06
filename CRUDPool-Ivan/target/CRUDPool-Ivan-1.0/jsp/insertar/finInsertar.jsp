<%-- 
    Document   : finInsertar
    Created on : 05-nov-2017, 18:46:45
    Author     : Ivan
--%>

<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Se ha insertado el registro</h1>
        <%
            HttpSession sesion=request.getSession();
            Ave ave=(Ave)sesion.getAttribute("ave");
        %>
        <p>Anilla: <%=ave.getAnilla()%></p>
        <p>Especie: <%=ave.getEspecie()%></p>
        <p>Lugar: <%=ave.getLugar()%></p>
        <p>Fecha: <%=ave.getFecha()%></p>
        <a href="<%=request.getContextPath()%>/index.html">Volver</a>
    </body>
</html>

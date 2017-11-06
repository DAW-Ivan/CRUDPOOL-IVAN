<%-- 
    Document   : leer.jsp
    Created on : 04-nov-2017, 16:28:35
    Author     : Ivan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de aves</title>
        <style type="text/css">
            div{float: left;margin-left: 10%;width: 20%;}
            h1{text-align: center;}
        </style>
    </head>
    <body>
        <h1>Listado de aves</h1>
        <%
            ArrayList<Ave> aves = (ArrayList) request.getAttribute("aves");
            for (Ave a : aves) {
        %>
        <div>
            <p>Especie: <%=a.getEspecie()%></p>
            <p>Lugar: <%=a.getLugar()%></p>
            <p>Fecha: <%=a.getFecha()%></p>
            <hr/>
        </div>
        <%
            }
        %>
        <a href="<%=request.getContextPath()%>/index.html">Volver</a>
    </body>
</html>

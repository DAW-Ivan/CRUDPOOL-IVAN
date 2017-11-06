<%-- 
    Document   : eliminar
    Created on : 06-nov-2017, 12:14:40
    Author     : Ivan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar</title>
    </head>
    <body>
        <h1>Se van a elimiar los siguientes registros</h1>
        <%
        ArrayList<Ave> aves=(ArrayList)request.getAttribute("aves");
        for(Ave a:aves){
            %>
            <p><%=a.toString()%></p>
            <%
        }
        %>
        <form action="concluir" method="post">
            <input type="submit" name="eliminar" value="Confirmar"/>
            <input type="submit" name="cancelar" value="Cancelar"/>
        </form>
    </body>
</html>

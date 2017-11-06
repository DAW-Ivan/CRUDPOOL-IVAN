<%-- 
    Document   : leerEliminar
    Created on : 04-nov-2017, 16:56:17
    Author     : Ivan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar registros</title>
    </head>
    <body>
        <h1>Escoge registro/s a eliminar</h1>
        <form action="realizar" method="post">
            <%
                ArrayList<Ave> aves = (ArrayList) request.getAttribute("aves");
                for (Ave a : aves) {
            %>
            <p><input type="checkbox" name="ave" value="<%=a.getAnilla()%>" id="<%=a.getEspecie()%>"/> <label for="<%=a.getEspecie()%>"><%=a.getEspecie()%></label></p>
                <%
                    }
                %>
            <input type="submit" name="eliminar" value="Enviar"/>
            <input type="submit" name="cancelar" value="Cancelar"/>
        </form>
    </body>
</html>

<%-- 
    Document   : leerInsertar
    Created on : 04-nov-2017, 17:02:20
    Author     : Ivan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar registro</title>
    </head>
    <body>
        <h1>Rellene el nuevo registro</h1>
        <%ArrayList<Ave> aves = (ArrayList) request.getAttribute("aves");%>
        <form action="realizar" method="post">
            <p><label>Anilla:</label> <input type="text" name="anilla" value="<%=aves.size() + 1%>" readonly/></p>
            <p><label>Especie:</label> <input type="text" name="especie"/></p>
            <p><label>Lugar:</label> <input type="text" name="lugar"/></p>
            <p><label>Fecha:</label> <input type="text" name="fecha" placeholder="aaaa-mm-dd"/></p>
            <input type="submit" name="insertar" value="Enviar"/>
            <input type="submit" name="cancelar" value="Cancelar"/>
        </form>    
    </body>
</html>

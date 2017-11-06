<%-- 
    Document   : finEliminar
    Created on : 06-nov-2017, 12:15:14
    Author     : Ivan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fin Eliminar</title>
    </head>
    <body>
        <%
        HttpSession sesion=request.getSession();
        ArrayList<Ave>aves=(ArrayList)sesion.getAttribute("aves");
        %>
        <p>Se han eliminado <%=aves.size()%> registro/s</p>
        <a href="<%=request.getContextPath()%>/index.html">Volver</a>
    </body>
</html>

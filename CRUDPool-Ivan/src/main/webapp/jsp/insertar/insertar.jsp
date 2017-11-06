<%-- 
    Document   : insertar
    Created on : 05-nov-2017, 0:35:33
    Author     : Ivan
--%>

<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar</title>
    </head>
    <body>
        <h1>Compruebe los datos a introducir</h1>
        <%
            HttpSession sesion=request.getSession();
           Ave ave=(Ave)sesion.getAttribute("ave");
        %>
        <p>Especie: <%=ave.getEspecie()%></p>
        <p>Lugar: <%=ave.getLugar()%></p>
        <p>Fecha: <%=ave.getFecha()%></p>
        <form action="concluir" method="post">
            <p>
                <input type="submit" name="insertar" value="Confirmar"/>
                <input type="submit" name="cancelar" value="Cancelar"/>
            </p>
        </form>
    </body>
</html>

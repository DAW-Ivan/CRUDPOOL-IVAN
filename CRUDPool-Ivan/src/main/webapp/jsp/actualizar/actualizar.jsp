<%-- 
    Document   : actualizar
    Created on : 05-nov-2017, 20:05:06
    Author     : Ivan
--%>

<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar</title>
    </head>
    <body>
        <%
            Ave ave=(Ave)request.getAttribute("ave");
        %>
        <h1>Actualiza los datos del registro</h1>
        <form action="concluir" method="post">
            <div>
                <p>Datos originales</p>
                <input type="text" value="<%=ave.getAnilla()%>" readonly/>
                <input type="text" value="<%=ave.getEspecie()%>" readonly/>
                <input type="text" value="<%=ave.getLugar()%>" readonly/>
                <input type="text" value="<%=ave.getFecha()%>" readonly/>
            </div>
            <div>
                <p>Nuevos valores</p>
                <input type="text" name="anilla" value="<%=ave.getAnilla()%>" readonly/>
                <input type="text" name="especie"/>
                <input type="text" name="lugar"/>
                <input type="text" name="fecha"/>
            </div>
            <p>
                <input type="submit" name="actualizar" value="Confirmar"/>
                <input type="submit" name="cancelar" value="Cancelar"/>
            </p>
        </form>
    </body>
</html>

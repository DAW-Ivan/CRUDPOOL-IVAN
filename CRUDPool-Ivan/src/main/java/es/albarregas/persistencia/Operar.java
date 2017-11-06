/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.persistencia;

import es.albarregas.beans.Ave;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "Operar", urlPatterns = {"/operar"})
public class Operar extends HttpServlet {


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataSource pool = null;
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        String opcion = request.getParameter("opcion");
        String url = null;

        ArrayList<Ave> aves = new ArrayList<Ave>();
        Ave ave = null;
        try {

            Context initialContext = new InitialContext();
            pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/pruebasjava");
            try {
                conexion = pool.getConnection();
                String sql = "select * from pajaros";
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(sql);

                while (resultado.next()) {
                    ave = new Ave();
                    ave.setAnilla(resultado.getString(1));
                    ave.setEspecie(resultado.getString(2));
                    ave.setLugar(resultado.getString(3));
                    ave.setFecha(resultado.getString(4));
                    aves.add(ave);
                }
                if (aves.isEmpty()) {
                    if(opcion.equals("in")){
                        url="jsp/insertar/leerInsertar.jsp";
                    }else{
                        url="jsp/error.jsp";
                        request.setAttribute("error", "La base de datos esta vacía");
                    }
                } else {
                    switch (opcion) {
                        case "in":
                            url = "jsp/insertar/leerInsertar.jsp";
                            break;
                        case "ac":
                            url = "jsp/actualizar/leerActualizar.jsp";
                            break;
                        case "el":
                            url = "jsp/eliminar/leerEliminar.jsp";
                            break;
                        case "mo":
                            url = "/jsp/leer/leer.jsp";
                            break;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Operar.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NamingException ex) {
            Logger.getLogger(Operar.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("aves", aves);
        request.getRequestDispatcher(url).forward(request, response);

    }

}

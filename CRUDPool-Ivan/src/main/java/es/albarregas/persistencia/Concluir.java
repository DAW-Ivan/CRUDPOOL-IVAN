/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.persistencia;

import es.albarregas.beans.Ave;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "Concluir", urlPatterns = {"/concluir"})
public class Concluir extends HttpServlet {

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

        Enumeration<String> nombres = request.getParameterNames();
        String nombre;
        String url = null;
        HttpSession sesion = request.getSession();
        Ave ave;
        ArrayList<Ave> aves=null;
        DataSource pool = null;
        Connection conexion = null;
        Statement sentencia = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        String sql = null;

        try {
            Context initialContext = new InitialContext();
            pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/pruebasjava");

            try {
                conexion = pool.getConnection();
                while (nombres.hasMoreElements()) {
                    nombre = nombres.nextElement();
                    switch (nombre) {                       
                        case "insertar":
                            ave = (Ave) sesion.getAttribute("ave");
                            sql = "INSERT INTO pajaros VALUES (?,?,?,?)";
                            preparada = conexion.prepareStatement(sql);
                            preparada.setString(1, ave.getAnilla());
                            preparada.setString(2, ave.getEspecie());
                            preparada.setString(3, ave.getLugar());
                            preparada.setString(4, ave.getFecha());
                            preparada.executeUpdate();
                            url = "jsp/insertar/finInsertar.jsp";
                            preparada.close();
                            conexion.close();
                            break;
                        case "actualizar":
                            ave=new Ave();
                            ave.setAnilla(request.getParameter("anilla"));
                            ave.setEspecie(request.getParameter("especie"));
                            ave.setLugar(request.getParameter("lugar"));
                            ave.setFecha(request.getParameter("fecha"));            
                            sql = "UPDATE pajaros set especie=?,lugar=?,fecha=? WHERE pajaros.anilla=?;";
                            preparada = conexion.prepareStatement(sql);
                            preparada.setString(1, ave.getEspecie());
                            preparada.setString(2, ave.getLugar());
                            preparada.setString(3, ave.getFecha());
                            preparada.setString(4, ave.getAnilla());
                            request.setAttribute("ave", ave);
                            preparada.executeUpdate();
                            url = "jsp/actualizar/finActualizar.jsp";
                            preparada.close();
                            conexion.close();
                            break;
                        case "eliminar":
                            sesion=request.getSession();
                            aves=(ArrayList)sesion.getAttribute("aves");
                            sql="delete from pajaros where anilla=?";                            
                            for(Ave a:aves){
                                preparada=conexion.prepareStatement(sql);
                                preparada.setString(1, a.getAnilla());
                                preparada.executeUpdate();
                                preparada.close();
                            }
                            conexion.close();
                            url="jsp/eliminar/finEliminar.jsp";
                            break;
                        case "cancelar":
                            url = "index.html";
                            break;
                    }
                }
                request.getRequestDispatcher(url).forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        }

    }

}

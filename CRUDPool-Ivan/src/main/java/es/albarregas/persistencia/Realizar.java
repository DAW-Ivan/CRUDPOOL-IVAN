/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.persistencia;

import es.albarregas.beans.Ave;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "Realizar", urlPatterns = {"/realizar"})
public class Realizar extends HttpServlet {
    
    private DataSource pool = null;
    private Connection conexion = null;
    private Statement sentencia = null;
    private PreparedStatement preparada = null;
    private ResultSet resultado = null;
    private String sql = null;
    private Ave ave = null;

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
        String nombre = null;
        String url = null;
        
        try {
            Context initialContext = new InitialContext();
            pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/pruebasjava");
            
            try {
                conexion = pool.getConnection();
                
                while (nombres.hasMoreElements()) {
                    nombre = nombres.nextElement();
                    switch (nombre) {
                        case "insertar":
                            insertar(request, response);
                            url = "jsp/insertar/insertar.jsp";
                            break;
                        case "actualizar":
                            try {
                                actualizar(request, response);
                                url = "jsp/actualizar/actualizar.jsp";
                            } catch (NullPointerException ex) {
                                ex.printStackTrace();
                                url = "index.html";
                            }
                            break;
                        case "eliminar":
                            eliminar(request, response);
                            url = "jsp/eliminar/eliminar.jsp";
                            break;
                        case "cancelar":
                            url = "index.html";
                            break;
                    }
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
    
    private void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ave = new Ave();
        Enumeration<String> nombres = request.getParameterNames();
        ave.setAnilla(request.getParameter(nombres.nextElement()));
        ave.setEspecie(request.getParameter(nombres.nextElement()));
        ave.setLugar(request.getParameter(nombres.nextElement()));
        ave.setFecha(request.getParameter(nombres.nextElement()));
        HttpSession sesion = request.getSession();
        sesion.setAttribute("ave", ave);
    }
    
    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ave = new Ave();
        String anilla = request.getParameter("anilla");
        sql = "select * from pajaros where anilla=?";
        preparada = conexion.prepareStatement(sql);
        preparada.setString(1, anilla);
        resultado = preparada.executeQuery();
        resultado.next();
        ave.setAnilla(resultado.getString(1));
        ave.setEspecie(resultado.getString(2));
        ave.setLugar(resultado.getString(3));
        ave.setFecha(resultado.getString(4));
        request.setAttribute("ave", ave);
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ArrayList<Ave> aves = new ArrayList<Ave>();
        String[] datos = request.getParameterValues("ave");
        sql = "select * from pajaros where anilla=?";
        HttpSession sesion=request.getSession();
        for (int d = 0; d < datos.length; d++) {
            ave=new Ave();
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1, datos[d]);
            resultado = preparada.executeQuery();
            while (resultado.next()) {                
                ave.setAnilla(resultado.getString(1));
                ave.setEspecie(resultado.getString(2));
                ave.setLugar(resultado.getString(3));
                ave.setFecha(resultado.getString(4));
                aves.add(ave);
            }
            resultado.close();
            preparada.close();
        }
        conexion.close();
        request.setAttribute("aves", aves);
        sesion.setAttribute("aves", aves);
    }
    
}

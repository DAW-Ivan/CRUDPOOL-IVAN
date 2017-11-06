/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.persistencia;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Ivan
 */
@WebFilter(filterName = "filtro", urlPatterns = {"/*"})
public class Filtro implements Filter {
    
    private String encoding = "UTF-8";
    
    public void init(FilterConfig fConfig) throws ServletException {
        
        if(fConfig.getInitParameter("encoding")!=null){
            encoding=fConfig.getInitParameter("encoding");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        request.setCharacterEncoding(encoding);
	chain.doFilter(request, response);
                
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

}

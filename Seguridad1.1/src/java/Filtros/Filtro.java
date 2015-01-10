/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import login.dominio.Login;
import login.dominio.UsuarioSesion;
//import org.primefaces.component.menu.*;
//import org.primefaces.component.menuitem.UIMenuItem;
//import org.primefaces.component.menubar.*;
//import org.primefaces.model.menu.*;

/**
 *
 * @author Susana
 */
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String destino = httpRequest.getRequestURL().toString();
        String pathInfo = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (pathInfo.startsWith("/faces/includes") || pathInfo.startsWith("/faces/javax.faces.resource")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession httpSession = httpRequest.getSession(true);
        UsuarioSesion usuarioSesion = (UsuarioSesion) httpSession.getAttribute("usuarioSesion");

        if (usuarioSesion == null) {
            usuarioSesion = new UsuarioSesion();
            usuarioSesion.setJndi("jdbc/__webSystem");
            httpSession.setAttribute("usuarioSesion", usuarioSesion);
        }
        if (destino.endsWith("/")) {
            chain.doFilter(request, response);
        } else if (usuarioSesion.getPassword() == null && !destino.contains("login.xhtml")) {
            httpResponse.sendRedirect("/Seguridad1.1/faces/login.xhtml");
        } else {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}

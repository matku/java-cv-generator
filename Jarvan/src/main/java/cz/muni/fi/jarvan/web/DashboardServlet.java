/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.jarvan.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author martin
 */
@WebServlet(DashboardServlet.URL_MAPPING + "/*")
public class DashboardServlet extends HttpServlet
{
    private static final String DASHBOARD_JSP = "/WEB-INF/view/dashboard.jsp";
    public static final String URL_MAPPING = "/dashboard";
    
    private final static Logger log = LoggerFactory.getLogger(DashboardServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        
        req.getRequestDispatcher(DASHBOARD_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    
}

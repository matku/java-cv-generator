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
@WebServlet(AboutServlet.URL_MAPPING + "/*")
public class AboutServlet extends HttpServlet
{
    private static final String ABOUT_JSP = "/WEB-INF/view/about.jsp";
    public static final String URL_MAPPING = "/about";
    
    private final static Logger log = LoggerFactory.getLogger(AboutServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ABOUT_JSP).forward(req, resp);
    }
    
    
}

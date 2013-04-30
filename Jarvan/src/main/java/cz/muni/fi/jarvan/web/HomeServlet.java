package cz.muni.fi.jarvan.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(HomeServlet.URL_MAPPING + "/*")
public class HomeServlet extends HttpServlet
{
    
    private static final String HOME_JSP = "/WEB-INF/view/home.jsp";
    public static final String URL_MAPPING = "/home";

    private final static Logger log = LoggerFactory.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HOME_JSP).forward(req, resp);        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //req.getRequestURL();
        resp.sendRedirect(req.getContextPath() + URL_MAPPING);
    }
    
    
}
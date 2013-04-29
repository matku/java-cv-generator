package cz.muni.fi.jarvan.web.jarvan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = {"/en" + HomeServlet.URL_MAPPING + "/*", 
                            "/sk" + HomeServlet.URL_MAPPING + "/*", 
                            "/cz" + HomeServlet.URL_MAPPING + "/*", 
                            HomeServlet.URL_MAPPING + "/*"})
public class HomeServlet extends HttpServlet
{
    Language lang = new Language();
    
    private static final String HOME_JSP = "/WEB-INF/view/home.jsp";
    public static final String URL_MAPPING = "/home";

    private final static Logger log = LoggerFactory.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer URL = req.getRequestURL();
        try
        {
            String language = lang.getLanguage(URL);
            log.info("Lang = " + language);
            req.setAttribute("webPath", lang.getBaseUrl());
            req.getRequestDispatcher(HOME_JSP).forward(req, resp);
        } catch (LanguageException e)
        {
            log.warn("Language not supported!!!");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //req.getRequestURL();
    }
    
    
}
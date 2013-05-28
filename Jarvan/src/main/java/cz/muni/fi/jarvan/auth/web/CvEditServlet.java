package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.Library;
import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.XMLParser;
import org.slf4j.Logger;
import cz.muni.fi.jarvan.web.HomeServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(CvEditServlet.URL_MAPPING + "/*")
public class CvEditServlet extends HttpServlet
{
    private static final String CVEDIT_JSP = "/WEB-INF/view/cvEdit.jsp";
    public static final String URL_MAPPING = "/auth/cv/edit";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return;
        }
        XMLParser parser = new XMLParser(Settings.getPathLibrary());
        
        req.setAttribute("cvs", parser.getCvs(req.getSession().getAttribute("isLogged").toString()));
        req.getRequestDispatcher(CVEDIT_JSP).forward(req, resp);
    }
}

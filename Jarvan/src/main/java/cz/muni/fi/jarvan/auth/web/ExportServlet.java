
package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.XMLParser;
import cz.muni.fi.jarvan.auth.XMLWriter;
import org.slf4j.Logger;
import cz.muni.fi.jarvan.web.HomeServlet;
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
@WebServlet(ExportServlet.URL_MAPPING + "/*")
public class ExportServlet extends HttpServlet
{
    private static final String EXPORT_JSP = "/WEB-INF/view/cvExport.jsp";
    public static final String URL_MAPPING = "/auth/export";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        XMLParser parser = new XMLParser(Settings.getPathLibrary());
        req.setAttribute("cvs", parser.getCvs(req.getSession().getAttribute("isLogged").toString()));
        req.getRequestDispatcher(EXPORT_JSP).forward(req, resp);
    }
}

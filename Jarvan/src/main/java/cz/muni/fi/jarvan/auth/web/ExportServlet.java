
package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.XMLParser;
import cz.muni.fi.jarvan.auth.XMLWriter;
import cz.muni.fi.jarvan.auth.cv.CvManager;
import cz.muni.fi.jarvan.auth.cv.XmlException;
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
    
    private final static Logger log = LoggerFactory.getLogger(ExportServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        XMLParser parser = new XMLParser(Settings.getPathLibrary());
        req.setAttribute("cvs", parser.getCvs(req.getSession().getAttribute("isLogged").toString()));
        
        //system ls
        XMLParser email = new XMLParser(Settings.getPathUser());
        String mail = email.getEmail(req.getSession().getAttribute("isLogged").toString());
        
        req.setAttribute("list", email.getList(mail));
        
        req.getRequestDispatcher(EXPORT_JSP).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        XMLParser cvsParser = new XMLParser(Settings.getPathLibrary());
        req.setAttribute("cvs", cvsParser.getCvs(req.getSession().getAttribute("isLogged").toString()));
        
        req.setCharacterEncoding("utf-8");
        String cvName = req.getParameter("cvName");
        String lang = req.getParameter("lang");
        
        
        XMLParser email = new XMLParser(Settings.getPathUser());
        String mail = email.getEmail(req.getSession().getAttribute("isLogged").toString());
        String xmlPath = Settings.getPathCV() + cvName + "_" + mail + ".xml";
        
        // change language
        XMLWriter writer = new XMLWriter(xmlPath);
        writer.changeCvLanguage(lang);
        
        CvManager create = new CvManager();
        
        try
        {
            create.generate(xmlPath);
        }
        catch (XmlException e)
        {
            log.error("Generate error " + e.getMessage());
        }
        
        //system ls
        XMLParser cvList = new XMLParser(Settings.getPathCV());
        req.setAttribute("list", cvList.getList(mail));
        
        req.setAttribute("success", "Congratulation ! You have successfully exported your CV :) ");
        req.getRequestDispatcher(EXPORT_JSP).forward(req, resp);
    }
}

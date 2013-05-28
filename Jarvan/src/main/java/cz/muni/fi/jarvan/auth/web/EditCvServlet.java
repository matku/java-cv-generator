package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.Cv;
import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.XMLParser;
import org.slf4j.Logger;
import cz.muni.fi.jarvan.web.HomeServlet;
import java.io.IOException;
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
@WebServlet(EditCvServlet.URL_MAPPING + "/*")
public class EditCvServlet extends HttpServlet
{
    private static final String EDITCV_JSP = "/WEB-INF/view/editCv.jsp";
    public static final String URL_MAPPING = "/auth/cv/editCv";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return;
        }
        
        XMLParser parser = new XMLParser(Settings.getPathCV() + req.getPathInfo().substring(1) + ".xml");
        Cv cv = parser.getCv();
        String fileName = req.getPathInfo().substring(1);
        List<String> names = new XMLParser(Settings.getPathLibrary()).getCvs(req.getSession().getAttribute("isLogged").toString());
        
        for (String name : names)
        {
            if (fileName.equals(name + "_" + new XMLParser(Settings.getPathUser()).getEmail(req.getSession().getAttribute("isLogged").toString())))
            {
                cv.setName(name);
            }
        }
        
        req.setAttribute("name", cv.getName());
        req.setAttribute("firstName", cv.getFirstName());
        req.setAttribute("surname", cv.getLastName());
        req.setAttribute("titleBefore", cv.getTitle());
        req.setAttribute("titleAfter", cv.getTitleAfter());
        String[] birthday = cv.getDateOfBirth().split("\\.");
        req.setAttribute("birthdayDay", birthday[0]);
        req.setAttribute("birthdayMonth", birthday[1]);
        req.setAttribute("birthdayYear", birthday[2]);
        req.setAttribute("address", cv.getStreet());
        req.setAttribute("psc", cv.getZip());
        req.setAttribute("town", cv.getCity());
        req.setAttribute("homePhone", cv.getHomeNumber());
        req.setAttribute("mobilePhone", cv.getMobileNumber());
        
        
        req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}

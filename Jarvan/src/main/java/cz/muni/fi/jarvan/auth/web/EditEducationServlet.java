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
@WebServlet(EditEducationServlet.URL_MAPPING + "/*")
public class EditEducationServlet extends HttpServlet
{
    private static final String EDITEDU_JSP = "/WEB-INF/view/editEducation.jsp";
    public static final String URL_MAPPING = "/auth/cv/editEducation";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        String fileName = req.getPathInfo().substring(1);
        req.setAttribute("cvName", fileName);
        XMLParser parser = new XMLParser(Settings.getPathCV() + fileName + ".xml");
        req.setAttribute("schools", parser.getSchools());
        req.getRequestDispatcher(EDITEDU_JSP).forward(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        req.setCharacterEncoding("utf-8");
        String action = req.getPathInfo().substring(req.getPathInfo().length()-5);
        
        switch(action)
        {
            case "/edit":
                String schoolEnd = req.getParameter("schoolEnd");
                String schoolFieldOfStudy = req.getParameter("schoolFieldOfStudy");
                
                String school = req.getParameter("school");
                String name = req.getPathInfo().substring(1, req.getPathInfo().length()-5);
                XMLWriter writer = new XMLWriter(Settings.getPathCV() + name + ".xml");
                if (!writer.editSchool(school, schoolFieldOfStudy, schoolEnd))
                {
                    req.setAttribute("otherError", "Error while creating work, please contact our administrators");
                    req.getRequestDispatcher(EDITEDU_JSP).forward(req, resp);
                    return;
                }
                
                
                
                
                req.setAttribute("success", "Congratulation ! You have successfully edited your work :) ");
                break;
        }
        
        
        String fileName = req.getPathInfo().substring(1, req.getPathInfo().length()-5);
        req.setAttribute("cvName", fileName);
        XMLParser parser = new XMLParser(Settings.getPathCV() + fileName + ".xml");
        req.setAttribute("schools", parser.getSchools());
        req.getRequestDispatcher(EDITEDU_JSP).forward(req, resp);
    }
}

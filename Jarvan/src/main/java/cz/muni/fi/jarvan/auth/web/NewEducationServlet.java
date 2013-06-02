package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.CvException;
import cz.muni.fi.jarvan.auth.Education;
import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.XMLParser;
import cz.muni.fi.jarvan.auth.XMLWriter;
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
@WebServlet(NewEducationServlet.URL_MAPPING + "/*")
public class NewEducationServlet extends HttpServlet
{
    private static final String NEWEDU_JSP = "/WEB-INF/view/newEducation.jsp";
    public static final String URL_MAPPING = "/auth/cv/newEducation";
    
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
        List<String> names = new XMLParser(Settings.getPathLibrary()).getCvs(req.getSession().getAttribute("isLogged").toString());
        
        for (String name : names)
        {
            if (fileName.equals(name + "_" + new XMLParser(Settings.getPathUser()).getEmail(req.getSession().getAttribute("isLogged").toString())))
            {
                req.setAttribute("name", name);
            }
        }
        req.getRequestDispatcher(NEWEDU_JSP).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        req.setCharacterEncoding("utf-8");
        String action = req.getPathInfo().substring(req.getPathInfo().length()-4);
        switch(action)
        {
            case "/add":
                //school
                String schoolType = req.getParameter("type");
                String schoolStart = req.getParameter("schoolStart");
                String schoolEnd = req.getParameter("schoolEnd");
                String schoolCity = req.getParameter("schoolCity");
                String schoolName = req.getParameter("schoolName");
                String schoolFieldOfStudy = req.getParameter("schoolFieldOfStudy");
                if (schoolType.equals("university"))
                {
                    if ("".equals(schoolFieldOfStudy))
                    {
                        String fileName = req.getPathInfo().substring(1, req.getPathInfo().length()-4);
                        req.setAttribute("cvName", fileName);
                        log.error("Did not fill field of study in university.");
                        req.setAttribute("schoolError", "You must fill in all required fields");
                        req.getRequestDispatcher(NEWEDU_JSP).forward(req, resp);
                        return;
                    }
                }
                try
                {
                    Education edu = new Education();
                    edu.setFrom(schoolStart);
                    if (!schoolEnd.equals(""))
                    {
                        edu.setTo(schoolEnd);
                    }
                    edu.setCity(schoolCity);
                    if (!"".equals(schoolFieldOfStudy))
                    {
                        edu.setFieldOfStudy(schoolFieldOfStudy);
                    }
                    edu.setName(schoolName);

                    String name = req.getPathInfo().substring(1, req.getPathInfo().length()-4);
                    XMLWriter writer = new XMLWriter(Settings.getPathCV() + name + ".xml");
                    if (!writer.addEducation(edu, schoolType))
                    {
                        req.setAttribute("otherError", "Error while creating education, please contact our administrators");
                        req.getRequestDispatcher(NEWEDU_JSP).forward(req, resp);
                        return;
                    }
                    
                } catch (CvException e)
                {
                    log.error("could not create CV", e);
                    resp.sendRedirect("/JarvanUpdate/404");
                    return;
                }
                req.setAttribute("success", "Congratulation ! You have successfully added your education :) ");
                break;
        }
        
        req.getRequestDispatcher(NEWEDU_JSP).forward(req, resp);
    }
}

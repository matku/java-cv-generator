package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.CvException;
import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.Work;
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
@WebServlet(NewWorkServlet.URL_MAPPING + "/*")
public class NewWorkServlet extends HttpServlet
{
    private static final String NEWWORK_JSP = "/WEB-INF/view/newWork.jsp";
    public static final String URL_MAPPING = "/auth/cv/newWork";
    
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
        req.getRequestDispatcher(NEWWORK_JSP).forward(req, resp);
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
                //work
                String workStart = req.getParameter("workStart");
                String workEnd = req.getParameter("workEnd");
                String workEmployer = req.getParameter("workEmployer");
                String workJob = req.getParameter("workJob");
                
                try
                {
                    Work work = new Work();
                    work.setFrom(workStart);
                    if (!workEnd.equals(""))
                    {
                        work.setTo(workEnd);
                    }
                    work.setEmployer(workEmployer);
                    work.setPosition(workJob);

                    String name = req.getPathInfo().substring(1, req.getPathInfo().length()-4);
                    XMLWriter writer = new XMLWriter(Settings.getPathCV() + name + ".xml");
                    if (!writer.addWork(work))
                    {
                        req.setAttribute("otherError", "Error while creating work, please contact our administrators");
                        req.getRequestDispatcher(NEWWORK_JSP).forward(req, resp);
                        return;
                    }
                    
                } catch (CvException e)
                {
                    log.error("could not create CV", e);
                    resp.sendRedirect("/JarvanUpdate/404");
                    return;
                }
                break;
        }
        req.setAttribute("success", "Congratulation ! You have successfully added your work :) ");
        req.getRequestDispatcher(NEWWORK_JSP).forward(req, resp);
    }
}

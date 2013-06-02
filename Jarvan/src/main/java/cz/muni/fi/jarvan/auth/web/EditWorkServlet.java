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
@WebServlet(EditWorkServlet.URL_MAPPING + "/*")
public class EditWorkServlet extends HttpServlet
{
    private static final String EDITWORK_JSP = "/WEB-INF/view/editWork.jsp";
    public static final String URL_MAPPING = "/auth/cv/editWork";
    
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
        req.setAttribute("works", parser.getWorks());
        req.getRequestDispatcher(EDITWORK_JSP).forward(req, resp);
    }
    
    @Override
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
                String workEnd = req.getParameter("workEnd");
                String workJob = req.getParameter("workJob");
                
                String work = req.getParameter("work");
                String name = req.getPathInfo().substring(1, req.getPathInfo().length()-5);
                XMLWriter writer = new XMLWriter(Settings.getPathCV() + name + ".xml");
                if (!writer.editWork(work, workJob, workEnd))
                {
                    req.setAttribute("otherError", "Error while creating work, please contact our administrators");
                    req.getRequestDispatcher(EDITWORK_JSP).forward(req, resp);
                    return;
                }
                
                
                
                
                req.setAttribute("success", "Congratulation ! You have successfully edited your work :) ");
                break;
        }
        
        
        String fileName = req.getPathInfo().substring(1, req.getPathInfo().length()-5);
        req.setAttribute("cvName", fileName);
        XMLParser parser = new XMLParser(Settings.getPathCV() + fileName + ".xml");
        req.setAttribute("works", parser.getWorks());
        req.getRequestDispatcher(EDITWORK_JSP).forward(req, resp);
    }
}

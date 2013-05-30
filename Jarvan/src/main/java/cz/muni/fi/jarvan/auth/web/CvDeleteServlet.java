package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.Show;
import cz.muni.fi.jarvan.auth.XMLParser;
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
@WebServlet(CvDeleteServlet.URL_MAPPING + "/*")
public class CvDeleteServlet extends HttpServlet
{
    private static final String CVDELETE_JSP = "/WEB-INF/view/cvDelete.jsp";
    public static final String URL_MAPPING = "/auth/cv/delete";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
               
        XMLParser email = new XMLParser(Settings.getPathUser());
        String mail = email.getEmail(req.getSession().getAttribute("isLogged").toString());
        
        Show show = new Show();
        req.setAttribute("list", show.getPdfFiles(mail));
        
        req.getRequestDispatcher(CVDELETE_JSP).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getPathInfo();
        switch(action)
        {
            case "/process":
                String file = req.getParameter("file");
                
                String cmd = "rm " + Settings.getPathCV() + file;
                Settings.executeCmd(cmd);
                
                req.setAttribute("success", "Congratulation ! You have been successfully deleted file :) ");
                req.getRequestDispatcher(CVDELETE_JSP).forward(req, resp);
        }
    }
    
}

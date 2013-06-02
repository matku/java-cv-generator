
package cz.muni.fi.jarvan.auth.web;

import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.User;
import cz.muni.fi.jarvan.auth.XMLWriter;
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
 * Servlet for changing user password. Password are stored in hash md5
 * @author martin
 */
@WebServlet(ChangePasswordServlet.URL_MAPPING + "/*")
public class ChangePasswordServlet extends HttpServlet
{
    
    private static final String CHANGEPASSWORD_JSP = "/WEB-INF/view/changePassword.jsp";
    public static final String URL_MAPPING = "/auth/changePassword";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        req.getRequestDispatcher(CHANGEPASSWORD_JSP).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String new2Password = req.getParameter("new2Password");
        
        if(!newPassword.equals(new2Password))
        {
            req.setAttribute("error", "Passwords doesn't match !");
        }
        else
        {        
                User tmpUser = new User();
                tmpUser.setPassword(newPassword);
                XMLWriter writer = new XMLWriter(Settings.getPathUser());
                
                if(writer.changePassword(req.getSession().getAttribute("isLogged").toString(), oldPassword, newPassword) == 1)
                    req.setAttribute("error", "Old password isn't correct");
                else
                    req.setAttribute("success", "Congratulation ! Your password has been chaged ! ");
        }
        
        req.getRequestDispatcher(CHANGEPASSWORD_JSP).forward(req, resp);
    }
    
    
}

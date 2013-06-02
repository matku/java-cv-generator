
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
 * Change username, if username already exists, print error, else chage it.
 * @author martin
 */
@WebServlet(ChangeUsernameServlet.URL_MAPPING + "/*")
public class ChangeUsernameServlet extends HttpServlet
{
 
    private static final String CHANGEUSERNAME_JSP = "/WEB-INF/view/changeUsername.jsp";
    public static final String URL_MAPPING = "/auth/changeUsername";
    
    private final static Logger log = LoggerFactory.getLogger(ChangeUsernameServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        req.getRequestDispatcher(CHANGEUSERNAME_JSP).forward(req, resp);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String oldUsername = req.getParameter("oldUsername");
        String newUsername = req.getParameter("newUsername");
        String new2Username = req.getParameter("new2Username");
        
        String username = req.getSession().getAttribute("isLogged").toString();
        
        if(!username.equals(oldUsername))
        {
            req.setAttribute("error", "Type your username correct !");
        }
        else
        {        
            if(!newUsername.equals(new2Username))
            {
                req.setAttribute("error", "New usernames aren't the same !!");
            }
            else
            {
                User tmpUser = new User();
                tmpUser.setUsername(newUsername);
                if(tmpUser.userAlreadyExists())
                {
                    req.setAttribute("error", "This username already exists !!");
                }
                else
                {
                    XMLWriter writer = new XMLWriter(Settings.getPathUser());
                    if(!writer.changeUsername(oldUsername, newUsername))
                    {
                        req.setAttribute("error", "Error occured !!");
                    }
                    else
                    {
                        req.getSession().setAttribute("isLogged", newUsername);
                        req.setAttribute("success", "Congratulation ! Your username has been chaged ! ");
                    }
                }
            }
        }
        
        req.getRequestDispatcher(CHANGEUSERNAME_JSP).forward(req, resp);
    }
    
}

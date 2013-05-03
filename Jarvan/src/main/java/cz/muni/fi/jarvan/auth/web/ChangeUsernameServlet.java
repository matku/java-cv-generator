
package cz.muni.fi.jarvan.auth.web;

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
    
}

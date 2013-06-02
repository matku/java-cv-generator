
package cz.muni.fi.jarvan.web;

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
@WebServlet(LogoutServlet.URL_MAPPING + "/*")
public class LogoutServlet extends HttpServlet
{
    private static final String LOGOUT_JSP = "/WEB-INF/view/logout.jsp";
    public static final String URL_MAPPING = "/logout";
    
    private final static Logger log = LoggerFactory.getLogger(LogoutServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("isLogged", null);
        System.gc();
        req.getRequestDispatcher(LOGOUT_JSP).forward(req, resp);
    }
}

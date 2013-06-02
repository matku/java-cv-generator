
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
 * 404 page error   
 * @author martin
 */
@WebServlet(E404Servlet.URL_MAPPING + "/*")
public class E404Servlet extends HttpServlet
{
    private static final String ERROR_JSP = "/WEB-INF/view/404.jsp";
    public static final String URL_MAPPING = "/404";
    
    private final static Logger log = LoggerFactory.getLogger(E404Servlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
    }
    
    
}

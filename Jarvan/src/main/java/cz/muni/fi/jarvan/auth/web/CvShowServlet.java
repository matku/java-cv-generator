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
@WebServlet(CvShowServlet.URL_MAPPING + "/*")
public class CvShowServlet extends HttpServlet
{
    private static final String CVSHOW_JSP = "/WEB-INF/view/cvShow.jsp";
    public static final String URL_MAPPING = "/auth/cv/show";
    
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
        req.setAttribute("list", show.showList(mail));
        
        req.getRequestDispatcher(CVSHOW_JSP).forward(req, resp);
    }
}

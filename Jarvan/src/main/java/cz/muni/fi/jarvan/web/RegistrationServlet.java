package cz.muni.fi.jarvan.web;

import cz.muni.fi.jarvan.auth.Registration;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@WebServlet(RegistrationServlet.URL_MAPPING + "/*")
public class RegistrationServlet extends HttpServlet
{
    private static final String REGISTRATION_JSP = "/WEB-INF/view/registration.jsp";
    public static final String URL_MAPPING = "/registration";
    
    private final static Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getPathInfo();
        switch(action)
        {
            case "/process":
                String username = req.getParameter("username");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String password2 = req.getParameter("password2");
                
                if(!password.equals(password2))
                {
                    log.error("Could not register. Given passwords don't match.");
                    req.setAttribute("error", "Passwords don't match !!!");
                    req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
                    return;
                }
                
                Registration registration = new Registration(username, email, password);
                
                if(!registration.tryRegister())
                {
                    log.error("Could not register. Given username or email already used.");
                    req.setAttribute("error", "User or email already exists !!!");
                    req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
                    return;
                }
                
                log.info("User " + username + " has been succesfully registered.");
                req.setAttribute("success", "Congratulation ! You have been successfully registered :) ");
                req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
        }
    }
    
    
}
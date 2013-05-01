package cz.muni.fi.jarvan.web;

import cz.muni.fi.jarvan.auth.Registration;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(RegistrationServlet.URL_MAPPING + "/*")
public class RegistrationServlet extends HttpServlet
{
    private static final String REGISTRATION_JSP = "/WEB-INF/view/registration.jsp";
    public static final String URL_MAPPING = "/registration";

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
                    req.setAttribute("error", "Passwords don't match !!!");
                    req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
                    return;
                }
                
                Registration registration = new Registration(username, email, password);
                
                if(!registration.tryRegister())
                {
                    req.setAttribute("error", "User or email already exists !!!");
                    req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
                    return;
                }
                
                req.setAttribute("success", "Congratulation ! You have been successfully registered :) ");
                req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
        }
    }
    
    
}
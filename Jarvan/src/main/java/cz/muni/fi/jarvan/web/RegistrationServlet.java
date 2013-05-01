package cz.muni.fi.jarvan.web;

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
        resp.sendRedirect(req.getContextPath() + URL_MAPPING);
    }
    
    
}
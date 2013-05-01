/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.jarvan.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin
 */
@WebServlet(ContactServlet.URL_MAPPING + "/*")
public class ContactServlet extends HttpServlet
{
    private static final String CONTACT_JSP = "/WEB-INF/view/contact.jsp";
    public static final String URL_MAPPING = "/contact";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CONTACT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + URL_MAPPING);
    }
    
    
}

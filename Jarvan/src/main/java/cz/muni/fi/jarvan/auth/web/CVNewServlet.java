
package cz.muni.fi.jarvan.auth.web;


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
@WebServlet(CVNewServlet.URL_MAPPING + "/*")
public class CVNewServlet extends HttpServlet
{
    private static final String CVNEW_JSP = "/WEB-INF/view/cvNew.jsp";
    public static final String URL_MAPPING = "/auth/cv/create";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getPathInfo();
        switch(action)
        {
            case "/new":
                //personal info
                String sex = req.getParameter("sex");
                if (sex == null)
                {
                    req.setAttribute("sexError", "Sex is required");
                    req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                    return;
                }
                String firstName = req.getParameter("name");
                String lastName = req.getParameter("surname");
                String titleBefore = req.getParameter("titleBefore");
                String titleAfter = req.getParameter("titleAfter");
                String birthday = req.getParameter("birthday");
                //kontaktna adresa
                String address = req.getParameter("address");
                String psc = req.getParameter("psc");
                String town = req.getParameter("town");
                String state = req.getParameter("state");
                String homePhone = req.getParameter("homePhone");
                String mobilePhone = req.getParameter("mobilePhone");
                String driverLicence = req.getParameter("driverLicence");
                //vzdelanie
                String topEducation = req.getParameter("topEducation");
                //high school
                String schoolStart = req.getParameter("schoolStart");
                String schoolEnd = req.getParameter("schoolEnd");
                String schoolName = req.getParameter("schoolName");
                String schoolCity = req.getParameter("schoolCity");
                String schoolFieldOfStudy = req.getParameter("schoolFieldOfStudy");
                if (!schoolCity.equals("") || !schoolEnd.equals("") || 
                    !schoolName.equals("") || !schoolStart.equals("") || 
                    !schoolFieldOfStudy.equals(""))
                {
                    if (schoolCity.equals("") || schoolName.equals("") || 
                        schoolStart.equals(""))
                    {
                        req.setAttribute("schoolError", "You must fill in all required school fields if you have filled one of them");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                }
                //university
                String universityStart = req.getParameter("universityStart");
                String universityEnd = req.getParameter("universityEnd");
                String universityName = req.getParameter("universityName");
                String universityCity = req.getParameter("universityCity");
                String universityFieldOfStudy = req.getParameter("universityFieldOfStudy");
                if (!schoolCity.equals("") || !schoolEnd.equals("") || 
                    !schoolName.equals("") || !schoolStart.equals("") || 
                    !schoolFieldOfStudy.equals(""))
                {
                    if (universityCity.equals("") || universityName.equals("") ||
                        universityStart.equals("") || universityFieldOfStudy.equals(""))
                    {
                        req.setAttribute("universityError", "You must fill in all required university fields if you have filled one of them");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                }
                //work
                String workStart = req.getParameter("workStart");
                String workEnd = req.getParameter("workEnd");
                String workEmployer = req.getParameter("workEmployer");
                String workJob = req.getParameter("workJob");
                if (!workStart.equals("") || !workEnd.equals("") || 
                    !workEmployer.equals("") || !workJob.equals(""))
                {
                    if (workStart.equals("") || workEnd.equals("") ||
                        workEmployer.equals("") || workJob.equals(""))
                    {
                        req.setAttribute("workError", "You must fill in all required work fields if you have filled one of them");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                }
                
                //languages
                String languages = req.getParameter("languages");
                //other
                String other = req.getParameter("other");
                
                
                /*System.err.println("firstName: " + firstName + "\n" + 
                                   "lastName: " + lastName + "\n" + 
                                   "titleBefore: " + titleBefore + "\n" + 
                                   "titleAfter: " + titleAfter + "\n" + 
                                   "birthday: " + birthday + "\n" + 
                                   "address: " + address + "\n" + 
                                   "psc: " + psc + "\n" + 
                                   "town: " + town + "\n" + 
                                   "state: " + state + "\n" + 
                                   "homePhone: " + homePhone + "\n" + 
                                   "mobilePhone: " + mobilePhone + "\n" + 
                                   "driverLicense: " + driverLicence + "\n" + 
                                   "topEducation: " + topEducation + "\n" + 
                                   "High School: \n" + 
                                   "schoolStart: " + schoolStart + "\n" + 
                                   "schoolEnd: " + schoolEnd + "\n" + 
                                   "schoolName: " + schoolName + "\n" + 
                                   "schoolCity: " + schoolCity + "\n" + 
                                   "schoolFieldOfStudy: " + schoolFieldOfStudy + "\n" + 
                                   "University: \n" + 
                                   "universityStart: " + universityStart + "\n" + 
                                   "universityEnd: " + universityEnd + "\n" + 
                                   "universityName: " + universityName + "\n" + 
                                   "universityCity: " + universityCity + "\n" + 
                                   "universityFieldOfStudy: " + universityFieldOfStudy + "\n" + 
                                   "Work: \n" + 
                                   "workStart: " + workStart + "\n" + 
                                   "workEnd: " + workEnd + "\n" + 
                                   "workEmployer: " + workEmployer + "\n" + 
                                   "workJob: " + workJob + "\n" + 
                                   "\n" + 
                                   "languages: " + languages + "\n" + 
                                   "other: " + other);*/
                
                
                
                break;
        }
        req.setAttribute("success", "Congratulation ! You have successfully created your CV :) ");
        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
    }
    
    
}


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
@WebServlet(CvNewServlet.URL_MAPPING + "/*")
public class CvNewServlet extends HttpServlet
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
                    log.error("Sex not selected.");
                    req.setAttribute("sexError", "Sex is required");
                    req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                    return;
                }
                String firstName = req.getParameter("name");
                String lastName = req.getParameter("surname");
                String[] titlesBefore = req.getParameter("titleBefore").split("\\.");
                String titleBefore;
                System.err.println("Titles Before: " + titlesBefore.length);
                for (int i = 0; i < titlesBefore.length; i++) {
                    titleBefore = titlesBefore[i].trim();
                    if (titleBefore.equals(""))
                    {
                        System.err.println("prazdny");
                        continue;
                    }
                    titleBefore += ".";
                    System.err.println("titleBefore: " + titleBefore);
                    
                }
                String[] titlesAfter = req.getParameter("titleAfter").split("\\.");
                String titleAfter;
                System.err.println("Titles After: " + titlesAfter.length);
                for (int i = 0; i < titlesAfter.length; i++) {
                    titleAfter = titlesAfter[i].trim();
                    if (titleAfter.equals(""))
                    {
                        System.err.println("prazdny");
                        continue;
                    }
                    titleAfter += ".";
                    System.err.println("titleAfter: " + titleAfter);
                    
                }
                String birthdayDay = req.getParameter("birthdayDay");
                String birthdayMonth = req.getParameter("birthdayMonth");
                String birthdayYear = req.getParameter("birthdayYear");
                //kontaktna adresa
                String address = req.getParameter("address");
                String psc = req.getParameter("psc");
                String town = req.getParameter("town");
                String state = req.getParameter("state");
                String homePhone = req.getParameter("homePhone");
                String mobilePhone = req.getParameter("mobilePhone");
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
                        log.error("Did not fill nothing or everything in high school.");
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
                if (!universityCity.equals("") || !universityEnd.equals("") || 
                    !universityName.equals("") || !universityStart.equals("") || 
                    !universityFieldOfStudy.equals(""))
                {
                    if (universityCity.equals("") || universityName.equals("") ||
                        universityStart.equals("") || universityFieldOfStudy.equals(""))
                    {
                        log.error("Did not fill nothing or everything in university.");
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
                        log.error("Did not fill nothing or everything in work.");
                        req.setAttribute("workError", "You must fill in all required work fields if you have filled one of them");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                }
                
                //languages
                String[] languages = req.getParameter("languages").split("\n");
                //other
                String[] other = req.getParameter("other").split(",");
                
                
                
                System.err.println("firstName: " + firstName + "\n" + 
                                   "lastName: " + lastName + "\n" + 
                                   "birthdayDay: " + birthdayDay + "\n" + 
                                   "birthdayMonth: " + birthdayMonth + "\n" + 
                                   "birthdayYear: " + birthdayYear + "\n" + 
                                   "address: " + address + "\n" + 
                                   "psc: " + psc + "\n" + 
                                   "town: " + town + "\n" + 
                                   "state: " + state + "\n" + 
                                   "homePhone: " + homePhone + "\n" + 
                                   "mobilePhone: " + mobilePhone + "\n" + 
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
                                   "workJob: " + workJob);
                System.err.println("Languages: ");
                String[] language;
                for (int i = 0; i < languages.length; i++) {
                    
                    if (languages[i].trim().equals(""))
                    {
                        log.error("Wrong format of languages");
                        req.setAttribute("languageError", "wrong format of languages");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                    try
                    {
                        language = languages[i].split(":", 2);
                        if (language[0].trim().equals("") || language[1].trim().equals(""))
                        {
                            log.error("Wrong format of languages");
                            req.setAttribute("languageError", "wrong format of languages");
                            req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                            return;
                        }
                    } catch (IndexOutOfBoundsException e)
                    {
                        log.error("Wrong format of languages");
                        req.setAttribute("languageError", "wrong format of languages");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                    System.err.println(language[0].trim());
                    System.err.println(language[1].trim());
                }
                
                System.err.println("Other: ");
                for (int i = 0; i < other.length; i++) {
                    if (other[i].trim().equals(""))
                    {
                        log.error("Wrong format of other");
                        req.setAttribute("otherError", "wrong format of others");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                    System.err.println(other[i].trim());
                }
                
                
                break;
        }
        req.setAttribute("success", "Congratulation ! You have successfully created your CV :) ");
        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
    }
    
    
}

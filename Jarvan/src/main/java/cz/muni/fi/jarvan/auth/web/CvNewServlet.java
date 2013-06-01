
package cz.muni.fi.jarvan.auth.web;

import cz.muni.fi.jarvan.auth.Cv;
import cz.muni.fi.jarvan.auth.CvException;
import cz.muni.fi.jarvan.auth.Education;
import cz.muni.fi.jarvan.auth.Library;
import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.User;
import cz.muni.fi.jarvan.auth.Work;
import cz.muni.fi.jarvan.auth.XMLWriter;
import cz.muni.fi.jarvan.web.HomeServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        req.setCharacterEncoding("utf-8");
        String action = req.getPathInfo();
        switch(action)
        {
            case "/new":
                //personal info
                String name = req.getParameter("name");
                String sex = req.getParameter("sex");
                if (sex == null)
                {
                    log.error("Sex not selected.");
                    req.setAttribute("sexError", "Sex is required");
                    req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                    return;
                }
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("surname");
                String titleBefore = req.getParameter("titleBefore");
                String titleAfter = req.getParameter("titleAfter");
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
                String[] languages = null;
                if (!req.getParameter("languages").equals(""))
                {
                    languages = req.getParameter("languages").split("\n");
                }
                //other
                String[] skill = null;
                if (!req.getParameter("other").equals(""))
                {
                    skill = req.getParameter("other").split(",");
                }
                
                
                try
                {
                    User usr = new User();
                    usr.setUsername(req.getSession().getAttribute("isLogged").toString());
                    usr.setEmail(User.parser.getEmail(req.getSession().getAttribute("isLogged").toString()));
                    Cv cv = new Cv();
                    cv.setName(name + "_" + usr.getEmail());
                    cv.setFirstName(firstName);
                    cv.setLastName(lastName);
                    cv.setMale(sex.equals("Muz") ? true : false);
                    cv.setTitle(titleBefore);
                    cv.setTitleAfter(titleAfter);
                    cv.setDateOfBirth(birthdayDay + "." + birthdayMonth + "." + birthdayYear);
                    cv.setStreet(address);
                    cv.setZip(psc);
                    cv.setCity(town);
                    cv.setState(state);
                    cv.setEmail(usr.getEmail());
                    if (!homePhone.equals(""))
                    {
                        cv.setHomeNumber(homePhone);
                    }
                    if (!mobilePhone.equals(""))
                    {
                        cv.setMobileNumber(mobilePhone);
                    }
                    cv.setHighestEducation(topEducation);
                    
                    if (!schoolStart.equals(""))
                    {
                        Education school = new Education();
                        school.setFrom(schoolStart);
                        if (!schoolEnd.equals(""))
                        {
                            school.setTo(schoolEnd);
                        }
                        school.setFieldOfStudy(schoolFieldOfStudy);
                        school.setName(schoolName);
                        school.setCity(schoolCity);
                        
                        List<Education> highSchool = new ArrayList<>();
                        highSchool.add(school);
                        cv.setHighSchool(highSchool);
                    }
                    
                    if (!universityStart.equals(""))
                    {
                        Education university = new Education();
                        university.setFrom(universityStart);
                        if (!universityEnd.equals(""))
                        {
                            university.setTo(universityEnd);
                        }
                        university.setFieldOfStudy(universityFieldOfStudy);
                        university.setName(universityName);
                        university.setCity(universityCity);
                        
                        List<Education> universities = new ArrayList<>();
                        universities.add(university);
                        cv.setUniversity(universities);
                    }
                    
                    if (!workStart.equals(""))
                    {
                        Work work = new Work();
                        work.setFrom(workStart);
                        if (!workEnd.equals(""))
                        {
                            work.setTo(workEnd);
                        }
                        work.setEmployer(workEmployer);
                        work.setPosition(workJob);
                        
                        List<Work> job = new ArrayList<>();
                        job.add(work);
                        cv.setWork(job);
                    }
                    
                    Map<String, String> langs = new TreeMap<>();
                    
                    if (languages != null)
                    {
                        String[] language;
                        for (int i = 0; i < languages.length; i++) 
                        {
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
                            langs.put(language[0].trim(), language[1].trim());
                        }
                    }
                    
                    cv.setLanguages(langs);
                    
                    List<String> skills = new ArrayList<>();
                    
                    if (skill != null)
                    {
                        for (int i = 0; i < skill.length; i++)
                        {
                            if (skill[i].trim().equals(""))
                            {
                                log.error("Wrong format of other");
                                req.setAttribute("otherError", "wrong format of others");
                                req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                                return;
                            }
                            skills.add(skill[i].trim());
                        }
                    }
                    
                    cv.setSkills(skills);
                    
                    XMLWriter writer = new XMLWriter(Settings.getPathCV()+ name + "_" + usr.getEmail() + ".xml");
                    if (!writer.createCv(cv, usr))
                    {
                        req.setAttribute("otherError", "CV with this name already exists");
                        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
                        return;
                    }
                    Library library = new Library();
                    library.addCV(req.getSession().getAttribute("isLogged").toString(), name + "_" + usr.getEmail());
                    
                } catch (CvException e)
                {
                    log.error("could not create CV", e);
                    resp.sendRedirect("/JarvanUpdate/404");
                    return;
                }
                break;
        }
        req.setAttribute("success", "Congratulation ! You have successfully created your CV :) ");
        req.getRequestDispatcher(CVNEW_JSP).forward(req, resp);
    }
    
    
}

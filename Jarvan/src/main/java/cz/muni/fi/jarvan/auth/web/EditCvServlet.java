package cz.muni.fi.jarvan.auth.web;


import cz.muni.fi.jarvan.auth.Cv;
import cz.muni.fi.jarvan.auth.CvException;
import cz.muni.fi.jarvan.auth.Settings;
import cz.muni.fi.jarvan.auth.XMLParser;
import cz.muni.fi.jarvan.auth.XMLWriter;
import org.slf4j.Logger;
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
@WebServlet(EditCvServlet.URL_MAPPING + "/*")
public class EditCvServlet extends HttpServlet
{
    private static final String EDITCV_JSP = "/WEB-INF/view/editCv.jsp";
    public static final String URL_MAPPING = "/auth/cv/editCv";
    
    private final static Logger log = LoggerFactory.getLogger(ChangePasswordServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return;
        }
        
        XMLParser parser = new XMLParser(Settings.getPathCV() + req.getPathInfo().substring(1) + ".xml");
        Cv cv = parser.getCv();
        String fileName = req.getPathInfo().substring(1);
        req.setAttribute("cvName", fileName);
        List<String> names = new XMLParser(Settings.getPathLibrary()).getCvs(req.getSession().getAttribute("isLogged").toString());
        
        for (String name : names)
        {
            if (fileName.equals(name + "_" + new XMLParser(Settings.getPathUser()).getEmail(req.getSession().getAttribute("isLogged").toString())))
            {
                cv.setName(name);
            }
        }
        
        req.setAttribute("name", cv.getName());
        req.setAttribute("firstName", cv.getFirstName());
        req.setAttribute("surname", cv.getLastName());
        req.setAttribute("titleBefore", cv.getTitle());
        req.setAttribute("titleAfter", cv.getTitleAfter());
        req.setAttribute("address", cv.getStreet());
        req.setAttribute("psc", cv.getZip());
        req.setAttribute("town", cv.getCity());
        req.setAttribute("homePhone", cv.getHomeNumber());
        req.setAttribute("mobilePhone", cv.getMobileNumber());
        
        String langs = "";
        if (cv.getLanguages() != null && !cv.getLanguages().isEmpty())
        {
            for (Map.Entry<String, String> e : cv.getLanguages().entrySet())
            {
                if (!langs.equals(""))
                    langs += "\n";
                langs += e.getKey() + ": " + e.getValue();
            }
        }
        req.setAttribute("languages", langs);
        
        String skills = "";
        if (cv.getSkills() != null && !cv.getSkills().isEmpty())
        {
            for (String skill : cv.getSkills())
            {
                if (!skills.equals(""))
                    skills += ", ";
                skills += skill;
            }
        }
        req.setAttribute("skills", skills);
        req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("isLogged") == null)
        {
            resp.sendRedirect(req.getContextPath() + HomeServlet.URL_MAPPING);
            return ;
        }
        req.setCharacterEncoding("utf-8");
        String action = req.getPathInfo().substring(req.getPathInfo().length()-5);
        switch(action)
        {
            case "/edit":
                //personal info
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("surname");
                String titleBefore = req.getParameter("titleBefore");
                String titleAfter = req.getParameter("titleAfter");
                //kontaktna adresa
                String address = req.getParameter("address");
                String psc = req.getParameter("psc");
                String town = req.getParameter("town");
                String state = req.getParameter("state");
                String homePhone = req.getParameter("homePhone");
                String mobilePhone = req.getParameter("mobilePhone");
                //vzdelanie
                String topEducation = req.getParameter("topEducation");
                
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
                    Cv cv = new Cv();
                    cv.setFirstName(firstName);
                    cv.setLastName(lastName);
                    cv.setTitle(titleBefore);
                    cv.setTitleAfter(titleAfter);
                    cv.setStreet(address);
                    cv.setZip(psc);
                    cv.setCity(town);
                    cv.setState(state);
                    cv.setHomeNumber(homePhone);
                    cv.setMobileNumber(mobilePhone);
                    cv.setHighestEducation(topEducation);
                    
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
                                req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
                                return;
                            }
                            try
                            {
                                language = languages[i].split(":", 2);
                                if (language[0].trim().equals("") || language[1].trim().equals(""))
                                {
                                    log.error("Wrong format of languages");
                                    req.setAttribute("languageError", "wrong format of languages");
                                    req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
                                    return;
                                }
                            } catch (IndexOutOfBoundsException e)
                            {
                                log.error("Wrong format of languages");
                                req.setAttribute("languageError", "wrong format of languages");
                                req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
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
                                req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
                                return;
                            }
                            skills.add(skill[i].trim());
                        }
                    }
                    
                    cv.setSkills(skills);
                    
                    String name = req.getPathInfo().substring(1, req.getPathInfo().length()-5);
                    
                    XMLWriter writer = new XMLWriter(Settings.getPathCV() + name + ".xml");
                    if (!writer.editCv(cv))
                    {
                        req.setAttribute("otherError", "there were some issues in editing CV, "
                                + "please try again or contact our tech support");
                        req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
                        return;
                    }
                    
                } catch (CvException e)
                {
                    log.error("could not create CV", e);
                    resp.sendRedirect("/JarvanUpdate/404");
                    return;
                }
                req.setAttribute("success", "Congratulation ! You have successfully edited your CV :) ");
                break;
        }
        
        req.getRequestDispatcher(EDITCV_JSP).forward(req, resp);
    }
}

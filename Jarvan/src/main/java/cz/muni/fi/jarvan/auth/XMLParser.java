package cz.muni.fi.jarvan.auth;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

/**
 * Class for parsing data from XML
 * Methods: getUsers, getEmails
 * @author martin
 */
public class XMLParser {

    private Document doc;
    private final static Logger log = LoggerFactory.getLogger(User.class);
  
    /**
     * Sets xml path for DocumentBuilder for parsing
     * @param path 
     */
    public XMLParser(String path)
    {
        try
        {
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(xmlFile);
        }
        catch(SAXException | IOException | ParserConfigurationException error)
        {
            log.error("XMLDocument error: " + error);
        }
    }
    
    /**
     * Returns all users from user.xml
     * @return List<String> users
     */
    public List<String> getUsers()
    {
        List<String> attributes = new ArrayList<>();
        NodeList users = doc.getElementsByTagName("user");
        
        for(int i=0; i<users.getLength(); i++)
        {
            Element userElement = (Element) users.item(i);
            attributes.add(userElement.getAttribute("id"));
        }
        
        return attributes;
    }
    
    /**
     * Returns all email from user.xml
     * @return List<String> emails 
     */
    public List<String> getEmails()
    {
        List<String> emailList = new ArrayList<>();
       
        NodeList emails = doc.getElementsByTagName("email");

        for (int i = 0; i < emails.getLength(); i++)
        {
            emailList.add(emails.item(i).getTextContent());
        }
        
        return emailList;
    }
    
    public String getEmail(String username)
    {
        NodeList users = doc.getElementsByTagName("user");
        
        for(int i=0; i<users.getLength(); i++)
        {
            Element userElement = (Element) users.item(i);
            if (userElement.getAttribute("id").equals(username))
            {
                Element email = (Element) userElement.getElementsByTagName("email").item(0);
                return email.getTextContent();
            }
        }
        return null;
    }
    
    public List<String> getCvs(String username)
    {
        NodeList users = doc.getElementsByTagName("user");
        
        Element user = null;
        for (int i = 0; i < users.getLength(); i++)
        {
            Element userElement = (Element) users.item(i);
            if (userElement.getAttribute("id").equals(username))
            {
                user = userElement;
                break;
            }
        }
        
        if (user == null)
        {
            return null;
        }
        List<String> cvList = new ArrayList<>();
        
        NodeList cvs = user.getChildNodes();
        
        for (int i = 0; i < cvs.getLength(); i++)
        {
            Element cv = (Element) cvs.item(i);
            String[] parts = cv.getAttribute("name").split("_");
            String mail = "";
            int counter = 0;
            
            XMLParser parser = new XMLParser(Settings.getPathUser());
            String email = parser.getEmail(username);
            
            while(!parts[counter].equals(email))
            {
                mail += parts[counter];
                counter++;
            }
            
            cvList.add(mail);
        }
        
        return cvList;
    }
    
    public Cv getCv()
    {
        Cv cv = new Cv();

        try
        {
            Element root = (Element) doc.getElementsByTagName("cv").item(0);

            Element personalInfo = (Element) root.getElementsByTagName("personalInfo").item(0);

            Element name = (Element) personalInfo.getElementsByTagName("name").item(0);

            Element firstName = (Element) name.getElementsByTagName("first").item(0);
            cv.setFirstName(firstName.getTextContent());
            Element lastName = (Element) name.getElementsByTagName("last").item(0);
            cv.setLastName(lastName.getTextContent());
            
            NodeList titles = name.getElementsByTagName("title");
            
            for (int i = 0; i < titles.getLength(); i++)
            {
                Element title = (Element) titles.item(i);
                if (title.getAttribute("position").equals("") || title.getAttribute("position").equals("before"))
                {
                    cv.setTitle(title.getTextContent());
                }
                if (title.getAttribute("position").equals("after"))
                {
                    cv.setTitleAfter(title.getTextContent());
                }
            }
            
            Element sex = (Element) personalInfo.getElementsByTagName("sex").item(0);
            cv.setMale(sex.equals("male") ? true : false);
            
            Element birthday = (Element) personalInfo.getElementsByTagName("birthday").item(0);
            Element day = (Element) birthday.getElementsByTagName("day").item(0);
            Element month = (Element) birthday.getElementsByTagName("month").item(0);
            Element year = (Element) birthday.getElementsByTagName("year").item(0);
            cv.setDateOfBirth(day.getTextContent() + "." + month.getTextContent() + "." + year.getTextContent());
            
            Element address = (Element) personalInfo.getElementsByTagName("address").item(0);
            Element street = (Element) address.getElementsByTagName("street").item(0);
            cv.setStreet(street.getTextContent());
            Element city = (Element) address.getElementsByTagName("city").item(0);
            cv.setCity(city.getTextContent());
            Element country = (Element) address.getElementsByTagName("country").item(0);
            cv.setState(country.getTextContent());
            Element zip = (Element) address.getElementsByTagName("zip").item(0);
            cv.setZip(zip.getTextContent());
            
            NodeList contacts = personalInfo.getElementsByTagName("contact");
            
            for (int i = 0; i < contacts.getLength(); i++)
            {
                Element contact = (Element) contacts.item(i);
                switch(contact.getAttribute("type"))
                {
                    case "home":
                        cv.setHomeNumber(contact.getTextContent());
                        break;
                    case "mobile":
                        cv.setMobileNumber(contact.getTextContent());
                        break;
                    case "email":
                        cv.setEmail(contact.getTextContent());
                        break;
                }
            }
            
            Element education = (Element) root.getElementsByTagName("education").item(0);
            
            cv.setHighestEducation(education.getAttribute("highest"));
            
            NodeList schools = education.getElementsByTagName("school");
            
            List<Education> highSchools = new ArrayList<>();
            List<Education> universities = new ArrayList<>();
            
            for (int i = 0; i < schools.getLength(); i++)
            {
                Element school = (Element) schools.item(i);
                Education ed = new Education();
                
                Element start = (Element) school.getElementsByTagName("start").item(0);
                ed.setFrom(start.getTextContent());
                if (school.getElementsByTagName("end").getLength() != 0)
                {
                    Element end = (Element) school.getElementsByTagName("end").item(0);
                    ed.setTo(end.getTextContent());
                }
                Element schoolName = (Element) school.getElementsByTagName("name").item(0);
                ed.setName(schoolName.getTextContent());
                Element schoolCity = (Element) school.getElementsByTagName("city").item(0);
                ed.setCity(schoolCity.getTextContent());
                if (school.getElementsByTagName("specialization").getLength() != 0)
                {
                    Element specialization = (Element) school.getElementsByTagName("specialization").item(0);
                    ed.setFieldOfStudy(specialization.getTextContent());
                }
                
                
                switch(school.getAttribute("type"))
                {
                    case "high":
                        highSchools.add(ed);
                        break;
                    case "univ":
                        universities.add(ed);
                        break;
                }
            }
            cv.setHighSchool(highSchools);
            cv.setUniversity(universities);

            NodeList jobs = root.getElementsByTagName("jobs");
            
            List<Work> works = new ArrayList<>();
            
            for (int i = 0; i < jobs.getLength(); i++)
            {
                Element job = (Element) jobs.item(i);
                Work work = new Work();

                Element start = (Element) job.getElementsByTagName("start").item(0);
                work.setFrom(start.getTextContent());
                if (job.getElementsByTagName("end").getLength() != 0)
                {
                    Element end = (Element) job.getElementsByTagName("end").item(0);
                    work.setTo(end.getTextContent());
                }
                Element employer = (Element) job.getElementsByTagName("employer").item(0);
                work.setEmployer(employer.getTextContent());
                Element position = (Element) job.getElementsByTagName("position").item(0);
                work.setPosition(position.getTextContent());
                works.add(work);
            }
            cv.setWork(works);

            NodeList languages = root.getElementsByTagName("language");
            
            Map<String, String> langs = new TreeMap<>();

            for (int i = 0; i < languages.getLength(); i++)
            {
                Element language = (Element) languages.item(i);
                langs.put(language.getTextContent(), language.getAttribute("level"));
            }
            cv.setLanguages(langs);

            NodeList skills = root.getElementsByTagName("skill");
            
            List<String> others = new ArrayList<>();

            for (int i = 0; i < skills.getLength(); i++)
            {
                Element skill = (Element) skills.item(0);
                others.add(skill.getTextContent());
            }
            cv.setSkills(others);
            
        } catch (CvException | DOMException e)
        {
            log.error("nejaka chyba pri parsovani XML" + e);
        }
        
        return cv;
    }
    
    /**
     * Login method for user 
     * If false prints error message
     * @return true/false
     */
    public boolean login(String username, String password)
    {
        
        NodeList user = doc.getElementsByTagName("user");
        
        for(int i=0; i<user.getLength(); i++)
        {
            
            Element userElement = (Element) user.item(i);
                        
            if(userElement.getAttribute("id").equals(username))
            {
                
                NodeList userData = userElement.getElementsByTagName("password");
                
                if(userData.item(0).getTextContent().equals(password))
                    return true;
                
            }
        }
        
        return false;
        
    }
    
    /**
     * Finds language of cv
     * @return language 
     */
    public String getLang()
    {
        String lang;
        
        NodeList cv = doc.getElementsByTagName("cv");
                
        Element findLang = (Element) cv.item(0);
        lang = (String) findLang.getAttribute("lang");       
        
        return lang;
    }
    
 }

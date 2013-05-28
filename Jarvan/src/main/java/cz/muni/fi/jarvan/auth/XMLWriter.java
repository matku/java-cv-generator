package cz.muni.fi.jarvan.auth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.Date;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;



public class XMLWriter {

    private File xmlFile;
    private Document doc;
    private final static Logger log = LoggerFactory.getLogger(User.class);
    
    public XMLWriter(String path)
    {
        try
        {
            this.xmlFile = new File(path);
            
            if(!this.xmlFile.exists())
            {
                log.info("File at location " + path + " doesn't exist. Creating ...");

                try
                {
                    this.xmlFile.createNewFile();
                    FileWriter fw = new FileWriter(this.xmlFile);    
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
                    bw.write("<cv>");
                    bw.write("</cv>");
                    bw.close();
                }
                catch (IOException e)
                {
                    log.error("File " + path + " not created" + e.getMessage());
                    throw new RuntimeException("File creation error", e);
                }
                log.info("File created");
            }
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(xmlFile);
        }
        catch(SAXException | IOException | ParserConfigurationException error)
        {
            log.error("XMLDocument error: " + error);
            throw new RuntimeException("DOM error", error);
        }
    }
    
    public boolean createUser(User usr)
    {
        try
        {
            Element user = doc.createElement("user");
            user.setAttribute("id", usr.getUsername());
            Element username = doc.createElement("username");
            username.setTextContent(usr.getUsername());
            Element password = doc.createElement("password");
            password.setTextContent(usr.getPassword());
            Element email = doc.createElement("email");
            email.setTextContent(usr.getEmail());
            user.appendChild(username);
            user.appendChild(password);
            user.appendChild(email);

            NodeList users = doc.getElementsByTagName("users");
            users.item(0).appendChild(user);
            System.err.println("Should be registered.");
        } catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
            System.err.println("Should be serialized");
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean changePassword(String username, String newPassword)
    {
        
        return true;
    }
    
    public boolean createCv(Cv newCv, User user)
    {
        try
        {
            //CV
            Element cv = (Element) doc.getElementsByTagName("cv").item(0);
            cv.setAttribute("user", user.getUsername());
            cv.setAttribute("created", new Date().toString());
            cv.setAttribute("modified", new Date().toString());
            cv.setAttribute("lang", "en");
            
            //personal info:
            Element personalInfo = doc.createElement("personalInfo");
            
            //name w/ titles
            Element name = doc.createElement("name");
            
            if (!newCv.getTitle().equals(""))
            {
                Element title = doc.createElement("title");
                title.setAttribute("position", "before");
                title.setTextContent(newCv.getTitle());
                name.appendChild(title);
            }
            if (!newCv.getTitleAfter().equals(""))
            {
                Element titleAfter = doc.createElement("title");
                titleAfter.setAttribute("position", "after");
                titleAfter.setTextContent(newCv.getTitle());
                name.appendChild(titleAfter);
            }
            Element first = doc.createElement("first");
            first.setTextContent(newCv.getFirstName());
            name.appendChild(first);
            Element last = doc.createElement("last");
            last.setTextContent(newCv.getLastName());
            name.appendChild(last);
            
            personalInfo.appendChild(name);
            
            //sex
            Element sex = doc.createElement("sex");
            if (newCv.isMale())
            {
                sex.setTextContent("male");
            }
            else
            {
                sex.setTextContent("female");
            }
            
            personalInfo.appendChild(sex);
            
            //birthday
            Element birthday = doc.createElement("birthday");
            
            try
            {
                String[] birth = newCv.getDateOfBirth().split("\\.", 3);
                Element day = doc.createElement("day");
                day.setTextContent(birth[0]);
                birthday.appendChild(day);
                Element month = doc.createElement("month");
                month.setTextContent(birth[1]);
                birthday.appendChild(month);
                Element year = doc.createElement("year");
                year.setTextContent(birth[2]);
                birthday.appendChild(year);
            } catch (IndexOutOfBoundsException e)
            {
                log.error("Wrong birth date format");
                return false;
            }
            
            personalInfo.appendChild(birthday);
            
            //address
            Element address = doc.createElement("address");
            
            Element street = doc.createElement("street");
            street.setTextContent(newCv.getStreet());
            address.appendChild(street);
            Element city = doc.createElement("city");
            city.setTextContent(newCv.getCity());
            address.appendChild(city);
            Element country = doc.createElement("country");
            country.setTextContent(newCv.getState());
            address.appendChild(country);
            Element zip = doc.createElement("zip");
            zip.setTextContent(newCv.getZip());
            address.appendChild(zip);
            
            personalInfo.appendChild(address);
            
            //contacts
            if (newCv.getHomeNumber() != null)
            {
                Element contact = doc.createElement("contact");
                contact.setTextContent(newCv.getHomeNumber());
                contact.setAttribute("type", "home");
                personalInfo.appendChild(contact);
            }
            
            if (newCv.getMobileNumber() != null)
            {
                Element contact = doc.createElement("contact");
                contact.setTextContent(newCv.getMobileNumber());
                contact.setAttribute("type", "mobile");
                personalInfo.appendChild(contact);
            }
            
            Element contact = doc.createElement("contact");
            contact.setTextContent(newCv.getEmail());
            contact.setAttribute("type", "email");
            personalInfo.appendChild(contact);
            
            cv.appendChild(personalInfo);
            //end personal info
            
            //education
            Element education = doc.createElement("education");
            education.setAttribute("highest", newCv.getHighestEducation());
            
            if (newCv.getHighSchool() != null)
            {
                for (Education ed : newCv.getHighSchool())
                {
                    Element school = doc.createElement("school");
                    school.setAttribute("type", "high");
                    
                    Element schoolName = doc.createElement("name");
                    schoolName.setTextContent(ed.getName() + ", " + ed.getCity());
                    school.appendChild(schoolName);
                    
                    if (ed.getFieldOfStudy() != null)
                    {
                        Element schoolSpecialization = doc.createElement("specialization");
                        schoolSpecialization.setTextContent(ed.getFieldOfStudy());
                        school.appendChild(schoolSpecialization);
                    }
                    
                    Element schoolStart = doc.createElement("start");
                    schoolStart.setTextContent(ed.getFrom());
                    school.appendChild(schoolStart);
                    
                    if (ed.getTo() != null)
                    {
                        Element schoolEnd = doc.createElement("end");
                        schoolEnd.setTextContent(ed.getTo());
                        school.appendChild(schoolEnd);
                    }
                    
                    education.appendChild(school);
                }
            }
            
            if (newCv.getUniversity() != null)
            {
                for (Education ed : newCv.getUniversity())
                {
                    Element school = doc.createElement("school");
                    school.setAttribute("type", "univ");
                    
                    Element schoolName = doc.createElement("name");
                    schoolName.setTextContent(ed.getName() + ", " + ed.getCity());
                    school.appendChild(schoolName);
                    
                    Element schoolSpecialization = doc.createElement("specialization");
                    schoolSpecialization.setTextContent(ed.getFieldOfStudy());
                    school.appendChild(schoolSpecialization);
                    
                    Element schoolStart = doc.createElement("start");
                    schoolStart.setTextContent(ed.getFrom());
                    school.appendChild(schoolStart);
                    
                    if (ed.getTo() != null)
                    {
                        Element schoolEnd = doc.createElement("end");
                        schoolEnd.setTextContent(ed.getTo());
                        school.appendChild(schoolEnd);
                    }
                    
                    education.appendChild(school);
                }
            }
            cv.appendChild(education);
            //end education
            
            //work
            if (newCv.getWork() != null)
            {
                Element jobs = doc.createElement("jobs");
                for (Work w : newCv.getWork())
                {
                    Element work = doc.createElement("work");
                    
                    Element workStart = doc.createElement("start");
                    workStart.setTextContent(w.getFrom());
                    work.appendChild(workStart);
                    
                    if (w.getTo() != null)
                    {
                        Element workEnd = doc.createElement("end");
                        workEnd.setTextContent(w.getTo());
                        work.appendChild(workEnd);
                    }
                    
                    Element workEmployer = doc.createElement("employer");
                    workEmployer.setTextContent(w.getEmployer());
                    work.appendChild(workEmployer);
                    
                    Element workPosition = doc.createElement("position");
                    workPosition.setTextContent(w.getPosition());
                    work.appendChild(workPosition);
                    
                    jobs.appendChild(work);
                }
                
                cv.appendChild(jobs);
            }
            //end work
            
            //languages
            if (newCv.getLanguages() != null)
            {
                for (Map.Entry<String, String> e : newCv.getLanguages().entrySet())
                {
                    Element language = doc.createElement("language");
                    language.setAttribute("level", e.getValue());
                    language.setTextContent(e.getKey());
                    
                    cv.appendChild(language);
                }
            }
            //end languages
            
            //skills
            if (newCv.getSkills() != null)
            {
                for (String sk : newCv.getSkills())
                {
                    Element skill = doc.createElement("skill");
                    skill.setTextContent(sk);
                    
                    cv.appendChild(skill);
                }
            }
            //end skills
            
        } catch (DOMException e)
        {
            log.error("Error: ", e.getMessage());
            return false;
        }
        
        try {
            this.serializetoXML(xmlFile);
            System.err.println("Should be serialized");
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        return true;
    }
    
    public void serializetoXML(URI output)
            throws IOException, TransformerConfigurationException, TransformerException {
        // Vytvorime instanci tovarni tridy
        TransformerFactory factory = TransformerFactory.newInstance();
        // Pomoci tovarni tridy ziskame instanci tzv. kopirovaciho transformeru
        Transformer transformer = factory.newTransformer();
        // Vstupem transformace bude dokument v pameti
        DOMSource source = new DOMSource(doc);
        // Vystupem transformace bude vystupni soubor
        StreamResult result = new StreamResult(output.toString());
        // Provedeme transformaci
        transformer.transform(source, result);
    }

    public void serializetoXML(File output) throws IOException,
            TransformerException {
        serializetoXML(output.toURI());
    }
    
    
    /**
    * Deletes user from users.xml 
    * @param username username
    */
    public boolean deleteUser(String username)
    {
        try
        {
            NodeList users = doc.getElementsByTagName("user");
            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);
                if (user.getAttribute("id").equals(username)){
                    doc.getElementsByTagName("users").item(0).removeChild(user);
                    break;
                }
            }
                    
        } catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
            System.err.println("Should be serialized");
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Adds user or cv name to library.xml
     * @param username
     * @param cvName 
     * @return 
     */
    public boolean addCvToXml(String username, String cvName)
    {
        try
        {
            NodeList cvUser = doc.getElementsByTagName("user");

            // if user doesn't exists create user and add cvName
            // only library ta is created
            if(cvUser.getLength() == 0)
            {
                Element library = (Element) doc.getElementsByTagName("library").item(0);

                Element user = doc.createElement("user");
                user.setAttribute("id", username);

                Element cv = doc.createElement("cv");
                cv.setAttribute("name", cvName);

                user.appendChild(cv);
                library.appendChild(user);

            }
            else
            {
                boolean userExists = false;
                
                // find user and add cv
                for (int i = 0; i < cvUser.getLength(); i++)
                {
                    Element user = (Element) cvUser.item(i);
                    if (user.getAttribute("id").equals(username))
                    {
                        Element cv = doc.createElement("cv");
                        cv.setAttribute("name", cvName);
                        user.appendChild(cv);
                        userExists = true;
                        break;
                    }
                }

                 
                // if user doesn't exists
                if(userExists)
                {
                    Element library = (Element) doc.getElementsByTagName("library").item(0);

                    Element user = doc.createElement("user");
                    user.setAttribute("id", username);

                    Element cv = doc.createElement("cv");
                    cv.setAttribute("name", cvName);

                    user.appendChild(cv);
                    library.appendChild(user);
                }
            }
        }
        catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
            System.err.println("Should be serialized");
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    public boolean changeCvLanguage(String lang)
    {
        
        try
        {
            NodeList cv = doc.getElementsByTagName("cv");
            Element root = (Element) cv.item(0);
            root.setAttribute("lang", lang);
                    
        } catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
            System.err.println("Should be serialized");
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        
        return true;
        
    }
}
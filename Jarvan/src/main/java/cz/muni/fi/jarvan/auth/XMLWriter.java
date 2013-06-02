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
    private final static Logger log = LoggerFactory.getLogger(XMLWriter.class);
    
    /**
     * Creates an XMLWriter object and sets File and Document, 
     * creating them in the process if they don't exist
     * @param path 
     */
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
    
    /**
     * Creates user if he doesn't exist - writes his info into the users.xml file
     * @param usr   User object to be saved to file
     * @return  false on error, true on success
     */
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
        } catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
            
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        return true;
    }
    
    /***
     * Changes user password
     * @param oldPassword
     * @param newPassword
     * @return 0 correct changes
     * @return 1 old password isn't correct
     * @return -1 error writing in users.xml file
     */
    public int changePassword(String username, String oldPassword, String newPassword)
    {
        
        try
        {
            NodeList users = doc.getElementsByTagName("user");
            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);
                if (user.getAttribute("id").equals(username))
                {
                    
                    Element data = (Element) user.getChildNodes();
                    String tmpPassword = User.md5(oldPassword);
                    if(tmpPassword.equals(data.getElementsByTagName("password").item(0).getTextContent()))
                    {
                        data.getElementsByTagName("password").item(0).setTextContent(User.md5(newPassword));
                        break;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
                    
        } catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return -1;
        }
        try {
            this.serializetoXML(xmlFile);
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return -1;
        }        
        
        return 0;
    }
    
    /**
     * Method for changing username, checks if username already exists. If not system will
     * allow changing username otherwise it will print error message.
     * @param oldUsername
     * @param username
     * @return True  - correct changes 
     * @return False - error while changes
     */
    public boolean changeUsername(String oldUsername, String username)
    {
        
        try
        {
            NodeList users = doc.getElementsByTagName("user");
            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);
                if (user.getAttribute("id").equals(oldUsername))
                {
                    user.setAttribute("id", username);
                    Element data = (Element) user.getChildNodes();
                    if(oldUsername.equals(data.getElementsByTagName("username").item(0).getTextContent()))
                    {
                        data.getElementsByTagName("username").item(0).setTextContent(username);
                        break;
                    }
                }
            }
                    
        } catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        
        return true;       
    }
    
    /**
     * Creates new CV in a blank XML file, 
     * does nothing if there already is a CV stored in the file
     * @param newCv     CV object for saving
     * @param user      User to which the new CV should belong
     * @return          false on error, true on success
     */
    public boolean createCv(Cv newCv, User user)
    {
        try
        {
            if (doc.getElementsByTagName("personalInfo").getLength() != 0)
            {
                return false;
            }
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
                titleAfter.setTextContent(newCv.getTitleAfter());
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
                    
                    Element schoolStart = doc.createElement("start");
                    schoolStart.setTextContent(ed.getFrom());
                    school.appendChild(schoolStart);
                    
                    if (ed.getTo() != null)
                    {
                        Element schoolEnd = doc.createElement("end");
                        schoolEnd.setTextContent(ed.getTo());
                        school.appendChild(schoolEnd);
                    }
                    
                    Element schoolName = doc.createElement("name");
                    schoolName.setTextContent(ed.getName());
                    school.appendChild(schoolName);
                    
                    Element schoolCity = doc.createElement("city");
                    schoolCity.setTextContent(ed.getCity());
                    school.appendChild(schoolCity);
                    
                    if (ed.getFieldOfStudy() != null)
                    {
                        Element schoolSpecialization = doc.createElement("specialization");
                        schoolSpecialization.setTextContent(ed.getFieldOfStudy());
                        school.appendChild(schoolSpecialization);
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
                    
                    Element schoolStart = doc.createElement("start");
                    schoolStart.setTextContent(ed.getFrom());
                    school.appendChild(schoolStart);
                    
                    if (ed.getTo() != null)
                    {
                        Element schoolEnd = doc.createElement("end");
                        schoolEnd.setTextContent(ed.getTo());
                        school.appendChild(schoolEnd);
                    }
                    
                    Element schoolName = doc.createElement("name");
                    schoolName.setTextContent(ed.getName());
                    school.appendChild(schoolName);
                    
                    Element schoolCity = doc.createElement("city");
                    schoolCity.setTextContent(ed.getCity());
                    school.appendChild(schoolCity);
                    
                    Element schoolSpecialization = doc.createElement("specialization");
                    schoolSpecialization.setTextContent(ed.getFieldOfStudy());
                    school.appendChild(schoolSpecialization);
                    
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
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Method for serializing Document into the XML file itself
     * @param output
     * @throws IOException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
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
                if(!userExists)
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
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        
        return true;       
    }
    
    /**
     * Edits majority of CV elements, except school, work, sex and birthday
     * @param cv    new CV with information to replace the old one
     * @return      false on error, true on success
     */
    public boolean editCv(Cv cv)
    {
        try
        {
            Element root = (Element) doc.getElementsByTagName("cv").item(0);
            root.setAttribute("modified", new Date().toString());
            
            Element personalInfo = (Element) root.getElementsByTagName("personalInfo").item(0);
            
            Element name = (Element) personalInfo.getElementsByTagName("name").item(0);
            
            NodeList titles = name.getElementsByTagName("title");
            Element titleBefore = null;
            Element titleAfter = null;
            if (titles.getLength() != 0)
            {
                for (int i = 0; i < titles.getLength(); i++)
                {
                    Element title = (Element) titles.item(i);
                    if (title.getAttribute("position").equals("before"))
                    {
                        titleBefore = title;
                    }
                    else
                    {
                        titleAfter = title;
                    }
                }
            }
            if (titleBefore == null && !"".equals(cv.getTitle()))
            {
                titleBefore = doc.createElement("title");
                titleBefore.setAttribute("position", "before");
                titleBefore.setTextContent(cv.getTitle());
                name.appendChild(titleBefore);
            }
            else
            {
                if (!"".equals(cv.getTitle()))
                {
                    titleBefore.setTextContent(cv.getTitle());
                }
                else
                {
                    personalInfo.removeChild(titleBefore);
                }
            }
            if (titleAfter == null && !"".equals(cv.getTitleAfter()))
            {
                titleAfter = doc.createElement("title");
                titleAfter.setAttribute("position", "after");
                titleAfter.setTextContent(cv.getTitleAfter());
                name.appendChild(titleBefore);
            }
            else
            {
                if (!"".equals(cv.getTitleAfter()))
                {
                    titleAfter.setTextContent(cv.getTitleAfter());
                }
                else
                {
                    personalInfo.removeChild(titleAfter);
                }
            }
            
            Element firstName = (Element) name.getElementsByTagName("first").item(0);
            firstName.setTextContent(cv.getFirstName());
            Element lastName = (Element) name.getElementsByTagName("last").item(0);
            lastName.setTextContent(cv.getLastName());
            
            Element address = (Element) personalInfo.getElementsByTagName("address").item(0);
            
            Element street = (Element) address.getElementsByTagName("street").item(0);
            street.setTextContent(cv.getStreet());
            Element city = (Element) address.getElementsByTagName("city").item(0);
            city.setTextContent(cv.getCity());
            Element country = (Element) address.getElementsByTagName("country").item(0);
            country.setTextContent(cv.getState());
            Element zip = (Element) address.getElementsByTagName("zip").item(0);
            zip.setTextContent(cv.getZip());
            
            
            NodeList contacts = personalInfo.getElementsByTagName("contact");
            
            Element home = null;
            Element mobile = null;
            
            for (int i = 0; i < contacts.getLength(); i++)
            {
                Element contact = (Element) contacts.item(i);
                switch(contact.getAttribute("type"))
                {
                    case "home":
                        home = contact;
                        break;
                    case "mobile":
                        mobile = contact;
                        break;
                    case "email":
                        break;
                }
            }
            
            if (mobile == null && !"".equals(cv.getMobileNumber()))
            {
                mobile = doc.createElement("contact");
                mobile.setAttribute("type", "mobile");
                mobile.setTextContent(cv.getMobileNumber());
                personalInfo.appendChild(mobile);
            }
            else
            {
                if (!"".equals(cv.getMobileNumber()))
                {
                    mobile.setTextContent(cv.getMobileNumber());
                }
                else
                {
                    personalInfo.removeChild(mobile);
                }
            }
            
            if (home == null && !"".equals(cv.getHomeNumber()))
            {
                home = doc.createElement("contact");
                home.setAttribute("type", "home");
                home.setTextContent(cv.getHomeNumber());
                personalInfo.appendChild(home);
            }
            else
            {
                if (!"".equals(cv.getHomeNumber()))
                {
                    home.setTextContent(cv.getHomeNumber());
                }
                else
                {
                    personalInfo.removeChild(home);
                }
            }
            
            Element education = (Element) root.getElementsByTagName("education").item(0);
            education.setAttribute("highest", cv.getHighestEducation());
            
            NodeList languages = root.getElementsByTagName("language");
            int maxLang = languages.getLength();
            System.err.println("maxL: " + maxLang);
            for (int i = 0; i < maxLang; i++)
            {
                System.err.println("removed " + languages.item(0).getTextContent());
                root.removeChild(languages.item(0));
            }
            
            for (Map.Entry<String, String> e : cv.getLanguages().entrySet())
            {
                Element language = doc.createElement("language");
                language.setAttribute("level", e.getValue());
                language.setTextContent(e.getKey());
                root.appendChild(language);
                System.err.println("added " + language.getTextContent());
            }
            
            NodeList skills = root.getElementsByTagName("skill");
            int maxSkill = skills.getLength();
            System.err.println("maxS: " + maxSkill);
            for (int i = 0; i < maxSkill; i++)
            {
                System.err.println("removed " + skills.item(0).getTextContent());
                root.removeChild(skills.item(0));
            }
            
            for (String sk : cv.getSkills())
            {
                Element skill = doc.createElement("skill");
                skill.setTextContent(sk);
                root.appendChild(skill);
                System.err.println("added" + skill.getTextContent());
            }
            
        } catch (DOMException e)
        {
            log.error("Error DOM: ", e.getMessage());
            return false;
        }
        
        try {
            this.serializetoXML(xmlFile);
        } catch (IOException | TransformerException ex) {
            log.error("Error serialize: ", ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Adds a new work to an existing CV, creating <jobs> element in the process
     * if it doesn't exist
     * @param newWork   work to be added
     * @return  false on error, true on success
     */
    public boolean addWork(Work newWork)
    {
        try
        {
            NodeList jobs = doc.getElementsByTagName("jobs");
            Element job;
            if (jobs.getLength() == 0)
            {
                job = doc.createElement("jobs");
                doc.getElementsByTagName("cv").item(0).appendChild(job);
            }
            else
            {
                job = (Element) jobs.item(0);
            }
            
            Element work = doc.createElement("work");
            
            Element start = doc.createElement("start");
            start.setTextContent(newWork.getFrom());
            work.appendChild(start);
            if (newWork.getTo() != null)
            {
                Element end = doc.createElement("end");
                end.setTextContent(newWork.getTo());
                work.appendChild(end);
            }
            Element employer = doc.createElement("employer");
            employer.setTextContent(newWork.getEmployer());
            work.appendChild(employer);
            Element position = doc.createElement("position");
            position.setTextContent(newWork.getPosition());
            work.appendChild(position);
            
            job.appendChild(work);
            
        } catch (DOMException e)
        {
            log.error("Error DOM: ", e.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
        } catch (IOException | TransformerException ex) {
            log.error("Error serialize: ", ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Adds a new school to an existing CV
     * @param newEdu    new Education
     * @param type  can have 2 values: [highSchool, university] 
     *              determines the type of the school, 
     *              therefore the need for specialization element
     * @return  false on error, true on success
     */
    public boolean addEducation(Education newEdu, String type)
    {
        try
        {
            Element education = (Element) doc.getElementsByTagName("education").item(0);
            
            Element school = doc.createElement("school");
            if (type.equals("highSchool"))
            {
                school.setAttribute("type", "high");
            }
            else if (type.equals("university"))
            {
                school.setAttribute("type", "univ");
            }
            else
            {
                return false;
            }
            
            Element start = doc.createElement("start");
            start.setTextContent(newEdu.getFrom());
            school.appendChild(start);
            if (newEdu.getTo() != null)
            {
                Element end = doc.createElement("end");
                end.setTextContent(newEdu.getTo());
                school.appendChild(end);
            }
            Element name = doc.createElement("name");
            name.setTextContent(newEdu.getName());
            school.appendChild(name);
            Element city = doc.createElement("city");
            city.setTextContent(newEdu.getCity());
            school.appendChild(city);
            if (newEdu.getFieldOfStudy() != null)
            {
                Element specialization = doc.createElement("specialization");
                specialization.setTextContent(newEdu.getFieldOfStudy());
                school.appendChild(specialization);
            }
            
            education.appendChild(school);
            
        } catch (DOMException e)
        {
            log.error("Error DOM: ", e.getMessage());
            return false;
        }
        
        try {
            this.serializetoXML(xmlFile);
        } catch (IOException | TransformerException ex) {
            log.error("Error serialize: ", ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Edits an existing work in current CV
     * @param work  start and name of the employer of the work altered (as primary key)
     * @param job   working position, can be empty string
     * @param end   end year of work, can be empty string
     * @return  false on error, true on success
     */
    public boolean editWork(String work, String job, String end)
    {
        boolean edited = false;
        try
        {
            NodeList works = doc.getElementsByTagName("work");
            String[] workSplit = work.split(";", 2);
            for (int i = 0; i < works.getLength(); i++)
            {
                Element w = (Element) works.item(i);
                Element employer = (Element) w.getElementsByTagName("employer").item(0);
                if (workSplit[1].trim().equals(employer.getTextContent()))
                {
                    Element from = (Element) w.getElementsByTagName("start").item(0);
                    if (workSplit[0].substring(2).trim().equals(from.getTextContent()))
                    {
                        if (!"".equals(end))
                        {
                            Element to = (Element) w.getElementsByTagName("end").item(0);
                            if (to == null)
                            {
                                to = doc.createElement("end");
                                w.appendChild(to);
                            }
                            to.setTextContent(end);
                        }
                        if (!"".equals(job))
                        {
                            Element position = (Element) w.getElementsByTagName("position").item(0);
                            position.setTextContent(job);
                        }
                        edited = true;
                        break;
                    }
                }

            }
        } catch (DOMException e)
        {
            log.error("Error DOM: ", e.getMessage());
            return false;
        }
        if (!edited)
        {
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
        } catch (IOException | TransformerException ex) {
            log.error("Error serialize: ", ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Edits an existing school in current CV
     * @param school    start, name and city of the school altered (as primary key)
     * @param field     new field of study at the school, can be empty string
     * @param end       new end year of study, can be empty string
     * @return  false on error, true on success
     */
    public boolean editSchool(String school, String field, String end)
    {
        boolean edited = false;
        try
        {
            NodeList schools = doc.getElementsByTagName("school");
            String[] schoolSplit = school.split("; ", 2);
            String[] schoolSplit2 = schoolSplit[1].split(",");
            String from = schoolSplit[0].substring(2).trim();
            String city = schoolSplit2[schoolSplit2.length - 1].trim();
            String name = "";
            for (int i = 0; i < schoolSplit2.length - 1; i++)
            {
                if (!name.equals(""))
                    name += ",";
                name += schoolSplit2[i];
            }
            for (int i = 0; i < schools.getLength(); i++)
            {
                Element s = (Element) schools.item(i);
                Element schoolName = (Element) s.getElementsByTagName("name").item(0);
                if (name.equals(schoolName.getTextContent()))
                {
                    Element schoolCity = (Element) s.getElementsByTagName("city").item(0);
                    if (city.equals(schoolCity.getTextContent()))
                    {
                        Element start = (Element) s.getElementsByTagName("start").item(0);
                        if (from.equals(start.getTextContent()))
                        {
                            if (!"".equals(end))
                            {
                                Element to = (Element) s.getElementsByTagName("end").item(0);
                                if (to == null)
                                {
                                    to = doc.createElement("end");
                                    s.appendChild(to);
                                }
                                System.err.println(end);
                                to.setTextContent(end);
                            }
                            if (!"".equals(field))
                            {
                                Element specialization = (Element) s.getElementsByTagName("specialization").item(0);
                                if (specialization == null)
                                {
                                    specialization = doc.createElement("specialization");
                                    s.appendChild(specialization);
                                }
                                specialization.setTextContent(field);
                            }
                            edited = true;
                            break;
                        }
                    }
                }

            }
        } catch (DOMException e)
        {
            log.error("Error DOM: ", e.getMessage());
            return false;
        }
        if (!edited)
        {
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
        } catch (IOException | TransformerException ex) {
            log.error("Error serialize: ", ex.getMessage());
            return false;
        }
        
        return true;
    }
}
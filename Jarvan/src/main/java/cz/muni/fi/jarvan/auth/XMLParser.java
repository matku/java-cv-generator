package cz.muni.fi.jarvan.auth;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            Element idElement = (Element) emails.item(i);
            emailList.add(emails.item(i).getTextContent());
        }
        
        return emailList;
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
}

package cz.muni.fi.jarvan.auth;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author martin
 */
public class User {
    
    private final static Logger log = LoggerFactory.getLogger(User.class);
    
    private String username;
    private String password;
    private String email;
    private Document doc;

    public User()
    {
        
        try
        {
            String path = getPath();
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList userList = doc.getElementsByTagName("user");
        }
        catch(Exception error)
        {
            System.out.println("ERROR: " + error);
        }
        
        
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = md5(password);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
    public boolean userAlreadyExists()
    {
        return false;
    }
    
    public boolean emailAlreadyExists()
    {
        return false;
    }
    
    public static String md5(String input) {
         
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            log.error("MD5 method failed, no idea why\n Error: " + e );
        }
        return md5;
    }
       
    public String getPath()
    {
        String oldPath = User.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
        String newPath = "";
        
        String[] parts = oldPath.split("/");
        int counter = 0;
        
        while(!parts[counter].equals("target"))
        {
            newPath += parts[counter] + "/";
            counter++;
        }
        
        newPath += "src/main/resources/config/users.xml";
        
        return newPath;
    }
}

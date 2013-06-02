package cz.muni.fi.jarvan.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Class for user
 * Methods: setters and getters for username, password, email
 * 
 * @author martin
 */
public class User {
    
    private final static Logger log = LoggerFactory.getLogger(User.class);
    
    private String username;
    private String password;
    private String email;
    public static XMLParser parser;
    public static XMLWriter writer;

    public User()
    {
        
        if (parser == null) parser = new XMLParser(Settings.getPathUser());
        if (writer == null) writer = new XMLWriter(Settings.getPathUser());
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
    
    /**
     * Checks if typed username already exists in xml file
     * Username is unique
     * @return true if user exists
     */
    public boolean userAlreadyExists()
    {       
        List<String> dbUsers = parser.getUsers();
        
        for(int i=0; i<dbUsers.size(); i++)
        {
            if(dbUsers.get(i).equals(this.username))
                return true;
        }
        
        return false;
    }
    
    /**
     * Checks if typed email already exists in xml file
     * Email is unique
     * @return true if email exists
     */
    public boolean emailAlreadyExists()
    {
        List<String> dbEmail = parser.getEmails();
        
        for(int i=0; i<dbEmail.size(); i++)
        {
            
            if(dbEmail.get(i).equals(this.email))
                return true;
        }
        
        return false;
    }
    
    /**
     * Method for hashing password into MD5
     * @return String hash
     */
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
}

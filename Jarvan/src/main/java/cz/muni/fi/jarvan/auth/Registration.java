
package cz.muni.fi.jarvan.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for register users, checks if username and email already exists
 * TO DO: store user data to XML
 * @author martin
 */
public class Registration {
    
    private final static Logger log = LoggerFactory.getLogger(Registration.class);
    
    User user;

    public Registration(String username, String email, String password) {
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
    }
    
    /*
     * Method for registering user and checking username and email if already
     * exists
     */
    public boolean tryRegister()
    {
       
        if (user.userAlreadyExists())
        {
            log.error("Could not register. User already in use.");
            return false;
        }
        
        if (user.emailAlreadyExists())
        {
            log.error("Could not register. E-mail already in use.");
            return false;
        }
        
        //save user to XML
        User.writer.createUser(user);
        
        
        return true;
    }
    
}

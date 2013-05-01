/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.jarvan.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
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
    
    public boolean tryRegister()
    {
        if (user.userAlreadyExists() || user.emailAlreadyExists())
        {
            log.error("Could not register. User or email already in use.");
            return false;
        }
        
        //save user to XML
        
        
        
        return true;
    }
    
}

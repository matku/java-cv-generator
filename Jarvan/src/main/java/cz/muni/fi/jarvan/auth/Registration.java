/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.jarvan.auth;

/**
 *
 * @author martin
 */
public class Registration {
    
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
            return false;
        }
        
        //save user to XML
        
        
        
        return true;
    }
    
}

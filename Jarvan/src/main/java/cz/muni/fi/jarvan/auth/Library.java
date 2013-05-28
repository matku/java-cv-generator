
package cz.muni.fi.jarvan.auth;

import java.io.File;

/**
 * Class for storing CV like database, will parse library.xml
 * @author martin
 */
public class Library {
    
    /**
     * Checks if library.xml exists, if not it will create file.
     */
    public Library()
    {
        if(!(new File(Settings.getPathLibrary()).exists()))
        {
            
        }
    }
}

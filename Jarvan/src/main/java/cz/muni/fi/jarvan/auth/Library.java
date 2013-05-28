
package cz.muni.fi.jarvan.auth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for storing CV like database, will parse library.xml
 * @author martin
 */
public class Library {
    
    private final static Logger log = LoggerFactory.getLogger(Library.class);
    private String libraryName;
    
    /**
     * Checks if library.xml exists, if not it will create it.
     */
    public Library()
    {
        
        this.libraryName = Settings.getPathLibrary();
        
        File library = new File(this.libraryName);
        if(!library.exists())
        {
            log.info("File library doesn't exist. Creating ...");
            
            try
            {
                library.createNewFile();
                FileWriter fw = new FileWriter(library);    
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
                bw.write("<library>");
                bw.write("</library>");
                bw.close();
            }
            catch (IOException e)
            {
                log.error("File library.xml not created" + e.getMessage());
            }
        }
    }
}

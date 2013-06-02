
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
    public Library() throws IOException
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
                throw new RuntimeException("Library error", e);
            }
        }
    }
    
    /**
     * Method for adding concrete user his new CvName (into library.xml)
     * @param username
     * @param cvName
     * @return True  - CV was added successfully
     * @return False - error occurred, see log file
     */
    public boolean addCV(String username, String cvName)
    {
        if(username == null)
        {
            log.error("Username is null");
            return false;
        }
        
        if(username.equals(""))
        {
            log.error("Username is empty");
            return false;
        }
        
        if(cvName == null)
        {
            log.error("CvName is null");
            return false;
        }
        
        if(cvName.equals(""))
        {
            log.error("CvName is empty");
            return false;
        }
        
        XMLWriter newCV = new XMLWriter(Settings.getPathLibrary());
        if(newCV.addCvToXml(username, cvName))
        {
            log.info("New CV " + cvName + "added to library.xml");
            return true;
        }
        return false;
    }
}


package cz.muni.fi.jarvan.auth;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for getPath metdhods
 * @author martin
 */
public class Settings {
    
    private final static Logger log = LoggerFactory.getLogger(Settings.class);
    public Settings(){}

    /**
     * Method for finding correct path to users.xml
     * @return path
     */
    public static String getPathUser()
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

        newPath += "config/users.xml";

        return newPath;
    }

    /**
     * Method for finding correct path to users.xml
     * @return path
     */
    public static String getPathLibrary()
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

        newPath += "config/library.xml";

        return newPath;
    }

    /**
    * Method for finding correct path to xsl and xsd files
    *
    * @return path
    */
    public static String getPathManager()
    {
           String oldPath = User.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
           String newPath = "";

           String[] parts = oldPath.split("/");
           int counter = 0;

           while (!parts[counter].equals("target")) {
                   newPath += parts[counter] + "/";
                   counter++;
           }

           newPath += "config/";

           return newPath;
   }

    /**
    * Method for finding correct path to xsl and xsd files
    *
    * @return path
    */
    public static String getPathCV()
    {
           String oldPath = User.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
           String newPath = "";

           String[] parts = oldPath.split("/");
           int counter = 0;

           while (!parts[counter].equals("target")) {
                   newPath += parts[counter] + "/";
                   counter++;
           }

           newPath += "config/cv/";

           return newPath;
   }


    /**
     * executes command in terminal
     * @param cmd command to be executed
     * @return success
     */
     public static boolean executeCmd(String cmd) {
            String output = null;

            try {
                    Process p = Runtime.getRuntime().exec(cmd);

                    return true;
            } catch (IOException e) {
                    log.error("Command error execution: ", e.getMessage());
            }

            return false;
    }

}

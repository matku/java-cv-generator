
package cz.muni.fi.jarvan.auth;

/**
 * Class for getPath metdhods
 * @author martin
 */
public class Settings {
    
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
        
        newPath += "src/main/resources/config/users.xml";
        
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
        
        newPath += "src/main/resources/config/library.xml";
        
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

           newPath += "src/main/resources/config/";

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

           newPath += "src/main/resources/config/cv/";

           return newPath;
   }
    
}

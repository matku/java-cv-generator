
package cz.muni.fi.jarvan.auth;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author martin
 */
public class Show {
    
    private final static Logger log = LoggerFactory.getLogger(User.class);
    
     /***
     * Method for selecting PDF FILENAME, LANGUAGE and DOWNLOAD LINK
     * @param mail
     * @return list of data
     */
    public List<String> getList(String mail)
    {
        String prefix = "{*" + mail + ".pdf}";
            List<String> cvList = new ArrayList<>();

            Path dir = Paths.get(Settings.getPathCV());
            try 
            {
                DirectoryStream<Path> ds = Files.newDirectoryStream(dir, prefix);
                for (Path entry: ds)
                {
                    String tmpFileName = entry.toString();
                    String newPath = tmpFileName.substring(0, tmpFileName.length() - 4) + ".xml";

                    XMLParser tmpParser = new XMLParser(newPath);
                    String tmpLang = tmpParser.getLang();

                    // add to list FILENAME, LANG, FILEPATH
                    cvList.add("<tr><td>" + entry.getFileName().toString() + "</td>");
                    cvList.add("<td>" + tmpLang + "</td>");
                    cvList.add("<td><a href=file:///" + entry.toString() + " target=\"_blank\">"+ "Stiahnut" + "</a></td></tr>");
                }
                
                return cvList;
                
            } catch (IOException e) {
                log.error("Files not found in cv " + e.getMessage());
            }
            
            return cvList;
    }
    
    
    /***
     * Method for selecting PDF FILENAME, LANGUAGE and DOWNLOAD LINK
     * @param mail
     * @return list of data
     */
    public List<String> showList(String mail)
    {
        String prefix = "{*" + mail + ".pdf}";
            List<String> cvList = new ArrayList<>();

            Path dir = Paths.get(Settings.getPathCV());
            try 
            {
                DirectoryStream<Path> ds = Files.newDirectoryStream(dir, prefix);
                for (Path entry: ds)
                {
                    String tmpFileName = entry.toString();
                    String newPath = tmpFileName.substring(0, tmpFileName.length() - 4) + ".xml";

                    XMLParser tmpParser = new XMLParser(newPath);
                    String tmpLang = tmpParser.getLang();

                    // add to list FILENAME, LANG, FILEPATH
                    cvList.add("<tr><td>" + entry.getFileName().toString() + "</td>");
                    cvList.add("<td>" + tmpLang + "</td>");
                    cvList.add("<td><a href=\"http://localhost:8084/JarvanUpdate/auth/cv/edit\">Upravit</td>");
                    cvList.add("<td><a href=\"http://localhost:8084/JarvanUpdate/auth/cv/delete\">Zmazat</td>");
                }
                
                return cvList;
                
            } catch (IOException e) {
                log.error("Files not found in cv " + e.getMessage());
            }
            
            return cvList;
    }
    
}

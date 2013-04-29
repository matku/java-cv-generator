/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.web;

/**
 *
 * @author martin
 * Class for getting content of page in multi-language
 */
public class WebContent {
    
    private String lang;
    
    public WebContent(String lang)
    {
        
        if(!isSupported(lang))
            System.out.println("Language not supported !!!");
        
        // Set language DEFAULT: EN 
        if(lang.equals(" "))
            this.lang = "en";
        else
            this.lang = "lang";
        
        //load xml file for searching in it
    }
    
    /*
     * Checks if language is supported
     */
    private boolean isSupported(String lang)
    {
        return (lang.equals("sk") || lang.equals("en") || lang.equals("de") || lang.equals(" "));
    }
    
    public String getTitle()
    {
        return "Jarvan";
    }
    
    public String getAbout()
    {
        return "";
    }
    
    public String getContact()
    {
        return "";
    }
    
    public String getMainMenu()
    {
        return "";
    }
    
    public String getMenu()
    {
        //return array{home[0], about[1]. contact[2]}
        return "";
    }
    
}

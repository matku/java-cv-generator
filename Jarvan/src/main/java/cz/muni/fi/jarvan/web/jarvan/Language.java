package cz.muni.fi.jarvan.web.jarvan;



/**
 * Class Language represents used language as standard ISO code
 * 
 * @author Martin Otahal
 */
public class Language
{
    private String lang = null;

    
    /**
     * Method parseURL parses given URL and searches it for translation language
     * and sets it
     * - if no language is given, default english is set
     * 
     * @param URL   URL to search for language
     */
    public void parseURL(StringBuffer URL)
    {
        String newURL = URL.toString();
        String[] parsedURL = newURL.split("/", 5);
        String[] newLang = parsedURL[4].split("/");
        try
        {
            newLang[1].length();
        } catch (IndexOutOfBoundsException e)
        {
            this.lang = "en";
        }
        if (newLang[0].equals("sk"))
        {
            this.lang = "sk";
        }
        if (newLang[0].equals("cz"))
        {
            this.lang = "cz";
        }
        if (newLang[0].equals("en"))
        {
            this.lang = "en";
        }    
    }
    
    
    /**
     * method getLanguage returns ISO standard code for language used (example: en)
     * 
     * @param URL   StringBuffer with whole web page URL 
     * @return      language code extracted from the URL
     * @throws LanguageException        when given language is not supported
     */
    public String getLanguage(StringBuffer URL) throws LanguageException
    {
        this.lang = null;
        this.parseURL(URL);
        if (this.lang == null)
        {
            throw new LanguageException("Not supported language.\n");
        }
        return this.lang;
    }
    
    
    /**
     * method getBaseUrl returns base web page url for css to function properly
     * Default: http://localhost:8080/JarvanUpdate
     * 
     * edit code if you feel like Yoda himself!
     * 
     * @return      base web page url
     */
    public String getBaseUrl()
    {
        // in case edit else dont do it
        return "http://localhost:8080/JarvanUpdate";
    }
}
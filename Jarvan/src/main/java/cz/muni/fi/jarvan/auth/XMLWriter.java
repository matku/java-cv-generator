package cz.muni.fi.jarvan.auth;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;



public class XMLWriter {

    private File xmlFile;
    private Document doc;
    private final static Logger log = LoggerFactory.getLogger(User.class);
    
    public XMLWriter(String path)
    {
        try
        {
            this.xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(xmlFile);
        }
        catch(SAXException | IOException | ParserConfigurationException error)
        {
            log.error("XMLDocument error: " + error);
        }
    }
    
    public boolean createUser(User usr)
    {
        try
        {
            Element user = doc.createElement("user");
            user.setAttribute("id", usr.getUsername());
            Element username = doc.createElement("username");
            username.setTextContent(usr.getUsername());
            Element password = doc.createElement("password");
            password.setTextContent(usr.getPassword());
            Element email = doc.createElement("email");
            email.setTextContent(usr.getEmail());
            user.appendChild(username);
            user.appendChild(password);
            user.appendChild(email);

            NodeList users = doc.getElementsByTagName("users");
            users.item(0).appendChild(user);
            System.err.println("Should be registered.");
        } catch (DOMException ex)
        {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        try {
            this.serializetoXML(xmlFile);
            System.err.println("Should be serialized");
        } catch (IOException | TransformerException ex) {
            log.error("Error: ", ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean changePassword(String username, String newPassword)
    {
        
        return true;
    }
    
    public void serializetoXML(URI output)
            throws IOException, TransformerConfigurationException, TransformerException {
        // Vytvorime instanci tovarni tridy
        TransformerFactory factory = TransformerFactory.newInstance();
        // Pomoci tovarni tridy ziskame instanci tzv. kopirovaciho transformeru
        Transformer transformer = factory.newTransformer();
        // Vstupem transformace bude dokument v pameti
        DOMSource source = new DOMSource(doc);
        // Vystupem transformace bude vystupni soubor
        StreamResult result = new StreamResult(output.toString());
        // Provedeme transformaci
        transformer.transform(source, result);
    }

    public void serializetoXML(File output) throws IOException,
            TransformerException {
        serializetoXML(output.toURI());
    }
}
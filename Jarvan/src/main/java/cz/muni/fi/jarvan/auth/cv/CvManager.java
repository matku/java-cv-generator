package cz.muni.fi.jarvan.auth.cv;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validation, transformation, compilation
 * @author matuss
 */
public class CvManager {

	private String xml;
	private String xmlSchema;
	private final static Logger log = LoggerFactory.getLogger(CvManager.class);

	public CvManager()
	{
		this.xmlSchema = getPath() + "cvValidator.xsd";
	}

	private boolean validate() throws IOException, SAXException
	{
		Source schemaFile = new StreamSource(new File(this.xmlSchema));
        Source cvXml = new StreamSource(new File(this.xml));

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();

        try{
            validator.validate(cvXml);
            return true;
        }
        catch (SAXException e)
        {
			log.error("XML is invalid: " + e.getLocalizedMessage());
			return false;
        }
	}

	public void generate(String xml) throws IOException, SAXException
	{
		this.xml = xml;
		if (!validate())
			throw new IllegalArgumentException("XML file is invalid.");

		// transform

		// compile
		//String cmd = "pdflatex " + " file";
		//executeCmd(cmd);
	}

	public boolean executeCmd(String cmd)
	{
		String output = null;

		 try
		 {
            Process p = Runtime.getRuntime().exec(cmd);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
			// Here is the standard output of the command:
            while ((output = stdInput.readLine()) != null)
			{
                System.out.println(output);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((output = stdError.readLine()) != null)
			{
                System.out.println(output);
            }

			return true;
        }
        catch (IOException e)
		{
            log.error("Command error execution: ", e.getMessage());
        }

		return false;
	}

	/**
     * Method for finding correct path to xsl and xsd files
     * @return path
     */
    private String getPath()
    {
        String oldPath = CvManager.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
        String newPath = "";

        String[] parts = oldPath.split("/");
        int counter = 0;

        while(!parts[counter].equals("target"))
        {
            newPath += parts[counter] + "/";
            counter++;
        }

        newPath += "src/main/resources/config/";

        return newPath;
    }
}

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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.muni.fi.jarvan.auth.Settings;

/**
 * Validation, transformation, compilation
 *
 * @author matuss
 */
public class CvManager {

	private String xml;
	private String xmlSchema;
	private String xslTransform;
	private final static Logger log = LoggerFactory.getLogger(CvManager.class);

	public CvManager() {
		this.xmlSchema = Settings.getPathManager() + "cvValidator.xsd";
		this.xslTransform = Settings.getPathManager() + "xml2tex.xsl";
	}

	/**
	 * method to validate xml file against xml schema
	 * @return success
	 */
	private boolean validate() {
		try {
			Source schemaFile = new StreamSource(new File(this.xmlSchema));
			Source cvXml = new StreamSource(new File(this.xml));

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();


			validator.validate(cvXml);
			return true;
		} catch (SAXException e) {
			log.error("XML is invalid: " + e.getLocalizedMessage());
			return false;
		} catch (IOException e) {
			log.error("File opening error: " + e.getLocalizedMessage());
			return false;
		}
	}

	/**
	 * method to generate pdf from xml file
	 * @param xml xml file name in resources folder
	 * @return pdf file name
	 * @throws IllegalArgumentException when given xml doesnt validate against schema
	 */
	public String generate(String xml) throws IllegalArgumentException {
		this.xml = xml;
		if (!validate()) {
			throw new IllegalArgumentException("XML file is invalid.");
		}

		String outputFile = xml.substring(0, xml.length() - 4) + ".tex";

		// transform
		TransformerFactory factory = TransformerFactory.newInstance();
		StreamSource xslt = new StreamSource(
				new File(this.xslTransform));
		try {
			Transformer transformer = factory.newTransformer(xslt);
			StreamSource xmlFile = new StreamSource(new File(Settings.getPathManager() + this.xml));
			StreamResult tex = new StreamResult(new File(Settings.getPathManager() + outputFile));
			transformer.transform(xmlFile, tex);
		} catch (TransformerConfigurationException ex) {
			System.out.println("config exception: " + ex.getMessage());
		} catch (TransformerException ex) {
			System.out.println("transform exception: " + ex.getMessage());
		}
		// TODO podat dalej spravu o chybe

		// compile
		String cmd = "pdflatex " + outputFile + " && rm -f " + outputFile;
		executeCmd(cmd);
		return outputFile.substring(0, outputFile.length() - 4) + ".pdf";
	}

	/**
	 * executes command in terminal
	 * @param cmd command to be executed
	 * @return success
	 */
	private boolean executeCmd(String cmd) {
		String output = null;

		try {
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			// Here is the standard output of the command:
			while ((output = stdInput.readLine()) != null) {
				System.out.println(output);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((output = stdError.readLine()) != null) {
				System.out.println(output);
			}

			return true;
		} catch (IOException e) {
			log.error("Command error execution: ", e.getMessage());
		}

		return false;
	}

	
}

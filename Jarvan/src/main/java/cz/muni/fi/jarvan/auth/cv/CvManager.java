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
import javax.sound.midi.Soundbank;

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
	 * @param xml xml file name to convert
	 * @param outputFile file name of output with extension
	 * @throws XmlException when given xml doesnt validate against schema or something veery bad happens
	 */
	public void generate(String xml, String outputFile) throws XmlException {
		this.xml = xml;
		if (!validate()) {
			throw new XmlException("XML file is invalid.");
		}

		outputFile = outputFile.substring(0, outputFile.length() - 4) + ".tex";


		// transform
		TransformerFactory factory = TransformerFactory.newInstance();
		StreamSource xslt = new StreamSource(
				new File(this.xslTransform));
		try {
			Transformer transformer = factory.newTransformer(xslt);
			StreamSource xmlFile = new StreamSource(new File(this.xml));
			StreamResult tex = new StreamResult(new File(outputFile));
			transformer.transform(xmlFile, tex);
		} catch (TransformerConfigurationException ex) {
			System.out.println("config exception: " + ex.getMessage());
			throw new XmlException("Bad thing happened: " + ex.getMessage());
		} catch (TransformerException ex) {
			System.out.println("transform exception: " + ex.getMessage());
			throw new XmlException("Bad thing happened: " + ex.getMessage());
		}

		// compile
		String cmd = Settings.getPathCV() + "compileCv.sh " + outputFile;
		System.out.println("Executing: '" + cmd + "'");
		if (!Settings.executeCmd(cmd))
			throw new XmlException("Unable to run compilation.");
	}
}

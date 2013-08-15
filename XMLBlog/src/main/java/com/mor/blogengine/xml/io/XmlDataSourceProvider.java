
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xml.io;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.common.PropertiesUserObject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

/**
 *
 * @author laurent
 */
public class XmlDataSourceProvider extends PropertiesUserObject {

    public static final String JAXP_SCHEMA_LANGUAGE
            = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    public static final String JAXP_SCHEMA_SOURCE
            = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    private Document mProvidedDoc = null;
    private URL xml = null;

    public XmlDataSourceProvider(Properties p) {
        mConfig = p;
    }

    /**
     * <pre>
     * Provide a Dom4J document from various source type
     *
     *
     * @return built document
     * </pre>
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws DocumentException
     * @throws IOException
     */
    public Document provide()
            throws SAXException, ParserConfigurationException, DocumentException, IOException {

        xml = getXml();
        URL schema = getSchema();

        if ((schema != null) && (xml != null)) {

            mProvidedDoc = createReaderAgainstSchema(schema).read(xml);

            return mProvidedDoc;
        }

        return null;
    }

    /**
     * Setup validation features for given reader
     *
     * @param pReader given reader
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @param schemaSource
     * @return
     */
    private SAXReader createReaderAgainstSchema(URL schemaSource)
            throws SAXException, ParserConfigurationException, IOException {

        trace("Parser created OK");
        SAXReader reader = new SAXReader();
        trace("reader created OK");
        trace("set reader properties");
        // set the validation feature to true to report validation errors
        reader.setFeature("http://xml.org/sax/features/validation", true);
        // set the validation/schema feature to true to report validation errors against a schema
        reader.setFeature("http://apache.org/xml/features/validation/schema", true);
        //set the validation/schema-full-checking feature to true to enable full schema, grammar-constraint checking
        reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
        trace("returning reader...");
        return reader;

    }

    /**
     * writes in a file
     *
     * @param document DOM model to write in
     * @param pOutputFile output file
     * @throws java.io.IOException
     * @param pDocument
     */
    boolean write(Document pDocument) {

        try {
            OutputFormat format = new OutputFormat();
            format.setEncoding(getFileEncoding());
            XMLWriter writer;
            writer = new XMLWriter(
                    new OutputStreamWriter(new FileOutputStream(xml.getFile()), Charset.forName(getFileEncoding())));

            writer.write(pDocument);
            writer.close();
            return true;
        }
        catch (IOException ex) {
            trace("Error saving file..." + ex);
            return false;
        }

    }

    /**
     * Saves changes in dom structure
     *
     * @return true if saved correctly
     */
    public boolean saveChanges() {

        if (isPersistingNecessary()) {

            trace("saving...");
            return write(mProvidedDoc);
        } else {
            trace("if persisting was set to on changes would be saved to file");
            return true;
        }

    }
}
//~ Formatted by Jindent --- http://www.jindent.com


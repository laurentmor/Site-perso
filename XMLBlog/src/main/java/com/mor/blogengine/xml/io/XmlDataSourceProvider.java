
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xml.io;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.common.PropertiesUserObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import org.xml.sax.SAXException;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;

import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author laurent
 */
public class XmlDataSourceProvider extends PropertiesUserObject {

    public static final String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    public static final String JAXP_SCHEMA_SOURCE =
            "http://java.sun.com/xml/jaxp/properties/schemaSource";

    public XmlDataSourceProvider(Properties p) {
        mConfig = p;
    }

    /**
     * <pre>
     * Provide a Dom4J document from various source type
     * @param xml
     * @param schema
     *
     * @return built document
     * </pre>
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws DocumentException
     * @throws IOException
     */
    public Document provide(URL xml, URL schema)
            throws SAXException, ParserConfigurationException, DocumentException, IOException {
        SAXReader reader = null;

        if ((schema != null) && (xml != null)) {
            reader = createReaderAgainstSchema(schema);

            return reader.read(xml);
        }

        return null;
    }

    /**
     * Setup validation features for given reader
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
}
//~ Formatted by Jindent --- http://www.jindent.com



/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.test.data;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.xml.io.XmlDataSourceProvider;

import junit.framework.TestCase;

import org.dom4j.Document;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;

import java.net.URL;

import java.util.Properties;

/**
 *
 * @author laurent
 */
public abstract class AbstractInMemoryXmlDataSourceTestCase extends TestCase {

    /**
     *
     */
    protected Document document = null;

    /**
     *
     */
    protected Properties properties = null;

    /**
     *
     */
    protected URL schema = null;

    /**
     *
     */
    protected URL xml = null;

    /**
     *
     * @param testName
     */
    protected AbstractInMemoryXmlDataSourceTestCase(String testName) {
        super(testName);
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        properties = new Properties();

        String file = getClass().getResource("/blog-resources/config.xml").getFile();

        properties.loadFromXML(new FileInputStream(file));
        schema   = getClass().getResource(properties.getProperty("datasource.xsd"));
        xml      = getClass().getResource(properties.getProperty("datasource.xml"));
        document = new XmlDataSourceProvider(properties).provide(xml, schema);
        
    
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

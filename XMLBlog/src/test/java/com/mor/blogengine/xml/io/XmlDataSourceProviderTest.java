
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.xml.io;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;

//~--- JDK imports ------------------------------------------------------------

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.CDATA;
import org.dom4j.tree.DefaultCDATA;

/**
 *
 * @author laurent
 */
public class XmlDataSourceProviderTest extends AbstractInMemoryXmlDataSourceTestCase {


    /**
     *
     * @param testName
     */
    public XmlDataSourceProviderTest(String testName) {
        super(testName);
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of provide method, of class XmlDataSourceProvider.
     */
    public void testProvide() {
        try {
            assertNotNull(new XmlDataSourceProvider(properties).provide(xml, schema));
            assertNull(new XmlDataSourceProvider(properties).provide(null, schema));
            assertNull(new XmlDataSourceProvider(properties).provide(xml, null));
            assertNull(new XmlDataSourceProvider(properties).provide(null, null));
            
        } catch (Exception ex) {
            Logger.getLogger(XmlDataSourceProviderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

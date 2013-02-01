
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;
import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import java.net.URL;

/**
 *
 * @author laurent
 */
public class SearchEngineConfiguratorTest extends AbstractInMemoryXmlDataSourceTestCase {

    /**
     *
     * @param testName
     */
    public SearchEngineConfiguratorTest(String testName) {
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
     * Test of findContent method, of class SearchEngineConfigurator.
     */
    public void testFindContent() {
        boolean result = false;

        try {
            System.out.println("findContent");

            SearchEngineConfigurator configurator = new SearchEngineConfigurator(properties);

            result = (configurator.findContent("/Blog//Category") == null);
            assertFalse(result);
            assertNull(configurator.findContent("222/$"));
        } catch (Exception e) {
            assertFalse(false);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

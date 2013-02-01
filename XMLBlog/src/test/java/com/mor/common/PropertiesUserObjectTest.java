
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.common;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.xpath.XPathVersion;
import junit.framework.TestCase;

import org.springframework.test.AssertThrows;

//~--- JDK imports ------------------------------------------------------------

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author laurent
 */
public class PropertiesUserObjectTest extends TestCase {
    PropertiesUserObjectImpl instance   = null;
    Properties               properties = null;

    /**
     *
     * @param testName
     */
    public PropertiesUserObjectTest(String testName) {
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
     * Test of trace method, of class PropertiesUserObject.
     */
    public void testTrace() {
        System.out.println("trace");

        Level  level        = Level.SEVERE;
        String traceMessage = "allo";

        properties = new Properties();

        // properties.put("application.mode", "test");
        // properties.put("application.debug", "On");
        instance         = new PropertiesUserObjectImpl();
        instance.mConfig = properties;

        // instance.trace(level, traceMessage);
        new AssertThrows(MissingResourceException.class) {
            @Override
            public void test() throws Exception {
                instance.trace("nb");
            }
        }.runTest();
    }

    /**
     * Test of isPersistingNecessary method, of class PropertiesUserObject.
     */
    public void testIsPersistingNecessary() {
        System.out.println("isPersistingNecessary");
        instance   = new PropertiesUserObjectImpl();
        properties = new Properties();
        properties.put("application.mode", "test");
        properties.put("application.debug", "On");
        instance.mConfig = properties;

        boolean result = instance.isPersistingNecessary();

        assertFalse(result);
        properties.put("application.mode", "prod");
        result = instance.isPersistingNecessary();
        assertTrue(result);
        properties.remove("application.mode");
    }

    /**
     * Test of getSupportedXpathVersion method, of class PropertiesUserObject.
     */
    public void testGetSupportedXpathVersion() {
        System.out.println("getSupportedXpathVersion");
        instance   = new PropertiesUserObjectImpl();
        properties = new Properties();
        properties.put("application.mode", "test");
        properties.put("application.debug", "On");
        properties.put("xpath.version", "1.0F");
        instance.mConfig = properties;

        XPathVersion result = instance.getSupportedXpathVersion();

        assertEquals(result, XPathVersion.typeLess);
    }

    /**
     * Test of isDebugPropertiesSet method, of class PropertiesUserObject.
     */
    public void testIsDebugPropertiesSet() {
        System.out.println("isDebugPropertiesSet");
        instance   = new PropertiesUserObjectImpl();
        properties = new Properties();
        properties.put("application.mode", "test");
        properties.put("application.debug", "On");
        properties.put("xpath.version", "1.0F");
        instance.mConfig = properties;
        assertTrue(instance.isDebugPropertiesSet());
        properties.remove("application.mode");
        assertFalse(instance.isDebugPropertiesSet());
    }

    private class PropertiesUserObjectImpl extends PropertiesUserObject {}
}


//~ Formatted by Jindent --- http://www.jindent.com

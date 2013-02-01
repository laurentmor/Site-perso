
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.text;

//~--- non-JDK imports --------------------------------------------------------

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author laurent
 */
public class StringUtilTest extends TestCase {

    /**
     *
     * @param testName
     */
    public StringUtilTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(StringUtilTest.class);

        return suite;
    }

    /**
     *
     * @throws java.lang.Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     *
     * @throws java.lang.Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     *
     */
    public void testCreate() {
        StringUtil s = new StringUtil();

        assertNotNull(s);
    }

    /**
     * Test of toHTMLString method, of class StringUtil.
     */
    public void testToHTMLString_String() {
        System.out.println("toHTMLString");

        String escaped = StringUtil.toHTMLString("Helas");
        String expect  = "Helas".trim();

        assertTrue(expect.equalsIgnoreCase(escaped));
    }

    /**
     * Test of toStringHTML method, of class StringUtil.
     */
    public void testToStringHTML() {
        System.out.println("toStringHTML");

        String S         = "Helas";
        String expResult = "Helas";
        String result    = StringUtil.toStringHTML(S);

        assertEquals(expResult, result);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

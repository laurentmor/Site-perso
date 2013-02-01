
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.exception;

//~--- non-JDK imports --------------------------------------------------------

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author laurent
 */
public class ExceptionSuite extends TestCase {

    /**
     *
     * @param testName
     */
    public ExceptionSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(java.util.ResourceBundle.getBundle("messages_fr").getString("EXCEPTIONSUITE"));

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
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.exception;

//~--- non-JDK imports --------------------------------------------------------

import junit.framework.TestCase;

/**
 *
 * @author laurent
 */
public class ElementExistingExceptionTest extends TestCase {

    /**
     *
     * @param testName
     */
    public ElementExistingExceptionTest(String testName) {
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
     *
     */
    public void testException() {
        ElementExistingException elementExistingException = new ElementExistingException();

        assertNotNull(elementExistingException);
        elementExistingException = new ElementExistingException("xxx");
        assertNotNull(elementExistingException);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

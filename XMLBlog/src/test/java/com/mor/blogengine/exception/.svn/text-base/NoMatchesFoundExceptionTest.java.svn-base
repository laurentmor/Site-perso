
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
public class NoMatchesFoundExceptionTest extends TestCase {

    /**
     *
     * @param testName
     */
    public NoMatchesFoundExceptionTest(String testName) {
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
        boolean thrown = false;

        try {
            if ("a".equals("a")) {
                throw new NoMatchesFoundException("a", true);
            }

            if ("a".equals("a")) {
                throw new NoMatchesFoundException("a", false);
            }
        } catch (NoMatchesFoundException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    /**
     *
     */
    public void testException_() {
        boolean thrown = false;

        try {
            if ("a".equals("a")) {
                throw new NoMatchesFoundException("a", false);
            }
        } catch (NoMatchesFoundException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

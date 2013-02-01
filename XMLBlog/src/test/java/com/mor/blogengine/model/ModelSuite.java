
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author laurent
 */
public class ModelSuite extends TestCase {

    /**
     *
     * @param testName
     */
    public ModelSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(java.util.ResourceBundle.getBundle("messages_fr").getString("MODELSUITE"));

        suite.addTest(BlogCommentTest.suite());
        suite.addTest(BlogEntryTest.suite());
        suite.addTest(BlogCategoryTest.suite());

        return suite;
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
}


//~ Formatted by Jindent --- http://www.jindent.com

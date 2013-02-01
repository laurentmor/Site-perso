
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.controllers.ControllersSuite;

//import com.mor.blogengine.dao.DaoSuite;
import com.mor.blogengine.exception.ExceptionSuite;
import com.mor.blogengine.text.TextSuite;
import com.mor.blogengine.xml.XmlSuite;
import com.mor.blogengine.xpath.XpathSuite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author laurent
 */
public class BlogengineSuite extends TestCase {

    /**
     *
     * @param testName
     */
    public BlogengineSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(java.util.ResourceBundle.getBundle("messages_fr").getString("BLOGENGINESUITE"));

        suite.addTest(BlogCommentTest.suite());
        suite.addTest(TextSuite.suite());
        suite.addTest(XpathSuite.suite());
        suite.addTest(ControllersSuite.suite());
        suite.addTest(BlogEntryTest.suite());
        suite.addTest(BlogCategoryTest.suite());
        suite.addTest(XmlSuite.suite());

        // suite.addTest(DaoSuite.suite());
        suite.addTest(ExceptionSuite.suite());

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

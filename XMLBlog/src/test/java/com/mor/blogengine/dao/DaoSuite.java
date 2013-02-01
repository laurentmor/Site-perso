
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.lang.BitField;

/**
 *
 * @author laurent
 */
public class DaoSuite extends TestCase {

    /**
     *
     * @param testName
     */
    public DaoSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(java.util.ResourceBundle.getBundle("messages_fr").getString("DAOSUITE"));

        suite.addTest(BlogCategoryRepositoryTest.suite());
        suite.addTest(BlogCommentRepositoryTest.suite());
        suite.addTest(BlogEntryRepositoryTest.suite());

        BitField field = new BitField(11111);

        System.out.println(field);

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


/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author laurent
 */
public class BlogCommentTest extends AbstractInMemoryXmlDataSourceTestCase {

    /**
     *
     * @param testName
     */
    public BlogCommentTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BlogCommentTest.class);

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
     * Test of hashCode method, of class BlogComment.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        BlogComment comment = new BlogComment(properties.getProperty("comment.with.knownID.entryID"),
                                  properties.getProperty("comment.with.knownID.date"),
                                  properties.getProperty("comment.with.knownID.author"),
                                  properties.getProperty("comment.with.knownID.page"),
                                  properties.getProperty("comment.with.knownID.text"));

        assertEquals(properties.getProperty("comment.with.knownID.ID"), comment.getEntityID());
    }

    /**
     * Test of equals method, of class BlogComment.
     */
    public void testEquals() {
        System.out.println("equals");

        BlogComment comment = new BlogComment(properties.getProperty("comment.with.knownID.entryID"),
                                  properties.getProperty("comment.with.knownID.date"),
                                  properties.getProperty("comment.with.knownID.author"),
                                  properties.getProperty("comment.with.knownID.page"),
                                  properties.getProperty("comment.with.knownID.text"));
        BlogComment comment2 = new BlogComment(properties.getProperty("comment.with.knownID.entryID"),
                                   properties.getProperty("comment.with.knownID.date"),
                                   properties.getProperty("comment.with.knownID.author"),
                                   properties.getProperty("comment.with.knownID.page"),
                                   properties.getProperty("comment.with.knownID.text"));

        assertTrue(comment.equals(comment2));
        assertFalse(comment.equals(null));
        assertFalse(comment.equals(""));
        assertFalse(comment.equals(new BlogComment(properties.getProperty("comment.with.knownID.entryID"),
                properties.getProperty("comment.with.knownID.date"),
                properties.getProperty("comment.with.knownID.author"),
                properties.getProperty("comment.with.knownID.page"), null)));
        assertFalse(comment.equals(new BlogComment(properties.getProperty("comment.with.knownID.entryID"), null,
                properties.getProperty("comment.with.knownID.author"),
                properties.getProperty("comment.with.knownID.page"),
                properties.getProperty("comment.with.knownID.text"))));

        BlogComment comment3 = new BlogComment(properties.getProperty("comment.with.knownID.entryID"),
                                   properties.getProperty("comment.with.knownID.date"),
                                   properties.getProperty("comment.with.knownID.author") + "1",
                                   properties.getProperty("comment.with.knownID.page"),
                                   properties.getProperty("comment.with.knownID.text"));
        BlogComment comment4 = new BlogComment(properties.getProperty("comment.with.knownID.entryID") + "1",
                                   properties.getProperty("comment.with.knownID.date") + "1",
                                   properties.getProperty("comment.with.knownID.author") + "1",
                                   properties.getProperty("comment.with.knownID.page") + "1",
                                   properties.getProperty("comment.with.knownID.text") + "1");

        assertFalse(comment.equals(comment3));
        assertFalse(comment.equals(comment4));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

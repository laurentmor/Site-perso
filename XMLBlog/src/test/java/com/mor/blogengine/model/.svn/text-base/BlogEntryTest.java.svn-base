
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.dom4j.tree.DefaultElement;

/**
 *
 * @author laurent
 */
public class BlogEntryTest extends AbstractInMemoryXmlDataSourceTestCase {
    private BlogEntry instance = null;

    /**
     *
     * @param testName
     */
    public BlogEntryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BlogEntryTest.class);

        return suite;
    }

    /**
     *
     * @throws java.lang.Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new BlogEntry(properties.getProperty("entry.with.resume.date"),
                                 properties.getProperty("entry.with.resume.text"),
                                 properties.getProperty("entry.with.resume.catID"),
                                 properties.getProperty("entry.with.resume.resume"),
                                 properties.getProperty("entry.with.resume.allowComment"));
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
     * Test of toElement method, of class BlogEntry.
     */
    public void testToElement() {
        DefaultElement exp = new DefaultElement("Entry");

        // exp.addAttribute("@ID", "ID_265186057");
        DefaultElement text = new DefaultElement("Text");

        text.addText("EntryText");

        DefaultElement resume = new DefaultElement("Resume");

        resume.addText("resume");
        exp.addAttribute("@date", "2009-09-07");
        exp.addAttribute("@categoryID", "ID_435");
        exp.addAttribute("@allowComments", "true");
        exp.add(text);
        exp.add(resume);

        // assertEquals(exp, instance.toElement());
        // assertT
        boolean thrown = false;

        try {
            BlogEntry entry = new BlogEntry(resume);
        } catch (java.util.NoSuchElementException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    /**
     * Test of getCatHash method, of class BlogEntry.
     */
    public void testGetCatHash() {
        System.out.println("getCatHash");

        String expResult = properties.getProperty("entry.with.resume.catID");
        String result    = instance.getCatID();

        assertEquals(expResult, result);
    }

    /**
     * Test of getTexte method, of class BlogEntry.
     */
    public void testGetTexte() {
        System.out.println("getTexte");

        String expResult = properties.getProperty("entry.with.resume.text");
        String result    = instance.getTexte();

        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class BlogEntry.
     */
    public void testGetDate() {
        System.out.println("getDate");

        String expResult = properties.getProperty("entry.with.resume.date");
        String result    = instance.getDate();

        assertEquals(expResult, result);
    }

    /**
     * Test of getAllowComments method, of class BlogEntry.
     */
    public void testGetAllowComments() {
        System.out.println("getAllowComments");

        String expResult = properties.getProperty("entry.with.resume.allowComment");
        String result    = instance.getAllowComments();

        assertEquals(expResult, result);
    }

    /**
     * Test of canComment method, of class BlogEntry.
     */
    public void testCanComment_false() {
        System.out.println("canComment");

        BlogEntry ins = new BlogEntry(properties.getProperty("entry.with.resume.date"),
                                      properties.getProperty("entry.with.resume.text"),
                                      properties.getProperty("entry.with.resume.catID"),
                                      properties.getProperty("entry.with.resume.resume"),
                                      properties.getProperty("entry.with.resume.allowComment"));
        boolean expResult = true;
        boolean result    = ins.canComment();

        assertEquals(expResult, result);
        ins = new BlogEntry(properties.getProperty("entry.with.resume.nocomment.date"),
                            properties.getProperty("entry.with.resume.nocomment.text"),
                            properties.getProperty("entry.with.resume.nocomment.catID"),
                            properties.getProperty("entry.with.resume.nocomment.resume"),
                            properties.getProperty("entry.with.resume.nocomment.allowComment"));
        assertFalse(ins.canComment());
    }

    /**
     *
     */
    public void testCanComment_true() {
        BlogEntry ins = new BlogEntry(properties.getProperty("entry.with.resume.date"),
                                      properties.getProperty("entry.with.resume.text"),
                                      properties.getProperty("entry.with.resume.catID"),
                                      properties.getProperty("entry.with.resume.resume"),
                                      properties.getProperty("entry.with.resume.allowComment"));
        boolean expResult = true;
        boolean result    = ins.canComment();

        assertEquals(expResult, result);
    }

    /**
     * Test of getResume method, of class BlogEntry.
     */
    public void testGetResume() {
        System.out.println("getResume");

        String expResult = properties.getProperty("entry.with.resume.resume");
        String result    = instance.getResume();

        assertEquals(expResult, result);

        BlogEntry ins = new BlogEntry(properties.getProperty("entry.without.resume.date"),
                                      properties.getProperty("entry.without.resume.text"),
                                      properties.getProperty("entry.without.resume.catID"), null,
                                      properties.getProperty("entry.without.resume.allowComment"));

        assertEquals("No resume", ins.getResume());
        ins = new BlogEntry(properties.getProperty("entry.without.resume.date"),
                            properties.getProperty("entry.without.resume.text"),
                            properties.getProperty("entry.without.resume.catID"), "",
                            properties.getProperty("entry.without.resume.allowComment"));
        assertEquals("No resume", ins.getResume());
        ins = new BlogEntry(properties.getProperty("entry.without.resume.date"),
                            properties.getProperty("entry.without.resume.text"),
                            properties.getProperty("entry.without.resume.catID"), "Pommes",
                            properties.getProperty("entry.without.resume.allowComment"));
        assertEquals(ins.getResume(), "Pommes");
    }

    /**
     * Test of hashCode method, of class BlogEntry.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        String result = instance.getEntityID();

        assertNotNull(result);
        assertTrue((result.startsWith("ID_")));
    }

    /**
     * Test of equals method, of class BlogEntry.
     */
    public void testEquals() {
        System.out.println("equals");

        Object obj = new BlogEntry(properties.getProperty("entry.with.resume.date"),
                                   properties.getProperty("entry.with.resume.text"),
                                   properties.getProperty("entry.with.resume.catID"),
                                   properties.getProperty("entry.with.resume.resume"),
                                   properties.getProperty("entry.with.resume.allowComment"));
        BlogEntry obj2 = new BlogEntry(properties.getProperty("entry.with.resume.date"),
                                       properties.getProperty("entry.with.resume.text"),
                                       properties.getProperty("entry.with.resume.catID"),
                                       properties.getProperty("entry.with.resume.resume"),
                                       properties.getProperty("entry.with.resume.allowComment"));
        Object obj3 = "sss";
        Object obj4 = new BlogEntry(properties.getProperty("entry.with.resume.notext.date"), null,
                                    properties.getProperty("entry.with.resume.notext.catID"),
                                    properties.getProperty("entry.with.resume.notext.resume"),
                                    properties.getProperty("entry.with.resume.notext.allowComment"));
        Object obj5 = new BlogEntry(properties.getProperty("entry.with.resume.notextnocat.date"), null, null,
                                    properties.getProperty("entry.with.resume.notextnocat.resume"),
                                    properties.getProperty("entry.with.resume.notextnocat.allowComment"));

//      assertFalse(instance.equals(obj));
        assertEquals(true, instance.equals(obj2));
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(obj3));
        assertFalse(instance.equals(obj4));
        assertFalse(instance.equals(obj5));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

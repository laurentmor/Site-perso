
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.xml;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author laurent
 */


public class XmlEntityFactoryTest extends TestCase {

    /**
     *
     * @param testName
     */
    public XmlEntityFactoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(XmlEntityFactoryTest.class);

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
     * Test of createEntiesMap method, of class BlogEntityFactory.
     */
    public void testCreateEntiesMapCategory() {
        System.out.println("createEntiesMap-Category");

        List<DefaultElement> pList    = new ArrayList<DefaultElement>();
        BlogCategory         category = new BlogCategory("vide", "desc");

        pList.add(category.toElement());

        BlogEntityFactory         instance  = new BlogEntityFactory();
        Map<String, BlogCategory> expResult = new HashMap<String, BlogCategory>();

        expResult.put(category.getEntityID(), category);

        Map<String, BlogCategory> result = instance.createCategoryMap(pList);

        assertEquals(1, result.size());
    }

    /**
     *
     */
    public void testCreateEntry() {
        System.out.println("createEntiesMap");

        List<DefaultElement> pList = new ArrayList<DefaultElement>();
        BlogEntry            entry = new BlogEntry("2009-11-11", "Texte", "ID_233", "Resume", "Yes");

        pList.add(entry.toElement());

        BlogEntityFactory      instance  = new BlogEntityFactory();
        Map<String, BlogEntry> expResult = new HashMap<String, BlogEntry>();

        expResult.put(entry.getEntityID(), entry);

        Map<String, BlogEntry> result = instance.createEntryMap(pList);

        assertEquals(1, result.size());
    }

    /**
     *
     */
    public void testCreateComment() {
        System.out.println("createEntiesMap");

        List<DefaultElement> pList   = new ArrayList<DefaultElement>();
        BlogComment          comment = new BlogComment("ID_444", "2009-12-23", "Bob mooner", "http://test", "Texte");

        pList.add(comment.toElement());

        BlogEntityFactory        instance  = new BlogEntityFactory();
        Map<String, BlogComment> expResult = new HashMap<String, BlogComment>();

        expResult.put(comment.getEntityID(), comment);

        Map<String, BlogComment> result = instance.createCommentMap(pList);

        assertEquals(1, result.size());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

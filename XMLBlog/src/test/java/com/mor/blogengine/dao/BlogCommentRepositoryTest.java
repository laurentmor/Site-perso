
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;
import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;
import com.mor.blogengine.xpath.SearchCriteria;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.dom4j.tree.DefaultElement;

import org.springframework.test.AssertThrows;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

/**
 *
 * @author laurent
 */
@SuppressWarnings("unchecked")
//@SuppressWarnings("deprecation")
public class BlogCommentRepositoryTest extends AbstractInMemoryXmlDataSourceTestCase {
    BlogCommentRepository instance = null;

    /**
     *
     * @param testName
     */
    public BlogCommentRepositoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BlogCommentRepositoryTest.class);

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

    /**
     * Test of add method, of class BlogCommentRepository.
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public void testAdd() throws Exception {
        System.out.println("add");
        instance = new BlogCommentRepository(properties);
        assertFalse(instance.add(null));
    }

    /**
     * Test of append method, of class BlogCommentRepository.
     * @throws Exception
     */
    
    public void testAppend() throws Exception {
        System.out.println("append");

        BlogEntry toWhat=new BlogEntry("2004-09-09", "test entry !", "ID_1222333", "feed","false" );
        BlogComment what   = new BlogComment(toWhat.getEntityID(), "2009-11-30", "author", "no page", "Hi bob!");

        //assertFalse(new BlogEntryRepository(properties).add(toWhat));
        instance = new BlogCommentRepository(properties);
        assertTrue(instance.append(what, toWhat.getEntityID()));

        

       new AssertThrows(NoMatchesFoundException.class) {

            @Override
            public void test() throws Exception {
                BlogComment what   = new BlogComment("ID_12", "2009-11-30", "author", "no page", "Hi bob!"); 
                instance.append(what, "ID_12");
            }
        }.runTest();
        
    }

    /**
     * Test of remove method, of class BlogCommentRepository.
     * @throws Exception
     */
    public void testRemove() throws Exception {
        System.out.println("remove");

        BlogComment t = new BlogComment("ID_152369776", "Mon Nov 23 00:00:00 EST 2009", "Mike", "no page", "Hi HI");

        instance = new BlogCommentRepository(properties);

        boolean expResult = false;

        //assertTrue(instance.append(t, "ID_152369776"));

        boolean result = instance.remove(t);

        assertTrue(result);
        new AssertThrows(NoMatchesFoundException.class) {
            @Override
            public void test() throws Exception {
                BlogComment t = new BlogComment("ID_1", "mon", "ccc", "xd", "Hi");

                instance.remove(t);
            }
        }.runTest();
    }

    /**
     * Test of edit method, of class BlogCommentRepository.
     * @throws Exception
     */
    public void testEdit() throws Exception {
        System.out.println("edit");
BlogComment t = new BlogComment("ID_152369776", "Mon Nov 23 00:00:00 EST 2009", "Mike", "no page", "Hi HI");
        BlogComment t2 = new BlogComment("ID_152369776", "Mon Nov 23 00:00:00 EST 2009", "Mike Smith", "no page", "Hi HI");

        instance = new BlogCommentRepository( properties);
        

        boolean expResult = true;
        boolean result    = instance.edit(t, t2);

        assertEquals(expResult, result);
        new AssertThrows(NoMatchesFoundException.class) {
            @Override
            public void test() throws Exception {
                instance.edit(new BlogComment("ID_1", "Date", "aut", "none", "text"),
                              new BlogComment("ID_1", "mon", "ccc", "xd", "Hi"));
            }
        }.runTest();
    }

    /**
     * Test of getElementsForCriteria method, of class BlogCommentRepository.
     * @throws Exception
     */
    public void testGetElementsForCriteria() throws Exception {
        System.out.println("getElementsForCriteria");

        try {
            SearchCriteria searchParam = SearchCriteria.CATEGORY;
            String         paramValue  = "";

            instance = new BlogCommentRepository(properties);

            List<DefaultElement> expResult = null;
            List<DefaultElement> result    = instance.getElementsForCriteria(searchParam, paramValue);
        } catch (NoMatchesFoundException nmfe) {
            assertFalse(false);
        }
    }

    /**
     *
     */
    public void testAppendParam() {
        new AssertThrows(UnsupportedOperationException.class) {
            @Override
            public void test() throws Exception {
                BlogComment t = new BlogComment("ID_1", "mon", "ccc", "xd", "Hi");

                instance = new BlogCommentRepository(properties);
                instance.append(t);
            }
        }.runTest();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

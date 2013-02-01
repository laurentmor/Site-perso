
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogEntry;
import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;
import com.mor.blogengine.xpath.SearchCriteria;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

import org.springframework.test.AssertThrows;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laurent
 */
public class BlogEntryRepositoryTest extends AbstractInMemoryXmlDataSourceTestCase {
    BlogEntryRepository repo = null;

    /**
     *
     * @param testName
     */
    public BlogEntryRepositoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BlogEntryRepositoryTest.class);

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
     * Test of add method, of class BlogEntryRepository.
     */
    public void testAdd() {
        try {
            System.out.println("add");

            BlogEntry           t         = new BlogEntry("2009-11-30", "Simple entry", "ID_233", "feed", "true");
          repo = new BlogEntryRepository(properties);
            boolean             expResult = true;
            boolean             result    = repo.add(t);

            
            
            
            assertEquals(expResult, result);
            new AssertThrows(ElementExistingException.class) {

                @Override
                public void test() throws Exception {
                   BlogEntry entry=new BlogEntry("2004-09-09", "test entry !", "ID_1222333", "feed","false" );
                    repo.add(entry);    
                }
            }.runTest();
            
        } catch (ElementExistingException ex) {
            //Never raised but thrown in previous test case 
        } catch (DocumentException ex) {
            assertFalse(false);
        }
    }

    /**
     * Test of append method, of class BlogCategoryRepository.
     */

    // TODO modify if we intend to introduce sub category concep
    public void testAppend() {
        try {
            BlogEntry t2 = new BlogEntry("2009-11-23", "Simpl entry", "ID_233", "feed", "Yes");

            repo = new BlogEntryRepository(properties);
            assertFalse(repo.append(null, "this"));
            assertFalse(repo.append(t2));
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementExistingException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of remove method, of class BlogEntryRepository.
     */
    public void testRemove() {
        try {
            System.out.println("remove");

            BlogEntry t  = new BlogEntry("2009-11-30", "Simple entry", "ID_233", "feed", "Yes");
            BlogEntry t2 = new BlogEntry("2009-11-23", "Simpl entry", "ID_233", "feed", "Yes");

            repo = new BlogEntryRepository(properties);
            repo.add(t);
            assertTrue(repo.remove(t));
            assertFalse(repo.remove(t2));
        } catch (NoMatchesFoundException ex) {
            assertFalse(false);
        } catch (ElementExistingException ex) {
            assertFalse(false);
        } catch (DocumentException ex) {
            Logger.getLogger(BlogEntryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of edit method, of class BlogEntryRepository.
     * @throws Exception
     */
    public void testEdit() throws Exception {
        System.out.println("edit");

        BlogEntry entry=new BlogEntry("2004-09-09", "test entry !", "ID_1222333", "feed","false" );
            BlogEntry entry2=new BlogEntry("2004-09-09", "test entry edited !", "ID_1222333", "feed","false" );


        repo = new BlogEntryRepository(properties);
        
        assertTrue(repo.edit(entry, entry2));
        
    }

    /**
     * Test of getElementsForCriteria method, of class BlogEntryRepository.
     * @throws Exception
     */
    public void testGetElementsForCriteria() throws Exception {
        System.out.println("getElementsForCriteria");

        SearchCriteria      searchParam = SearchCriteria.ALL;
        String              paramValue  = null;
        BlogEntryRepository instance    = new BlogEntryRepository(properties);
        BlogEntry           t           = new BlogEntry("2009-11-30", "Simple entry", "ID_233", "feed", "Yes");

        instance.add(t);

        List<DefaultElement> result = instance.getElementsForCriteria(searchParam, paramValue);

        assertEquals(1, result.size());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

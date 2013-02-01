
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogCategory;
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
public class BlogCategoryRepositoryTest extends AbstractInMemoryXmlDataSourceTestCase {
    private IRepository<BlogCategory, DefaultElement, SearchCriteria, DocumentException> repo = null;

    /**
     *
     * @param testName
     */
    public BlogCategoryRepositoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BlogCategoryRepositoryTest.class);

        return suite;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repo = new BlogCategoryRepository(properties);
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
    public void testCreate() {
        assertNotNull(new BlogCategoryRepository(properties));
    }

    /**
     * Test of add method, of class BlogCategoryRepository.
     */
    public void testAdd() {
        System.out.println("add");

        BlogCategory t           = new BlogCategory("Jokes1232");
        BlogCategory t2          = new BlogCategory("added");
        boolean      addedFirst  = false;
        boolean      addedSecond = false;

        try {
            addedFirst  = repo.add(t);
            addedSecond = repo.add(t2);
        } catch (ElementExistingException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertTrue(addedFirst);
        assertFalse(addedSecond);
    }

    /**
     * Test of append method, of class BlogCategoryRepository.
     */

    // TODO modify if we intend to introduce sub category concep
    public void testAppend() {
        try {
            assertFalse(repo.append(null, "this"));
            assertFalse(repo.append(new BlogCategory("test")));
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementExistingException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of remove method, of class BlogCategoryRepository.
     */
    public void testRemove() {
        try {
            System.out.println("remove");

            BlogCategory t = new BlogCategory("Jokes");
            boolean      b = repo.add(t);

            assertTrue(b);
            assertTrue(repo.remove(t));
            new AssertThrows(NoMatchesFoundException.class) {

                @Override
                public void test() throws Exception {
                    repo.remove(new BlogCategory("123"));
                }
            }.runTest();
            
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementExistingException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of edit method, of class BlogCategoryRepository.
     */
    public void testEdit() {
        boolean result = false;

        try {
            System.out.println("edit");

            BlogCategory what = new BlogCategory("added", "");

            try {
                result = repo.edit(what, new BlogCategory("To edit", "Desc1"));
            } catch (NoMatchesFoundException ex) {
                Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ElementExistingException ex) {
                Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
            }

            assertTrue("Failed ", result);
            new AssertThrows(NoMatchesFoundException.class) {
                @Override
                public void test() throws Exception {
                    BlogCategory what = new BlogCategory("remove", "");

                    repo.edit(new BlogCategory("allo"), what);
                    System.out.println("assertedThrows");
                }
            }.runTest();
        } catch (DocumentException ex) {
            Logger.getLogger(BlogCategoryRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*new AssertThrows(ElementExistingException.class) {
            @Override
            public void test() throws Exception {
                BlogCategory what  = new BlogCategory("remove", "");
                BlogCategory what2 = new BlogCategory("added");

                repo.add(what);
               
                repo.edit(what, what2);
                System.out.println("assertedThrows");
            }
        }.runTest();*/
    }

    /**
     * Test of getElementsForCriteria method, of class BlogCategoryRepository.
     */
    public void testGetElementsForCriteria() {
        try {
            System.out.println("getElementsForCriteria");

            SearchCriteria       searchParam = SearchCriteria.ALL;
            String               paramValue  = null;
            List<DefaultElement> result      = repo.getElementsForCriteria(searchParam, paramValue);

            assertNotNull(result);
            assertNull(repo.getElementsForCriteria(SearchCriteria.SINGLE, "ID_123"));
        } catch (NoMatchesFoundException ex) {}
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

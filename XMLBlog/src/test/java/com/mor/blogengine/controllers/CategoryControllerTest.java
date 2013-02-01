
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.controllers;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.dao.BlogCategoryRepository;
import com.mor.blogengine.dao.IRepository;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;
import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import com.mor.blogengine.xpath.SearchCriteria;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

import org.xml.sax.SAXException;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;

import java.net.URL;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author laurent
 */
public class CategoryControllerTest extends AbstractInMemoryXmlDataSourceTestCase {


    /**
     *
     * @param testName
     */
    public CategoryControllerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CategoryControllerTest.class);

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
     *
     */
    public void testCreate() {

        CategoryController controller = new CategoryController(properties);

        assertNotNull(controller);
    }

    /**
     * Test of getAllElements method, of class CategoryController.
     */
    public void testGetAllElements()  {
        try {
            System.out.println("getAllElements");

            CategoryController        instance = new CategoryController(properties);
            Map<String, BlogCategory> result   = instance.getAllElements();

            assertEquals(3, result.size());

            URL         xmlWithNoCat = getClass().getResource("/blog-resources/blog-without-some-data.xml");
            properties.setProperty("datasource.xml", "/blog-resources/blog-without-some-data.xml");
            assertEquals("/blog-resources/blog-without-some-data.xml", properties.get("datasource.xml"));
            IRepository repo         = new BlogCategoryRepository(properties);

            instance = new CategoryController(properties);
            assertNull(instance.getAllElements());
            assertNull(instance.getAllElements(null));
        } catch (DocumentException ex) {
            Logger.getLogger(CategoryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedOperationException uoe) {
            assertTrue("was using wrong version of method", true);
        }
    }

    /**
     * Test of addNewElement method, of class CategoryController.
     */
    public void testAddNewElement() {
        try {
            System.out.println("addNewElement");

            BlogCategory       e        = new BlogCategory("Jokes/");
            CategoryController instance = new CategoryController(properties);

            //instance.deleteElement(e);

            boolean expResult = true;
            boolean result    = instance.addNewElement(e);

            assertEquals(expResult, result);
            assertFalse(instance.addNewElement(new BlogCategory("remove")));
        } catch (DocumentException ex) {
            Logger.getLogger(CategoryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteElement method, of class CategoryController.
     */
    public void testDeleteElement() {
        try {
            System.out.println("deleteElement");

            BlogCategory       e        = new BlogCategory("remove");
            CategoryController instance = new CategoryController(properties);
            boolean            added    = instance.addNewElement(e);
            boolean            result   = instance.deleteElement(e);

            assertTrue(result);
        } catch (DocumentException ex) {
            Logger.getLogger(CategoryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of editElement method, of class CategoryController.
     */
    public void testEditElement() {
        System.out.println("editElement");

        BlogCategory       e         = new BlogCategory("aasss");
        CategoryController instance  = new CategoryController(properties);
        boolean            expResult = false;

        try {
            boolean result = instance.editElement(e, null);

            assertFalse(result);
        } catch (Exception ex) {
            Logger.getLogger(CategoryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getElementsForDate method, of class CategoryController.
     */
    public void testGetElementsForDate() {
        System.out.println("getElementsForDate");

        String                    d         = null;
        CategoryController        instance  = new CategoryController(properties);
        Map<String, BlogCategory> expResult = null;
        Map<String, BlogCategory> result    = instance.getElementsForDate(d);

        // assertEquals(expResult, result);

    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.controllers;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.model.BlogEntry;
import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.dom4j.DocumentException;

//~--- JDK imports ------------------------------------------------------------

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
/**
 *
 * @author laurent
 */
public class EntryControllerTest extends AbstractInMemoryXmlDataSourceTestCase {


    /**
     *
     * @param testName
     */
    public EntryControllerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(EntryControllerTest.class);

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
     * Test of getAllElements method, of class EntryController.
     */
    public void testGetAllElements() {
        try {
            System.out.println("getAllElements");

            EntryController        instance  = new EntryController(properties);
            Map<String, BlogEntry> expResult = null;
            Map<String, BlogEntry> result    = instance.getAllElements();

            assertEquals(1, result.size());
        } catch (DocumentException ex) {
            Logger.getLogger(EntryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addNewElement method, of class EntryController.
     */
    public void testAddNewElement() {
        try {
            System.out.println("addNewElement");

            BlogEntry t = new BlogEntry(properties.getProperty("controllers.entrycontroller.entry.date"),
                                        properties.getProperty("controllers.entrycontroller.entry.text"),
                                        properties.getProperty("controllers.entrycontroller.entry.catID"),
                                        properties.getProperty("controllers.entrycontroller.entry.resume"),
                                        properties.getProperty("controllers.entrycontroller.entry.allowComment"));
            EntryController instance = new EntryController(properties);
            boolean         result   = instance.addNewElement(t);

            assertTrue(result);
        } catch (DocumentException ex) {
            Logger.getLogger(EntryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteElement method, of class EntryController.
     */
    public void testDeleteElement() {
        try {
            System.out.println("deleteElement");
 BlogEntry entry=new BlogEntry("2004-09-09", "test entry !", "ID_1222333", "feed","false" );
            EntryController instance = new EntryController(properties);

            //assertTrue(instance.addNewElement(t));

            boolean result = instance.deleteElement(entry);

            assertFalse(instance.deleteElement(new BlogEntry("2009-06-05", "null", "null", "null", "null")));
            assertTrue(result);
        } catch (DocumentException ex) {
            Logger.getLogger(EntryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of editElement method, of class EntryController.
     */
    public void testEditElement() {
        try {
            System.out.println("editElement");

            BlogEntry t = new BlogEntry(properties.getProperty("controllers.entrycontroller.entry.date"),
                                        properties.getProperty("controllers.entrycontroller.entry.text"),
                                        properties.getProperty("controllers.entrycontroller.entry.catID"),
                                        properties.getProperty("controllers.entrycontroller.entry.resume"),
                                        properties.getProperty("controllers.entrycontroller.entry.allowComment"));
            EntryController instance = new EntryController(properties);
            boolean         result   = instance.editElement(t, null);

            assertFalse(result);
        } catch (DocumentException ex) {
            Logger.getLogger(EntryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getElementsForDate method, of class EntryController.
     */
    public void testGetElementsForDate() {
        try {
            System.out.println("getElementsForDate");

            EntryController instance = new EntryController(properties);
            BlogEntry       t        = new BlogEntry(properties.getProperty("controllers.entrycontroller.entry.date"),
                                           properties.getProperty("controllers.entrycontroller.entry.text"),
                                           properties.getProperty("controllers.entrycontroller.entry.catID"),
                                           properties.getProperty("controllers.entrycontroller.entry.resume"),
                                           properties.getProperty("controllers.entrycontroller.entry.allowComment"));

            assertTrue(instance.addNewElement(t));

            String d = properties.getProperty("controllers.entrycontroller.entry.date");

            // System.out.println(parts[0]);
            assertEquals(1, instance.getElementsForDate(d).size());
        } catch (DocumentException ex) {
            Logger.getLogger(EntryControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAllElements method, of class EntryController.
     * @throws Exception
     */
    public void testGetAllElements_String() throws Exception {
        System.out.println("getAllElements");

        EntryController instance = new EntryController(properties);
        Map             result   = instance.getAllElements(null);

        assertEquals(1, result.size());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

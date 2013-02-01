
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.controllers;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;


//~--- JDK imports ------------------------------------------------------------
import java.net.URL;

import java.util.Map;

/**
 *
 * @author Lsurent
 */
public class CommentControllerTest extends AbstractInMemoryXmlDataSourceTestCase {
    private CommentController                                                           controller = null;
   

    /**
     *
     * @param testName
     */
    public CommentControllerTest(String testName) {
        super(testName);
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        controller = new CommentController(properties);
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
     * Test of getAllElements method, of class CommentController.
     */
    public void testGetAllElements_0args() {
        System.out.println("getAllElements");

        Map expResult = null;
        Map result    = controller.getAllElements();

        assertEquals(expResult, result);
    }

    /**
     * Test of addNewElement method, of class CommentController.
     * @throws Exception
     */
    public void testAddNewElement() throws Exception {
        System.out.println("addNewElement");

        BlogComment e = new BlogComment(properties.getProperty("comment.with.knownID.entryID"),
                                        properties.getProperty("comment.with.knownID.date"),
                                        properties.getProperty("comment.with.knownID.author"),
                                        properties.getProperty("comment.with.knownID.page"),
                                        properties.getProperty("comment.with.knownID.text"));
        boolean result = controller.addNewElement(e);

        assertTrue(result);
    }

    /**
     * Test of deleteElement method, of class CommentController.
     * @throws Exception
     */
    public void testDeleteElement() throws Exception {
        System.out.println("deleteElement");

        BlogComment e = new BlogComment("ID_152369776",
                                        "Mon Nov 23 00:00:00 EST 2009",
                                        "Mike",
                                        "no page",
                                        "Hi HI");
        
         boolean result = controller.deleteElement(e);
        assertTrue(result);
    }

    /**
     * Test of editElement method, of class CommentController.
     * @throws Exception
     */
    public void testEditElement() throws Exception {
        System.out.println("editElement");

        BlogComment what =new BlogComment("ID_152369776",
                                        "Mon Nov 23 00:00:00 EST 2009",
                                        "Mike",
                                        "no page",
                                        "Hi HI");
        BlogComment with = new BlogComment("ID_152369776",
                                        "Mon Nov 23 00:00:00 EST 2009",
                                        "Mike",
                                        "no page",
                                        "Hi HI mister");
        // assertTrue(controller.addNewElement(with));
        boolean expResult = true;
        boolean result    = controller.editElement(what, with);

        assertEquals(expResult, result);
    }

    /**
     * Test of getElementsForDate method, of class CommentController.
     */
    public void testGetElementsForDate() {
        System.out.println("getElementsForDate");

        String d      = properties.getProperty("comment.with.knownID.date");
        Map    result = controller.getElementsForDate(d);

//      assertEquals(1, result.size());
    }

    /**
     * Test of getAllElements method, of class CommentController.
     * @throws Exception
     */
    public void testGetAllElements_String() throws Exception {
        System.out.println("getAllElements");

        String parentID = properties.getProperty("comment.with.knownID.entryID");
        Map    result   = controller.getAllElements(parentID);

//      assertEquals(1, result.size());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

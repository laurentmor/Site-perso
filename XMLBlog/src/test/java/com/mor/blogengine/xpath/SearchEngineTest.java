
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;
import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;
import java.net.URL;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

/**
 *
 * @author laurent
 */
public class SearchEngineTest extends AbstractInMemoryXmlDataSourceTestCase {

    /**
     *
     * @param testName
     */
    public SearchEngineTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SearchEngineTest.class);

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
     * Test of geInstanceForDoc method, of class SearchEngine.
     */
    public void testGeInstanceForDoc() {
        System.out.println("geInstanceForDoc");

        SearchEngine result = new SearchEngine(properties);

        assertNotNull(result);
    }

    /**
     * Test of getCategories method, of class SearchEngine.
     */
    public void testGetCategories() {
        System.out.println("getCategories");

        SearchEngine instance = new SearchEngine(properties);
        List         result   = instance.getElementsForCriteria("Category", SearchCriteria.ALL, null);

        assertNotNull(result);

        boolean sizeInRange = (result.size() == 3);

        assertTrue(sizeInRange);
    }

    /**
     * Test of getEntriesForCategory method, of class SearchEngine.
     */
    public void testGetEntriesForCategory() {
        System.out.println("getEntriesForCategory");

        String       pCatID   = properties.getProperty("search.entry.for.cat");
        SearchEngine instance = new SearchEngine(properties);
        List         result   = instance.getElementsForCriteria("Entry", SearchCriteria.CATEGORY, pCatID);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test of getEntriesforDate method, of class SearchEngine.
     */
    public void testGetEntriesforDate() {
        System.out.println("getEntriesforDate");

        String         pDate    = properties.getProperty("search.entry.for.date");
        SearchEngine   instance = new SearchEngine(properties);
        DefaultElement result   = instance.getElementsForCriteria("Entry", SearchCriteria.DATE, pDate).get(0);

        assertNotNull(result);
    }

    /**
     * Test of getSingleElement method, of class SearchEngine.
     */
    public void testGetSingleElement() {
        System.out.println("getSingleElement");

        SearchEngine   instance = new SearchEngine(properties);
        DefaultElement result   = instance.getElementsForCriteria("Entry", SearchCriteria.SINGLE,
                                      properties.getProperty("search.entry.for.ID")).get(0);

        assertNotNull(result);
        assertEquals(properties.getProperty("search.entry.for.date"), result.valueOf("@date"));
        assertNull(instance.getElementsForCriteria("Entry", SearchCriteria.SINGLE,
                properties.getProperty("search.entry.for.nonexistantID")));
        assertNull(instance.getElementsForCriteria(null, SearchCriteria.ALL, null));
    }

    /**
     * Test of getComentsForEntry method, of class SearchEngine.
     */
    public void testGetComentsForEntry() {
        System.out.println("getComentsForEntry");

        SearchEngine instance = new SearchEngine(properties);
        List         result   = instance.getElementsForCriteria("Comment", SearchCriteria.BY_ENTRY_ID,
                                    properties.getProperty("search.entry.for.ID"));

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test of getEntries method, of class SearchEngine.
     */
    public void testGetEntries() {
        System.out.println("getEntries");

        SearchEngine instance = new SearchEngine(properties);
        List         result   = instance.getElementsForCriteria("Entry", SearchCriteria.ALL, null);

        assertEquals(1, result.size());
    }

    /**
     * Test of init method, of class SearchEngine.
     */
    @org.junit.Test
    public void testInit() {
        System.out.println("init");
        Properties config = null;
        SearchEngine instance = null;
        instance.init(config);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElementsForCriteria method, of class SearchEngine.
     */
    @org.junit.Test
    public void testGetElementsForCriteria() {
        System.out.println("getElementsForCriteria");
        String elementType = "";
        SearchCriteria criteria = null;
        String criteriaValue = "";
        SearchEngine instance = null;
        List expResult = null;
        List result = instance.getElementsForCriteria(elementType, criteria, criteriaValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

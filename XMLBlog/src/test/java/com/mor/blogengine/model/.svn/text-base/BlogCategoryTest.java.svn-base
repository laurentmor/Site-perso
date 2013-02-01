
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
public class BlogCategoryTest extends AbstractInMemoryXmlDataSourceTestCase {

    /**
     *
     * @param testName
     */
    public BlogCategoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BlogCategoryTest.class);

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
     * Test of getCatName method, of class BlogCategory.
     */
    public void testGetCatName() {
        System.out.println("[TEST:Classe BlogCategory.getCatName()]");

        BlogCategory instance =
            new BlogCategory(properties.getProperty("category.without.discription.name.no.spec.char"));

        assertEquals(instance.getCatName(), properties.getProperty("category.without.discription.name.no.spec.char"));
        instance = new BlogCategory(properties.getProperty("category.without.discription.name.spec.char"));
        assertEquals(instance.getCatName(),
                     properties.getProperty("category.without.discription.name.spec.char.expected") + "&eacute;");
    }

    /**
     * Test of compareTo method, of class BlogCategory.
     */
    public void testCompareTo() {
        System.out.println("compareTo");

        Object sameCat = new BlogCategory(properties.getProperty("category.with.description.name"),
                                          properties.getProperty("category.with.description.description"));
        Object notSameCat = new BlogCategory(properties.getProperty("category.with.description.notSameCat.name"),
                                properties.getProperty("category.with.description.notSameCat.description"));
        BlogCategory instance = new BlogCategory(properties.getProperty("category.with.description.name"),
                                    properties.getProperty("category.with.description.description"));
        int expResult = 1;
        int result    = instance.compareTo(sameCat);

        assertEquals(expResult, result);
        assertEquals(-1, instance.compareTo(notSameCat));
        assertEquals(instance.getCatName(), ((BlogCategory) sameCat).getCatName());

        // assertFalse(instance.getCatName().equalsIgnoreCase("cccvv"));
        // assertTrue(instance.getCatName().equalsIgnoreCase("JAVA"));
        assertEquals(-1, ((BlogCategory) sameCat).compareTo(notSameCat));
    }

    /**
     * Test of getDescription method, of class BlogCategory.
     */
    public void testGetDescription() {
        System.out.println("getDescription");

        BlogCategory instance = new BlogCategory(properties.getProperty("category.with.description.name"),
                                    properties.getProperty("category.with.description.description"));
        String expResult = properties.getProperty("category.with.description.description");
        String result    = instance.getDescription();

        assertEquals(expResult, result);
    }

    /**
     * Test of toElement method, of class BlogCategory.
     */
    public void testToElement() {}

    /**
     * Test of hashCode method, of class BlogCategory.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        BlogCategory instance = new BlogCategory(properties.getProperty("category.with.known.hascode.name"),
                                    properties.getProperty("category.with.known.hascode.description"));
        int expResult = 22602780;
        int result    = instance.hashCode();

        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class BlogCategory.
     */
    public void testEquals() {
        System.out.println("equals");

        Object o = new BlogCategory(properties.getProperty("category.with.description.name"),
                                    properties.getProperty("category.with.description.description"));
        BlogCategory instance = new BlogCategory(properties.getProperty("category.with.description.name"),
                                    properties.getProperty("category.with.description.description"));
        boolean expResult = true;
        boolean result    = instance.equals(o);

        assertFalse(instance.equals(null));
        assertEquals(expResult, result);
        assertFalse(instance.equals(new String()));

        // eqs with null catName
        assertFalse(instance.equals(new BlogCategory(null, "")));
        assertFalse(new BlogCategory().equals(o));
        assertFalse(new BlogCategory("test").equals(o));
        assertFalse(instance.equals(new BlogCategory(null, "")));
        assertFalse(instance.equals(new BlogCategory("test")));
        assertTrue(result);
        assertFalse(new BlogCategory("test").equals(new BlogCategory("test2")));
        assertFalse(new BlogCategory("test", "d1").equals(new BlogCategory("test", "d2")));
        assertTrue(
            new BlogCategory(
                properties.getProperty("category.with.description.name"),
                properties.getProperty("category.with.description.description")).equals(
                    new BlogCategory(
                        properties.getProperty("category.with.description.name"),
                        properties.getProperty("category.with.description.description"))));
        assertFalse(
            new BlogCategory(
                properties.getProperty("category.with.description.name"),
                properties.getProperty("category.with.description.description")).equals(
                    new BlogCategory(properties.getProperty("category.with.description.name"), "description ")));
        assertFalse(
            new BlogCategory(
                properties.getProperty("category.with.description.name"),
                properties.getProperty("category.with.description.description")).equals(
                    new BlogCategory(
                        properties.getProperty("category.with.description.name") + "00087766",
                        properties.getProperty("category.with.description.description"))));
        assertFalse(
            new BlogCategory(properties.getProperty("category.with.description.name"), "desc").equals(
                new BlogCategory(properties.getProperty("category.with.description.name"), "xxx")));
    }

    /**
     *
     */
    public void testToString() {
        BlogCategory instance = new BlogCategory(properties.getProperty("category.with.description.name"),
                                    properties.getProperty("category.with.description.description"));

        assertEquals(instance.toString(), properties.getProperty("category.with.description.description"));
        assertNull(new BlogCategory("test").toString());
    }

    /**
     *
     */
    public void testEmptyCat() {
        BlogCategory bc = new BlogCategory();

        assertNotNull(bc);
    }

    /**
     * Test of formatAttributesValuesAsHTML method, of class BlogCategory.
     */
    public void testFormatAttributesValuesAsHTML() {
        System.out.println("formatAttributesValuesAsHTML");

        BlogCategory instance = new BlogCategory("école");

        instance.formatAttributesValuesAsHTML();
        assertNotNull(instance.getCatName());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

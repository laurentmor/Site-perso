
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import junit.framework.TestCase;

import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.NoSuchElementException;

/**
 *
 * @author laurent
 */
public class AbstractBlogEntityTest extends TestCase {

    /**
     *
     * @param testName
     */
    public AbstractBlogEntityTest(String testName) {
        super(testName);
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
     * Test of equals method, of class AbstractBlogEntity.
     */
    public void testEquals() {
        System.out.println("equals");

        Object             obj       = new DefaultElement("test");
        AbstractBlogEntity instance  = new AbstractBlogEntityImpl();
        boolean            expResult = true;
        boolean            result    = instance.equals(null);

        assertFalse(result);
        assertTrue(new AbstractBlogEntityImpl().equals(new AbstractBlogEntityImpl()));
        assertFalse(instance.equals(new Object()));
        assertFalse(instance.equals(null));

        AbstractBlogEntity ins = new BlogEntry();

        assertTrue(ins.equals(new BlogEntry()));
        assertFalse(ins.equals(new BlogCategory()));
    }

    /**
     * Test of getEntityID method, of class AbstractBlogEntity.
     */
    public void testGetEntityID() {
        System.out.println("getEntityID");

        AbstractBlogEntity instance  = new AbstractBlogEntityImpl();
        String             expResult = "ID_20";
        String             result    = instance.getEntityID();

        assertEquals(expResult, result);
    }

    /**
     * Test of setEntityID method, of class AbstractBlogEntity.
     */
    public void testSetEntityID() {
        System.out.println("setEntityID");

        AbstractBlogEntity instance = new AbstractBlogEntityImpl();
        DefaultElement     e        = new DefaultElement("Nom");

        e.addAttribute("ID", "ID_21");
        instance.mAassociatedElement = e;
        instance.setEntityID();
    }

    /**
     * Test of toElement method, of class AbstractBlogEntity.
     */
    public void testToElement() {
        System.out.println("toElement");

        AbstractBlogEntity instance  = new AbstractBlogEntityImpl();
        DefaultElement     expResult = new DefaultElement("test");
        DefaultElement     result    = instance.toElement();

        assertEquals(expResult.getName(), result.getName());
    }

    /**
     * Test of formatAttributesValuesAsHTML method, of class AbstractBlogEntity.
     */
    public void testFormatAttributesValuesAsHTML() {
        System.out.println("formatAttributesValuesAsHTML");

        AbstractBlogEntity instance = new AbstractBlogEntityImpl();

        instance.formatAttributesValuesAsHTML();
    }

    /**
     * Test of hashCode method, of class AbstractBlogEntity.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        AbstractBlogEntity instance  = new AbstractBlogEntityImpl();
        int                expResult = 20;
        int                result    = instance.hashCode();

        assertEquals(expResult, result);
    }

    /**
     *
     */
    public void testGetIDXml() {
        AbstractBlogEntity instance = new AbstractBlogEntityImpl(new DefaultElement("reste"));
        boolean            thrown   = false;

        try {
            String s = instance.getEntityIDInXml();
        } catch (NoSuchElementException e) {
            thrown = true;
        }

        assertTrue(thrown);

        DefaultElement de = new DefaultElement("reste");

        de.addAttribute("ID", "ID_2356");
        instance = new AbstractBlogEntityImpl(de);
        thrown   = false;

        String s = null;

        try {
            s = instance.getEntityIDInXml();
        } catch (NoSuchElementException e) {
            thrown = true;
        }

        assertFalse(thrown);
        assertEquals(s, "ID_2356");
    }

    /**
     *
     */
    class AbstractBlogEntityImpl extends AbstractBlogEntity {
        private static final long serialVersionUID = 1L;

        private AbstractBlogEntityImpl() {}

        AbstractBlogEntityImpl(DefaultElement defaultElement) {
            mAassociatedElement = defaultElement;
        }

        @Override
        public DefaultElement toElement() {
            return new DefaultElement("test");
        }

        @Override
        public void formatAttributesValuesAsHTML() {}

        @Override
        public int hashCode() {
            return 20;
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

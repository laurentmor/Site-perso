
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xml;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;
import com.mor.blogengine.test.data.AbstractInMemoryXmlDataSourceTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
/**
 *
 * @author laurent
 */
public class XmlFileManagerImplTest extends AbstractInMemoryXmlDataSourceTestCase {

    /**
     *
     * @param testName
     */
    public XmlFileManagerImplTest(String testName) {
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

    public static Test suite() {
        TestSuite suite = new TestSuite(XmlFileManagerImplTest.class);

        return suite;
    }

    /**
     * Test of getInstanceForDoc method, of class XmlFileManagerImpl.
     * @throws Exception
     */
    public void testGetInstanceForDoc() throws Exception {
        System.out.println("getInstanceForDoc");

        IXmlFileManager<DefaultElement> fileManager = XmlFileManagerImpl.getInstanceForDoc( properties);

        assertNotNull(fileManager);
        assertNull(XmlFileManagerImpl.mInstance);
        assertNotNull(XmlFileManagerImpl.getInstanceForDoc( properties));
        assertNull(XmlFileManagerImpl.getInstanceForDoc(null));
    }

    /**
     * Test of saveChanges method, of class XmlFileManagerImpl.
     * @throws Exception
     */
    public void testSaveChanges() throws Exception {
        System.out.println("saveChanges");

        XmlFileManagerImpl instance = XmlFileManagerImpl.getInstanceForDoc( properties);

        assertTrue(instance.saveChanges());
        instance.mInited = false;
        assertFalse(instance.saveChanges());
    }

    /**
     * Test of add method, of class XmlFileManagerImpl.
     * @throws Exception
     */
    public void testAdd() throws Exception {
        System.out.println("add");

        DefaultElement element = new DefaultElement("added");

        element.addAttribute("ID", "ID_320524799");
        element.addAttribute("name", "added");
        element.addAttribute("description", "");

        boolean added = XmlFileManagerImpl.getInstanceForDoc( properties).add(element);

        assertTrue(added);
    }

    /**
     * Test of remove method, of class XmlFileManagerImpl.
     * @throws Exception
     */
    public void testRemove() throws Exception {
        System.out.println("remove");

        DefaultElement element2 = new BlogCategory("remove").toElement();
        DefaultElement element3 = new BlogCategory("removeAA").toElement();

        assertTrue(XmlFileManagerImpl.getInstanceForDoc( properties).add(element2));

        boolean removedFirstPass = XmlFileManagerImpl.getInstanceForDoc( properties).remove(element2);
        boolean removedSecondPass = XmlFileManagerImpl.getInstanceForDoc( properties).remove(element3);

        assertTrue(removedFirstPass);
        assertFalse(removedSecondPass);

        ArrayList<DefaultElement> batch = new ArrayList<DefaultElement>();

        batch.add(element2);
        assertFalse(XmlFileManagerImpl.getInstanceForDoc( properties).remove(batch, "ID_123"));
        assertFalse(XmlFileManagerImpl.getInstanceForDoc( properties).remove(batch, "ID_1"));
    }

    /**
     * Test of append method, of class XmlFileManagerImpl.
     */
    public void testAppend() {
        System.out.println("append");

        BlogEntry entry = new BlogEntry("2004-09-09", "test entry !", "ID_1222333", "feed", "true");
        BlogComment comment = new BlogComment("ID_152369776", "2009-12-24", "Bill Summers", "None", "Thx!");

        try {
            //boolean added = XmlFileManagerImpl.getInstanceForDoc(properties).add(entry.toElement());

            //assertTrue(added);

            boolean appended = XmlFileManagerImpl.getInstanceForDoc( properties).append(entry.toElement(),
                    comment.toElement());

            assertTrue(appended);
        } catch (Exception ex) {
            Logger.getLogger(XmlFileManagerImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of init method, of class XmlFileManagerImpl.
     */
    public void testInit() {
        try {
            System.out.println("init");


            Properties config = properties;
            XmlFileManagerImpl instance = XmlFileManagerImpl.getInstanceForDoc( config);

            assertTrue(instance.init( config));

        } catch (DocumentException ex) {
            Logger.getLogger(XmlFileManagerImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of write method, of class XmlFileManagerImpl.
     * @throws Exception
     */
    public void testWrite() throws Exception {
        System.out.println("write");

        Document pDocument = document;
        XmlFileManagerImpl instance = XmlFileManagerImpl.getInstanceForDoc( properties);

        assertTrue(instance.write(document));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


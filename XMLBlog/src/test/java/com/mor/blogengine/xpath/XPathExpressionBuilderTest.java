/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.dom4j.tree.DefaultAttribute;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laurent
 */
public class XPathExpressionBuilderTest extends TestCase {

    static junit.framework.Test suite() {
        return new TestSuite(XPathExpressionBuilderTest.class);
    }

    public XPathExpressionBuilderTest() {
    }

    //@BeforeClass
    public static void setUpClass() throws Exception {
    }

    //@AfterClass
    public static void tearDownClass() throws Exception {
    }

    //@Before
    public void setUp() {
    }

    //@After
    public void tearDown() {
    }

    /**
     * Test of compileExpression method, of class XPathExpressionBuilder.
     */
    //@Test
    public void testCompileExpression() {
        System.out.println("compileExpression");
        List list=new ArrayList();
        List<DefaultAttribute> attributes=new ArrayList<DefaultAttribute>();
        list.add("Node1");
        attributes.add(new DefaultAttribute("att1", "val1"));
        XPathExpressionBuilder instance = new XPathExpressionBuilder("root",list , attributes, XPathVersion.typeLess);
        String expResult = "/root//Node1[@att1=val1]";
        String result = instance.compileExpression();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertAttributeListToStringList method, of class XPathExpressionBuilder.
     */
    //@Test
    public void testConvertAttributeListToStringList() {
        System.out.println("convertAttributeListToStringList");
        List<DefaultAttribute> pList = null;
        XPathExpressionBuilder instance = null;
        List expResult = null;
        List result = instance.convertAttributeListToStringList(pList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNodesToExpression method, of class XPathExpressionBuilder.
     */
    //@Test
    public void testAddNodesToExpression() {
        System.out.println("addNodesToExpression");
        boolean includePrefix = false;
        XPathExpressionBuilder instance = null;
        String expResult = "";
        String result = instance.addNodesToExpression(includePrefix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttributesToExpression method, of class XPathExpressionBuilder.
     */
    //@Test
    public void testAddAttributesToExpression() {
        System.out.println("addAttributesToExpression");
        XPathExpressionBuilder instance = null;
        String expResult = "";
        String result = instance.addAttributesToExpression();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatKeyAttributeValue method, of class XPathExpressionBuilder.
     */
    //@Test
    public void testFormatKeyAttributeValue() {
        System.out.println("formatKeyAttributeValue");
        String pKey = "";
        String pValue = "";
        XPathExpressionBuilder instance = null;
        String expResult = "";
        String result = instance.formatKeyAttributeValue(pKey, pValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

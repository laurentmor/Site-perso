/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author laurent
 */
public class XPathVersionTest {

    @Rule
    public TestName name = new TestName();

    public XPathVersionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("test : " + name.getMethodName());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of valueOf method, of class XPathVersion.
     */
    @Test
    public void testValueOf() {

        String aName = "typeLess";
        XPathVersion expResult = XPathVersion.typeLess;
        XPathVersion result = XPathVersion.valueOf(aName);
        assertEquals(expResult, result);

    }

    /**
     * Test of getVersion method, of class XPathVersion.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        XPathVersion instance = XPathVersion.typeLess;
        float expResult = 1.0F;
        float result = instance.getVersion();
        assertEquals(expResult, result, 0.0);

    }
}

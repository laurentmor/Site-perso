/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

import com.mor.blogengine.xpath.XPathExpressionBuilder;
import com.mor.blogengine.xpath.XPathVersion;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.tree.DefaultAttribute;
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
public class XPathExpressionBuilderTest {

    private List<String> nodes = null;
    private String root = null;
    private String ns = null;
    private List<DefaultAttribute> attList = null;
    @Rule
    public TestName name = new TestName();

    public XPathExpressionBuilderTest() {
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
        ns = "NS";
        root = "R1";
        nodes = new ArrayList<>();
        nodes.add("N1");
        nodes.add("N2");
        nodes.add("N3");
        attList = new ArrayList<>();
        attList.add(new DefaultAttribute("A1", "V1"));
        attList.add(new DefaultAttribute("A2", "V2"));
        attList.add(new DefaultAttribute("A3", "V3"));
    }

    @After
    public void tearDown() {
        nodes.clear();
        nodes = null;
        root = null;
        ns = null;
    }

    /**
     * Test afin d'obtenir une expression sans attributs et sans prefixe de NS
     */
    @Test
    public void testExpressionSansNSSansAttributs() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(root, nodes, XPathVersion.typeLess);
        String expected = "/R1//N1//N2//N3";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }

    /**
     * Test afin d'obtenir une expression sans attributs
     */
    @Test
    public void testExpressionAvecNSSansAttributs() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, true);
        String expected = "/NS:R1//NS:N1//NS:N2//NS:N3";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }

    @Test
    public void testExpressionAvecNSEtUnNoeud() {
        nodes.clear();
        nodes.add("N1");
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, true);
        String expected = "/NS:R1//NS:N1";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }
    
     @Test
    public void testExpressionSansNSEtUnNoeud() {
        nodes.clear();
        nodes.add("N1");
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, false);
        String expected = "/R1//N1";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }

    @Test
    public void testConvertAttributeListToStringListNonNull() {

        XPathExpressionBuilder builder = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typeLess);
        List<String> result = builder.convertAttributeListToStringList(attList);
        List<String> expected = new ArrayList<>();
        expected.add("A1='V1'");
        expected.add("A2='V2'");
        expected.add("A3='V3'");
        assertEquals(expected.get(0), result.get(0));

    }

    @Test
    public void testConvertAttributeListToStringListNull() {

        XPathExpressionBuilder builder = new XPathExpressionBuilder(root, nodes, null, XPathVersion.typeLess);

        List<String> result = builder.convertAttributeListToStringList(null);
        List<String> expected = new ArrayList<>();

        assertEquals(expected, result);

    }

    @Test
    public void testAddNodesToExpressionAvecNS() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, true);
        String result = builder.addNodesToExpression(true);
        String expected = "//NS:N1//NS:N2//NS:N3";
        assertEquals(expected, result);
    }

    @Test
    public void testAddNodesToExpressionSansNS() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, false);
        String result = builder.addNodesToExpression();
        String expected = "//N1//N2//N3";
        assertEquals(expected, result);
    }

    @Test
    public void testAddPlusieursAttributs() {

        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typeLess);
        String expResult = "[@A1='V1' and @A2='V2' and @A3='V3']";
        String result = instance.addAttributesToExpression();
        assertEquals(expResult, result);

    }

    @Test
    public void testAddUnAttribut() {
        attList.clear();
        attList.add(new DefaultAttribute("A1", "V1"));
        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typeLess);
        String expResult = "[@A1='V1']";
        String result = instance.addAttributesToExpression();
        assertEquals(expResult, result);

    }

    @Test
    public void testFormatKeyAttributeValueAvecInt() {
        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typed);
        String result = instance.formatKeyAttributeValue("K1", "3");
        String expected = "K1=xs:double(3)";
        assertEquals(expected, result);
    }

    @Test
    public void testFormatKeyAttributeValueAvecBoolean() {
        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typed);
        String result = instance.formatKeyAttributeValue("K1", "true");
        String expected = "K1=xs:boolean(true)";
        assertEquals(expected, result);
    }
}

/**
 * Copyright 2021 Laurent
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mor.blogengine.xpath;

import com.mor.blogengine.xpath.XPathExpressionBuilder;
import com.mor.blogengine.xpath.XPathVersion;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.tree.DefaultAttribute;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author laurent
 */
@DisplayName("X Path Expression Builder Test")
class XPathExpressionBuilderTest {

    private List<String> nodes = null;

    private String root = null;

    private String ns = null;

    private List<DefaultAttribute> attList = null;


    public XPathExpressionBuilderTest() {
    }

    @BeforeAll
    static void setUpClass() {
    }

    @AfterAll
    static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
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

    @AfterEach
    void tearDown() {
        nodes.clear();
        nodes = null;
        root = null;
        ns = null;
    }

    /**
     * Test afin d'obtenir une expression sans attributs et sans prefixe de NS
     */
    @Test
    @DisplayName("Test Expression Sans NS Sans Attributs")
    void testExpressionSansNSSansAttributs() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(root, nodes, XPathVersion.typeLess);
        String expected = "/R1//N1//N2//N3";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }

    /**
     * Test afin d'obtenir une expression sans attributs
     */
    @Test
    @DisplayName("Test Expression Avec NS Sans Attributs")
    void testExpressionAvecNSSansAttributs() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, true);
        String expected = "/NS:R1//NS:N1//NS:N2//NS:N3";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Expression Avec NS Et Un Noeud")
    void testExpressionAvecNSEtUnNoeud() {
        nodes.clear();
        nodes.add("N1");
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, true);
        String expected = "/NS:R1//NS:N1";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Expression Sans NS Et Un Noeud")
    void testExpressionSansNSEtUnNoeud() {
        nodes.clear();
        nodes.add("N1");
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, false);
        String expected = "/R1//N1";
        String result = builder.compileExpression();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Convert Attribute List To String List Non Null")
    void testConvertAttributeListToStringListNonNull() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typeLess);
        List<String> result = builder.convertAttributeListToStringList(attList);
        List<String> expected = new ArrayList<>();
        expected.add("A1='V1'");
        expected.add("A2='V2'");
        expected.add("A3='V3'");
        assertEquals(expected.get(0), result.get(0));
    }

    @Test
    @DisplayName("Test Convert Attribute List To String List Null")
    void testConvertAttributeListToStringListNull() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(root, nodes, null, XPathVersion.typeLess);
        List<String> result = builder.convertAttributeListToStringList(null);
        List<String> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Add Nodes To Expression Avec NS")
    void testAddNodesToExpressionAvecNS() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, true);
        String result = builder.addNodesToExpression(true);
        String expected = "//NS:N1//NS:N2//NS:N3";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Add Nodes To Expression Sans NS")
    void testAddNodesToExpressionSansNS() {
        XPathExpressionBuilder builder = new XPathExpressionBuilder(ns, root, nodes, XPathVersion.typeLess, false);
        String result = builder.addNodesToExpression();
        String expected = "//N1//N2//N3";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Add Plusieurs Attributs")
    void testAddPlusieursAttributs() {
        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typeLess);
        String expResult = "[@A1='V1' and @A2='V2' and @A3='V3']";
        String result = instance.addAttributesToExpression();
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Test Add Un Attribut")
    void testAddUnAttribut() {
        attList.clear();
        attList.add(new DefaultAttribute("A1", "V1"));
        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typeLess);
        String expResult = "[@A1='V1']";
        String result = instance.addAttributesToExpression();
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Test Format Key Attribute Value Avec Int")
    void testFormatKeyAttributeValueAvecInt() {
        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typed);
        String result = instance.formatKeyAttributeValue("K1", "3");
        String expected = "K1=xs:double(3)";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Format Key Attribute Value Avec Boolean")
    void testFormatKeyAttributeValueAvecBoolean() {
        XPathExpressionBuilder instance = new XPathExpressionBuilder(root, nodes, attList, XPathVersion.typed);
        String result = instance.formatKeyAttributeValue("K1", "true");
        String expected = "K1=xs:boolean(true)";
        assertEquals(expected, result);
    }
}

/*
 * Copyright (c) 2024
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
 *
 *
 */
package com.mor.blogengine.xpath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.tree.DefaultAttribute;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author laurent
 */
@DisplayName("XPath Expression Builder Test")
class XpathExpressionBuilderTest {

  private List<String> nodes;

  private String root;

  private String ns;

  private List<DefaultAttribute> attList;

  public XpathExpressionBuilderTest() {}

  @BeforeAll
  static void setUpClass() {}

  @AfterAll
  static void tearDownClass() {}

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

  /** No Namespace No Attributes Test. */
  @Test
  @DisplayName("No Namespace No Attributes Test")
  void noNamespaceNoAttributesTest() {
    XpathExpressionBuilder builder = new XpathExpressionBuilder(root, nodes, XpathVersion.typeLess);
    String expected = "/R1//N1//N2//N3";
    String result = builder.compileExpression();
    assertEquals(expected, result);
  }

  /** No Namespace with Attributes Test. */
  @Test
  @DisplayName("with Namespace No Attributes Test")
  void withNamespaceNoAttributesTest() {
    XpathExpressionBuilder builder =
        new XpathExpressionBuilder(ns, root, nodes, XpathVersion.typeLess, true);
    String expected = "/NS:R1//NS:N1//NS:N2//NS:N3";
    String result = builder.compileExpression();
    assertEquals(expected, result);
  }

  /** Test with Namespace and one node. */
  @Test
  @DisplayName("Test with Namespace and one node")
  void testWithNSAndANode() {
    nodes.clear();
    nodes.add("N1");
    XpathExpressionBuilder builder =
        new XpathExpressionBuilder(ns, root, nodes, XpathVersion.typeLess, true);
    String expected = "/NS:R1//NS:N1";
    String result = builder.compileExpression();
    assertEquals(expected, result);
  }

  /** Test without Namespace and one node. */
  @Test
  @DisplayName("Test without Namespace and one node")
  void testWithoutNamespaceAndOneNode() {
    nodes.clear();
    nodes.add("N1");
    XpathExpressionBuilder builder =
        new XpathExpressionBuilder(ns, root, nodes, XpathVersion.typeLess, false);
    String expected = "/R1//N1";
    String result = builder.compileExpression();
    assertEquals(expected, result);
  }

  /** Test Convert Attribute List To String List Non Null. */
  @Test
  @DisplayName("Test Convert Attribute List To String List Non Null")
  void testConvertAttributeListToStringListNonNull() {
    XpathExpressionBuilder builder =
        new XpathExpressionBuilder(root, nodes, attList, XpathVersion.typeLess);
    List<String> result = builder.convertAttributeList(attList);
    List<String> expected = new ArrayList<>();
    expected.add("A1='V1'");
    expected.add("A2='V2'");
    expected.add("A3='V3'");
    assertEquals(expected.getFirst(), result.getFirst());
  }

  /** Test Convert Attribute List To String List Null. */
  @Test
  @DisplayName("Test Convert Attribute List To String List Null")
  void testConvertAttributeListToStringListNull() {
    XpathExpressionBuilder builder =
        new XpathExpressionBuilder(root, nodes, null, XpathVersion.typeLess);
    List<String> result = builder.convertAttributeList(null);
    List<String> expected = new ArrayList<>();
    assertEquals(expected, result);
  }

  /** Test Add Nodes To Expression with NameSpace. */
  @Test
  @DisplayName("Test Add Nodes To Expression with NameSpace")
  void testAddNodesToExpressionWithNS() {
    XpathExpressionBuilder builder =
        new XpathExpressionBuilder(ns, root, nodes, XpathVersion.typeLess, true);
    String result = builder.addNodesToExpression(true);
    String expected = "//NS:N1//NS:N2//NS:N3";
    assertEquals(expected, result);
  }

  /** Test Add Nodes To Expression without NameSpace. */
  @Test
  @DisplayName("Test Add Nodes To Expression without NameSpace")
  void testAddNodesToExpressionWithoutNS() {
    XpathExpressionBuilder builder =
        new XpathExpressionBuilder(ns, root, nodes, XpathVersion.typeLess, false);
    String result = builder.addNodesToExpression();
    String expected = "//N1//N2//N3";
    assertEquals(expected, result);
  }

  /** Test Add Many Attributes. */
  @Test
  @DisplayName("Test Add Many Attributes")
  void testAddManyAttributes() {
    XpathExpressionBuilder instance =
        new XpathExpressionBuilder(root, nodes, attList, XpathVersion.typeLess);
    String expResult = "[@A1='V1' and @A2='V2' and @A3='V3']";
    String result = instance.addAttributesToExpression();
    assertEquals(expResult, result);
  }

  /** Test Add one Attribute. */
  @Test
  @DisplayName("Test Add one Attribute")
  void testAddOneAttribute() {
    attList.clear();
    attList.add(new DefaultAttribute("A1", "V1"));
    XpathExpressionBuilder instance =
        new XpathExpressionBuilder(root, nodes, attList, XpathVersion.typeLess);
    String expResult = "[@A1='V1']";
    String result = instance.addAttributesToExpression();
    assertEquals(expResult, result);
  }

  /** Test Format Key Attribute Value with int. */
  @Test
  @DisplayName("Test Format Key Attribute Value with int")
  void testFormatKeyAttributeValueWithInt() {
    XpathExpressionBuilder instance =
        new XpathExpressionBuilder(root, nodes, attList, XpathVersion.typed);
    String result = instance.formatKeyAttributeValue("K1", "3");
    String expected = "K1=xs:double(3)";
    assertEquals(expected, result);
  }

  /** Test Format Key Attribute Value with Boolean. */
  @Test
  @DisplayName("Test Format Key Attribute Value with Boolean")
  void testFormatKeyAttributeValueWithBoolean() {
    XpathExpressionBuilder instance =
        new XpathExpressionBuilder(root, nodes, attList, XpathVersion.typed);
    String result = instance.formatKeyAttributeValue("K1", "true");
    String expected = "K1=xs:boolean(true)";
    assertEquals(expected, result);
  }
}

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

// ~--- non-JDK imports --------------------------------------------------------

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.tree.DefaultAttribute;

/**
 * Builder class that can supply either XPATH 1.0 and 2.0.
 *
 * @author Laurent<br>
 */
public final class XpathExpressionBuilder {

  /** Default xpath version support. */
  private final XpathVersion version;

  /** Should a presented prefix by applied to expression content. */
  private final boolean considerPrefix;

  /** Searched nodes attributes. */
  private final List<DefaultAttribute> attributes;

  /** Root node. */
  private final String root;

  /** list of child nodes required until reaching searched. */
  private final List<String> subNodes;

  /** Namespace prefix as i.e 'site'. */
  private String prefix;

  /**
   * Build an XPATH expression builder Object with Following parameters.<br>
   *
   * @param rootNode Main node of document<br>
   * @param nodes sub-Node list that compose a tree i.e. <code>Node1,Node2,Node3</code>
   * @param xpathVersion Xpath version supported
   */
  public XpathExpressionBuilder(
      final String rootNode, final List<String> nodes, final XpathVersion xpathVersion) {
    this(null, rootNode, nodes, null, false, xpathVersion);
  }

  /**
   * Build an XPATH expression builder Object with Following parameters<br>
   * .
   *
   * @param nodes sub-Node list that compose a tree i.e Node1,Node2,Node3
   * @param xpathVersion The XPath version supported
   * @param pref Namespace prefix
   * @param rootNode base of expression
   * @param consider should we include prefix in expression
   */
  public XpathExpressionBuilder(
      final String pref,
      final String rootNode,
      final List<String> nodes,
      final XpathVersion xpathVersion,
      final boolean consider) {
    this(pref, rootNode, nodes, null, consider, xpathVersion);
  }

  /**
   * Build an XPATH expression builder Object with Following parameters.
   *
   * @param xpathVersion supported xpath Version
   * @param pRootNode Main node of document<br>
   * @param pNodeList sub-Node list that compose a tree i.e Node1,Node2,Node3<br>
   * @param pAttributesList final node attributes list<br>
   *     Usage:<br>
   *     String prefix="aPrefix"; String root="Root"; List nodes=new Arraylist();
   *     nodes.add("Node1"); Attribute attrib=new Attribute("number","1"); List attributes=new
   *     ArrayList(); attributes.add(attrib); //in the end with would do: String expression=new
   *     XpathExpressionBuilder(prefix,root,nodes,attributes).compileExpression(); //expression
   *     would then be: /aPrefix:root//aPrefix:Node1 <br>
   *     nuber="1"]
   */
  public XpathExpressionBuilder(
      final String pRootNode,
      final List<String> pNodeList,
      final List<DefaultAttribute> pAttributesList,
      final XpathVersion xpathVersion) {
    this(null, pRootNode, pNodeList, pAttributesList, false, xpathVersion);
  }

  /**
   * Build an XPATH expression builder Object with Following parameters.
   *
   * @param xpathVersion supported xPathVersion
   * @param pref an element ca# n have a prefix or not i.e &lt;XXX:YYY&gt;
   * @param rootNode Main node of document<br>
   * @param nodeList sub-Node list that compose a tree i.e Node1,Node2,Node3<br>
   * @param attributeList final node attributes list<br>
   * @param includePrefix include prefix or not in expression. Usage:<code> *  String
   *                      prefix="aPrefix"; String root="Root"; List nodes=new Arraylist();
   *                      nodes.add("Node1"); Attribute attrib=new Attribute("number","1"); List
   *                      attributes=new ArrayList(); attributes.add(attrib); //in the end we would
   *                      do: String expression=new
   *                      XpathExpressionBuilder(prefix,root,nodes,attributes).compileExpression();
   *                      //expression would then be: /aPrefix:root//aPrefix:Node1[@num=1]
   *                      </code>
   */
  public XpathExpressionBuilder(
      final String pref,
      final String rootNode,
      final List<String> nodeList,
      final List<DefaultAttribute> attributeList,
      final boolean includePrefix,
      final XpathVersion xpathVersion) {
    considerPrefix = includePrefix;

    if (includePrefix) {
      this.prefix = pref;
    }

    root = rootNode;
    subNodes = nodeList;
    this.attributes = attributeList;
    version = xpathVersion;
  }

  /**
   * Provide needed expression to do the search.
   *
   * @return well-formed xpath expression
   */
  public String compileExpression() {
    StringBuilder sb = new StringBuilder();

    if (considerPrefix) {
      sb.append("/").append(prefix).append(":").append(root);
    } else {
      sb.append("/").append(root);
    }

    sb.append(addNodesToExpression(considerPrefix));
    sb.append(addAttributesToExpression());

    return sb.toString();
  }

  /**
   * convert given attributes to Xpath.
   *
   * @param l attribute List
   * @return converted attributes List
   */
  List<String> convertAttributeList(@NotNull final List<DefaultAttribute> l) {
    List<String> list = new ArrayList<>();
    if (l != null) {
      for (DefaultAttribute attribute : l) {

        String currentKey = attribute.getName();
        String currentValue = attribute.getValue();
        String keyValueString = formatKeyAttributeValue(currentKey, currentValue);

        list.add(keyValueString);
      }
    }

    return Collections.unmodifiableList(list);
  }

  String addNodesToExpression() {
    return addNodesToExpression(false);
  }

  /**
   * @param includePrefix should we include NS
   * @return Nodes String
   */
  String addNodesToExpression(final boolean includePrefix) {
    int lNodeCount = subNodes.size();
    final int i = 3;
    StringBuilder sb =
        new StringBuilder(
            lNodeCount
                * (prefix != null ? (prefix.length() + i) : i)); // pre-allocate StringBuilder size

    String p = includePrefix ? this.prefix + ":" : "";

    for (String s : subNodes) {

      sb.append("//").append(p).append(s);
    }

    return sb.toString();
  }

  /**
   * add proper attributes to expression.
   *
   * @return expression with attributes
   */
  String addAttributesToExpression() {
    if (attributes == null || attributes.isEmpty()) {
      return "";
    }
    List<String> l = convertAttributeList(attributes);
    int lLength = l.size();
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < lLength; i++) {
      if (i > 0) {
        sb.append(" and ");
      }
      sb.append("@").append(l.get(i));
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * Typed format of an attribute.
   *
   * @param pKey attribute name
   * @param pValue attribute value
   * @return correctly formatted attribute
   */
  String formatKeyAttributeValue(final String pKey, final String pValue) {

    // strong typing was introduced in XPATH 2.0 only
    if (XpathVersion.typed == version) {
      if (NumberUtils.isNumber(pValue)) {
        return pKey + "=" + "xs:double(" + pValue + ")";
      } else if (Boolean.parseBoolean(pValue)) {
        return pKey + "=" + "xs:boolean(" + pValue + ")";
      }
    }

    return pKey + "=" + "'" + pValue + "'";
  }
}

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

//~--- non-JDK imports --------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.tree.DefaultAttribute;

/**
 * Builder class that can supply either XPATH 1.0 and 2.0 if VERSION_SUPPORT is set to 2.0f
 *
 * @author Laurent<br />
 */
public final class XPathExpressionBuilder {

  /**
   * Default xpath version support
   */
  private final XPathVersion mVersionSupport;
  /**
   * Should a presented prefix by applied to expression content
   */
  private final boolean mConsiderPrefix;
  /**
   * Searched nodes attributes
   */
  private final List<DefaultAttribute> mAttributes;
  /**
   * Root node
   */
  private final String mRootNode;
  /**
   * list of child nodes required until reaching searched
   */
  private final List<String> mSubNodeList;
  /**
   * Namespace prefix as i.e 'site'
   */
  private String mPrefix;

  /**
   * <pre>
   * Build an XPATH expression builder Object with Following parameters<br/>
   *
   * @param pRootNode Main node of document<br/>
   * @param pNodeList sub-Node list that compose a tree i.e
   * <code>
   * Node1,Node2,Node3<br/>
   * @number="1"]
   * </code>
   * </pre><br/>
   *
   * @param xpathVersion <br/>
   */
  public XPathExpressionBuilder(String pRootNode, List<String> pNodeList,
      XPathVersion xpathVersion) {
    this(null, pRootNode, pNodeList, null, false, xpathVersion);
  }

  /**
   * <pre>
   * Build an XPATH expression builder Object with Following parameters<br/>
   *
   * @param pNodeList sub-Node list that compose a tree i.e
   * Node1,Node2,Node3<br/>
   * @number="1"]
   *
   * </pre><br/>
   *
   * @param xpathVersion <br/>
   */
  public XPathExpressionBuilder(String pPrefix, String pRootNode, List<String> pNodeList,
      XPathVersion xpathVersion, boolean consider) {
    this(pPrefix, pRootNode, pNodeList, null, consider, xpathVersion);
  }

  /**
   * <pre>
   * Build an XPATH expression builder Object with Following parameters<br/>
   *
   * @param pRootNode Main node of document<br/>
   * @param pNodeList sub-Node list that compose a tree i.e
   * Node1,Node2,Node3<br/>
   * @param pAttributesList final node attributes list<br/>
   * Usage:<br/>
   * <code>
   *  String prefix="aPrefix";
   *  String root="Root";
   *  List nodes=new Arraylist();
   *  nodes.add("Node1");
   *  Attribute attrib=new Attribute("number","1");
   *  List attributes=new ArrayList();
   *  attributes.add(attrib);
   * //in the end with would do:
   * String expression=new  XPathExpressionBuilder(prefix,root,nodes,attributes).compileExpression();
   * //expression would then be:
   * /aPrefix:root//aPrefix:Node1[
   * @nuber="1"]
   * </code>
   * </pre><br/>
   *
   * @param xpathVersion <br/>
   */
  public XPathExpressionBuilder(String pRootNode, List<String> pNodeList,
      List<DefaultAttribute> pAttributesList, XPathVersion xpathVersion) {
    this(null, pRootNode, pNodeList, pAttributesList, false, xpathVersion);
  }

  /**
   * <pre>
   * Build an XPATH expression builder Object with Following parameters<br/>
   *
   * @param pPrefix an element ca# n have a prefix or not i.e
   * &lt;XXX:YYY&gt;<br/>
   * @param pRootNode Main node of document<br/>
   * @param pNodeList sub-Node list that compose a tree i.e
   * Node1,Node2,Node3<br/>
   * @param pAttributesList final node attributes list<br/>
   * Usage:      <code>
   *  String prefix="aPrefix";
   *  String root="Root";
   *  List nodes=new Arraylist();
   *  nodes.add("Node1");
   *  Attribute attrib=new Attribute("number","1");
   *  List attributes=new ArrayList();
   *  attributes.add(attrib);
   * //in the end with would do:
   * String expression=new  XPathExpressionBuilder(prefix,root,nodes,attributes).compileExpression();
   * //expression would then be:
   * /aPrefix:root//aPrefix:Node1[\
   * @num=1 ]
   */
  public XPathExpressionBuilder(String pPrefix, String pRootNode, List<String> pNodeList,
      List<DefaultAttribute> pAttributesList, boolean includePrefix, XPathVersion xpathVersion) {
    mConsiderPrefix = includePrefix;

    if (includePrefix) {
      mPrefix = pPrefix;
    }

    mRootNode = pRootNode;
    mSubNodeList = pNodeList;
    mAttributes = pAttributesList;
    mVersionSupport = xpathVersion;
  }

  /**
   * Provide needed expression to do the search
   *
   * @return well-formed xpath expression
   */
  public String compileExpression() {
    StringBuilder sb = new StringBuilder();

    if (mConsiderPrefix) {
      sb.append("/").append(mPrefix).append(":").append(mRootNode);
    } else {
      sb.append("/").append(mRootNode);
    }

    sb.append(addNodesToExpression(mConsiderPrefix));
    sb.append(addAttributesToExpression());

    return sb.toString();
  }

  /**
   *
   */
  List<String> convertAttributeListToStringList(List<DefaultAttribute> pList) {

    List<String> lList = new ArrayList<>();
    if (pList != null) {
      for (DefaultAttribute attribute : pList) {

        String lCurrentKey = attribute.getName();
        String lCurrentValue = attribute.getValue();
        String lKeyValueString = formatKeyAttributeValue(lCurrentKey, lCurrentValue);

        lList.add(lKeyValueString);

      }
    }

    return lList;
  }

  String addNodesToExpression() {
    return addNodesToExpression(false);
  }

  /**
   *
   */
  String addNodesToExpression(final boolean includePrefix) {
    int lNodeCount = mSubNodeList.size();
    StringBuilder sb = new StringBuilder(lNodeCount * (mPrefix != null ? (mPrefix.length() + 3)
        : 3)); // pre-allocate StringBuilder size

    String prefix = includePrefix ? mPrefix + ":" : "";

    for (String s : mSubNodeList) {

      sb.append("//").append(prefix).append(s);
    }

    return sb.toString();
  }


  /**
   *
   */
  String addAttributesToExpression() {
    if (mAttributes == null || mAttributes.isEmpty()) {
      return "";
    }
    List<String> l = convertAttributeListToStringList(mAttributes);
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
   *
   */
  String formatKeyAttributeValue(String pKey, String pValue) {
    String lKeyValueString = "";

    // strong typing was introduced in XPATH 2.0 only
    if (XPathVersion.typed == mVersionSupport) {
      if (NumberUtils.isNumber(pValue)) {
        lKeyValueString = pKey + "=" + "xs:double(" + pValue + ")";
      } else if (Boolean.parseBoolean(pValue)) {
        lKeyValueString = pKey + "=" + "xs:boolean(" + pValue + ")";
      }
    } else {
      lKeyValueString = pKey + "=" + "'" + pValue + "'";

    }

    return lKeyValueString;
  }
}


/*
 See licence.txt
 * 5. Utilise des listes plutôt que des arrays: c'est un peu plus slow, mais c'est plus facile d'utilisation et ça réduit le risque d'erreurs
 * 6. Certains attributs ne sont pas préfixés par 'm' donc la convention n'est pas uniforme
 * 8. Il faut éviter d'appeler des méthodes dans un constructeur: ces méthodes peuvent être redéfinies par une sous-classe et l'effet est imprévisible
 *
 */
package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------
import org.dom4j.tree.DefaultAttribute;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Builder class that can supply either XPATH 1.0 and 2.0 if VERSION_SUPPORT is
 * set to 2.0f
 *
 * @author Laurent<br/>
 */
public final class XPathExpressionBuilder {

    /**
     * Default xpath version support
     */
    private XPathVersion mVersionSupport = XPathVersion.typeLess;
    /**
     * Should a presetted prefix by applied to expression content
     */
    private boolean mConsiderPrefix = false;
    /**
     * resulting expression of the builder
     */
    private String mExpression = null;
    /**
     * Searched nodes attributes
     */
    private List<DefaultAttribute> mAttributes = null;
    /**
     * Namespace prefix as i.e 'site'
     */
    private String mPrefix = null;
    /**
     * Root node
     */
    private String mRootNode = null;
    /**
     * list of child nodes requiered until reaching searched
     */
    private List<String> mSubNodeList = null;

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
     *  String prefix="aPrfix";
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
     * @param xpathVersion <br/>
     */
    public XPathExpressionBuilder(String pRootNode, List pNodeList,
            XPathVersion xpathVersion) {
        this(null, pRootNode, pNodeList, null, false, xpathVersion);
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
     *  String prefix="aPrfix";
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
     * @param xpathVersion <br/>
     */
    public XPathExpressionBuilder(String pPrefix, String pRootNode, List pNodeList,
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
     *  String prefix="aPrfix";
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
     * @param xpathVersion <br/>
     */
    public XPathExpressionBuilder(String pRootNode, List pNodeList,
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
     *  String prefix="aPrfix";
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
     * @param includePrefix
     * @param xpathVersion
     *
     * </code>
     * </pre>
     */
    public XPathExpressionBuilder(String pPrefix, String pRootNode, List pNodeList,
            List<DefaultAttribute> pAttributesList, boolean includePrefix, XPathVersion xpathVersion) {
        mConsiderPrefix = includePrefix;

        if (includePrefix) {
            mPrefix = (pPrefix);
        }

        mRootNode = pRootNode;
        mSubNodeList = pNodeList;
        mAttributes = pAttributesList;
        mVersionSupport = xpathVersion;
    }

    /**
     * Provide needed expression to do the search
     *
     * @return well formed xpath expression
     */
    public String compileExpression() {
        StringBuilder sb = new StringBuilder();


        if (mConsiderPrefix) {
            sb = sb.append("/").append(mPrefix).append(":").append(mRootNode);
        } else {
            sb = sb.append("/").append(mRootNode);
        }

        sb = sb.append(addNodesToExpression(mConsiderPrefix));
        sb = sb.append(addAttributesToExpression());
        mExpression = sb.toString();

        return mExpression;
    }

    /**
     *
     * @param pList
     * @return
     */
    List convertAttributeListToStringList(List<DefaultAttribute> pList) {

        List lList = new ArrayList();
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
     * @param includePrefix
     * @return
     */
    String addNodesToExpression(final boolean includePrefix) {
        int lNodeCount = mSubNodeList.size();

        StringBuilder sb = new StringBuilder();


        if (includePrefix) {      // Include prefix if ns is present
            if (lNodeCount > 1) {    // check nodes count
                for (String node : mSubNodeList) {
                    sb = sb.append("//").append(mPrefix).append(":").append(node);
                }


            } else {
                sb = sb.append("//").append(mPrefix).append(":").append(mSubNodeList.get(0));
            }
        } else {
            if (lNodeCount > 1) {
                for (String node : mSubNodeList) {
                    sb = sb.append("//").append(node);

                }
            } else {
                sb = sb.append("//").append(mSubNodeList.get(0));

            }
        }
        sb.trimToSize();



        return sb.toString();
    }

    /**
     * @return
     */
    String addAttributesToExpression() {
        if (mAttributes == null) {
            System.out.println("null");
            return "";
        } else {
            List l = new ArrayList(convertAttributeListToStringList(mAttributes));
            int lLength = mAttributes.size();

            StringBuilder sb = new StringBuilder();

            if (lLength > 1) {
                sb = sb.append("[");

                for (int currentAttributeIndex = 0; currentAttributeIndex < lLength; currentAttributeIndex++) {
                    if (currentAttributeIndex == lLength - 1) {
                        sb = sb.append("@").append(l.get(currentAttributeIndex));
                    } else {
                        sb = sb.append("@").append(l.get(currentAttributeIndex)).append(" and ");
                    }
                }

                sb = sb.append("]");
            } else {
                sb = sb.append("[@").append(l.get(0)).append("]");
            }

            return sb.toString();
        }


    }

    /**
     * @param pKey
     * @param pValue
     * @return
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
            lKeyValueString = pKey + "=" + "'" + (pValue) + "'";

        }

        return lKeyValueString;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.common.PropertiesUserObject;
import java.util.Properties;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.InvalidXPathException;
import org.dom4j.XPath;

/**
 * Content finder<br/>
 *
 * @param <resultType>
 * @author laurent
 */
public class SearchEngineConfigurator<resultType> extends PropertiesUserObject {

    private Document mDoc = null;

    SearchEngineConfigurator(Properties config, Document searchDoc) {
        mConfig = config;
        mDoc = searchDoc;
    }

    resultType findContent(String pExpression) throws InvalidXPathException {
        XPath xpathSelector = null;

        xpathSelector = DocumentHelper.createXPath(pExpression);

        resultType list = (resultType) ((xpathSelector.selectNodes(mDoc).size() > 0)
                ? xpathSelector.selectNodes(mDoc)
                : null);
        trace("Searched " + pExpression);


        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


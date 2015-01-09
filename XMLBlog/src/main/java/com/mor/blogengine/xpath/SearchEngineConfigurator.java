
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.common.PropertiesUserObject;
import java.util.List;
import java.util.Properties;
import org.dom4j.Document;
import static org.dom4j.DocumentHelper.createXPath;
import org.dom4j.InvalidXPathException;
import org.dom4j.XPath;

/**
 * Content finder<br/>
 *
 * @param <resultType>
 * @author laurent
 */
public class SearchEngineConfigurator<resultType extends List<?>> extends PropertiesUserObject {

    private Document mDoc = null;

    SearchEngineConfigurator(Properties config, Document searchDoc) {
        mConfig = config;
        mDoc = searchDoc;
    }

    resultType findContent(String pExpression) throws InvalidXPathException, NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {

        XPath xpathSelector = createXPath(pExpression);

        @SuppressWarnings("unchecked")
        resultType list = (resultType) ((xpathSelector.selectNodes(mDoc).size() > 0)
                ? xpathSelector.selectNodes(mDoc)
                : null);
        trace("Searched " + pExpression);
        if (list == null) {

            throw new NoMatchesFoundException(pExpression, isDebugOn());

        }

        return list;
    }
}
//~ Formatted by Jindent --- http://www.jindent.com


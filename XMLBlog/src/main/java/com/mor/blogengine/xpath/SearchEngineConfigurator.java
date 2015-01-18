/* 
 * The MIT License
 *
 * Copyright 2015 Laurent Morissette.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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



/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import com.mor.common.PropertiesUserObject;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.InvalidXPathException;
import org.dom4j.XPath;
import org.dom4j.xpath.DefaultXPath;
import org.xml.sax.SAXException;

/**
 * Content finder<br/>
 *
 * @param <resultType>
 * @author laurent
 */
public class SearchEngineConfigurator<resultType> extends PropertiesUserObject {
    private Document mDoc = null;

    SearchEngineConfigurator(Properties config) {
        try {
            mConfig=config;
           
            //trace(getXml());        
            mDoc=new XmlDataSourceProvider(mConfig).provide(getXml(), getSchema());
        } catch (SAXException ex) {
            trace( ex.getMessage());
        } catch (ParserConfigurationException ex) {
            trace( ex.getMessage());
        } catch (DocumentException ex) {
            trace( ex.getMessage());
        } catch (IOException ex) {
            trace( ex.getMessage());
        }
    }

    resultType findContent(String pExpression) throws InvalidXPathException {
        XPath xpathSelector = null;

        xpathSelector =DocumentHelper.createXPath(pExpression);

        resultType list = (resultType) ((xpathSelector.selectNodes(mDoc).size() > 0)
                                        ? xpathSelector.selectNodes(mDoc)
                                        : null);
        

        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

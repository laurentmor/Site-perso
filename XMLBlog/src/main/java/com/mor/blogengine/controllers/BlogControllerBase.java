/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.controllers;

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.xml.BlogEntityFactory;
import com.mor.blogengine.xml.IBlogEntityFactory;
import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import com.mor.common.PropertiesUserObject;
import java.io.IOException;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;
import org.xml.sax.SAXException;

/**
 *
 * @author laurent
 */
public abstract class BlogControllerBase extends PropertiesUserObject {

    /**
     *
     */
    private Document document = null;
    private XmlDataSourceProvider provider = null;
    private IBlogEntityFactory<DefaultElement> factory = null;

    /**
     * Base class for controllers configuration  
     * As we use same configuration in each context we can generalise it
     * @param p Configuration settings
     */
    public BlogControllerBase(final Properties p) throws MissingPropertyException, IncorrectPropertyValueException {

        mConfig = p;
        provider = new XmlDataSourceProvider(mConfig);
        try {
            document = provider.provide();
            factory = new BlogEntityFactory();
        }
        catch (ParserConfigurationException ex) {
            trace("Parser configuration error" + ex.getMessage());
        }
        catch (DocumentException ex) {
            trace("Document error " + ex.getMessage());
        }
        catch (IOException ex) {
            trace("I/O error " + ex.getMessage());
        }
        catch (SAXException ex) {
            trace(ex.getMessage() + "SaxError error");
        }

    }

    /**
     * @return the document
     */
    public final Document getDocument() {
        return document;
    }

    /**
     * @return the provider
     */
    public final XmlDataSourceProvider getProvider() {
        return provider;
    }

    /**
     * @return the factory
     */
    public final IBlogEntityFactory<DefaultElement> getFactory() {
        return factory;
    }
}

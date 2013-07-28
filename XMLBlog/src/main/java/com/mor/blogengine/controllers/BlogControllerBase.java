/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.controllers;

import com.mor.blogengine.xml.BlogEntityFactory;
import com.mor.blogengine.xml.IBlogEntityFactory;
import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import com.mor.common.PropertiesUserObject;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

/**
 *
 * @author laurent
 */
public class BlogControllerBase extends PropertiesUserObject {

    protected IBlogEntityFactory factory = null;
    protected Document document = null;
    protected XmlDataSourceProvider provider = null;
     
    public BlogControllerBase(Properties p) {
        factory = new BlogEntityFactory();
        mConfig = p;
        provider = new XmlDataSourceProvider(p);
        try {
            document = provider.provide();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BlogControllerBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(BlogControllerBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogControllerBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(BlogControllerBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.dao;

import com.mor.blogengine.xml.IXMLHandler;
import com.mor.blogengine.xml.XMLHandlerImpl;
import com.mor.common.PropertiesUserObject;
import java.util.Properties;
import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;

/**
 *
 * @author laurent
 */
public class BlogRepositoryBase extends PropertiesUserObject{

    IXMLHandler<DefaultElement> handler=null;
    Document doc=null;

    public BlogRepositoryBase(Document d, Properties p) {
    doc=d;
    mConfig=p;
    handler= XMLHandlerImpl.getInstanceForDoc(mConfig, doc);
    }
    
}

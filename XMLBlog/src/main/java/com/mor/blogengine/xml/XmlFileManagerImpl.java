/*
# Copyright (c) 2007-2009 Laurent Morissette
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
 */



package com.mor.blogengine.xml;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import com.mor.common.PropertiesUserObject;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileWriter;
import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.xml.sax.SAXException;

/**
 * Classe utilitaire pour la gestion du XML de blog
 *
 * @author Laurent
 * @version $version
 *
 */
public final class XmlFileManagerImpl extends PropertiesUserObject implements IXmlFileManager<DefaultElement> {

    /** class instance */
    static final XmlFileManagerImpl mInstance = null;

    /** parsed  XML document */
    private Document mDoc = null;

    /** XML root */
    private DefaultElement mRootElement = null;

    /** XML file to load */
    private URL mXmlFile = null;
    boolean     mInited  = false;

 
/** Get an insance of  class using singleton pattern implementation
     * @param pDoc
     * @param config
     * @return an instance of class
     * @throws DocumentException
     */
    public static XmlFileManagerImpl getInstanceForDoc(Properties config) throws DocumentException {
        if ((config != null) ) {
            if (mInstance == null) {
                return new XmlFileManagerImpl(config);
            }
        }

        return mInstance;
    }

    private XmlFileManagerImpl(Properties config) {
        this.mConfig = config;
      
        mInited      = init(this.mConfig);
    }


     boolean init(Properties config) {
        try {
        
            mDoc     = new XmlDataSourceProvider(config).provide(getXml(), getSchema());
          mConfig  = config;
          mXmlFile = getClass().getResource(mConfig.getProperty("datasource.xml"));

          boolean inited = false;

          if (mDoc != null) {
              mRootElement = (DefaultElement) mDoc.getRootElement();
              inited       = true;
          }

          return inited;
        } catch (SAXException ex) {
            Logger.getLogger(XmlFileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlFileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(XmlFileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlFileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
     * writes in a file
     * @param document DOM model to write in
     * @param pOutputFile output file
     * @throws java.io.IOException
     * @param pDocument
     */
    boolean write(Document pDocument) {
        boolean written = false;

        try {
            XMLWriter writer = new XMLWriter(new FileWriter(mXmlFile.getFile()));

            writer.write(pDocument);
            writer.close();
            written = true;
        } catch (IOException ex) {
            written = false;
            trace("Error saving file..." + ex);
        }

        return written;
    }

    /**
     *   Saves changes in blog structure
     * @return  true if saved correctly
     */
    @Override
    public boolean saveChanges() {
        boolean saved = false;

        if (mInited) {
            if (isPersistingNecessary()) {
                write(mDoc);
                trace("saving...");
                saved = true;
            } else {
                saved = true;
                trace("if persisting was set to on changes would be saved to file");
            }
        }

        return saved;
    }

    /**
     * Add given Node to blog structure
     * @param element the element to add
     * @return added or not (true or false)
     */
    @Override
    public boolean add(DefaultElement element) {
        ArrayList<DefaultElement> list = new ArrayList<DefaultElement>();

        list.add(element);

        boolean added = add(list);

        return added;
    }

    /**
     * remove  given element to blog structure
     * @param element the element to remove
     * @return removed or not (true or false)
     */
    @Override
    public boolean remove(DefaultElement element) {
        boolean removed = false;

        try {
            ArrayList<DefaultElement> list = new ArrayList<DefaultElement>();

            list.add(element);
            removed = remove(list);
            saveChanges();
        } catch (Exception e) {
            trace("" + e);
        }

        return removed;
    }

    /**
     * Append a node to parent node
     * @param root The node to add under
     * @param content what to add to root node
     * @return appended or not
     */
    @Override
    public boolean append(DefaultElement root, DefaultElement content) {
        String         elemID    = root.valueOf("@ID");
        DefaultElement elemInDoc = (DefaultElement) mRootElement.elementByID(elemID);

        if(elemInDoc!=null){elemInDoc.add(content);

        int indexOfappended = elemInDoc.indexOf(content);

        return (indexOfappended != -1);
        }
        return false;
    }

    /**
     *
     *
     * Add given Nodes to blog structure
     * @param addBatch the elements to add
     * @return added or not (true or false)
     */
    @Override
    public boolean add(List<DefaultElement> addBatch) {
        boolean added = false;

        for (DefaultElement defaultElement : addBatch) {
            mRootElement.add(defaultElement);
            added = true;
        }

        saveChanges();

        return added;
    }

    /**
     * remove  given elements to blog structure
     * @param removeBatch the elements to remove
     * @return removed or not (true or false)
     */
    @Override
    public boolean remove(List<DefaultElement> removeBatch) {
        boolean removed = false;

        for (DefaultElement defaultElement : removeBatch) {
            DefaultElement e = (DefaultElement) mRootElement.elementByID(defaultElement.valueOf("@ID"));

            removed = mRootElement.remove(e);
        }

        saveChanges();

        return removed;
    }

    @Override
    public boolean remove(DefaultElement child, String parentID) {
        boolean        removed     = false;
        DefaultElement foundParent = (DefaultElement) mRootElement.elementByID(parentID);

        if (foundParent != null) {
            trace("Parent found{0}");

            DefaultElement foundChild = (DefaultElement) foundParent.elementByID(child.valueOf("@ID"));

            if (foundChild != null) {
                trace("child  found");
                removed = foundParent.remove(foundChild);
                saveChanges();

                return removed;
            }
        }

        return false;
    }

    @Override
    public boolean remove(List<DefaultElement> removeBatch, String parentID) {
        boolean removed = false;

        for (DefaultElement defaultElement : removeBatch) {
            removed = remove(defaultElement, parentID);
        }

        return removed;
    }

    
}


//~ Formatted by Jindent --- http://www.jindent.com

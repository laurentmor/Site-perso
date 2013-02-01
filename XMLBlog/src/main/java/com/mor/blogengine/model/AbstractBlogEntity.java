/*
# Copyright (c) 2007 Laurent Morissette
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



package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import org.dom4j.Namespace;
import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;

import java.util.NoSuchElementException;

/**
 *
 *
 *
 * Changelog:<br/>
 * 0.1 Initial version of code based on specs stated as in SRS_blog.pdf<br/>
 * 0.3 Converted from JDom to dom4j API to support XPath procesing<br/>
 * 0.4 Added Namespace declaration<br/>
 * 0.5 Defined hashCode and equals methods<br/>
 * 0.6 Changed definition of toElement method signature to match needs of org.dom4j.tree.DefaultElement<br/>
 * 1.0 Refactored visibility of setters<br/>
 * 1.1 Refactored Class name to AbstractBlogElement<br/>
 * 1.3 Removed references to hash attrribute as unique ID unique ID is now entityID<br/>
 * 1.4 Changed  XML API to XOM in order to provide lightweight data processing<br/>
 * 1.5 Rolled back from XOM to DOM4J as search functionality was not working
 *     properly and switched to XPATH 1.0 which is non typed<br/>
 *
 * Abstract entity class to build a domain blog class entity supported by dom4j API
 *
 * @author Laurent
 *
 */
public abstract class AbstractBlogEntity implements Serializable {

    /**
     * Tags prefix part index in NS definition
     */
    private final static int  PREFIX_PART      = 0;
    private static final long serialVersionUID = 1L;

    /**
     * Namespace URI part index in NS definition
     */
    private final int URI_PART = 1;

    /**
     * Xml representation of this Entry
     */
    DefaultElement mAassociatedElement = null;

    /** unique entity ID */
    private String mEntityID = null;

    /**
     * Complete Namespace infos
     */
    private final String[] mNamespaceParts = { "site", "http://xml.netbeans.org/schema/blog" };

    /**
     * Concrete Namespace declaration
     */
    protected final Namespace mNamespace = new Namespace(mNamespaceParts[PREFIX_PART], mNamespaceParts[URI_PART]);

    /**
     * Default constructor
     */
    public AbstractBlogEntity() {}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        AbstractBlogEntity ot = (AbstractBlogEntity) obj;

        return getEntityID().equals(ot.getEntityID());
    }

    /**
     *
     * @return unique identifyer of elrment
     */
    public String getEntityID() {
        if (mAassociatedElement != null) {
            return getEntityIDInXml();
        }
        
       

        return "ID_" + hashCode();
    }

    /**
     * @return unique identifyer of elrment in XML
     * @throws java.util.NoSuchElementException
     */
    String getEntityIDInXml() throws NoSuchElementException {
        {
            if (mAassociatedElement.attribute("ID") == null) {
                throw new NoSuchElementException("Pas d'attribut id pour l'element courrant consulter le XSD");
            }

            return (mAassociatedElement.valueOf("@ID"));
        }
    }

    /**
     * Set unique ID for entity
     *
     */
    public void setEntityID() {
        mEntityID = getEntityIDInXml();
    }

    /**
     * a-like as {@link #toString() }
     *
     * @return an XML representation of element
     */
    public abstract DefaultElement toElement();

    /**
     *
     * Format entity for correct HTML display
     */
    abstract void formatAttributesValuesAsHTML();

    @Override
    public abstract int hashCode();
}


//~ Formatted by Jindent --- http://www.jindent.com

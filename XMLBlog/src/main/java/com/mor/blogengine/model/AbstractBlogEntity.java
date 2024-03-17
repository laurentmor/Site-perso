/**
 * Copyright 2021 Laurent
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import org.dom4j.Namespace;
import org.dom4j.tree.DefaultElement;

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
 * 0.6 Changed definition of toElement method signature to match needs of
 * org.dom4j.tree.DefaultElement<br/>
 * 1.0 Refactored visibility of setters<br/>
 * 1.1 Refactored Class name to AbstractBlogElement<br/>
 * 1.3 Removed references to hash attribute as unique ID unique ID is now
 * entityID<br/>
 * 1.4 Changed XML API to XOM in order to provide lightweight data
 * processing<br/>
 * 1.5 Rolled back from XOM to DOM4J as search functionality was not working
 * properly and switched to XPATH 1.0 which is non typed<br/>
 *
 * Abstract entity class to build a domain blog class entity supported by dom4j
 * API
 *
 * @author Laurent
 *
 */
public abstract class AbstractBlogEntity implements Serializable {

    /**
     * Tags prefix part index in NS definition
     */
    private final static int PREFIX_PART = 0;
    private static final long serialVersionUID = 1L;

    /**
     * Namespace URI part index in NS definition
     */
    private static final int URI_PART = 1;
    /**
     * Complete Namespace infos
     */
    private final String[] mNamespaceParts = {"site", "http://xml.netbeans.org/schema/blog"};
    /**
     * Concrete Namespace declaration
     */
    protected final Namespace mNamespace = new Namespace(mNamespaceParts[PREFIX_PART], mNamespaceParts[URI_PART]);
    /**
     * Xml representation of this Entry
     */
    DefaultElement mAassociatedElement = null;

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
     * @return unique identifier of element in XML
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
        /**
         * unique entity ID
         */
        String mEntityID = getEntityIDInXml();
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

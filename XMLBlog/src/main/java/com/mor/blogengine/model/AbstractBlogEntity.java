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

import java.io.Serial;
import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * Changelog:<br/>
 * 0.1 Initial version of code based on specs stated as in SRS_blog.pdf<br/>
 * 0.3 Converted from JDom to dom4j API to support XPath processing<br/>
 * 0.4 Added Namespace declaration<br/>
 * 0.5 Defined hashCode and equals methods<br/>
 * 0.6 Changed definition of toElement method signature to match needs of
 * org.dom4j.tree.DefaultElement<br/>
 * 1.0 Refactored visibility of setters<br/>
 * 1.1 Refactored Class name to AbstractBlogElement<br/>
 * 1.3 Removed references to hash attribute as unique ID is now
 * entityID<br/>
 * 1.4 Changed XML API to XOM in order to provide lightweight data
 * processing<br/>
 * 1.5 Rolled back from XOM to DOM4J as search functionality was not working
 * properly and switched to XPATH 1.0 which is non typed<br/>
 * <p>
 * Abstract entity class to build a domain blog class entity supported by dom4j
 * API
 *
 * @author Laurent
 */
@SuppressWarnings("unused")
public abstract class AbstractBlogEntity implements Serializable {

    /**
     * Tags prefix part index in NS definition
     */
    private final static int PREFIX_PART = 0;
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Namespace URI part index in NS definition
     */
    private static final int URI_PART = 1;
    /**
     * Complete Namespace infos
     */
    private final String[] mNamespaceParts = {"site", "https://xml.netbeans.org/schema/blog"};
    /**
     * Concrete Namespace declaration
     */
    protected final Namespace mNamespace = new Namespace(mNamespaceParts[PREFIX_PART], mNamespaceParts[URI_PART]);
    /**
     * Xml representation of this Entry
     */
    DefaultElement mAssociatedElement = null;

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
     * @return unique identifier of element
     */
    public String getEntityID() {
        if (mAssociatedElement != null) {
            return getEntityIDInXml();
        }

        return "ID_" + hashCode();
    }

    /**
     * @return unique identifier of element in XML
     */
    String getEntityIDInXml() throws NoSuchElementException {
        {
            if (mAssociatedElement.attribute("ID") == null) {
                throw new NoSuchElementException("No ID attribute found, check XSD");
            }

            return (mAssociatedElement.valueOf("@ID"));
        }
    }

    /**
     * a-like as {@link #toString() }
     *
     * @return an XML representation of element
     */
    public abstract DefaultElement toElement();

    /**
     * Format entity for correct HTML display
     */
    abstract void formatAttributesValuesAsHTML();

    @Override
    public abstract int hashCode();
}


//~ Formatted by Jindent --- http://www.jindent.com

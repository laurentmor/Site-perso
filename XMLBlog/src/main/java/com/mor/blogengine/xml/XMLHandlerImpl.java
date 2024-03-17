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
package com.mor.blogengine.xml;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.common.PropertiesUserObject;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaire pour la gestion du XML de blog
 *
 * @author Laurent
 * @version $version
 *
 */
public final class XMLHandlerImpl extends PropertiesUserObject implements IXMLHandler<DefaultElement> {

    /**
     * class instance
     */
    static XMLHandlerImpl mInstance;

    /**
     * XML root
     */
    private DefaultElement mRootElement = null;

    private XMLHandlerImpl(@NonNull Properties config, Document d) {
        super(config);
        /**
         * parsed XML document
         */

        if (d != null) {
            mRootElement = (DefaultElement) d.getRootElement();
        }
    }

    /**
     * Get an insance of class using singleton pattern implementation
     *
     * @return an instance of class
     */
    public static XMLHandlerImpl getInstanceForDoc(Properties config, Document domTree) {
        if ((config != null)) {
            if (mInstance != null) {
                return mInstance;
            }
        }
        mInstance = new XMLHandlerImpl(config, domTree);

        return mInstance;
    }

    /**
     * Add given Node to blog structure
     *
     * @param element the element to add
     * @return added or not (true or false)
     */
    @Override
    public boolean add(DefaultElement element) {
        ArrayList<DefaultElement> list = new ArrayList<DefaultElement>();

        list.add(element);

        return add(list);
    }

    /**
     * remove given element to blog structure
     *
     * @param element the element to remove
     * @return removed or not (true or false)
     */
    @SneakyThrows
    @Override
    public boolean remove(DefaultElement element) {
        boolean removed = false;

        try {
            ArrayList<DefaultElement> list = new ArrayList<>();

            list.add(element);
            removed = remove(list);
        } catch (Exception e) {
            try {
                trace(String.valueOf(e));
            } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                trace(ex.getMessage());
            }
        }

        return removed;
    }

    /**
     * Append a node to parent node
     *
     * @param root The node to add under
     * @param content what to add to root node
     * @return appended or not
     */
    @Override
    public boolean append(DefaultElement root, DefaultElement content) {
        String elemID = root.valueOf("@ID");
        DefaultElement elemInDoc = (DefaultElement) mRootElement.elementByID(elemID);

        if (elemInDoc != null) {
            elemInDoc.add(content);

            int indexOfappended = elemInDoc.indexOf(content);

            return (indexOfappended != -1);
        }
        return false;
    }

    /**
     *
     *
     * Add given Nodes to blog structure
     *
     * @param addBatch the elements to add
     * @return added or not (true or false)
     */
    @Override
    public boolean add(List<DefaultElement> addBatch) {
        boolean added = false;

        for (val defaultElement : addBatch) {
            mRootElement.add(defaultElement);
            added = true;
        }

        return added;
    }

    /**
     * remove given elements to blog structure
     *
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

        return removed;
    }

    @SneakyThrows
    @Override
    public boolean remove(DefaultElement child, String parentID) {
        boolean removed = false;
        DefaultElement foundParent = (DefaultElement) mRootElement.elementByID(parentID);

        if (foundParent != null) {
            try {
                trace("Parent found{0}");
            } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                trace(ex.getMessage());
            }

            DefaultElement foundChild = (DefaultElement) foundParent.elementByID(child.valueOf("@ID"));

            if (foundChild != null) {
                try {
                    trace("child  found");
                } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                    Logger.getLogger(XMLHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                removed = foundParent.remove(foundChild);

                return removed;
            }
        }

        return false;
    }

    @Override
    public boolean remove(List<DefaultElement> removeBatch, String parentID) {
        boolean removed = false;

        for (val defaultElement : removeBatch) {
            removed = remove(defaultElement, parentID);
        }

        return removed;
    }

}


//~ Formatted by Jindent --- http://www.jindent.com

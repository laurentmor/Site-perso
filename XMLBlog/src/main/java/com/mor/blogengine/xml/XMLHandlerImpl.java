// * Copyright (c) 2024
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// *

package com.mor.blogengine.xml;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.common.PropertiesUserObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;

/**
 * Utility class that encapsulate i/o work.
 *
 * @author Laurent
 * @version $version
 */
public final class XMLHandlerImpl extends PropertiesUserObject implements
    IXMLHandler<DefaultElement> {

  /**
   * class instance
   */
  static XMLHandlerImpl mInstance;

  /**
   * XML root.
   */
  private DefaultElement rootElement;

  @SneakyThrows
  private XMLHandlerImpl(final @NonNull Properties config, final Document d) {
    super(config);

    if (d != null) {
      rootElement = (DefaultElement) d.getRootElement();
    }
    trace("Constructing XMLHandlerImpl");

  }

  /**
   * Get an instance of class using singleton pattern implementation.
   *
   * @param config  the object config
   * @param domTree the document tree
   * @return an instance of class
   */
  public static XMLHandlerImpl getInstanceForDoc(final Properties config, final Document domTree) {
    if (config != null) {
      if (mInstance != null) {
        return mInstance;
      }
    }
    if (config != null) {
      mInstance = new XMLHandlerImpl(config, domTree);
    }

    return mInstance;
  }

  /**
   * Add given Node to blog structure.
   *
   * @param element the element to add
   * @return added or not (true or false)
   */
  @Override
  public boolean add(final DefaultElement element) {
    ArrayList<DefaultElement> list = new ArrayList<>();

    list.add(element);

    return add(list);
  }

  /**
   * remove given element to blog structure.
   *
   * @param element the element to remove
   * @return removed or not (true or false)
   */
  @SneakyThrows
  @Override
  public boolean remove(final DefaultElement element) {
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
   * Append a node to parent node.
   *
   * @param root    The node to add under
   * @param content what to add to root node
   * @return appended or not
   */
  @Override
  public boolean append(final DefaultElement root, final DefaultElement content) {
    String elemId = root.valueOf("@ID");
    DefaultElement elemInDoc = (DefaultElement) rootElement.elementByID(elemId);

    if (elemInDoc != null) {
      elemInDoc.add(content);

      int indexOfAppended = elemInDoc.indexOf(content);

      return indexOfAppended != -1;
    }
    return false;
  }

  /**
   * Add given Nodes to blog structure.
   *
   * @param addBatch the elements to add
   * @return added or not (true or false)
   */
  @Override
  public boolean add(final List<DefaultElement> addBatch) {
    boolean added = false;

    for (val defaultElement : addBatch) {
      rootElement.add(defaultElement);
      added = true;
    }

    return added;
  }

  /**
   * remove given elements to blog structure.
   *
   * @param removeBatch the elements to remove
   * @return removed or not (true or false)
   */
  @Override
  public boolean remove(List<DefaultElement> removeBatch) {
    boolean removed = false;

    for (DefaultElement defaultElement : removeBatch) {
      DefaultElement e = (DefaultElement) rootElement.elementByID(defaultElement.valueOf("@ID"));

      removed = rootElement.remove(e);
    }

    return removed;
  }

  @SneakyThrows
  @Override
  public boolean remove(final DefaultElement child, final String parentId) {
    boolean removed;
    DefaultElement foundParent = (DefaultElement) rootElement.elementByID(parentId);

    if (foundParent != null) {

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

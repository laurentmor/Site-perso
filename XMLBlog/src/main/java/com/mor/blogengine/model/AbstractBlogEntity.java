/*
 * Copyright (c) 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package com.mor.blogengine.model;

// ~--- non-JDK imports --------------------------------------------------------

import java.io.Serial;
import java.io.Serializable;
import java.util.NoSuchElementException;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.tree.DefaultElement;

/***Changelog:<br/>.
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
 * Abstract entity class to build a domain blog class entity supported by dom4j
 * API
 *
 * @author Laurent
 */
@Setter
@Getter
@SuppressWarnings("unused")
public abstract class AbstractBlogEntity implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  /** Xml representation of this Entry. */
  private DefaultElement element;

  /** Default. */
  public AbstractBlogEntity() {
    super();
  }

  /**
   * .
   *
   * @return unique identifier of element
   */
  public String getId() {
    if (element != null) {
      return getEntityIdInXml();
    }

    return "ID_" + hashCode();
  }

  /**
   * @return unique identifier of element in XML
   */
  String getEntityIdInXml() throws NoSuchElementException {

    if (element.attribute("ID") == null) {
      throw new NoSuchElementException("No ID attribute found, check XSD");
    }

    return element.valueOf("@ID");
  }

  /**
   * a-like as {@link #toString() }.
   *
   * @return an XML representation of element
   */
  protected abstract DefaultElement toElement();

  /** Format entity for correct HTML display. */
  abstract void formatAttributes();
}

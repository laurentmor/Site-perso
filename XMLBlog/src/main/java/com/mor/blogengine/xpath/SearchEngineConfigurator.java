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

package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------

import static org.dom4j.DocumentHelper.createXPath;

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.common.PropertiesUserObject;
import java.util.List;
import java.util.Properties;
import lombok.NonNull;
import org.dom4j.Document;
import org.dom4j.InvalidXPathException;
import org.dom4j.XPath;

/**
 * Content finder.
 *
 * @param <R> Search result data type
 * @author laurent
 */
public final class SearchEngineConfigurator<R extends List<?>>
    extends PropertiesUserObject {

  /**
   * Document to search on.
   */
  private final Document document;

  SearchEngineConfigurator(final @NonNull Properties config,
      final @NonNull Document searchDoc) {
    super(config);
    document = searchDoc;
  }


  @SuppressWarnings("unchecked")
  R findContent(final String expression)
      throws InvalidXPathException,
                 NoMatchesFoundException,
                 MissingPropertyException,
                 IncorrectPropertyValueException {

    XPath xpathSelector = createXPath(expression);

    R list;
    if (!xpathSelector.selectNodes(document).isEmpty()) {
      list = (R) xpathSelector.selectNodes(document);
    } else {
      list = null;
    }
    trace("Searched " + expression);
    if (list == null) {

      throw new NoMatchesFoundException(expression, isDebugOn());

    }

    return list;
  }
}

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
 * Content finder<br/>
 *
 * @param <resultType>
 * @author laurent
 */
public class SearchEngineConfigurator<resultType extends List<?>> extends PropertiesUserObject {

  private final Document mDoc;

  SearchEngineConfigurator(@NonNull Properties config, Document searchDoc) {
    super(config);
    mDoc = searchDoc;
  }


  @SuppressWarnings ("unchecked")
  resultType findContent(String pExpression)
      throws InvalidXPathException, NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {

    XPath xpathSelector = createXPath(pExpression);

    resultType list;
    if (!xpathSelector.selectNodes(mDoc).isEmpty()) {
      list = (resultType) xpathSelector.selectNodes(mDoc);
    } else {
      list = null;
    }
    trace("Searched " + pExpression);
    if (list == null) {

      throw new NoMatchesFoundException(pExpression, isDebugOn());

    }

    return list;
  }
}

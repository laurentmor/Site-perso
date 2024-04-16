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

package com.mor.blogengine.dao;

import com.mor.blogengine.xml.IXMLHandler;
import com.mor.blogengine.xml.XMLHandlerImpl;
import com.mor.blogengine.xpath.IBlogSearchEngine;
import com.mor.blogengine.xpath.SearchEngine;
import com.mor.common.PropertiesUserObject;
import java.util.Properties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;

/**
 * @author laurent
 */
@Setter
@Getter
public class BlogRepositoryBase extends PropertiesUserObject {

  /**
   * object that do I/O operations.
   */
  private IXMLHandler<DefaultElement> handler;
  /**
   * object that do search operations.
   */
  private IBlogSearchEngine<DefaultElement> searchEngine;


  public BlogRepositoryBase(final Document d, final @NonNull Properties p) {
    super(p);
    handler = XMLHandlerImpl.getInstanceForDoc(getConfig(), d);
    searchEngine = SearchEngine.getInstanceForDoc(getConfig(), d);
  }

}

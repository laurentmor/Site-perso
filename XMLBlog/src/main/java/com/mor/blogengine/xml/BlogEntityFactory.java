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
package com.mor.blogengine.xml;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.tree.DefaultElement;

/**
 * Factory class to create concrete blog model element
 *
 * @author Laurent
 */


public class BlogEntityFactory implements IBlogEntityFactory<DefaultElement> {

  /**
   * Create a map of entries
   *
   * @param pList the list to create from
   * @return Created map
   */
  @Override
  public Map<String, BlogEntry> createEntryMap(final List<DefaultElement> pList) {

    Map<String, BlogEntry> map = new HashMap<>();

    pList.stream().map(BlogEntry::new).forEachOrdered(entry -> {
      String ID = entry.getEntityiD();
      map.put(ID, entry);
    });

    return map;
  }

  /**
   * Create a map categories
   *
   * @param pList the list to create from
   * @return Created map
   */
  @Override
  public Map<String, BlogCategory> createCategoryMap(final List<DefaultElement> pList) {

    Map<String, BlogCategory> map = new HashMap<>();

    pList.stream().map(BlogCategory::new).forEachOrdered(category -> {
      String ID = category.getEntityiD();
      map.put(ID, category);
    });

    return map;
  }

  /**
   * Create a map of Comment
   *
   * @param pList the list to create from
   * @return Created map
   */
  @Override
  public Map<String, BlogComment> createCommentMap(List<DefaultElement> pList) {

    Map<String, BlogComment> map = new HashMap<>();

    pList.stream().map(BlogComment::new).forEachOrdered(comment -> {
      String ID = comment.getEntityiD();
      map.put(ID, comment);
    });

    return map;
  }
}

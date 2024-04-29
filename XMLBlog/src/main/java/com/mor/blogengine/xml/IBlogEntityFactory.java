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

// ~--- JDK imports ------------------------------------------------------------

import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;
import java.util.List;
import java.util.Map;

/**
 * Factory object to create concrete model objects from XML content.<br>
 *
 * @param <dataStructure> data holder
 * @author laurent
 */
public interface IBlogEntityFactory<dataStructure> {

  /**
   * Create a map of entries.
   *
   * @param pList the list to create from
   * @return Created map
   */
  Map<String, BlogEntry> createEntryMap(List<dataStructure> pList);


  /**
   * Create a map categories.
   *
   * @param pList the list to create from
   * @return Created map
   */
  Map<String, BlogCategory> createCategoryMap(List<dataStructure> pList);

  /**
   * Create a map of Comment.
   *
   * @param pList the list to create from
   * @return Created map
   */
  Map<String, BlogComment> createCommentMap(List<dataStructure> pList);
}

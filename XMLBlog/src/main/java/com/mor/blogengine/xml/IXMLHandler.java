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

import java.util.List;

/**
 * Interface providing management service for blog Structure.
 *
 * @param <T> Structure element type
 * @author Laurent
 * @version 1.0.3
 */
public interface IXMLHandler<T> {

  /**
   * add an element in blog structure.
   *
   * @param element the element to add
   * @return true if added correctly
   */
  boolean add(T element);

  /**
   * add a batch of elements in blog structure.
   *
   * @param addBatch the batch of element to add
   * @return true if added correctly
   */
  boolean add(List<T> addBatch);

  /**
   * remove a batch of elements in blog structure.
   *
   * @param removeBatch the batch of element to remove
   * @return true if removed correctly
   */
  boolean remove(List<T> removeBatch);

  /**
   * remove an element in blog structure.
   *
   * @param element element to remove
   * @return true if removed correctly
   */
  boolean remove(T element);

  /**
   * remove an element with a parent node in blog structure.
   *
   * @param child the child element to remove
   * @param parentID parent node unique ID
   * @return true if removed correctly
   */
  boolean remove(T child, String parentID);

  /**
   * remove a batch of elements with a parent node in blog structure.
   *
   * @param removeBatch the batch of element to remove
   * @param id the parent Id
   * @return true if removed correctly
   */
  boolean remove(List<T> removeBatch, String id);

  /**
   * add an element to a node in blog structure.
   *
   * @param root the node to add to
   * @param content the element to add
   * @return true if added correctly
   */
  boolean append(T root, T content);
}

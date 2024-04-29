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
package com.mor.blogengine.controllers;

// ~--- JDK imports ------------------------------------------------------------

import java.util.Map;

/**
 * Controller interface.
 *
 * @param <T> The type of entity managed by the controller
 * @param <D> datasource exception
 * @author laurent
 */
public interface IBlogElementController<T, D extends Throwable> {

  /**
   * Get all corresponding elements.
   *
   * @return All elements of concrete type
   * @throws D when dataSource exception occurs
   */
  Map<String, T> getAllElements() throws D;

  /**
   * get all elements with given parent ID.
   *
   * @param parentID the parent node ID to check for
   * @return All elements of concrete type
   * @throws D when dataSource exception occurs
   */
  Map<String, T> getAllElements(String parentID) throws D;

  /**
   * Add an element of given type to data structure.
   *
   * @param e The element to add
   * @return true if element was added correctly
   * @throws D when dataSource exception occurs
   */
  boolean addNewElement(T e) throws D;

  /**
   * Delete (remove) a given element from data Structure.
   *
   * @param e The element to remove
   * @return true if removed correctly
   * @throws D when dataSource exception occurs
   */
  boolean deleteElement(T e) throws D;

  /**
   * edit an element.
   *
   * @param editWhat the element to edit
   * @param withWhat what to replace it with
   * @return true if edited correctly
   * @throws D when dataSource exception occurs
   */
  boolean editElement(T editWhat, T withWhat) throws D;

  /**
   * get an element for date.
   *
   * @param d the date to search for
   * @return resulting element
   * @throws D when dataSource exception occurs
   */
  Map<String, T> getElementsForDate(String d) throws D;
}

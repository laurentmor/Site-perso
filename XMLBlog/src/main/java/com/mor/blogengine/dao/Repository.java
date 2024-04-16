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


import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import java.util.List;

/**
 * This repository interface is intended to hide implementation details. from the controller layer
 * For instance: adding a BlogEntry is really simple process,<br/> the
 * {@link #add(java.lang.Object)} implementation would use null
 * {@link #getElementsForCriteria(java.lang.Object, java.lang.String) } to check if such entry
 * exists in which case return false<br/> User would be notified by controller and asked if he'd
 * like to edit the found entry
 * <p>
 * Changes : Removed find method to generalize search mechanism.
 *
 * @param <T> Repository type @see model layer
 * @param <R> Specify return type of blog data structure
 * @param <S> search criteria data holder
 * @param <D> data source exception
 * @author laurent
 */
public interface Repository<T, R, S, D extends Throwable> {

  /**
   * add an element to blog.
   *
   * @param t model object to add
   * @return true is added correctly
   * @throws ElementExistingException if given element is present
   */
  boolean add(T t) throws ElementExistingException, D;

  /**
   * append an element to a parent one to a blog.
   *
   * @param what     the element to append
   * @param parentId the ID of parent element to append to
   * @return true if element appended correctly
   */
  boolean append(T what, String parentId)
      throws NoMatchesFoundException, D;

  /**
   * append an element to a parent one to a blog.
   *
   * @param what the element to append
   * @return true if element appended correctly
   */
  boolean append(T what) throws D;

  /**
   * remove a category to blog.
   *
   * @param t the element to remove
   * @return true if element removed correctly
   */
  boolean remove(T t) throws NoMatchesFoundException, D;

  /**
   * edit a element in a blog.
   *
   * @param t  the element to edit
   * @param t2 the new element
   * @return true if element edited correctly
   */
  boolean edit(T t, T t2)
      throws NoMatchesFoundException, D;

  /**
   * Enable the search for certain category and criteria in XML.
   *
   * @param searchParam what to search<br/>
   * @param paramValue  search for what criteria<br/>
   * @return list of results<br/>
   */
  List<R> getElementsForCriteria(S searchParam, String paramValue)
      throws NoMatchesFoundException;
}

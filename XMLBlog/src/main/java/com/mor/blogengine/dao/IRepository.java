/**
 * Copyright 2021 Laurent
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mor.blogengine.dao;


import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;

import java.util.List;

/**
 * <pre>
 * This repository interface is intended to hide implementation details
 * from the controller layer
 * For instance: adding a BlogEntry is really simple proces,<br/>
 * the {@link #add(java.lang.Object)} implementation would use null {@link #getElementsForCriteria(java.lang.Object, java.lang.String)
 * }
 * to check if such entry exsist in which case return false<br/>
 * User would be notified by controller and asked if he'd like to edit the found
 * entry
 *
 * Changes : Removed find method to generalize search mecanism.
 *
 * @param <T> Repository type @see model layer
 * @param <ret> Specify return type of blog data structure
 * @param <searchParamsType> search criterias data holder
 * @param <datasourceException> if there is a problem with data source the
 * proper data source exception is thrown
 * @author laurent
 *
 * </pre>
 */
public interface IRepository<T, ret, searchParamsType, datasourceException extends Throwable> {

    /**
     * add an element to blog
     *
     * @param t model object to add
     * @return true is added correctly
     * @throws ElementExistingException if given element is present
     */
    boolean add(final T t) throws ElementExistingException, datasourceException;

    /**
     * append an element to a parent one to a blog
     *
     * @param what     the element to append
     * @param parentID the ID of parent element to append to
     * @return true if element appended correctly
     */
    boolean append(final T what, final String parentID)
            throws NoMatchesFoundException, datasourceException;

    /**
     * append an element to a parent one to a blog
     *
     * @param what the element to append
     * @return true if element appended correctly
     */
    boolean append(final T what) throws datasourceException;

    /**
     * remove a category to blog
     *
     * @param t the element to remove
     * @return true if element removed correctly
     */
    boolean remove(final T t) throws NoMatchesFoundException, datasourceException;

    /**
     * edit a element in a blog
     *
     * @param t  the element to edit
     * @param t2 the new element
     * @return true if element edited correctly
     */
    boolean edit(final T t, final T t2)
            throws NoMatchesFoundException, datasourceException, ElementExistingException;

    /**
     * Enable the search for certain category and criteria in XML <br/>
     *
     * @param searchParam what to search<br/>
     * @param paramValue  search for what criteria<br/>
     * @return list of results<br/>
     */
    List<ret> getElementsForCriteria(searchParamsType searchParam, String paramValue)
            throws NoMatchesFoundException;
}


//~ Formatted by Jindent --- http://www.jindent.com

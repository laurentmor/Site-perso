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
package com.mor.blogengine.controllers;

//~--- JDK imports ------------------------------------------------------------

import java.util.Map;

/**
 * @param <Type>                The type of entity managed by the controller
 * @param <dataSourceException>
 * @author laurent
 */
public interface IBlogElementController<Type, dataSourceException extends Throwable> {

    /**
     * @return All elements of concrete type
     */
    Map<String, Type> getAllElements() throws dataSourceException;

    /**
     * @param parentID the parent node ID to check for
     * @return All elements of concrete type
     */
    Map<String, Type> getAllElements(String parentID) throws dataSourceException;

    /**
     * Add an element of given type to data structure
     *
     * @param e The element to add
     * @return true if element was added correctly
     */
    boolean addNewElement(Type e) throws dataSourceException;

    /**
     * Delete (remove) a given element from data Structure
     *
     * @param e The element to remove
     * @return true if removed correctly
     */
    boolean deleteElement(Type e) throws dataSourceException;

    /**
     * edit an element
     *
     * @param editWhat the element to edit
     * @param withWhat what to replace it with
     * @return true if edited correctly
     */
    boolean editElement(Type editWhat, Type withWhat) throws dataSourceException;

    /**
     * get an element for date
     *
     * @param d the date to search for
     * @return resulting element
     */
    Map<String, Type> getElementsForDate(String d) throws dataSourceException;
}


//~ Formatted by Jindent --- http://www.jindent.com

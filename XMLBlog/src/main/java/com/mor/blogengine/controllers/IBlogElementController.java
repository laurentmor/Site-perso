/* 
 * The MIT License
 *
 * Copyright 2015 Laurent Morissette.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mor.blogengine.controllers;

//~--- JDK imports ------------------------------------------------------------

import java.util.Map;

/**
 *
 * @param <Type> The type of entity managed by the controller
 * @param <dataSourceException>
 * @author laurent
 */
public interface IBlogElementController<Type, dataSourceException extends Throwable> {

    /**
     *
     * @return All elements of concrete type
     * @throws dataSourceException
     */
    public Map<String, Type> getAllElements() throws dataSourceException;

    /**
     *
     * @param parentID the parent node ID to check for
     * @return All elements of concrete type
     * @throws dataSourceException
     *
     */
    public Map<String, Type> getAllElements(String parentID) throws dataSourceException;

    /**
     * Add an element of given type to data structure
     *
     * @param e The element to add
     * @return true if element element was added correctly
     * @throws dataSourceException
     */
    public boolean addNewElement(Type e) throws dataSourceException;

    /**
     * Delete (remove) a given element from data Structure
     *
     * @param e The element to remove
     * @return true if removed correctly
     * @throws dataSourceException
     */
    public boolean deleteElement(Type e) throws dataSourceException;

    /**
     * edit an element
     *
     * @return true if edited correctly
     * @throws dataSourceException
     * @param editWhat the element to edit
     * @param withWhat what to replace it with
     */
    public boolean editElement(Type editWhat, Type withWhat) throws dataSourceException;

    /**
     * get an element for date
     *
     * @param d the date to search for
     * @return resulting element
     * @throws dataSourceException
     */
    public Map<String, Type> getElementsForDate(String d) throws dataSourceException;
}


//~ Formatted by Jindent --- http://www.jindent.com

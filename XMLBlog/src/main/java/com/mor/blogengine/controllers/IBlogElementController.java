
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

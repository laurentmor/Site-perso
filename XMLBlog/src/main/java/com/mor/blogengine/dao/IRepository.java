/* 
 * The MIT License
 *
 * Copyright 2015 laurent.
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
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;

//~--- JDK imports ------------------------------------------------------------
import java.util.List;

/**
 * <pre>
 * This repository inteface is intended to hide implementation details
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
     * @throws datasourceException
     * @throws datasourceException
     * @throws datasourceException if there is a problem with data source
     */
    public boolean add(final T t) throws ElementExistingException, datasourceException;

    /**
     * append a element to a parernt one to a blog
     *
     * @param what the element to append
     * @param parentID the Id of parent element to append to
     * @return true if element appended correctly
     * @throws NoMatchesFoundException
     * @throws ElementExistingException if element to append already exist
     * @throws datasourceException
     *
     */
    public boolean append(final T what, final String parentID)
            throws NoMatchesFoundException, ElementExistingException, datasourceException;

    /**
     * append a element to a parernt one to a blog
     *
     * @param what the element to append
     *
     * @return true if element appended correctly
     * @throws NoMatchesFoundException
     * @throws ElementExistingException if element to append already exist
     * @throws datasourceException
     *
     */
    public boolean append(final T what) throws NoMatchesFoundException, ElementExistingException, datasourceException;

    /**
     * remove a category to blog
     *
     * @param t the element to remove
     * @return true if element removed correctly
     * @throws NoMatchesFoundException
     * @throws datasourceException
     */
    public boolean remove(final T t) throws NoMatchesFoundException, datasourceException;

    /**
     * edit a element in a blog
     *
     * @param t the element to edit
     * @param t2 the new element
     * @return true if element edited correctly
     * @throws NoMatchesFoundException
     * @throws ElementExistingException if element to add exist
     * @throws datasourceException
     */
    public boolean edit(final T t, final T t2)
            throws NoMatchesFoundException, datasourceException, ElementExistingException;

    /**
     * Enable the search for certain category and criteria in XML <br/>
     *
     * @param searchParam what to search<br/>
     * @param paramValue search for what criteria<br/>
     *
     * @return list of results<br/>
     * @throws NoMatchesFoundException
     */
    public List<ret> getElementsForCriteria(searchParamsType searchParam, String paramValue)
            throws NoMatchesFoundException;
}


//~ Formatted by Jindent --- http://www.jindent.com

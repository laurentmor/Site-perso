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
package com.mor.blogengine.xml;

//~--- JDK imports ------------------------------------------------------------
import java.util.List;

/**
 * Interface providing management service for blog Structure
 *
 * @param <T> Structure element type
 * @author Laurent
 * @version 1.0.3
 */
public interface IXMLHandler<T> {

    /**
     * add an element in blog structure
     *
     * @param element the element to add
     * @return true if added correctly
     */
    public boolean add(T element);

    /**
     * add a batch of elements in blog structure
     *
     * @param addBatch the batch of element to add
     * @return true if added correctly
     */
    public boolean add(List<T> addBatch);

    /**
     * remove a batch of elements in blog structure
     *
     * @param removeBatch the batch of element to remove
     * @return true if removed correctly
     */
    public boolean remove(List<T> removeBatch);

    /**
     * remove an element in blog structure
     *
     * @param element element to remove
     * @return true if removed correctly
     */
    public boolean remove(T element);

    /**
     * remove an element with a parent node in blog structure
     *
     * @param child the child element to remove
     * @param parentID parent node unique ID
     * @return true if removed correctly
     */
    public boolean remove(T child, String parentID);

    /**
     * remove a batch of elements with a parent node in blog structure
     *
     * @param removeBatch the batch of element to remove
     * @param parentID
     * @return true if removed correctly
     */
    public boolean remove(List<T> removeBatch, String parentID);

    /**
     * add an element to a node in blog structure
     *
     * @param root the node to add to
     * @param content the element to add
     * @return true if added correctly
     */
    public boolean append(T root, T content);
}


//~ Formatted by Jindent --- http://www.jindent.com

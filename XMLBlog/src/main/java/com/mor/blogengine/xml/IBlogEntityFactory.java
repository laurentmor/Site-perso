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
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;
import java.util.List;
import java.util.Map;

/**
 * Factory object to create concreate model objects from XML content<br/>
 *
 * @param <dataStructure>
 *
 * @author laurent
 *
 */
public interface IBlogEntityFactory<dataStructure> {

    /**
     * Create a map of entries
     *
     * @param pList the list to create from
     * @return Created map
     */
    public Map<String, BlogEntry> createEntryMap(List<dataStructure> pList);

    /**
     * Create a map categories
     *
     * @param pList the list to create from
     * @return Created map
     */
    public Map<String, BlogCategory> createCategoryMap(List<dataStructure> pList);

    /**
     * Create a map of Comment
     *
     * @param pList the list to create from
     * @return Created map
     */
    public Map<String, BlogComment> createCommentMap(List<dataStructure> pList);
}


//~ Formatted by Jindent --- http://www.jindent.com

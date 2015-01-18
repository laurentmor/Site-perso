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

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;

import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class to create concreate blog model element
 *
 * @author Laurent
 *
 */
public class BlogEntityFactory implements IBlogEntityFactory<DefaultElement> {

    /**
     * Create a map of entries
     *
     * @param pList the list to create from
     * @return Created map
     */
    @Override
    public Map<String, BlogEntry> createEntryMap(final List<DefaultElement> pList) {

        Map<String, BlogEntry> map = new HashMap<String, BlogEntry>();

        for (DefaultElement defaultElement : pList) {
            BlogEntry entry = new BlogEntry(defaultElement);
            String ID = entry.getEntityID();

            map.put(ID, entry);
        }

        return map;
    }

    /**
     * Create a map categories
     *
     * @param pList the list to create from
     * @return Created map
     */
    @Override
    public Map<String, BlogCategory> createCategoryMap(final List<DefaultElement> pList) {

        Map<String, BlogCategory> map = new HashMap<String, BlogCategory>();

        for (DefaultElement defaultElement : pList) {
            BlogCategory category = new BlogCategory(defaultElement);
            String ID = category.getEntityID();

            map.put(ID, category);
        }

        return map;
    }

    /**
     * Create a map of Comment
     *
     * @param pList the list to create from
     * @return Created map
     */
    @Override
    public Map<String, BlogComment> createCommentMap(List<DefaultElement> pList) {

        Map<String, BlogComment> map = new HashMap<String, BlogComment>();

        for (DefaultElement defaultElement : pList) {
            BlogComment comment = new BlogComment(defaultElement);
            String ID = comment.getEntityID();

            map.put(ID, comment);
        }

        return map;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

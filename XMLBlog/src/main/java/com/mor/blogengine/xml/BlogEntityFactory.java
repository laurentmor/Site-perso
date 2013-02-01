
/*
* BlogEntityFactory.java
*
* Created on 23 mai 2007, 19:54
*
* To change this template, choose Tools | Template Manager
* and open the template in the editor.
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
public class BlogEntityFactory implements IBlogEntityFactory {

    /**
     * Create a map of entries
     *
     * @param pList the list to  create from
     * @return Created map
     */
    @Override
    public Map<String, BlogEntry> createEntryMap(List pList) {
        List<DefaultElement>   list = pList;
        Map<String, BlogEntry> map  = new HashMap<String, BlogEntry>();

        for (DefaultElement defaultElement : list) {
            BlogEntry entry = new BlogEntry(defaultElement);
            String    ID    = entry.getEntityID();

            map.put(ID, entry);
        }

        return map;
    }

    /**
     * Create a map categories
     *
     * @param pList the list to  create from
     * @return Created map
     */
    @Override
    public Map<String, BlogCategory> createCategoryMap(List pList) {
        List<DefaultElement>      list = pList;
        Map<String, BlogCategory> map  = new HashMap<String, BlogCategory>();

        for (DefaultElement defaultElement : list) {
            BlogCategory category = new BlogCategory(defaultElement);
            String       ID       = category.getEntityID();

            map.put(ID, category);
        }

        return map;
    }

    /**
     * Create a map of Comment
     *
     * @param pList the list to  create from
     * @return Created map
     */
    @Override
    public Map<String, BlogComment> createCommentMap(List pList) {
        List<DefaultElement>     list = pList;
        Map<String, BlogComment> map  = new HashMap<String, BlogComment>();

        for (DefaultElement defaultElement : list) {
            BlogComment comment = new BlogComment(defaultElement);
            String      ID      = comment.getEntityID();

            map.put(ID, comment);
        }

        return map;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

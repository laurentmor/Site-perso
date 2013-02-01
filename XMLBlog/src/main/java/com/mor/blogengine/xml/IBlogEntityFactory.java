
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.xml;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;
import java.util.Map;

/**
 * Factory object to create concreate model objects from XML content<br/>
 *
 * @param <T>
 * @author laurent
 */
public interface IBlogEntityFactory<T> {

    /**
     * Create a map of entries
     *
     * @param pList the list to  create from
     * @return Created map
     */
    public Map<String, T> createEntryMap(List pList);

    /**
     * Create a map categories
     *
     * @param pList the list to  create from
     * @return Created map
     */
    public Map<String, T> createCategoryMap(List pList);

    /**
     * Create a map of Comment
     *
     * @param pList the list to  create from
     * @return Created map
     */
    public Map<String, T> createCommentMap(List pList);
}


//~ Formatted by Jindent --- http://www.jindent.com

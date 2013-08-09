
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

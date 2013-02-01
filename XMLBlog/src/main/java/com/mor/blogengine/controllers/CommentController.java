
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.controllers;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.dao.BlogCommentRepository;
import com.mor.blogengine.dao.IRepository;
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.xml.BlogEntityFactory;
import com.mor.blogengine.xml.IBlogEntityFactory;
import com.mor.blogengine.xpath.SearchCriteria;
import com.mor.common.PropertiesUserObject;
import java.net.URL;

import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laurent
 */
public class CommentController extends PropertiesUserObject implements IBlogElementController<BlogComment, DocumentException> {

    /**
     * Model objects factory
     */
    private IBlogEntityFactory                                                          factory = null;
    private IRepository<BlogComment, DefaultElement, SearchCriteria, DocumentException> repo    = null;

    /**
     *
     * @param repository
     */
    public CommentController( Properties config) {
        this.mConfig=config;
        
        repo    = new BlogCommentRepository(config);
        factory = new BlogEntityFactory();
    }

    @Override
    public Map<String, BlogComment> getAllElements() {
        return null;
    }

    @Override
    public boolean addNewElement(BlogComment e) throws DocumentException {
        try {
            return repo.append(e, e.getEntryID());
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementExistingException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean deleteElement(BlogComment e) throws DocumentException {
        try {
            return repo.remove(e);
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean editElement(BlogComment what, BlogComment with) throws DocumentException {
        try {
            return repo.edit(what, with);
        } catch (NoMatchesFoundException ex) {
            return false;
        } catch (ElementExistingException ex) {
            return false;
        }
    }

    @Override
    public Map<String, BlogComment> getElementsForDate(String d) {
        return null;
    }

    @Override
    public Map<String, BlogComment> getAllElements(String parentID) throws DocumentException {
        try {
            List list = repo.getElementsForCriteria(SearchCriteria.ALL, parentID);

            if (list != null) {
                return factory.createCommentMap(list);
            }
        } catch (NoMatchesFoundException ex) {
            return null;
        }

        return null;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

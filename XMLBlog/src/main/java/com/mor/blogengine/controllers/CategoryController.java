
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.controllers;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.dao.BlogCategoryRepository;
import com.mor.blogengine.dao.IRepository;
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.xpath.SearchCriteria;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

/**
 *
 * @author laurent
 */
public class CategoryController extends BlogControllerBase implements IBlogElementController<BlogCategory, DocumentException> {

    /**
     * reppository to interface with data source
     */
    private IRepository<BlogCategory, DefaultElement, SearchCriteria, DocumentException> repo = null;

    /**
     *
     * @param config
     */
    public CategoryController(Properties config) {
        super(config);
        repo = new BlogCategoryRepository(mConfig, getDocument());

    }

    @Override
    public Map<String, BlogCategory> getAllElements() {
        try {
            List<DefaultElement> list = repo.getElementsForCriteria(SearchCriteria.ALL, null);

            if (list != null) {
                return getFactory().createCategoryMap(list);
            } else {
                trace("test");
            }
        }
        catch (NoMatchesFoundException ex) {
            trace(ex.getMessage());
            return null;
        }

        return null;
    }

    @Override
    public boolean addNewElement(BlogCategory e) throws DocumentException {
        try {
            return repo.add(e);
        }
        catch (com.mor.blogengine.exception.ElementExistingException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    @Override
    public boolean deleteElement(BlogCategory e) throws DocumentException {
        try {
            return repo.remove(e);
        }
        catch (NoMatchesFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean editElement(BlogCategory what, BlogCategory with) throws DocumentException {
        try {
            return repo.edit(what, with);
        }
        catch (NoMatchesFoundException ex) {
            return false;
        }
        catch (ElementExistingException ex) {
            return false;
        }
    }

    @Override
    public Map<String, BlogCategory> getElementsForDate(String d) {
        return null;
    }

    @Override
    public Map<String, BlogCategory> getAllElements(String parentID) throws DocumentException {
        throw new UnsupportedOperationException("Use non-parametrised version");
    }
}
//~ Formatted by Jindent --- http://www.jindent.com



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.xpath.SearchCriteria;

import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;

/**
 *
 * @author laurent
 */
public class BlogCategoryRepository extends BlogRepositoryBase
        implements IRepository<BlogCategory, DefaultElement, SearchCriteria, DocumentException> {

    /**
     * Default constructor
     *
     * @param d document instance that holds blog data
     * @param config global configuration file for application
     */
    public BlogCategoryRepository(final Properties config, final Document d) {
        super(d, config);

    }

    /**
     * add a category to blog
     *
     * @param t the category to add
     * @return true if Category added correctly
     * @throws ElementExistingException if element to add exist
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean add(BlogCategory t) throws ElementExistingException, DocumentException {

        List<DefaultElement> list = null;
        boolean added = false;

        try {
            list = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());

            if (list == null) {
                throw new NoMatchesFoundException();
            } else {
                trace("Element already in ");

                throw new ElementExistingException();
            }
        }
        catch (NoMatchesFoundException ex) {
            try {
                trace("No match of element found proceeding to add operation");
            }
            catch (MissingPropertyException | IncorrectPropertyValueException ex1) {
                Logger.getLogger(BlogCategoryRepository.class.getName()).log(Level.SEVERE, null, ex1);
            }

            added = handler.add(t.toElement());

        }
        catch (MissingPropertyException | IncorrectPropertyValueException ex) {
            Logger.getLogger(BlogCategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return added;
    }

    /**
     * append a category to a parernt one to a blog
     *
     * @param what the category to append
     * @param parentID the Id of parent category to append to
     * @return true if Category appended correctly
     * @deprecated do not use sun-category concept not considered
     */
    @Override
    public boolean append(BlogCategory what, String parentID) {
        return false;
    }

    /**
     * remove a category to blog
     *
     * @param t the category to remove
     * @return true if Category removed correctly
     * @throws NoMatchesFoundException
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean remove(BlogCategory t) throws NoMatchesFoundException, DocumentException {
        boolean removed = false;

        List<DefaultElement> foundMatches = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());

        if (foundMatches == null) {
            try {
                trace("No match of element found remove failed");
            }
            catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                Logger.getLogger(BlogCategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            }

            throw new NoMatchesFoundException();
        } else {
            removed = handler.remove(foundMatches);
        }

        return removed;
    }

    /**
     * edit a category in a blog
     *
     * @param t the category to edit
     * @param t2 the new category
     * @return true if Category edited correctly
     * @throws NoMatchesFoundException
     * @throws ElementExistingException if element to add exist
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean edit(BlogCategory t, BlogCategory t2)
            throws NoMatchesFoundException, DocumentException, ElementExistingException {
        boolean edited = false;

        List<DefaultElement> foundMatches = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());

        if ((foundMatches == null)) {
            try {
                trace("No match of element found edit failed");
            }
            catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                Logger.getLogger(BlogCategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            }

            throw new NoMatchesFoundException();
        } else {
            try {
                edited = remove(t) && add(t2);
            }
            catch (ElementExistingException ex) {
                throw ex;
            }

            return edited;
        }
    }

    /**
     * Enable the search for certain category and criteria in XML <br/>
     *
     * @param searchParam what to search<br/>
     * @param paramValue search for what criteria<br/>
     *
     * @return list of results<br/>
     * @throws NoMatchesFoundException
     */
    @Override
    public List<DefaultElement> getElementsForCriteria(SearchCriteria searchParam, String paramValue)
            throws NoMatchesFoundException {
        List<DefaultElement> list = searchEngine.getElementsForCriteria("Category",
                searchParam, paramValue);

        return list;
    }

    /**
     * append a category to a parernt one to a blog
     *
     * @param what the category to append
     * @return true if Category appended correctly
     * @throws NoMatchesFoundException
     * @throws ElementExistingException if element to append already exist
     * @throws DocumentException if there is an issue with XML structure
     * @deprecated do not use sun-category concept not considered
     */
    @Override
    public boolean append(BlogCategory what)
            throws NoMatchesFoundException, ElementExistingException, DocumentException {
        return append(what, null);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

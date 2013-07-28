
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogEntry;
import com.mor.blogengine.xpath.SearchCriteria;
import com.mor.blogengine.xpath.SearchEngine;

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
public class BlogEntryRepository extends BlogRepositoryBase
        implements IRepository<BlogEntry, DefaultElement, SearchCriteria, DocumentException> {

    /**
     * Default constructor
     *
     * @param repo document instance that holds blog data
     * @param config global configuration file for application
     */
    public BlogEntryRepository(final Properties config, final Document d) {
      super(d, config);
        
    }

    /**
     * add an entry to blog
     *
     * @param t the entry to add
     * @return true if entry added correctly
     * @throws ElementExistingException if element to add exist
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean add(BlogEntry t) throws ElementExistingException, DocumentException {
        
        boolean added = false;
        List<DefaultElement> list = null;

        try {
            list = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(BlogEntryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (list == null) {
                      
            added = handler.add(t.toElement());

            return added;
        } else {
            throw new ElementExistingException();
        }
    }

    /**
     * append an entry to a parernt one to a blog
     *
     * @param what the category to append
     * @param toWhat the Id of parent category to append to
     * @return true if Category appended correctly
     * @deprecated do not use sub-category concept not considered
     */
    @Override
    public boolean append(BlogEntry what, String toWhat) {
        return false;
    }

    /**
     * remove an entry to blog
     *
     * @param t the category to remove
     * @return true if Category removed correctly
     * @throws NoMatchesFoundException
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean remove(BlogEntry t) throws NoMatchesFoundException, DocumentException {
        boolean removed = false;
        

        

        List<DefaultElement> list = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());

        if (list == null) {
            throw new NoMatchesFoundException();
        }

        removed = handler.remove(t.toElement());

        return removed;
    }

    /**
     * edit an centry in a blog
     *
     * @param t the entry to edit
     * @param t2 the new entry
     * @return true if entry edited correctly
     * @throws NoMatchesFoundException
     * @throws ElementExistingException if element to add exist
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean edit(BlogEntry t, BlogEntry t2)
            throws NoMatchesFoundException, DocumentException, ElementExistingException {
        try {
            boolean edited = remove(t) && add(t2);

            return edited;
        } catch (ElementExistingException ex) {
            throw ex;
        }
    }

    /**
     * Enable the search for certain entry and criteria in XML <br/>
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
        List<DefaultElement> list = new SearchEngine(mConfig,doc).getElementsForCriteria("Entry",
                searchParam, paramValue);

        if (list == null) {
            throw new NoMatchesFoundException();
        }

        return list;
    }

    @Override
    public boolean append(BlogEntry what) throws NoMatchesFoundException, ElementExistingException, DocumentException {
        return append(what, null);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.controllers;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.dao.BlogEntryRepository;
import com.mor.blogengine.dao.IRepository;
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogEntry;
import com.mor.blogengine.xml.BlogEntityFactory;
import com.mor.blogengine.xml.IBlogEntityFactory;
import com.mor.blogengine.xpath.SearchCriteria;
import com.mor.common.PropertiesUserObject;
import java.net.URL;

import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laurent
 */
public class EntryController extends PropertiesUserObject implements IBlogElementController<BlogEntry, DocumentException> {

    private IBlogEntityFactory factory = null;
    private IRepository<BlogEntry, DefaultElement, SearchCriteria, DocumentException> repo = null;

    /**
     *
     * @param repository
     */
    public EntryController( Properties config) {
        this.mConfig = config;
        
        repo = new BlogEntryRepository(config);
        factory = new BlogEntityFactory();

    }

    @Override
    public Map<String, BlogEntry> getAllElements() throws DocumentException {
        try {
            return factory.createEntryMap(repo.getElementsForCriteria(SearchCriteria.ALL, null));
        } catch (NoMatchesFoundException ex) {
            trace(ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean addNewElement(BlogEntry e) throws DocumentException {
        try {
            return repo.add(e);
        } catch (ElementExistingException ex) {
            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean deleteElement(BlogEntry e) throws DocumentException {
        try {
            return repo.remove(e);
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean editElement(BlogEntry what, BlogEntry with) throws DocumentException {
        try {
            return repo.edit(what, with);
        } catch (NoMatchesFoundException ex) {
            return false;
        } catch (ElementExistingException ex) {
            return false;
        }
    }

    @Override
    public Map<String, BlogEntry> getElementsForDate(String d) {
        try {
            return factory.createEntryMap(repo.getElementsForCriteria(SearchCriteria.DATE, d));
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * For the moment there's is no use of  definition as no needs were shown
     * To introduce parent entry concept
     *
     * @param parentID  the parent node ID to check for
     * @throws DocumentException  if there is an issue with XML structure
     */
    @Override
    public Map<String, BlogEntry> getAllElements(String parentID) throws DocumentException {
        return getAllElements();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


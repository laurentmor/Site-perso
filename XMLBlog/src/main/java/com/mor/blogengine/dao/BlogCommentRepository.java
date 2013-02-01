
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.xml.IXmlFileManager;
import com.mor.blogengine.xml.XmlFileManagerImpl;
import com.mor.blogengine.xpath.SearchCriteria;
import com.mor.blogengine.xpath.SearchEngine;
import com.mor.common.PropertiesUserObject;

import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;
import java.util.Properties;

/**
 *
 * @author laurent
 */
public class BlogCommentRepository extends PropertiesUserObject
        implements IRepository<BlogComment, DefaultElement, SearchCriteria, DocumentException> {
    

    /**
     * Default constructor
     *
     * @param repo document instance that holds blog data
     * @param config global configuration file for application
     */
    public BlogCommentRepository(final Properties config) {
        this.mConfig  = config;
        
 }

    /**
     *    add a comment to blog
     *
     *    @param t the comment to add
     *    @return true if comment added correctly
     *    @throws ElementExistingException if element to add exist
     *    @deprecated use {@link #append(BlogComment what, String parentID)}
     */
    @Override
    public boolean add(BlogComment t) throws ElementExistingException {
        return false;
    }

    /**
     * append a comment to a parernt entry  to a blog
     *
     * @param what  the comment to append
     * @param parentID  the Id of parent entry to append to
     * @return true if Category appended correctly
     * @throws NoMatchesFoundException
     *
     */
    @Override
    public boolean append(BlogComment what, String parentID) throws NoMatchesFoundException {
        trace("Appending element... " + what.getCommentText());

        List<DefaultElement> foundList = new SearchEngine(mConfig).getElementsForCriteria("Entry",
                                             SearchCriteria.SINGLE, parentID);
        

        try {
            DefaultElement                  relatedEntry   = foundList.get(0);
            IXmlFileManager<DefaultElement> xmlFileManager = XmlFileManagerImpl.getInstanceForDoc(mConfig);
            boolean appended = xmlFileManager.append(relatedEntry, what.toElement());

            xmlFileManager.saveChanges();

            return appended;
        } catch (Exception ex) {
            trace(ex.getMessage());
        }

        throw new NoMatchesFoundException();
    }

    /**
     * remove a comment to blog
     *
     * @param t the comment to remove
     * @return true if Comment removed correctly
     *
     * @throws NoMatchesFoundException
     * @throws DocumentException  if there is an issue with XML structure
     */
    @Override
    public boolean remove(BlogComment t) throws NoMatchesFoundException, DocumentException {
        List<DefaultElement> list = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());
        

        if (list != null) {
            trace("comment found removing it ...");

            return XmlFileManagerImpl.getInstanceForDoc(mConfig).remove(list.get(0), t.getEntryID());
        }

        return false;
    }

    /**
     * edit a comment in a blog
     *
     * @param t the comment to edit
     * @param t2  the new comment
     * @return true if Comment edited correctly
     * @throws NoMatchesFoundException
     * @throws DocumentException  if there is an issue with XML structure
     */
    @Override
    public boolean edit(BlogComment t, BlogComment t2) throws NoMatchesFoundException, DocumentException {
        boolean removed  = remove(t);
        boolean appended = append(t2, t2.getEntryID());

        return removed && appended;
    }

    /**
     *     Enable the search for comment and criteria in XML <br/>
     *
     *     @param searchParam  what to search<br/>
     *     @param paramValue  search for what criteria<br/>
     *
     * @return list of results<br/>
     * @throws NoMatchesFoundException
     */
    @Override
    public List<DefaultElement> getElementsForCriteria(SearchCriteria searchParam, String paramValue)
            throws NoMatchesFoundException {
        List<DefaultElement> list = new SearchEngine(mConfig).getElementsForCriteria("Comment",
                                        searchParam, paramValue);

        if (list == null) {
            throw new NoMatchesFoundException();
        }

        return list;
    }

    @Override
    public boolean append(BlogComment what)
            throws NoMatchesFoundException, ElementExistingException, DocumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

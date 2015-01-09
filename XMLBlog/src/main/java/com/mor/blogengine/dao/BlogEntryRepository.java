/* 
 * The MIT License
 *
 * Copyright 2015 laurent.
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
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogEntry;
import com.mor.blogengine.xpath.SearchCriteria;
import com.mor.blogengine.xpath.SearchEngine;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

/**
 *
 * @author laurent
 */
public class BlogEntryRepository extends BlogRepositoryBase
        implements IRepository<BlogEntry, DefaultElement, SearchCriteria, DocumentException> {

    /**
     * Default constructor
     *
     * @param d document instance that holds blog data
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
        }
        catch (NoMatchesFoundException ex) {
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
        }
        catch (ElementExistingException ex) {
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
        List<DefaultElement> list = new SearchEngine(mConfig, doc).getElementsForCriteria("Entry",
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

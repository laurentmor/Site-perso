/**
 * Copyright 2021 Laurent
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.xpath.SearchCriteria;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

import javax.naming.ConfigurationException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public BlogCategoryRepository(final Properties config, final Document d) throws ConfigurationException {
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
    public boolean add(BlogCategory t) throws ElementExistingException, DocumentException, ConfigurationException {

        List<DefaultElement> list;
        boolean added = false;

        try {
            list = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());

            if (list == null) {
                throw new NoMatchesFoundException();
            } else {
                trace("Element already in ");

                throw new ElementExistingException();
            }
        } catch (NoMatchesFoundException ex) {
            try {
                trace("No match of element found proceeding to add operation");
            } catch (MissingPropertyException | IncorrectPropertyValueException ex1) {
                Logger.getLogger(BlogCategoryRepository.class.getName()).log(Level.SEVERE, null, ex1);
            }

            added = handler.add(t.toElement());

        } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
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
     *  do not use subcategory concept not considered
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
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean remove(BlogCategory t) throws NoMatchesFoundException, DocumentException {
        boolean removed;

        List<DefaultElement> foundMatches = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());

        if (foundMatches == null) {
            try {
                trace("No match of element found remove failed");
            } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
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
     * @throws ElementExistingException if element to add exist
     * @throws DocumentException if there is an issue with XML structure
     */
    @Override
    public boolean edit(BlogCategory t, BlogCategory t2)
            throws NoMatchesFoundException, DocumentException, ElementExistingException {

        List<DefaultElement> foundMatches = getElementsForCriteria(SearchCriteria.SINGLE, t.getEntityID());

        if ((foundMatches == null)) {
            try {
                trace("No match of element found edit failed");
            } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                Logger.getLogger(BlogCategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            }

            throw new NoMatchesFoundException();
        } else {
            AtomicBoolean edited = new AtomicBoolean(false);
//            edited = false;
            try {
                if (remove(t) && add(t2)) edited.set(true);
            } catch (ElementExistingException | ConfigurationException ex) {
                try {
                    throw ex;
                } catch (ConfigurationException e) {
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
            }

            return edited.get();
        }
    }

    /**
     * Enable the search for certain category and criteria in XML <br/>
     *
     * @param searchParam what to search<br/>
     * @param paramValue search for what criteria<br/>
     *
     * @return list of results<br/>
     */
    @Override
    public List<DefaultElement> getElementsForCriteria(SearchCriteria searchParam, String paramValue)
            throws NoMatchesFoundException {

        return searchEngine.getElementsForCriteria("Category",
                searchParam, paramValue);
    }

    /**
     * append a category to a parernt one to a blog
     *
     * @param what the category to append
     * @return true if Category appended correctly
     * @throws ElementExistingException if element to append already exist
     * @throws DocumentException if there is an issue with XML structure
     *
     */
    @Override
    public boolean append(BlogCategory what)
            throws NoMatchesFoundException, ElementExistingException, DocumentException {
        return append(what, null);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

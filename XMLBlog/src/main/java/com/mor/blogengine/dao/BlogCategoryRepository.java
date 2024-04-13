/*
 * Copyright (c) 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.mor.blogengine.dao;

//~--- non-JDK imports --------------------------------------------------------

import static com.mor.blogengine.xpath.SearchCriteria.SINGLE;

import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.xpath.SearchCriteria;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

/**
 * * Category repository object.
 *
 *  @author laurent
 */
@SuppressWarnings("unused")
public final class BlogCategoryRepository extends BlogRepositoryBase
    implements Repository<BlogCategory,
                            DefaultElement, SearchCriteria, DocumentException> {

  /**
    Default constructor.

   * @param d      document instance that holds blog data

   * @param config global configuration file for application
   */
  public BlogCategoryRepository(final Properties config, final Document d) {
    super(d, config);

  }

  /**
   * add a category to blog.
   *
   * @param t the category to add
   * @return true if Category added correctly
   * @throws ElementExistingException if element to add exist
   */
  @Override
  public boolean add(final BlogCategory t) throws ElementExistingException {

    List<DefaultElement> list;
    boolean added;

    try {
      list = getElementsForCriteria(SINGLE, t.getEntityiD());

      if (list == null) {
        throw new NoMatchesFoundException();
      } else {
        //trace("Element already in ");

        throw new ElementExistingException();
      }
    } catch (NoMatchesFoundException ex) {

      added = getHandler().add(t.toElement());

    }
    return added;
  }

  /**
   * append a category to a parent one to a blog.
   *
   * @param what     the category to append
   * @param parentId the ID of parent category to append to
   * @return true if Category appended correctly do not use
   */
  @Override
  public boolean append(final BlogCategory what, final String parentId) {
    return false;
  }

  @Override
  public boolean append(final BlogCategory what) throws DocumentException {
    return false;
  }

  /**
   * remove a category to blog.
   *
   * @param t the category to remove
   * @return true if Category removed correctly
   */
  @Override
  public boolean remove(final BlogCategory t) throws NoMatchesFoundException {
    boolean removed;

    List<DefaultElement> foundMatches = getElementsForCriteria(SINGLE,
        t.getEntityiD());

    if (foundMatches == null) {
      try {
        trace("No match of element found remove failed");
      } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
        Logger.getLogger(BlogCategoryRepository.class.getName())
            .log(Level.SEVERE, null, ex);
      }

      throw new NoMatchesFoundException();
    } else {
      removed = getHandler().remove(foundMatches);
    }

    return removed;
  }

  /**
   * edit a category in a blog.
   *
   * @param t  the category to edit
   * @param t2 the new category
   * @return true if Category edited correctly
   */
  @Override
  public boolean edit(final BlogCategory t, final BlogCategory t2)
      throws NoMatchesFoundException {

    List<DefaultElement> foundMatches = getElementsForCriteria(SINGLE,
        t.getEntityiD());

    if (foundMatches == null) {
      try {
        trace("No match of element found edit failed");
      } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
        Logger.getLogger(BlogCategoryRepository.class.getName())
            .log(Level.SEVERE, null, ex);
      }

      throw new NoMatchesFoundException();
    } else {
      AtomicBoolean edited = new AtomicBoolean(false);

      try {
        if (remove(t) && add(t2)) {
          edited.set(true);
        }
      } catch (ElementExistingException ex) {
        //trace();
      }

      return edited.get();
    }
  }



  /**
   * Enable the search for certain category and criteria in XML. <br/>
   *
   * @param sc what to search<br/>
   * @param paramValue  search for what criteria<br/>
   * @return list of results<br/>
   */
  @Override
  public List<DefaultElement> getElementsForCriteria(final SearchCriteria sc,
      final String paramValue)
      throws NoMatchesFoundException {

    return getSearchEngine().getElementsForCriteria("Category",
        sc, paramValue);
  }

}

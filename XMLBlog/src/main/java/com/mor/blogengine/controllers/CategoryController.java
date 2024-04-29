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
package com.mor.blogengine.controllers;

// ~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.dao.BlogCategoryRepository;
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
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
 * Manages Categories.
 *
 * @author laurent
 */
public final class CategoryController
    extends BlogControllerBase<BlogCategory, DefaultElement, SearchCriteria, DocumentException>
    implements IBlogElementController<BlogCategory, DocumentException> {

  /**
   * Default constructor.
   *
   * @param config - app configuration
   * @throws MissingPropertyException when a config property is missing
   * @throws IncorrectPropertyValueException when a config property is incorrectly set
   */
  public CategoryController(final Properties config)
      throws MissingPropertyException, IncorrectPropertyValueException {
    super(config);
    repository = new BlogCategoryRepository(getConfig(), getDocument());
  }

  @Override
  public Map<String, BlogCategory> getAllElements() {
    try {
      List<DefaultElement> list = getRepository().getElementsForCriteria(SearchCriteria.ALL, null);

      if (list != null) {
        return getFactory().createCategoryMap(list);
      } else {
        trace("test");
      }
    } catch (NoMatchesFoundException ex) {
      try {
        trace(ex.getMessage());
      } catch (MissingPropertyException | IncorrectPropertyValueException ex1) {
        Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex1);
      }
      return null;
    } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
      Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return null;
  }

  @Override
  public boolean addNewElement(final BlogCategory e) throws DocumentException {
    try {
      return getRepository().add(e);
    } catch (ElementExistingException ex) {
      Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);

      return false;
    }
  }

  @Override
  public boolean deleteElement(final BlogCategory e) throws DocumentException {
    try {
      return getRepository().remove(e);
    } catch (NoMatchesFoundException ex) {
      Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return false;
  }

  @Override
  public boolean editElement(final BlogCategory what, final BlogCategory with)
      throws DocumentException {
    try {
      return getRepository().edit(what, with);
    } catch (NoMatchesFoundException ex) {
      return false;
    }
  }

  @Override
  public Map<String, BlogCategory> getElementsForDate(final String d) {
    return null;
  }

  @Override
  public Map<String, BlogCategory> getAllElements(final String parentID) {
    throw new UnsupportedOperationException("Use non-parametrised version");
  }
}

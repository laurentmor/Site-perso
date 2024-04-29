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

import com.mor.blogengine.dao.BlogCommentRepository;
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.xpath.SearchCriteria;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

/**
 * Manages Comments.
 *
 * @author laurent
 */
public final class CommentController
    extends BlogControllerBase<BlogComment, DefaultElement, SearchCriteria, DocumentException>
    implements IBlogElementController<BlogComment, DocumentException> {

  /**
   * Controller constructor with config.
   *
   * @param config the configuration
   * @throws MissingPropertyException if a property is missing from config
   * @throws IncorrectPropertyValueException if a property has incorrect value
   */
  public CommentController(final Properties config)
      throws MissingPropertyException, IncorrectPropertyValueException {
    super(config);

    repository = new BlogCommentRepository(config, getDocument());
  }

  @Override
  public Map<String, BlogComment> getAllElements() {
    return null;
  }

  @Override
  public boolean addNewElement(final BlogComment e) throws DocumentException {
    try {
      return getRepository().append(e, e.getEntryId());
    } catch (NoMatchesFoundException ex) {
      Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return false;
  }

  @Override
  public boolean deleteElement(final BlogComment e) throws DocumentException {
    try {
      return getRepository().remove(e);
    } catch (NoMatchesFoundException ex) {
      Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
    }

    return false;
  }

  @Override
  public boolean editElement(final BlogComment what, final BlogComment with)
      throws DocumentException {
    try {
      return getRepository().edit(what, with);
    } catch (NoMatchesFoundException ex) {
      return false;
    }
  }

  @Override
  public Map<String, BlogComment> getElementsForDate(final String d) {
    return null;
  }

  @Override
  public Map<String, BlogComment> getAllElements(final String parentID) {
    try {
      List<DefaultElement> list =
          getRepository().getElementsForCriteria(SearchCriteria.ALL, parentID);

      return getFactory().createCommentMap(list);

    } catch (NoMatchesFoundException ex) {
      return null;
    }
  }
}

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
package com.mor.blogengine.controllers;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.dao.BlogCommentRepository;
import com.mor.blogengine.dao.IRepository;
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.xpath.SearchCriteria;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author laurent
 */


public class CommentController extends BlogControllerBase implements IBlogElementController<BlogComment, DocumentException> {

    private final IRepository<BlogComment, DefaultElement, SearchCriteria, DocumentException> repo;

    /**
     *
     */
    public CommentController(Properties config) throws MissingPropertyException, IncorrectPropertyValueException {
        super(config);

        repo = new BlogCommentRepository(mConfig, getDocument());

    }

    @Override
    public Map<String, BlogComment> getAllElements() {
        return null;
    }

    @Override
    public boolean addNewElement(BlogComment e) throws DocumentException {
        try {
            return repo.append(e, e.getEntryID());
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean deleteElement(BlogComment e) throws DocumentException {
        try {
            return repo.remove(e);
        } catch (NoMatchesFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean editElement(BlogComment what, BlogComment with) throws DocumentException {
        try {
            return repo.edit(what, with);
        } catch (NoMatchesFoundException | ElementExistingException ex) {
            return false;
        }
    }

    @Override
    public Map<String, BlogComment> getElementsForDate(String d) {
        return null;
    }

    @Override
    public Map<String, BlogComment> getAllElements(String parentID) {
        try {
            List<DefaultElement> list = repo.getElementsForCriteria(SearchCriteria.ALL, parentID);

            return getFactory().createCommentMap(list);

        } catch (NoMatchesFoundException ex) {
            return null;
        }

    }
}


//~ Formatted by Jindent --- http://www.jindent.com

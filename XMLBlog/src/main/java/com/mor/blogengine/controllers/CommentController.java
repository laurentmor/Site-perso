/* 
 * The MIT License
 *
 * Copyright 2015 Laurent Morissette.
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

//~--- JDK imports ------------------------------------------------------------
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laurent
 */


public class CommentController extends BlogControllerBase implements IBlogElementController<BlogComment, DocumentException> {

    private IRepository<BlogComment, DefaultElement, SearchCriteria, DocumentException> repo = null;

    /**
     *
     * @param config
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
        }
        catch (NoMatchesFoundException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ElementExistingException x) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, x);
        }

        return false;
    }

    @Override
    public boolean deleteElement(BlogComment e) throws DocumentException {
        try {
            return repo.remove(e);
        }
        catch (NoMatchesFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean editElement(BlogComment what, BlogComment with) throws DocumentException {
        try {
            return repo.edit(what, with);
        }
        catch (NoMatchesFoundException ex) {
            return false;
        }
        catch (ElementExistingException ex) {
            return false;
        }
    }

    @Override
    public Map<String, BlogComment> getElementsForDate(String d) {
        return null;
    }

    @Override
    public Map<String, BlogComment> getAllElements(String parentID) throws DocumentException {
        try {
            List<DefaultElement> list = repo.getElementsForCriteria(SearchCriteria.ALL, parentID);

            return getFactory().createCommentMap(list);

        }
        catch (NoMatchesFoundException ex) {
            return null;
        }

    }
}


//~ Formatted by Jindent --- http://www.jindent.com

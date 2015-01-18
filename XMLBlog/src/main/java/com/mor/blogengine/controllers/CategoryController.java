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
import com.mor.blogengine.dao.BlogCategoryRepository;
import com.mor.blogengine.dao.IRepository;
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
 *
 * @author laurent
 */
public class CategoryController extends BlogControllerBase implements IBlogElementController<BlogCategory, DocumentException> {

    /**
     * reppository to interface with data source
     */
    private IRepository<BlogCategory, DefaultElement, SearchCriteria, DocumentException> repo = null;

    /**
     *
     * @param config
     */
    public CategoryController(Properties config) throws MissingPropertyException, IncorrectPropertyValueException {
        super(config);
        repo = new BlogCategoryRepository(mConfig, getDocument());

    }

    @Override
    public Map<String, BlogCategory> getAllElements() {
        try {
            List<DefaultElement> list = repo.getElementsForCriteria(SearchCriteria.ALL, null);

            if (list != null) {
                return getFactory().createCategoryMap(list);
            } else {
                trace("test");
            }
        }
        catch (NoMatchesFoundException ex) {
            try {
                trace(ex.getMessage());
            }
            catch (MissingPropertyException | IncorrectPropertyValueException ex1) {
                Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return null;
        }
        catch (MissingPropertyException | IncorrectPropertyValueException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean addNewElement(BlogCategory e) throws DocumentException {
        try {
            return repo.add(e);
        }
        catch (ElementExistingException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    @Override
    public boolean deleteElement(BlogCategory e) throws DocumentException {
        try {
            return repo.remove(e);
        }
        catch (NoMatchesFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean editElement(BlogCategory what, BlogCategory with) throws DocumentException {
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
    public Map<String, BlogCategory> getElementsForDate(String d) {
        return null;
    }

    @Override
    public Map<String, BlogCategory> getAllElements(String parentID) throws DocumentException {
        throw new UnsupportedOperationException("Use non-parametrised version");
    }
}
//~ Formatted by Jindent --- http://www.jindent.com


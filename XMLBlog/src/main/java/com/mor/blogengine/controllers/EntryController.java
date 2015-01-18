/*  * The MIT License * * Copyright 2015 Laurent Morissette. * * Permission is hereby granted, free of charge, to any person obtaining a copy * of this software and associated documentation files (the "Software"), to deal * in the Software without restriction, including without limitation the rights * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell * copies of the Software, and to permit persons to whom the Software is * furnished to do so, subject to the following conditions: * * The above copyright notice and this permission notice shall be included in * all copies or substantial portions of the Software. * * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN * THE SOFTWARE. */package com.mor.blogengine.controllers;//~--- non-JDK imports --------------------------------------------------------import com.mor.blogengine.dao.BlogEntryRepository;import com.mor.blogengine.dao.IRepository;import com.mor.blogengine.exception.ElementExistingException;import com.mor.blogengine.exception.IncorrectPropertyValueException;import com.mor.blogengine.exception.MissingPropertyException;import com.mor.blogengine.exception.NoMatchesFoundException;import com.mor.blogengine.model.BlogEntry;import com.mor.blogengine.xpath.SearchCriteria;import java.util.List;import org.dom4j.DocumentException;import org.dom4j.tree.DefaultElement;//~--- JDK imports ------------------------------------------------------------import java.util.Map;import java.util.Properties;import java.util.logging.Level;import java.util.logging.Logger;/** * * @author laurent */public class EntryController extends BlogControllerBase implements IBlogElementController<BlogEntry, DocumentException> {    private IRepository<BlogEntry, DefaultElement, SearchCriteria, DocumentException> repo = null;    /**     * Construct Entry controller class using given properties configuration     * @param config  related entryControlle configuration      */    public EntryController(Properties config) throws MissingPropertyException, IncorrectPropertyValueException {        super(config);        repo = new BlogEntryRepository(mConfig, getDocument());    }    @Override    public Map<String, BlogEntry> getAllElements() throws DocumentException {        try {            List<DefaultElement> elements = repo.getElementsForCriteria(SearchCriteria.ALL, null);            return getFactory().createEntryMap(elements);        }        catch (NoMatchesFoundException ex) {            try {                trace(ex.getMessage());            }            catch (MissingPropertyException | IncorrectPropertyValueException ex1) {                Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex1);            }        }        return null;    }    @Override    public boolean addNewElement(BlogEntry e) throws DocumentException {        try {            return repo.add(e);        }        catch (ElementExistingException ex) {            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);        }        return false;    }    @Override    public boolean deleteElement(BlogEntry e) throws DocumentException {        try {            return repo.remove(e);        }        catch (NoMatchesFoundException ex) {            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);        }        return false;    }    @Override    public boolean editElement(BlogEntry what, BlogEntry with) throws DocumentException {        try {            return repo.edit(what, with);        }        catch (NoMatchesFoundException | ElementExistingException ex) {            return false;        }    }    @Override    public Map<String, BlogEntry> getElementsForDate(String d) {        try {            return getFactory().createEntryMap(repo.getElementsForCriteria(SearchCriteria.DATE, d));        }        catch (NoMatchesFoundException ex) {            Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);        }        return null;    }    /**     * For the moment there's is no use of definition as no needs were shown To     * introduce parent entry concept     *     * @param parentID the parent node ID to check for     * @throws DocumentException if there is an issue with XML structure     */    @Override    public Map<String, BlogEntry> getAllElements(String parentID) throws DocumentException {        return getAllElements();    }}//~ Formatted by Jindent --- http://www.jindent.com
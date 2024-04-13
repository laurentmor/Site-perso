/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.blogengine.dao;//~--- non-JDK imports --------------------------------------------------------import static com.mor.blogengine.xpath.SearchCriteria.SINGLE;import com.mor.blogengine.exception.ElementExistingException;import com.mor.blogengine.exception.NoMatchesFoundException;import com.mor.blogengine.model.BlogEntry;import com.mor.blogengine.xpath.SearchCriteria;import java.util.List;import java.util.Properties;import lombok.SneakyThrows;import org.dom4j.Document;import org.dom4j.DocumentException;import org.dom4j.tree.DefaultElement;/** * @author laurent */public final class BlogEntryRepository extends BlogRepositoryBase    implements Repository<BlogEntry, DefaultElement, SearchCriteria, DocumentException> {  /**   * Default constructor   *   * @param d      document instance that holds blog data   * @param config global configuration file for application   */  public BlogEntryRepository(final Properties config, final Document d) {    super(d, config);  }  /**   * add an entry to blog.   *   * @param t the entry to add   * @return true if entry added correctly   */  @SneakyThrows  @Override  public boolean add(final BlogEntry t) {    boolean added;    List<DefaultElement> list = null;    try {      list = getElementsForCriteria(SINGLE, t.getEntityiD());    } catch (NoMatchesFoundException ex) {      trace(ex.getMessage(), ex);    }    if (list == null) {      added = getHandler().add(t.toElement());      return added;    } else {      throw new ElementExistingException();    }  }  /**   * append an entry to a parent one to a blog.   *   * @param what   the category to append   * @param toWhat the ID of parent category to append to   * @return true if Category appended correctly do not use sub-category concept not considered   */  @Override  public boolean append(final BlogEntry what, final String toWhat) {    return false;  }  /**   * remove an entry to blog.   *   * @param t the category to remove   * @return true if Category removed correctly   */  @Override  public boolean remove(final BlogEntry t) throws NoMatchesFoundException {    boolean removed;    List<DefaultElement> list = getElementsForCriteria(SINGLE, t.getEntityiD());    if (list == null) {      throw new NoMatchesFoundException();    }    removed = getHandler().remove(t.toElement());    return removed;  }  /**   * edit an entry in a blog   *   * @param t  the entry to edit   * @param t2 the new entry   * @return true if entry edited correctly   */  @Override  public boolean edit(BlogEntry t, BlogEntry t2)      throws NoMatchesFoundException, ElementExistingException {    return remove(t) && add(t2);  }  /**   * Enable the search for certain entry and criteria in XML <br/>   *   * @param searchParam what to search<br/>   * @param paramValue  search for what criteria<br/>   * @return list of results<br/>   */  @Override  public List<DefaultElement> getElementsForCriteria(SearchCriteria searchParam, String paramValue)      throws NoMatchesFoundException {    List<DefaultElement> list = getSearchEngine().getElementsForCriteria("Entry",        searchParam, paramValue);    if (list == null) {      throw new NoMatchesFoundException();    }    return list;  }  @Override  public boolean append(BlogEntry what) {    return append(what, null);  }}
/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.blogengine.xpath;//~--- non-JDK imports --------------------------------------------------------/* see License.txt */import static com.mor.blogengine.xpath.SearchCriteria.ALL;import static com.mor.blogengine.xpath.SearchCriteria.BY_ENTRY_ID;import static com.mor.blogengine.xpath.SearchCriteria.CATEGORY;import static com.mor.blogengine.xpath.SearchCriteria.DATE;import static com.mor.blogengine.xpath.SearchCriteria.SINGLE;import static com.mor.blogengine.xpath.SearchCriteria.SINGLE_WITH_PARENT;import com.mor.blogengine.exception.IncorrectPropertyValueException;import com.mor.blogengine.exception.MissingPropertyException;import com.mor.blogengine.exception.NoMatchesFoundException;import com.mor.common.PropertiesUserObject;import java.util.ArrayList;import java.util.Collections;import java.util.List;import java.util.Properties;import java.util.logging.Level;import java.util.logging.Logger;import javax.validation.constraints.NotNull;import org.dom4j.Document;import org.dom4j.InvalidXPathException;import org.dom4j.tree.DefaultAttribute;import org.dom4j.tree.DefaultElement;/** * Search for content in XML data Structure. * * @author Laurent *///@Slf4jpublic final class SearchEngine extends PropertiesUserObject implements    IBlogSearchEngine<DefaultElement> {  /** Class instance.*/  private static SearchEngine mInstance;  /** Configurator object that hold low level XPATH dom tree selection.*/  private SearchEngineConfigurator<List<DefaultElement>> configurator;  private SearchEngine(final Properties config, final Document d) {    super(config);    if (d == null) {      throw new IllegalArgumentException("Config properties must be set");    }    this.setConfig(config);    configurator = new SearchEngineConfigurator<>(getConfig(), d);  }  /**   * Get SearchEngine instance for specific config and XML document.   * @param config the configuration.   * @param domTree the XML document.   * @return the engine instance.   */  public static synchronized SearchEngine getInstanceForDoc(final Properties config, final Document domTree) {    if ((config != null)) {      if (mInstance != null) {        return mInstance;      }    }    mInstance = new SearchEngine(config, domTree);    return mInstance;  }  // -----------------------Search Methods definitions ----------------------//  /**   *   */  @NotNull List<DefaultElement> getCategories()      throws InvalidXPathException, NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {    trace("Building XPath search Query to get all categories");    List<String> nodes = new ArrayList<>();    nodes.add("Category");    String exp = new XPathExpressionBuilder("Blog", nodes, null,        getSupportedXpathVersion()).compileExpression();    return Collections.unmodifiableList(configurator.findContent(exp));  }  List<DefaultElement> getEntriesForCategory(final String id)      throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {    trace("Building XPath search Query to get entries for a category");    List<String> nodes = new ArrayList<>();    nodes.add("Entry");    DefaultAttribute attribute = new DefaultAttribute("categoryID", id);    List<DefaultAttribute> attributes = new ArrayList<>();    attributes.add(attribute);    String exp = new XPathExpressionBuilder("Blog", nodes, attributes,        getSupportedXpathVersion()).compileExpression();    return Collections.unmodifiableList(configurator.findContent(exp));  }  List<DefaultElement> getEntriesForDate(String pDate)      throws NoMatchesFoundException, InvalidXPathException, MissingPropertyException, IncorrectPropertyValueException {    trace("Building XPath search Query to get entries for a date");    List<String> lNodesList = new ArrayList<>();    List<DefaultAttribute> lAttList = new ArrayList<>();    lNodesList.add("Entry");    lAttList.add(new DefaultAttribute("date", pDate));    String exp = new XPathExpressionBuilder("Blog", lNodesList, lAttList,        getSupportedXpathVersion()).compileExpression();    return Collections.unmodifiableList(configurator.findContent(exp));  }  /**   *   */  List<DefaultElement> getSingleElement(final String pSearchedElementName, final String id)      throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {    trace("Building XPath search Query to get  a single element");    List<String> nodes = new ArrayList<>();    nodes.add(pSearchedElementName);    DefaultAttribute lAttribute = new DefaultAttribute("ID", id);    List<DefaultAttribute> lAttList = new ArrayList<>();    lAttList.add(lAttribute);    String exp = new XPathExpressionBuilder("Blog", nodes, lAttList,        getSupportedXpathVersion()).compileExpression();    return Collections.unmodifiableList(configurator.findContent(exp));  }  List<DefaultElement> getCommentsForEntry(String ID)      throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {    trace("Building XPath search Query to get comment for an entry");    List<String> lNodes = new ArrayList<>();    lNodes.add("Entry");    lNodes.add("Comment");    DefaultAttribute lAttribute = new DefaultAttribute("entryID", ID);    List<DefaultAttribute> lAttList = new ArrayList<>();    lAttList.add(lAttribute);    String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList,        getSupportedXpathVersion()).compileExpression();    trace(exp);    return Collections.unmodifiableList(configurator.findContent(exp));  }  List<DefaultElement> getEntries()      throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {    trace("Building XPath search Query to get all entries");    List<String> lNodes = new ArrayList<>();    lNodes.add("Entry");    return Collections.unmodifiableList(        configurator.findContent(new XPathExpressionBuilder("Blog", lNodes, null,            getSupportedXpathVersion()).compileExpression()));  }  /**   * Enable the search for certain criteria in XML <br/>   *   * @param elementType   what to search<br/>   * @param criteria      search for what criteria<br/>   * @param criteriaValue criteria value<br/>   * @return list of results<br/>   * @see SearchCriteria   */  @Override  public List<DefaultElement> getElementsForCriteria(String elementType, SearchCriteria criteria,      String criteriaValue) throws NoMatchesFoundException {    if (elementType != null) {      if (elementType.equalsIgnoreCase("Entry")) {        if (criteria == ALL) {          try {            return getEntries();          } catch (MissingPropertyException | IncorrectPropertyValueException ex) {            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);          }        }        if (criteria == DATE) {          try {            return getEntriesForDate(criteriaValue);          } catch (InvalidXPathException | MissingPropertyException |                   IncorrectPropertyValueException ex) {            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);          }        }        if (criteria == CATEGORY) {          try {            return getEntriesForCategory(criteriaValue);          } catch (MissingPropertyException | IncorrectPropertyValueException ex) {            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);          }        }      }      if (elementType.equalsIgnoreCase("Comment") && (criteria == BY_ENTRY_ID)) {        try {          return getCommentsForEntry(criteriaValue);        } catch (MissingPropertyException | IncorrectPropertyValueException ex) {          Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);        }      }      if (elementType.equalsIgnoreCase("Category") && (criteria == ALL)) {        try {          return getCategories();        } catch (InvalidXPathException | MissingPropertyException |                 IncorrectPropertyValueException ex) {          Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);        }      }      if (criteria == SINGLE_WITH_PARENT) {        return null;      }      if (criteria == SINGLE) {        try {          return getSingleElement(elementType, criteriaValue);        } catch (MissingPropertyException | IncorrectPropertyValueException ex) {          Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);        }      }    }    return null;  }}
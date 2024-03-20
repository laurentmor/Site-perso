/** * Copyright 2021 Laurent * <p> * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * <p> * http://www.apache.org/licenses/LICENSE-2.0 * <p> * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. *//* * Copyright 2021 Laurent * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */package com.mor.blogengine.xpath;//~--- non-JDK imports --------------------------------------------------------/* see License.txt */import com.mor.blogengine.exception.IncorrectPropertyValueException;import com.mor.blogengine.exception.MissingPropertyException;import com.mor.blogengine.exception.NoMatchesFoundException;import com.mor.common.PropertiesUserObject;import lombok.Singular;import org.dom4j.Document;import org.dom4j.InvalidXPathException;import org.dom4j.tree.DefaultAttribute;import org.dom4j.tree.DefaultElement;import javax.validation.constraints.NotNull;import java.util.ArrayList;import java.util.Collections;import java.util.List;import java.util.Properties;import java.util.logging.Level;import java.util.logging.Logger;import static com.mor.blogengine.xpath.SearchCriteria.*;/** * Search for content in XML data Structure<br/> * * @author Laurent *///@Slf4jpublic final class SearchEngine extends PropertiesUserObject implements IBlogSearchEngine<DefaultElement> {    @Singular    private static SearchEngine mInstance;    private SearchEngineConfigurator<List<DefaultElement>> configurator = null;    private XPathVersion mXpathVersion;    /**     *     */    private SearchEngine(Properties config, Document d) {        super(config);        init(config, d);    }    public static synchronized SearchEngine getInstanceForDoc(Properties config, Document domTree) {        if ((config != null)) {            if (mInstance != null) {                return mInstance;            }        }        mInstance = new SearchEngine(config, domTree);        return mInstance;    }    void init(Properties config, Document d) throws IllegalArgumentException {        if (d == null) {            throw new IllegalArgumentException("Config properties must be set");        }        mConfig = config;        configurator = new SearchEngineConfigurator<>(mConfig, d);        mXpathVersion = getSupportedXpathVersion();    }    // -----------------------Search Methods definitions ----------------------//    /**     *     */    @NotNull List<DefaultElement> getCategories() throws InvalidXPathException, NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {        trace("Building XPath search Query to get all categories");        List<String> lNodes = new ArrayList<>();        lNodes.add("Category");        String exp = new XPathExpressionBuilder("Blog", lNodes, null, mXpathVersion).compileExpression();        return Collections.unmodifiableList(configurator.findContent(exp));    }    List<DefaultElement> getEntriesForCategory(String pCatID) throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {        trace("Building XPath search Query to get entries for a category");        List<String> lNodes = new ArrayList<>();        lNodes.add("Entry");        DefaultAttribute lAttribute = new DefaultAttribute("categoryID", pCatID);        List<DefaultAttribute> lAttList = new ArrayList<>();        lAttList.add(lAttribute);        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();        return Collections.unmodifiableList(configurator.findContent(exp));    }    List<DefaultElement> getEntriesForDate(String pDate) throws NoMatchesFoundException, InvalidXPathException, MissingPropertyException, IncorrectPropertyValueException {        trace("Building XPath search Query to get entries for a date");        List<String> lNodesList = new ArrayList<>();        List<DefaultAttribute> lAttList = new ArrayList<>();        lNodesList.add("Entry");        lAttList.add(new DefaultAttribute("date", pDate));        String exp = new XPathExpressionBuilder("Blog", lNodesList, lAttList, mXpathVersion).compileExpression();        return Collections.unmodifiableList(configurator.findContent(exp));    }    /**     *     */    List<DefaultElement> getSingleElement(String pSearchedElementName, String id) throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {        trace("Building XPath search Query to get  a single element");        List<String> lNodes = new ArrayList<>();        lNodes.add(pSearchedElementName);        DefaultAttribute lAttribute = new DefaultAttribute("ID", id);        List<DefaultAttribute> lAttList = new ArrayList<>();        lAttList.add(lAttribute);        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();        return Collections.unmodifiableList(configurator.findContent(exp));    }    List<DefaultElement> getCommentsForEntry(String ID) throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {        trace("Building XPath search Query to get comment for an entry");        List<String> lNodes = new ArrayList<>();        lNodes.add("Entry");        lNodes.add("Comment");        DefaultAttribute lAttribute = new DefaultAttribute("entryID", ID);        List<DefaultAttribute> lAttList = new ArrayList<>();        lAttList.add(lAttribute);        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();        trace(exp);        return Collections.unmodifiableList(configurator.findContent(exp));    }    List<DefaultElement> getEntries() throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {        trace("Building XPath search Query to get all entries");        List<String> lNodes = new ArrayList<>();        lNodes.add("Entry");        return Collections.unmodifiableList(configurator.findContent(new XPathExpressionBuilder("Blog", lNodes, null,                mXpathVersion).compileExpression()));    }    /**     * Enable the search for certain criteria in XML <br/>     *     * @param elementType   what to search<br/>     * @param criteria      search for what criteria<br/>     * @param criteriaValue criteria value<br/>     * @return list of results<br/>     * @see SearchCriteria     */    @Override    public List<DefaultElement> getElementsForCriteria(String elementType, SearchCriteria criteria,                                                       String criteriaValue) throws NoMatchesFoundException {        if (elementType != null) {            if (elementType.equalsIgnoreCase("Entry")) {                if (criteria == ALL) {                    try {                        return getEntries();                    } catch (MissingPropertyException | IncorrectPropertyValueException ex) {                        Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);                    }                }                if (criteria == DATE) {                    try {                        return getEntriesForDate(criteriaValue);                    } catch (InvalidXPathException | MissingPropertyException | IncorrectPropertyValueException ex) {                        Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);                    }                }                if (criteria == CATEGORY) {                    try {                        return getEntriesForCategory(criteriaValue);                    } catch (MissingPropertyException | IncorrectPropertyValueException ex) {                        Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);                    }                }            }            if (elementType.equalsIgnoreCase("Comment") && (criteria == BY_ENTRY_ID)) {                try {                    return getCommentsForEntry(criteriaValue);                } catch (MissingPropertyException | IncorrectPropertyValueException ex) {                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);                }            }            if (elementType.equalsIgnoreCase("Category") && (criteria == ALL)) {                try {                    return getCategories();                } catch (InvalidXPathException | MissingPropertyException | IncorrectPropertyValueException ex) {                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);                }            }            if (criteria == SINGLE_WITH_PARENT) {                return null;            }            if (criteria == SINGLE) {                try {                    return getSingleElement(elementType, criteriaValue);                } catch (MissingPropertyException | IncorrectPropertyValueException ex) {                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);                }            }        }        return null;    }}//~ Formatted by Jindent --- http://www.jindent.com
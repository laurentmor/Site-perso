package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------

/*
 see License.txt
 */
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import static com.mor.blogengine.xpath.SearchCriteria.ALL;
import static com.mor.blogengine.xpath.SearchCriteria.BY_ENTRY_ID;
import static com.mor.blogengine.xpath.SearchCriteria.CATEGORY;
import static com.mor.blogengine.xpath.SearchCriteria.DATE;
import static com.mor.blogengine.xpath.SearchCriteria.SINGLE;
import static com.mor.blogengine.xpath.SearchCriteria.SINGLE_WITH_PARENT;
import com.mor.common.PropertiesUserObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.InvalidXPathException;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

/**
 * Search for content in XML data Structure<br/>
 *
 * @author Laurent
 */
public final class SearchEngine extends PropertiesUserObject implements IBlogSearchEngine<DefaultElement> {

    private SearchEngineConfigurator<List<DefaultElement>> configurator = null;
    private XPathVersion mXpathVersion;

    /**
     *
     *
     * @param config
     * @param d
     */
    public SearchEngine(Properties config, Document d) {

        init(config, d);
    }

    void init(Properties config, Document d) {
        if(null==config) throw new IllegalArgumentException("Config properties must be set");
        if(null==d) throw new IllegalArgumentException("Config properties must be set");
        mConfig = config;
        configurator = new SearchEngineConfigurator<>(mConfig, d);
        mXpathVersion = getSupportedXpathVersion();
    }

    // -----------------------Search Methods definitions ----------------------//
    /**
     *
     * @return
     */
    List<DefaultElement> getCategories() throws InvalidXPathException, NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {
        trace("Building XPath search Query to get all categories");

        List<String> lNodes = new ArrayList<>();

        lNodes.add("Category");

        String exp = new XPathExpressionBuilder("Blog", lNodes, null, mXpathVersion).compileExpression();

        return configurator.findContent(exp);

    }

    List<DefaultElement> getEntriesForCategory(String pCatID) throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {
        trace("Building XPath search Query to get entries for a category");

        List<String> lNodes = new ArrayList<>();

        lNodes.add("Entry");

        DefaultAttribute lAttribute = new DefaultAttribute("categoryID", pCatID);
        List<DefaultAttribute> lAttList = new ArrayList<>();

        lAttList.add(lAttribute);

        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();

        return configurator.findContent(exp);
    }

    List<DefaultElement> getEntriesforDate(String pDate) throws NoMatchesFoundException, InvalidXPathException, MissingPropertyException, IncorrectPropertyValueException {
        trace("Building XPath search Query to get entries for a date");

        List<String> lNodesList = new ArrayList<>();
        List<DefaultAttribute> lAttList = new ArrayList<>();

        lNodesList.add("Entry");
        lAttList.add(new DefaultAttribute("date", pDate));

        String exp = new XPathExpressionBuilder("Blog", lNodesList, lAttList, mXpathVersion).compileExpression();

        return configurator.findContent(exp);
    }

    /**
     *
     * @param pSearchedElement
     * @return
     * @param pSearchedElementName
     * @param id
     */
    List<DefaultElement> getSingleElement(String pSearchedElementName, String id) throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {
        trace("Building XPath search Query to get  a single element");

        List<String> lNodes = new ArrayList<>();

        lNodes.add(pSearchedElementName);

        DefaultAttribute lAttribute = new DefaultAttribute("ID", id);
        List<DefaultAttribute> lAttList = new ArrayList<>();

        lAttList.add(lAttribute);

        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();

        return configurator.findContent(exp);
    }

    List<DefaultElement> getComentsForEntry(String ID) throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {
        trace("Building XPath search Query to get comment for an entry");

        List<String> lNodes = new ArrayList<>();

        lNodes.add("Entry");
        lNodes.add("Comment");

        DefaultAttribute lAttribute = new DefaultAttribute("entryID", ID);
        List<DefaultAttribute> lAttList = new ArrayList<>();

        lAttList.add(lAttribute);

        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();

        trace(exp);

        return configurator.findContent(exp);
    }

    List<DefaultElement> getEntries() throws NoMatchesFoundException, MissingPropertyException, IncorrectPropertyValueException {
        trace("Building XPath search Query to get all entries");

        List<String> lNodes = new ArrayList<>();

        lNodes.add("Entry");

        return configurator.findContent(new XPathExpressionBuilder("Blog", lNodes, null,
                mXpathVersion).compileExpression());
    }

    /**
     * Enable the search for certain criteria in XML <br/>
     *
     * @see SearchCriteria
     * @param elementType what to search<br/>
     * @param criteria search for what criteria<br/>
     * @param criteriaValue criteria value<br/>
     * @return list of results<br/>
     */
    @Override
    public List<DefaultElement> getElementsForCriteria(String elementType, SearchCriteria criteria,
            String criteriaValue) throws NoMatchesFoundException {
        if (elementType != null) {
            if (elementType.equalsIgnoreCase("Entry")) {
                if (criteria == ALL) {
                    try {
                        return getEntries();
                    }
                    catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                        Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (criteria == DATE) {
                    try {
                        return getEntriesforDate(criteriaValue);
                    }
                    catch (InvalidXPathException | MissingPropertyException | IncorrectPropertyValueException ex) {
                        Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (criteria == CATEGORY) {
                    try {
                        return getEntriesForCategory(criteriaValue);
                    }
                    catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                        Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (elementType.equalsIgnoreCase("Comment") && (criteria == BY_ENTRY_ID)) {
                try {
                    return getComentsForEntry(criteriaValue);
                }
                catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (elementType.equalsIgnoreCase("Category") && (criteria == ALL)) {
                try {
                    return getCategories();
                }
                catch (InvalidXPathException ex) {
                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (MissingPropertyException ex) {
                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IncorrectPropertyValueException ex) {
                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (criteria == SINGLE_WITH_PARENT) {
                return null;
            }

            if (criteria == SINGLE) {
                try {
                    return getSingleElement(elementType, criteriaValue);
                }
                catch (MissingPropertyException | IncorrectPropertyValueException ex) {
                    Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }
}

//~ Formatted by Jindent --- http://www.jindent.com


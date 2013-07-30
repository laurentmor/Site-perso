package com.mor.blogengine.xpath;

//~--- non-JDK imports --------------------------------------------------------

/*
 see License.txt
 */
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.common.PropertiesUserObject;

import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

import static com.mor.blogengine.xpath.SearchCriteria.ALL;
import static com.mor.blogengine.xpath.SearchCriteria.BY_ENTRY_ID;
import static com.mor.blogengine.xpath.SearchCriteria.CATEGORY;
import static com.mor.blogengine.xpath.SearchCriteria.DATE;
import static com.mor.blogengine.xpath.SearchCriteria.SINGLE;
import static com.mor.blogengine.xpath.SearchCriteria.SINGLE_WITH_PARENT;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.InvalidXPathException;

/**
 * Search for content in XML data Structure<br/>
 *
 * @author Laurent
 */
public final class SearchEngine extends PropertiesUserObject implements IBlogSearchEngine<DefaultElement> {

    private SearchEngineConfigurator<List> configurator = null;
    private XPathVersion mXpathVersion;

    /**
     *
     *
     * @param pDoc
     * @param config
     */
    public SearchEngine(Properties config, Document d) {

        init(config, d);
    }

    void init(Properties config, Document d) {
        mConfig = config;
        configurator = new SearchEngineConfigurator(mConfig, d);
        mXpathVersion = getSupportedXpathVersion();
    }

    // -----------------------Search Methods definitions ----------------------//
    /**
     *
     * @return
     */
    List<DefaultElement> getCategories() throws InvalidXPathException, NoMatchesFoundException {
        trace("Building XPath search Query to get all categories");

        ArrayList lNodes = new ArrayList();

        lNodes.add("Category");

        String exp = new XPathExpressionBuilder("Blog", lNodes, null, mXpathVersion).compileExpression();
        
            return configurator.findContent(exp);
     
    }

    List getEntriesForCategory(String pCatID) throws NoMatchesFoundException {
        trace("Building XPath search Query to get entries for a category");

        ArrayList lNodes = new ArrayList();

        lNodes.add("Entry");

        DefaultAttribute lAttribute = new DefaultAttribute("categoryID", pCatID);
        List lAttList = new ArrayList();

        lAttList.add(lAttribute);

        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();

        return configurator.findContent(exp);
    }

    List getEntriesforDate(String pDate) throws NoMatchesFoundException {
        trace("Building XPath search Query to get entries for a date");

        ArrayList lNodesList = new ArrayList();
        ArrayList lAttList = new ArrayList();

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
    List<DefaultElement> getSingleElement(String pSearchedElementName, String id) throws NoMatchesFoundException {
        trace("Building XPath search Query to get  a single element");

        ArrayList lNodes = new ArrayList();

        lNodes.add(pSearchedElementName);

        DefaultAttribute lAttribute = new DefaultAttribute("ID", id);
        List lAttList = new ArrayList();

        lAttList.add(lAttribute);

        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();

        return configurator.findContent(exp);
    }

    List getComentsForEntry(String ID) throws NoMatchesFoundException {
        trace("Building XPath search Query to get comment for an entry");

        ArrayList lNodes = new ArrayList();

        lNodes.add("Entry");
        lNodes.add("Comment");

        DefaultAttribute lAttribute = new DefaultAttribute("entryID", ID);
        List lAttList = new ArrayList();

        lAttList.add(lAttribute);

        String exp = new XPathExpressionBuilder("Blog", lNodes, lAttList, mXpathVersion).compileExpression();

        trace(exp);

        return configurator.findContent(exp);
    }

    List getEntries() throws NoMatchesFoundException {
        trace("Building XPath search Query to get all entries");

        ArrayList lNodes = new ArrayList();

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
            String criteriaValue) throws NoMatchesFoundException{
        if (elementType != null) {
            if (elementType.equalsIgnoreCase("Entry")) {
                if (criteria == ALL) {
                    return getEntries();
                }

                if (criteria == DATE) {
                    return getEntriesforDate(criteriaValue);
                }

                if (criteria == CATEGORY) {
                    return getEntriesForCategory(criteriaValue);
                }
            }

            if (elementType.equalsIgnoreCase("Comment") && (criteria == BY_ENTRY_ID)) {
                return getComentsForEntry(criteriaValue);
            }

            if (elementType.equalsIgnoreCase("Category") && (criteria == ALL)) {
                return getCategories();
            }

            if (criteria == SINGLE_WITH_PARENT) {
                return null;
            }

            if (criteria == SINGLE) {
                return getSingleElement(elementType, criteriaValue);
            }
        }

        return null;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


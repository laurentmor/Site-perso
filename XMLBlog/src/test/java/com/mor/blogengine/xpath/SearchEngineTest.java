/*
 * The MIT License
 *
 * Copyright 2013 laurent.
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
package com.mor.blogengine.xpath;

import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.test.XMLConsumingTestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author laurent
 */
public class SearchEngineTest extends XMLConsumingTestCase {

    SearchEngine engine = null;

    @Before
    public void configure() {
        engine = new SearchEngine(getProperties(), getBlogDocument());
    }

    public SearchEngineTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void initSansDoc() {
        engine = new SearchEngine(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initAvecDoc() {
        engine = new SearchEngine(null, getBlogDocument());
    }

    @Test(expected = IllegalArgumentException.class)
    public void initAvecConfigSansDoc() {
        engine = new SearchEngine(getProperties(), null);
    }

    @Test
    public void iniCorrect() {
        engine = new SearchEngine(getProperties(), getBlogDocument());
    }

    @Test
    public void getElementsForCriteriaAvecTypeElementNull() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria(null, SearchCriteria.ALL, ""));
    }

    @Test
    public void getElementsForCriteriaAvecTypeElementEntryMaisCritereNull() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria("Entry", null, ""));
    }

    @Test
    public void getElementsForCriteriaAvecTypeElementEntry() throws NoMatchesFoundException {

        assertFalse(engine.getElementsForCriteria("Entry", SearchCriteria.ALL, "").isEmpty());
    }

    @Test
    public void getElementsForCriteriaAvecTypeInconnu() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria("bidon", null, ""));
    }

    @Test
    public void getElementsForCriteriaAvecTypeElementEntryAvecDate() throws NoMatchesFoundException {

        assertFalse(engine.getElementsForCriteria("Entry", SearchCriteria.DATE, "2004-09-09").isEmpty());
    }

    @Test
    public void getElementsForCriteriaAvecTypeElementEntryAvecCategorie() throws NoMatchesFoundException {

        assertFalse(engine.getElementsForCriteria("Entry", SearchCriteria.CATEGORY, "ID_1222333").isEmpty());
    }

    @Test
    public void getElementsForCriteriaAvecCommentEtCritereBidon() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria("Comment", SearchCriteria.ALL, ""));
    }

    @Test
    public void getElementsForCriteriaAvecCommentEtCritereNull() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria("Comment", null, ""));
    }

    @Test
    public void getElementsForCriteriaAvecCommentEtCritereCorrect() throws NoMatchesFoundException {

        assertFalse(engine.getElementsForCriteria("Comment", SearchCriteria.BY_ENTRY_ID, "ID_152369776").isEmpty());
    }

    @Test
    public void getElementsForCriteriaAvecCategoryEtCritereNull() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria("Category", null, ""));
    }

    @Test
    public void getElementsForCriteriaAvecCategoryEtCritereCorrect() throws NoMatchesFoundException {

        assertFalse(engine.getElementsForCriteria("Category", SearchCriteria.ALL, null).isEmpty());
    }

    @Test
    public void getElementsForCriteriaAvecCritereSingleWithParent() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria("", SearchCriteria.SINGLE_WITH_PARENT, ""));
    }

    @Test
    public void getElementsForCriteriaAvecCritereSingleAvecTypeNull() throws NoMatchesFoundException {

        assertNull(engine.getElementsForCriteria(null, SearchCriteria.SINGLE, ""));
    }

    @Test
    public void getElementsForCriteriaAvecSingleEtID() throws NoMatchesFoundException {

        assertFalse(engine.getElementsForCriteria("Category", SearchCriteria.SINGLE, "ID_320524799").isEmpty());
    }
}

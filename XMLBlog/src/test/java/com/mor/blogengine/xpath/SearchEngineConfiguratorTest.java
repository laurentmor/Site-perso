/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.common.XMLConsumingTestCase;
import java.util.List;
import org.dom4j.InvalidXPathException;
import org.dom4j.tree.DefaultElement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;
import static org.junit.Assert.*;

/**
 *
 * @author laurent
 */
public class SearchEngineConfiguratorTest extends XMLConsumingTestCase {

    public SearchEngineConfiguratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        initialise();

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        logTestName();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test pour assurer que la classe fournit le service correctement lorsqu'on
     * lui donne les bons paramètres.
     *
     * @throws com.mor.blogengine.exception.NoMatchesFoundException
     */
    @Test
    public void testConfigurerCorrectementAvecElementExistant() throws InvalidXPathException, NoMatchesFoundException {

        SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<List<DefaultElement>>(properties, document);
        List<DefaultElement> fnd = configurator.findContent("/root");
        assertEquals("root", fnd.get(0).getName());

    }

    /**
     * Test pour assurer que la classe fournit le service correctement lorsqu'on
     * lui donne les bons paramètres.
     *
     * @throws com.mor.blogengine.exception.NoMatchesFoundException
     */
    @Test(expected = NoMatchesFoundException.class)
    public void testConfigurerCorrectementAvecNonElementExistant() throws NoMatchesFoundException {
        SearchEngineConfigurator<List<DefaultElement>> configurator
                = new SearchEngineConfigurator<List<DefaultElement>>(properties,
                document);

        List<DefaultElement> fnd = configurator.findContent("/notFound");

    }
}

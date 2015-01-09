/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.test.XMLConsumingTestCase;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.InvalidXPathException;
import org.dom4j.tree.DefaultElement;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author laurent
 */
public class SearchEngineConfiguratorTest extends XMLConsumingTestCase {

    public SearchEngineConfiguratorTest() {
    }

   
    /**
     * Test pour assurer que la classe fournit le service correctement lorsqu'on
     * lui donne les bons paramètres.
     *
     * @throws com.mor.blogengine.exception.NoMatchesFoundException
     */
    @Test
    public void testConfigurerCorrectementAvecElementExistant() throws InvalidXPathException, NoMatchesFoundException {

        try {
            mConfig.remove("application.mode");
            
            SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<>(mConfig, getDefautDocument());
            List<DefaultElement> fnd = configurator.findContent("/root");
            assertEquals("root", fnd.get(0).getName());
        }
        catch (MissingPropertyException ex) {
            Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IncorrectPropertyValueException ex) {
            Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                = new SearchEngineConfigurator<>(getProperties(),
                getDefautDocument());

        try {
            configurator.findContent("/notFound");
        }
        catch (InvalidXPathException ex) {
            Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (MissingPropertyException ex) {
            Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IncorrectPropertyValueException ex) {
            Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

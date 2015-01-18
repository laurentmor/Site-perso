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
        catch (MissingPropertyException | IncorrectPropertyValueException ex) {
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

/**
 * Copyright 2021 Laurent
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mor.blogengine.xpath;

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.test.XMLConsumingTestCase;
import org.dom4j.InvalidXPathException;
import org.dom4j.tree.DefaultElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author laurent
 */
@DisplayName("Search Engine Configurator Test")
class SearchEngineConfiguratorTest extends XMLConsumingTestCase {

    public SearchEngineConfiguratorTest() {
        super();
    }

    @BeforeEach
    public void beforeEach() {

        mConfig = getProperties();
    }

    /**
     * Test pour assurer que la classe fournit le service correctement lorsqu'on
     * lui donne les bons paramètres.
     */
    // @Test
    //@DisplayName("Test Configurer Correctement Avec Element Existant")
    void testConfigurerCorrectementAvecElementExistant() throws InvalidXPathException, NoMatchesFoundException {
        try {
            //mConfig.remove("application.mode");
            SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<>(mConfig, getDefautDocument());
            List<DefaultElement> fnd = configurator.findContent("/root");
            assertEquals(fnd.getFirst().getName(), "root");
        } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
            Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test pour assurer que la classe fournit le service correctement lorsqu'on
     * lui donne les bons paramètres.
     */
    @Test
    @DisplayName("Test Configurer Correctement Avec Non Element Existant")
    void testConfigurerCorrectementAvecNonElementExistant() {
        Exception e = assertThrows(NoMatchesFoundException.class, () -> {
            SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<>(mConfig, getDefautDocument());
            try {
                configurator.findContent("/notFound");
            } catch (InvalidXPathException | MissingPropertyException | IncorrectPropertyValueException ex) {
                Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        assertEquals("No matches of /notFound were found during search process - redefine your search", e.getMessage());

    }
}

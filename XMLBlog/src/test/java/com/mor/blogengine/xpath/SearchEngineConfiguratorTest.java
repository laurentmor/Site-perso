/*
 * Copyright (c) 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package com.mor.blogengine.xpath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.test.XMLConsumingTestCase;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.InvalidXPathException;
import org.dom4j.tree.DefaultElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author laurent
 */
@DisplayName("Search Engine Configurator Test")
class SearchEngineConfiguratorTest extends XMLConsumingTestCase {

  public SearchEngineConfiguratorTest() {
    super();
  }


  /*
   * Test to ensure that SearchEngine is configured fine
   */
  @Test
  @DisplayName("Test to ensure that SearchEngine is configured fine - Element found")
  void testIfSearchEngineIsConfiguredFineElementFound()
      throws InvalidXPathException, NoMatchesFoundException {
    try {
      //mConfig.remove("application.mode");
      SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<>(
          mConfig, getDefautDocument());
      List<DefaultElement> fnd = configurator.findContent("/root");
      assertEquals(fnd.getFirst().getName(), "root");
    } catch (MissingPropertyException | IncorrectPropertyValueException ex) {
      Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /*
   * Test to ensure that SearchEngine is configured fine - Element not found
   */
  @Test
  @DisplayName("Test to ensure that SearchEngine is configured fine - Element not found")
  void testIfSearchEngineIsConfiguredFineElementNotFound() {
    Exception e = assertThrows(NoMatchesFoundException.class, () -> {
      SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<>(
          mConfig, getDefautDocument());
      try {
        configurator.findContent("/notFound");
      } catch (InvalidXPathException | MissingPropertyException |
               IncorrectPropertyValueException ex) {
        Logger.getLogger(SearchEngineConfiguratorTest.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
    assertEquals("No matches of /notFound were found during search process - redefine your search",
        e.getMessage());

  }
}

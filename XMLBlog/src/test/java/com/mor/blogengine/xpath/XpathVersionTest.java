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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author laurent
 */
@DisplayName ("XPath Version Test")
class XpathVersionTest {


  /**
   * Test of valueOf method, of class XpathVersion.
   */
  @Test
  @DisplayName("Test Value Of")
  void testValueOf() {
    String aName = "typeLess";
    XpathVersion expResult = XpathVersion.typeLess;
    XpathVersion result = XpathVersion.valueOf(aName);
    assertEquals(expResult, result);
  }

  /**
   * Test of getVersion method, of class XpathVersion.
   */
  @Test
  @DisplayName("Test Get Version")
  void testGetVersion() {

    XpathVersion instance = XpathVersion.typeLess;
    float expResult = 1.0F;
    float result = instance.getVersion();
    assertEquals(expResult, result, 0.0);
  }
}

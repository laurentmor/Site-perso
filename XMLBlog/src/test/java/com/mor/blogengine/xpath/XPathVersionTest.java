/**
 * Copyright 2021 Laurent
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
 */
package com.mor.blogengine.xpath;

import org.junit.Rule;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.rules.TestName;

/**
 * @author laurent
 */
@DisplayName("XPath Version Test")
class XPathVersionTest {



    /**
     * Test of valueOf method, of class XPathVersion.
     */
    @Test
    @DisplayName("Test Value Of")
    void testValueOf() {
        String aName = "typeLess";
        XPathVersion expResult = XPathVersion.typeLess;
        XPathVersion result = XPathVersion.valueOf(aName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVersion method, of class XPathVersion.
     */
    @Test
    @DisplayName("Test Get Version")
    void testGetVersion() {

        XPathVersion instance = XPathVersion.typeLess;
        float expResult = 1.0F;
        float result = instance.getVersion();
        assertEquals(expResult, result, 0.0);
    }
}

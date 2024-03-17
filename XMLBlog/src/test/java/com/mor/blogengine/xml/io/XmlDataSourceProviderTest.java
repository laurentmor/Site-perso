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
package com.mor.blogengine.xml.io;

// ~--- non-JDK imports --------------------------------------------------------

import com.mor.test.PropertiesConsumingTestCase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author laurent
 */
@DisplayName("Xml Data Source Provider Test")
public class XmlDataSourceProviderTest extends PropertiesConsumingTestCase {



    @Test
    @DisplayName("XmlDataSourceProviderTest.ProvideWithNoProperties")
    void testProvideWithNoProperties() {
        Exception e = assertThrows(NullPointerException.class, () -> new XmlDataSourceProvider(null).provide());
        assertEquals("Properties null or not loaded", e.getMessage());


    }
    @SneakyThrows
    @Test @DisplayName("XmlDataSourceProviderTest.ProvideWithProperties")
    void testProvideWithProperties(){

        XmlDataSourceProvider xmlDataSourceProvider =new XmlDataSourceProvider(mConfig);
        assertNotNull(xmlDataSourceProvider.provide());
    }


}
// ~ Formatted by Jindent --- http://www.jindent.com

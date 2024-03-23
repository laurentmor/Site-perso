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

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.test.XMLConsumingTestCase;
import lombok.SneakyThrows;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author laurent
 */
@DisplayName("Xml Data Source Provider Tests")
public class XmlDataSourceProviderTest extends XMLConsumingTestCase {


    @Test
    @DisplayName("XmlDataSourceProviderTest.ProvideWithNoProperties")
    void testProvideWithNoProperties() {
        Exception e = assertThrows(NullPointerException.class, () -> new XmlDataSourceProvider(null).provide());
        assertEquals("Properties null or not loaded", e.getMessage());


    }

    @SneakyThrows
    @Test
    @DisplayName("XmlDataSourceProviderTest.ProvideWithProperties")
    void testProvideWithProperties() {

        XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
        assertNotNull(xmlDataSourceProvider.provide());
    }


    @Test
    void provide() {

    }

    @Test
    @DisplayName("Test write() method with correct settings")
    void writeFine() throws Exception {
        XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
        try {

            assertTrue(xmlDataSourceProvider.write(getBlogDocument()));
        } catch (MissingPropertyException e) {
            throw new RuntimeException(e);
        } catch (IncorrectPropertyValueException e) {
            throw new Exception(e);
        }
    }
    @Test
    @DisplayName("Test write() method with missing mode")
    void writeWithMissingMode(){
        assertThrows(MissingPropertyException.class, () -> {
            mConfig.remove("application.mode");
            XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
            xmlDataSourceProvider.write(getBlogDocument());

        });
    }
    @Test
    @DisplayName("Test write() method with incorrect mode")
    void writeWithIncorrectMode(){
        mConfig.setProperty("application.mode","UAT");
       assertThrows(IncorrectPropertyValueException.class, () -> {

            XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
            xmlDataSourceProvider.write(getBlogDocument());

        });
    }
    @Test
    @DisplayName("Test write() method causing IOException")
    void writeCausingIOException(){

       assertThrows(IOException.class, () -> {
           File file=FileUtils.getFile(mConfig.getProperty("datasource.xml"));
           //locking file before write causes the needed IOExceptiom
           try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
               randomAccessFile.getChannel().lock();
           }
           XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
            xmlDataSourceProvider.write(getBlogDocument());

        });
    }

    @Test
    void saveChanges() {
    }
}
// ~ Formatted by Jindent --- http://www.jindent.com

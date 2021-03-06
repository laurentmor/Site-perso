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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.test.data;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.xml.io.XmlDataSourceProvider;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

//~--- JDK imports ------------------------------------------------------------

import javax.validation.constraints.NotNull;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;

import java.util.Properties;

/**
 *
 * @author laurent
 */
public abstract class AbstractInMemoryXmlDataSourceTestCase  {

    /**
     *
     */
    protected Document document = null;

    /**
     *
     */
    protected Properties properties = null;


    /**
     *
     * @throws Exception
     */

    protected void setUp(@NotNull(message = "Bang!") Properties p)  {

        properties = p;
        try {
            document = new XmlDataSourceProvider(properties).provide();
        } catch (SAXException | IncorrectPropertyValueException | ParserConfigurationException | DocumentException | IOException | MissingPropertyException e) {
            e.printStackTrace();
        }


    }


}


//~ Formatted by Jindent --- http://www.jindent.com

/** * Copyright 2021 Laurent * <p> * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * <p> * http://www.apache.org/licenses/LICENSE-2.0 * <p> * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * <p> * Copyright 2021 Laurent * <p> * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * <p> * http://www.apache.org/licenses/LICENSE-2.0 * <p> * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * <p> * Copyright 2021 Laurent * <p> * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * <p> * http://www.apache.org/licenses/LICENSE-2.0 * <p> * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. *//* * The MIT License * * Copyright 2015 Laurent Morissette. * * Permission is hereby granted, free of charge, to any person obtaining a copy * of this software and associated documentation files (the "Software"), to deal * in the Software without restriction, including without limitation the rights * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell * copies of the Software, and to permit persons to whom the Software is * furnished to do so, subject to the following conditions: * * The above copyright notice and this permission notice shall be included in * all copies or substantial portions of the Software. * * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN * THE SOFTWARE. */package com.mor.test;import org.dom4j.Document;import org.dom4j.DocumentException;import org.dom4j.DocumentHelper;import java.util.logging.Level;import java.util.logging.Logger;public abstract class XMLConsumingTestCase extends PropertiesConsumingTestCase {    public XMLConsumingTestCase() {        super();    }    public static Document getDefautDocument() {        try {            return DocumentHelper.parseText(                    "<root></root>"            );        } catch (DocumentException ex) {            Logger.getLogger(XMLConsumingTestCase.class.getName()).log(Level.SEVERE, null, ex);        }        return null;    }    public static Document getBlogDocument() {        String docText = """                <?xml version="1.0" encoding="UTF-8"?>                <!--Sample XML file generated by XMLSpy v2009 sp1 (https://www.altova.com)-->                <Blog xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="blog.xsd">                    <Entry date="2004-09-09" ID="ID_152369776" categoryID="ID_1222333" allowComments="false" >                      \s                        <Text>                            test entry !                        </Text>                        <Resume>                            feed                        </Resume>                        <Comment ID="ID_1476476016" date="Mon Nov 23 00:00:00 EST 2009" entryID="ID_152369776" author="Mike" webPage="no page">                            <CommentText>Hi HI</CommentText>                        </Comment>                    </Entry>                    <Category name="test" ID="ID_1222333"/>                    <Category ID="ID_320524799" name="added" description=""/>                    <Category ID="ID_-492027613" name="remove" description=""/>                </Blog>""";        try {            return DocumentHelper.parseText(                    docText            );        } catch (DocumentException ex) {            Logger.getLogger(XMLConsumingTestCase.class.getName()).log(Level.SEVERE, null, ex);        }        return null;    }}
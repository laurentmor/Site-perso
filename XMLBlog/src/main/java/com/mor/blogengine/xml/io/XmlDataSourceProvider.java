/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.blogengine.xml.io;//~--- non-JDK imports --------------------------------------------------------import com.mor.blogengine.exception.IncorrectPropertyValueException;import com.mor.blogengine.exception.MissingPropertyException;import com.mor.common.PropertiesUserObject;import jakarta.validation.constraints.NotNull;import java.io.File;import java.io.IOException;import java.util.Properties;import javax.xml.parsers.ParserConfigurationException;import lombok.Getter;import lombok.Setter;import org.apache.commons.io.FileUtils;import org.dom4j.Document;import org.dom4j.DocumentException;import org.dom4j.io.OutputFormat;import org.dom4j.io.SAXReader;import org.dom4j.io.XMLWriter;import org.xml.sax.SAXException;/** * @author laurent */@Setter@Getterpublic class XmlDataSourceProvider extends PropertiesUserObject {  public static final String JAXP_SCHEMA_LANGUAGE      = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";  public static final String JAXP_SCHEMA_SOURCE      = "http://java.sun.com/xml/jaxp/properties/schemaSource";  @NotNull (message = "Test")  private Document mProvidedDoc = null;  public XmlDataSourceProvider(      @NotNull (message = "Can`t create XmlDataSourceProvider because config is NULL") final Properties p) {    super(p);  }  /**   * <pre>   * Provide a Dom4J document from various source type   *   *   * @return built document   * </pre>   */  public Document provide()      throws SAXException, ParserConfigurationException, DocumentException, MissingPropertyException, IncorrectPropertyValueException {    File xml = getXml();    File schema = getSchema();    if (schema != null) {      mProvidedDoc = createReaderAgainstSchema().read(xml);    }    return mProvidedDoc;  }  /**   * Setup validation features for given reader   */  private SAXReader createReaderAgainstSchema()      throws SAXException, MissingPropertyException, IncorrectPropertyValueException {    trace("Parser created OK");    SAXReader reader = new SAXReader();    trace("reader created OK");    trace("set reader properties");    // set the validation feature to true to report validation errors    reader.setFeature("http://xml.org/sax/features/validation", true);    // set the validation/schema feature to true to report validation errors against a schema    reader.setFeature("http://apache.org/xml/features/validation/schema", true);    //set the validation/schema-full-checking feature to true to enable full schema, grammar-constraint checking    reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);    trace("returning reader...");    return reader;  }  /**   * writes in a file   */  boolean write(Document pDocument)      throws MissingPropertyException, IncorrectPropertyValueException, IOException {    try {      OutputFormat format = new OutputFormat();      format.setEncoding(getFileEncoding());      XMLWriter writer = new XMLWriter(FileUtils.openOutputStream(FileUtils.getFile(getXml())),          format);      writer.write(pDocument);      writer.close();      return true;    } catch (IOException ex) {      trace("Error saving file..." + ex);      throw ex;    }  }  /**   * Saves changes in dom structure   *   * @return true if saved correctly   */  public boolean saveChanges()      throws MissingPropertyException, IncorrectPropertyValueException, IOException {    boolean saved = false;    if (isPersistingNecessary()) {      trace("saving...");      saved = write(mProvidedDoc);    } else {      trace("if persisting was set to on changes would be saved to file");    }    return saved;  }}
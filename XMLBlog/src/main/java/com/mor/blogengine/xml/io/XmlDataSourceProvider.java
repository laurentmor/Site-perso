/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.blogengine.xml.io;// ~--- non-JDK imports --------------------------------------------------------import com.mor.blogengine.exception.IncorrectPropertyValueException;import com.mor.blogengine.exception.MissingPropertyException;import com.mor.common.PropertiesUserObject;import jakarta.validation.constraints.NotNull;import java.io.File;import java.io.IOException;import java.util.Properties;import javax.xml.XMLConstants;import javax.xml.parsers.ParserConfigurationException;import javax.xml.transform.stream.StreamSource;import javax.xml.validation.Schema;import javax.xml.validation.SchemaFactory;import javax.xml.validation.Validator;import lombok.Getter;import lombok.Setter;import org.apache.commons.io.FileUtils;import org.dom4j.Document;import org.dom4j.DocumentException;import org.dom4j.io.OutputFormat;import org.dom4j.io.SAXReader;import org.dom4j.io.XMLWriter;import org.xml.sax.SAXException;/** * Does low level i/o ops. * * @author laurent */@Setter@Getterpublic final class XmlDataSourceProvider extends PropertiesUserObject {  /** JAXP lang URL. */  public static final String JAXP_SCHEMA_LANGUAGE =      "http://java.sun.com/xml/jaxp/properties/schemaLanguage";  /** JAXP schema URL. */  public static final String JAXP_SCHEMA_SOURCE =      "http://java.sun.com/xml/jaxp/properties/schemaSource";  /** Document to handle. */  @NotNull(message = "Can`t create XmlDataSourceProvider because config is NULL")  private Document document = null;  /**   * Default constructor.   *   * @param p app config   */  public XmlDataSourceProvider(      @NotNull(message = "Can`t create XmlDataSourceProvider because config is NULL")          final Properties p) {    super(p);  }  /**   * Provide a Dom4J document from various source type.   *   * @return built document   * @throws SAXException when there's a Sax issue   * @throws ParserConfigurationException when there's a parser issue   * @throws DocumentException when there's a Document issue   * @throws MissingPropertyException when a config property is missing   * @throws IncorrectPropertyValueException when a config property is incorrectly set   */  public Document provide()      throws SAXException,          ParserConfigurationException,          DocumentException,          MissingPropertyException,          IncorrectPropertyValueException {    File xml = getXml();    File schema = getSchema();    trace(schema.getName());    if (isXmlValid(xml, schema)) {      document = createReaderAgainstSchema().read(xml);    }    return document;  }  /**   * Check if xml is valid.   *   * @param xml the XML to validate   * @param s the schema   * @return true if it's valid   */  private boolean isXmlValid(final File xml, final File s) {    try {      SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);      Schema schema = factory.newSchema(s);      Validator validator = schema.newValidator();      validator.validate(new StreamSource(xml));    } catch (IOException | SAXException e) {      return false;    }    return true;  }  /**   * Setup validation features for given reader.   *   * @throws SAXException when there's a parser issue   * @throws MissingPropertyException when a config property is missing   * @throws IncorrectPropertyValueException when a config property is incorrectly set   * @return the SaxReader   */  private SAXReader createReaderAgainstSchema()      throws SAXException, MissingPropertyException, IncorrectPropertyValueException {    trace("Parser created OK");    SAXReader reader = new SAXReader();    trace("reader created OK");    trace("set reader properties");    // set the validation feature to true to report validation errors    reader.setFeature("http://xml.org/sax/features/validation", true);    // set the validation/schema feature to true to report validation errors against a schema    reader.setFeature("http://apache.org/xml/features/validation/schema", true);    // set the validation/schema-full-checking feature to true to enable full schema,    // grammar-constraint checking    reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);    trace("returning reader...");    return reader;  }  /**   * writes in a file.   *   * @param d the doc to write   * @return true if write was successful   * @throws IOException when there's an i/o issue   * @throws MissingPropertyException when a config property is missing   * @throws IncorrectPropertyValueException when a config property is incorrectly set   */  boolean write(final Document d)      throws MissingPropertyException, IncorrectPropertyValueException, IOException {    try {      OutputFormat format = new OutputFormat();      format.setEncoding(getFileEncoding());      XMLWriter writer =          new XMLWriter(FileUtils.openOutputStream(FileUtils.getFile(getXml())), format);      writer.write(d);      writer.close();      return true;    } catch (IOException ex) {      trace("Error saving file..." + ex);      throw ex;    }  }  /**   * Saves changes in dom structure.   *   * @return true if saved correctly   * @throws IOException when there's an i/o issue   * @throws MissingPropertyException when a config property is missing   * @throws IncorrectPropertyValueException when a config property is incorrectly set   */  public boolean saveChanges()      throws MissingPropertyException, IncorrectPropertyValueException, IOException {    boolean saved = false;    if (isPersistingNecessary()) {      trace("****saving...***");      saved = write(document);    } else {      trace("if persisting was set to on changes would be saved to file");    }    return saved;  }}
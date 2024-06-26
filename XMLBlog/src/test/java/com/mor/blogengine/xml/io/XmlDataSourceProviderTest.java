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
package com.mor.blogengine.xml.io;

// ~--- non-JDK imports --------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.common.BeanValidator;
import com.mor.test.XMLConsumingTestCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.util.Set;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DS provider tests suite.
 *
 * @author laurent
 */
@DisplayName("Xml Data Source Provider Tests")
public class XmlDataSourceProviderTest extends XMLConsumingTestCase {

  /** Default constructor. */
  public XmlDataSourceProviderTest() {
    super();
  }

  /** Test Provide With No Properties. */
  @Test
  @DisplayName("XmlDataSourceProviderTest.ProvideWithNoProperties")
  void testProvideWithNoProperties() {
    XmlDataSourceProvider provider = new XmlDataSourceProvider(null);
    Validator validator = new BeanValidator().getValidator();
    Set<ConstraintViolation<XmlDataSourceProvider>> violations = validator.validate(provider);
    getLog().info(violations.stream().toList().getFirst().getMessage());
    assertFalse(violations.isEmpty());
  }

  /** Test Provide With Properties. */
  @SneakyThrows
  @Test
  @DisplayName("XmlDataSourceProviderTest.ProvideWithProperties")
  void testProvideWithProperties() {

    XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
    // assertNotNull(xmlDataSourceProvider.provide());
  }

  /**
   * Test write() method with correct settings
   *
   * @throws Exception when there is an issue.
   */
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

  /** Test write() method with missing mode. */
  @Test
  @DisplayName("Test write() method with missing mode")
  void writeWithMissingMode() {
    assertThrows(
        MissingPropertyException.class,
        () -> {
          mConfig.remove("application.mode");
          XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
          xmlDataSourceProvider.write(getBlogDocument());
        });
  }

  /** Test write() method with incorrect mode. */
  @Test
  @DisplayName("Test write() method with incorrect mode")
  void writeWithIncorrectMode() {
    mConfig.setProperty("application.mode", "UAT");
    assertThrows(
        IncorrectPropertyValueException.class,
        () -> {
          XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
          xmlDataSourceProvider.write(getBlogDocument());
        });
  }

  /** Test write() method causing IOException. */
  @Test
  @DisplayName("Test write() method causing IOException")
  void writeCausingIOException() {

    assertThrows(
        IOException.class,
        () -> {
          lockFile();
          XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
          xmlDataSourceProvider.write(getBlogDocument());
        });
  }

  /** Test saveChanges() in test mode with correct I/o. */
  @DisplayName("Test saveChanges() in test mode with correct I/o")
  @Test
  void saveChangesWithCorrectIOInTestMode() {
    XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
    try {
      assertFalse(xmlDataSourceProvider.saveChanges());
    } catch (MissingPropertyException | IOException | IncorrectPropertyValueException e) {
      throw new RuntimeException(e);
    }
  }

  // @Test
  @DisplayName("Test save() method in Production Mode")
  void saveInProductionMode()
      throws IOException, MissingPropertyException, IncorrectPropertyValueException {
    mConfig.setProperty("application.mode", "Production");
    XmlDataSourceProvider xmlDataSourceProvider = new XmlDataSourceProvider(mConfig);
    xmlDataSourceProvider.setDocument(getBlogDocument());
    assertTrue(xmlDataSourceProvider.saveChanges());
  }
}

// ~ Formatted by Jindent --- http://www.jindent.com

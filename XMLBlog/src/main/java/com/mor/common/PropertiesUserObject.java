/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.common;// ~--- JDK imports ------------------------------------------------------------import static com.mor.blogengine.xpath.XpathVersion.typeLess;import static com.mor.blogengine.xpath.XpathVersion.typed;import com.mor.blogengine.exception.IncorrectPropertyValueException;import com.mor.blogengine.exception.MissingPropertyException;import com.mor.blogengine.xpath.XpathVersion;import jakarta.validation.constraints.NotNull;import java.io.File;import java.util.Objects;import java.util.Properties;import lombok.Getter;import lombok.Setter;import lombok.extern.slf4j.Slf4j;import org.apache.commons.io.FileUtils;/** * Class to give access to properties file to subclass. * * @author laurent */@Setter@SuppressWarnings("SameReturnValue")@Slf4jpublic class PropertiesUserObject {  /** Persisting YES flag. */  static final boolean YES = true;  /** Persisting NO flag. */  static final boolean NO = false;  /** application mode key. */  static final String MODE_PROPERTY = "application.mode";  /** application debug key. */  static final String DEBUG_PROPERTY = "application.debug";  /** application LEVEL key. */  static final String LEVEL_PROPERTY = "log.level";  /** XML encoding charset key. */  static final String ENCODING_PROPERTY = "file.encoding";  /** supported XPath version key. */  static final String XPATH_PROPERTY = "xpath.version";  /** XKL datasource location key. */  static final String XML_PROPERTY = "datasource.xml";  /** schema location key. */  static final String XSD_PROPERTY = "datasource.xsd";  /** Object configuration holder. */  @Getter  // @NotNull (message = "Test 2")  private Properties config;  /** schema file. */  private File schema = null;  /**   * Default constructor with properties.   *   * @param p - configuration.   */  public PropertiesUserObject(@NotNull final Properties p) {    config = p;  }  /**   * Does some logging if application is in test mode and debug is set to On.   *   * @param traceMessage - what to trace   * @throws MissingPropertyException when debug property is missing.   * @throws IncorrectPropertyValueException when debug is incorrectly set.   */  public void trace(final String traceMessage)      throws MissingPropertyException, IncorrectPropertyValueException {    if (isModePropertySet() && areTestConditionsMet() && areDebugConditionsMet() && isDebugOn()) {      logBasedOnLevel(traceMessage, null);    }  }  /**   * Does some logging if application is in test mode and debug is set to On.   *   * @param traceMessage - what to trace   * @param t - exception   * @throws MissingPropertyException when debug property is missing   * @throws IncorrectPropertyValueException when debug is incorrectly set   */  public void trace(final String traceMessage, final Throwable t)      throws MissingPropertyException, IncorrectPropertyValueException {    if (isModePropertySet() && areTestConditionsMet() && areDebugConditionsMet() && isDebugOn()) {      logBasedOnLevel(traceMessage, t);    }  }  /**   * Check if application has the correct conditions to be in debug mode.   *   * @return true if we are in debug   * @throws MissingPropertyException when debug property is missing   * @throws IncorrectPropertyValueException when debug is incorrectly set   */  private boolean areDebugConditionsMet()      throws MissingPropertyException, IncorrectPropertyValueException {    return isDebugPropertiesSet() && isInKnownDebugState();  }  /**   * Check if application has the correct conditions to be in test mode.   *   * @return true if we are in test   * @throws MissingPropertyException when debug property is missing   * @throws IncorrectPropertyValueException when debug is incorrectly set   */  private boolean areTestConditionsMet()      throws IncorrectPropertyValueException, MissingPropertyException {    return isInKnownMode() && isInTestMode();  }  /**   * check if changes need to be saved on file.   *   * @return YES if we have to save change in file.   * @throws MissingPropertyException when mode Property is not set   */  public boolean isPersistingNecessary() throws MissingPropertyException {    if (isModePropertySet()) {      return isInTestMode() ? NO : YES;    }    throw new MissingPropertyException(MODE_PROPERTY);  }  /**   * Get the XPath version supported in config.   *   * @return the supported xpath version of specs.   */  public final XpathVersion getSupportedXpathVersion() {    if ("2.0f".equalsIgnoreCase(getPropertyValue(XPATH_PROPERTY))) {      return typed;    }    return typeLess;  }  /**   * check if configuration has mode property.   *   * @return does the config file have all the necessary properties ?   * @throws MissingPropertyException when mode Property is not set   */  public final boolean isModePropertySet() throws MissingPropertyException {    return isPropertyExistent(MODE_PROPERTY);  }  /**   * Get the application level.   *   * @return Level   */  private LogLevel getLoggingLevel() {    return LogLevel.valueOf(Objects.requireNonNull(getPropertyValue(LEVEL_PROPERTY)));  }  /**   * Get application files encoding.   *   * @return charset.   */  public final String getFileEncoding() {    return getPropertyValue(ENCODING_PROPERTY);  }  /**   * checks if application is in test mode.   *   * @return true if application is in test mode with debug flag set to on.   * @throws MissingPropertyException when mode Property is not set   */  protected final boolean isInTestMode() throws MissingPropertyException {    if (isModePropertySet()) {      return Objects.requireNonNull(getPropertyValue(MODE_PROPERTY)).equalsIgnoreCase("test");    }    return false;  }  /**   * Check if application config has given property.   *   * @param prop property to check   * @return true if property is in config   * @throws MissingPropertyException when property is missing   */  private boolean isPropertyExistent(final String prop) throws MissingPropertyException {    if (config.getProperty(prop) != null) {      return true;    } else {      throw new MissingPropertyException(prop);    }  }  /**   * check if the application is in Debug mode.   *   * @return tue if YES .   */  public final boolean isDebugOn() {    return Objects.requireNonNull(getPropertyValue(DEBUG_PROPERTY))        .equalsIgnoreCase(DebugMode.On.toString());  }  /**   * get the XSD to validate file.   *   * @return schema full url .   * @throws MissingPropertyException - if config is missing a property   * @throws IncorrectPropertyValueException if config is incorrect   */  protected final File getSchema()      throws MissingPropertyException, IncorrectPropertyValueException {    String fileName = getPropertyValue(XSD_PROPERTY);    File schemaFile = getFileFromProperty(fileName);    trace("Retrieving schema URL at : " + Objects.requireNonNull(schemaFile).getAbsolutePath());    schema = schemaFile;    return schema;  }  /**   * Get the application datasource XML.   *   * @return xml full url.   * @throws MissingPropertyException - if config is missing a property.   * @throws IncorrectPropertyValueException if config is incorrect.   */  protected final File getXml() throws MissingPropertyException, IncorrectPropertyValueException {    String xmlFullPath = getPropertyValue(XML_PROPERTY);    File xmlFile = getFileFromProperty(xmlFullPath);    trace("Retrieving XML URL at : " + xmlFile.getAbsolutePath());    return xmlFile;  }  /**   * Check if application has the debug property.   *   * @return true if config has the debug property   * @throws MissingPropertyException when debug property is missing   */  private boolean isDebugPropertiesSet() throws MissingPropertyException {    return isPropertyExistent(DEBUG_PROPERTY);  }  /**   * Get a config property value.   *   * @param property the config property key name.   * @return tue if YES .   */  public final String getPropertyValue(final String property) {    if (config != null && !config.isEmpty()) {      return config.getProperty(property);    }    return null;  }  /**   * Check if application has a known mode.   *   * @return true if mode is known   * @throws IncorrectPropertyValueException when debug is incorrectly set   */  private boolean isInKnownMode() throws IncorrectPropertyValueException {    String mode = getPropertyValue(MODE_PROPERTY);    String test = ApplicationMode.Test.toString();    String production = ApplicationMode.Production.toString();    boolean isKnownMode =        Objects.requireNonNull(mode).equalsIgnoreCase(test) || mode.equalsIgnoreCase(production);    if (isKnownMode) {      return YES;    }    throw new IncorrectPropertyValueException(        MODE_PROPERTY,        getPropertyValue(MODE_PROPERTY),        ApplicationMode.Test,        ApplicationMode.Production);  }  /**   * Check if application has the correct debug state.   *   * @return true if we are in debug   * @throws IncorrectPropertyValueException when debug is incorrectly set   */  private boolean isInKnownDebugState() throws IncorrectPropertyValueException {    String state = getPropertyValue(DEBUG_PROPERTY);    String on = DebugMode.On.toString();    String off = DebugMode.Off.toString();    boolean isKnownState =        Objects.requireNonNull(state).equalsIgnoreCase(on) || state.equalsIgnoreCase(off);    if (isKnownState) {      return YES;    } else {      throw new IncorrectPropertyValueException(          MODE_PROPERTY, getPropertyValue(MODE_PROPERTY), DebugMode.Off, DebugMode.On);    }  }  /**   * get a file based on its property full path.   *   * @param fileName full path file name   * @return the File object   */  private File getFileFromProperty(final String fileName) {    return FileUtils.getFile(fileName);  }  /**   * Log stuff based on level.   *   * @param traceMessage what to log   * @param t related exception   */  private void logBasedOnLevel(final String traceMessage, final Throwable t) {    switch (getLoggingLevel()) {      case DEBUG -> log.debug(traceMessage, t);      case WARN -> log.warn(traceMessage, t);      case ERROR -> log.error(traceMessage, t);      case INFO -> log.info(traceMessage, t);      default -> log.trace(traceMessage, t);    }  }}// -
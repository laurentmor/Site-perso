/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.test;import java.io.IOException;import java.util.Objects;import java.util.Properties;import java.util.logging.Logger;import lombok.extern.java.Log;import org.apache.commons.io.FileUtils;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.TestInfo;/** * @author laurent */@Logpublic abstract class PropertiesConsumingTestCase {  protected Properties mConfig;  public static Logger getLog() {    return log;  }  @BeforeEach  protected void loadConfig(TestInfo info) {    mConfig = new Properties();    String rootPath = Objects.requireNonNull(Thread.currentThread().        getContextClassLoader().        getResource("blog-resources/")).getPath();    try {      mConfig.loadFromXML(FileUtils.openInputStream(FileUtils.getFile(rootPath + "config.xml")));      // Replace datasource properties with full path based on context path      mConfig.setProperty("datasource.xml", rootPath + "blog.xml");      mConfig.setProperty("datasource.xsd", rootPath + "blog.xsd");      log.info("Configured fine for new test: \u001B[34m{" + info.getDisplayName() + "\u001B[0m}");    } catch (IOException e) {      throw new RuntimeException(e);    }  }}
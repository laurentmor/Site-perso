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
package com.mor.blogengine.controllers;

import com.mor.blogengine.dao.Repository;
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.xml.BlogEntityFactory;
import com.mor.blogengine.xml.IBlogEntityFactory;
import com.mor.blogengine.xml.io.XmlDataSourceProvider;
import com.mor.common.PropertiesUserObject;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import lombok.Getter;
import lombok.NonNull;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;
import org.xml.sax.SAXException;

/**
 * Blog controller superclass.
 *
 * @param <T> Model type
 * @param <R> return type
 * @param <S> search Type
 * @param <D> dataSourceException type
 */
@Getter
public abstract class BlogControllerBase<T, R, S, D extends Throwable>
    extends PropertiesUserObject {

  /** repository instance. */
  protected Repository<T, R, S, D> repository;

  /** The XML document itself. */
  private Document document;

  /** the entity factory that creates concrete model objects. */
  private IBlogEntityFactory<DefaultElement> factory;

  /**
   * Base class for controllers configuration As we use same configuration in each context we can
   * generalise it.
   *
   * @param p Configuration settings
   * @throws MissingPropertyException when mode Property is not set
   * @throws IncorrectPropertyValueException when a config property is set wrong
   */
  public BlogControllerBase(@NonNull final Properties p)
      throws MissingPropertyException, IncorrectPropertyValueException {

    super(p);
    XmlDataSourceProvider provider = new XmlDataSourceProvider(getConfig());
    try {
      document = provider.provide();
      factory = new BlogEntityFactory();
    } catch (ParserConfigurationException ex) {
      trace("Parser configuration error" + ex.getMessage());
    } catch (DocumentException ex) {
      trace("Document error " + ex.getMessage());
    } catch (SAXException ex) {
      trace(ex.getMessage() + "SaxError error");
    }
  }
}

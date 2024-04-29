/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.blogengine.model;// ~--- non-JDK imports --------------------------------------------------------import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;import jakarta.validation.constraints.NotNull;import java.io.Serial;import java.util.Objects;import lombok.Getter;import lombok.NonNull;import lombok.Setter;import org.apache.commons.lang.builder.HashCodeBuilder;import org.dom4j.tree.DefaultAttribute;import org.dom4j.tree.DefaultElement;/** * Object representing a blog category. * * @author Laurent Morissette */@Getter@Setterpublic final class BlogCategory extends AbstractBlogEntity {  @Serial private static final long serialVersionUID = 7735878793954382143L;  /** category name. -- GETTER -- */  @NotNull(message = "Category name cannot be null")  private String name;  /** category description. */  private String description = null;  /**   * create entity from XML.   *   * @param pElement XML node to create a category from   */  public BlogCategory(final DefaultElement pElement) {    if (null == pElement) {      throw new IllegalArgumentException("pElement required");    }    setElement(pElement);    name = getElement().valueOf("@ame");    description = getElement().valueOf("@description");    formatAttributes();  }  /**   * construct a category in a blog.   *   * @param pCatName name of the category   */  public BlogCategory(final @NonNull String pCatName) {    name = pCatName;    formatAttributes();  }  /**   * construct a category in a blog.   *   * @param pCatName name of the category   * @param pDesc description of category   */  public BlogCategory(@NonNull final String pCatName, final String pDesc) {    name = pCatName;    description = pDesc;    formatAttributes();  }  /** Default constructor. */  public BlogCategory() {    name = "Default";    description = "Default description";  }  /**   * Compares two categories.   *   * @param o Category   * @return 1 if both category are the same -1 if not   */  @SuppressWarnings("unused")  public int compareTo(final Object o) {    BlogCategory lCatComp = (BlogCategory) o;    if (getId().equalsIgnoreCase(lCatComp.getId())) {      return 1;    }    return -1;  }  /**   * a-like as {@link #toString() }.   *   * @return an XML representation of element   */  @Override  public DefaultElement toElement() {    // QName lElementDecl = new QName("Category", mNamespace);    DefaultElement lReturnElement = new DefaultElement("Category");    // Attribute= a=new Attribute("", mCatName)    lReturnElement.add(new DefaultAttribute("ID", getId()));    lReturnElement.add(new DefaultAttribute("name", getName()));    lReturnElement.add(new DefaultAttribute("description", getDescription()));    return lReturnElement;  }  @Override  void formatAttributes() {    name = escapeHtml(name);    description = escapeHtml(description);  }  @Override  public int hashCode() {    final int i = 15;    final int m = 745;    HashCodeBuilder hb = new HashCodeBuilder(i, m);    hb.append(getName());    hb.append(getDescription());    return hb.toHashCode();  }  @Override  public boolean equals(final Object obj) {    if (obj == null) {      return false;    }    if (getClass() != obj.getClass()) {      return false;    }    final BlogCategory other = (BlogCategory) obj;    if (!Objects.equals(this.name, other.name)) {      return false;    }    return Objects.equals(this.description, other.description);  }  @Override  public String toString() {    return (this.description == null) ? (name) : "Name: " + name + " Description: " + description;  }}
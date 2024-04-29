/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.blogengine.model;// ~--- non-JDK imports --------------------------------------------------------import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;import java.io.Serial;import lombok.EqualsAndHashCode;import lombok.Getter;import lombok.NonNull;import lombok.RequiredArgsConstructor;import lombok.Setter;import lombok.ToString;import org.dom4j.tree.DefaultAttribute;import org.dom4j.tree.DefaultElement;/** * Object representing a blog entry. * * @author Laurent Morissette */@SuppressWarnings ("unused")@Getter@Setter@RequiredArgsConstructor ()@ToString@EqualsAndHashCode (callSuper = true)public final class BlogEntry extends AbstractBlogEntity {  @Serial  private static final long serialVersionUID = 1790652709303847978L;  /**   * Are comments allowed for this entry.   */  private String allowComments = "false";  /**   * Category for this entry.   */  private String categoryId = null;  /**   * entry date.   */  private String date = null;  /**   * entry short text.   */  private String resume = null;  /**   * entry full text.   */  private String text = null;  /**   * Creates a new instance of blogEntry from xml node.   *   * @param e - element to construct from   */  public BlogEntry(final @NonNull DefaultElement e) {    super();    setElement(e);    setAllowComments(e.valueOf("@allowComments"));    setDate(e.valueOf("@date"));    setCategoryId(e.valueOf("@categoryID"));    setText(e.element("Text").getText());    if (e.element("Resume") == null) {      setResume("");    } else {      setResume(e.element("Resume").getTextTrim());    }    formatAttributes();  }  /**   * Default contractor.   *   * @param pDate          the date of entry redaction   * @param pTexte         text content of entry   * @param pCatID         category ID under which the entry is   * @param pResume        a shot abstract of the entry   * @param pAllowComments are comments permitted ?   */  public BlogEntry(    final String pDate,    final String pTexte,    final String pCatID,    final String pResume,    final String pAllowComments) {    setCategoryId(pCatID);    setDate(pDate);    setText(pTexte);    setResume(pResume);    setAllowComments(pAllowComments);    formatAttributes();  }  /**   * transform entry to XML.   *   * @return XML node representation of entry   */  @Override  public DefaultElement toElement() {    // this is element Declaration in complete form.    // QName lElementDecl = new QName("Entry", mNamespace);    DefaultElement lReturnElement = new DefaultElement("Entry");    lReturnElement.add(new DefaultAttribute("date", getDate()));    lReturnElement.add(new DefaultAttribute("categoryID", getCategoryId()));    lReturnElement.add(new DefaultAttribute("allowComments", getAllowComments()));    lReturnElement.add(new DefaultAttribute("ID", getId()));    //      this is element Declaration in complete form.    // QName lEntryTextDecl = new QName("Text", mNamespace);    DefaultElement lEntryText = new DefaultElement("Text");    lEntryText.addText(getText());    lReturnElement.add(lEntryText);    // QName lResumeDecl = new QName("Resume", mNamespace);    DefaultElement lEntryResume = new DefaultElement("Resume");    lEntryResume.addText(getResume());    lReturnElement.add(lEntryResume);    return lReturnElement;  }  /**   * Check if entry can be commented.   *   * @return are comments permitted or not ?   */  public boolean canComment() {    return allowComments.equalsIgnoreCase("true");  }  /**   * format Entity for HTML display.   */  @Override  void formatAttributes() {    resume = escapeHtml(resume);    text = escapeHtml(text);  }}
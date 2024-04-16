/* * Copyright (c) 2024 * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. * * */package com.mor.blogengine.model;//~--- non-JDK imports --------------------------------------------------------import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;import java.io.Serial;import java.util.Objects;import lombok.Getter;import lombok.Setter;import org.apache.commons.lang.builder.HashCodeBuilder;import org.dom4j.tree.DefaultAttribute;import org.dom4j.tree.DefaultElement;/** * Object representing a blog comment. * * @author Laurent Morissette */@SuppressWarnings ("unused")@Setter@Getterpublic final class BlogComment extends AbstractBlogEntity {  @Serial  private static final long serialVersionUID = -3684562856217228760L;  /**   * Comment author.   */  private String author = null;  /**   * Comment text. -- GETTER --   *   * @return text of comment   */  @Getter  private String commentText = null;  /**   * Comment date.   */  private String date = null;  /**   * Comment parent entry id. -- GETTER --   *   * @return the EntryID   */  @Getter  private String entryId = null;  /**   * Commenter web page .   */  private String webPage = null;  /**   * .   *   * @param element XML node to create a comment from   */  public BlogComment(final DefaultElement element) {    mAssociatedElement = element;    setDate(element.valueOf("@date"));    setAuthor(element.valueOf("@author"));    setWebPage(element.valueOf("@webPage"));    setCommentText(element.element("CommentText").getText());    setEntryId(element.valueOf("@entryID"));    formatAttributes();  }  /**   * Creates a new instance of blogComment.   *   * @param entry         entry ID related to the comment   * @param commentDate   date of comment   * @param commentAuthor author of the comment   * @param commentPage   web page of the author of comment   * @param text          text of comment   */  public BlogComment(final String entry, final String commentDate, final String commentAuthor,      final String commentPage,      final String text) {    setEntryId(entry);    setDate(commentDate);    setAuthor(commentAuthor);    setWebPage(commentPage);    setCommentText(text);    formatAttributes();  }  /**   *   */  String getDate() {    return date;  }  /**   *   */  private void setDate(final String date) {    this.date = date;  }  /**   *   */  String getAuthor() {    return author;  }  /**   *   */  private void setAuthor(String mAuthor) {    this.author = mAuthor;  }  /**   *   */  String getWebPage() {    return webPage;  }  /**   *   */  private void setWebPage(String mWebPage) {    this.webPage = mWebPage;  }  /**   *   */  private void setCommentText(String mCommentText) {    this.commentText = mCommentText;  }  @Override  public DefaultElement toElement() {    // this is element Declaration in complete form.    DefaultElement lReturnElement;    DefaultElement lCommentText = new DefaultElement("CommentText");    lCommentText.addText(commentText);    lReturnElement = new DefaultElement("Comment");    // lReturnElement.add(mNamespace);    lReturnElement.add(new DefaultAttribute("entryID", getEntryId()));    lReturnElement.add(new DefaultAttribute("ID", getEntityiD()));    lReturnElement.add(new DefaultAttribute("date", date));    lReturnElement.add(new DefaultAttribute("author", author));    lReturnElement.add(new DefaultAttribute("webPage", webPage));    lReturnElement.add(lCommentText);    return lReturnElement;  }  @Override  void formatAttributes() {    author = escapeHtml(author);    commentText = escapeHtml(commentText);  }  @Override  public int hashCode() {    return new HashCodeBuilder(19, 1001).append(getAuthor()).append(getCommentText())               .append(getDate()).append(            getWebPage()).toHashCode();  }  @Override  public boolean equals(Object obj) {    if (obj == null) {      return false;    }    if (getClass() != obj.getClass()) {      return false;    }    final BlogComment other = (BlogComment) obj;    if (!Objects.equals(this.author, other.author)) {      return false;    }    if (!Objects.equals(this.commentText, other.commentText)) {      return false;    }    if (!Objects.equals(this.date, other.date)) {      return false;    }    if (!Objects.equals(this.entryId, other.entryId)) {      return false;    }    return Objects.equals(this.webPage, other.webPage);  }  /**   *   */  void setEntryId(String mEntryID) {    this.entryId = mEntryID;  }}
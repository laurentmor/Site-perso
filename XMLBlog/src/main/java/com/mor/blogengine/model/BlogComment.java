
/*
* blogComment.java
*
* Created on 5 mai 2007, 14:36
*
* To change this template, choose Tools | Template Manager
* and open the template in the editor.
 */
package com.mor.blogengine.model;

//~--- non-JDK imports --------------------------------------------------------

import com.mor.blogengine.text.StringUtil;

import org.apache.commons.lang.builder.HashCodeBuilder;

import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

/**
 * Object representing a blog comment
 *
 * @author Laurent Morissette
 *
 */
public final class BlogComment extends AbstractBlogEntity {
    private static final long serialVersionUID = -3684562856217228760L;
    private String            mAuthor          = null;
    private String            mCommentText     = null;
    private String            mDate            = null;
    private String            mEntryID         = null;
    private String            mWebPage         = null;

    /**
     *
     *   @param element XML node to create a comment from
     */
    public BlogComment(DefaultElement element) {
        mAassociatedElement = element;
        setDate(element.valueOf("@date"));
        setAuthor(element.valueOf("@author"));
        setWebPage(element.valueOf("@webPage"));
        setCommentText(element.element("CommentText").getText());
        setEntityID();
        setEntryID(element.valueOf("@entriID"));
        formatAttributesValuesAsHTML();
    }

    /**
     * Creates a new instance of blogComment
     *
     * @param entryID entry ID related to the comment
     * @param pDate date of comment
     * @param pAuthor author of the comment
     * @param pPage web page of the author of comment
     * @param pText text of comment
     *
     */
    public BlogComment(String entryID, String pDate, String pAuthor, String pPage, String pText) {
        setEntryID(entryID);
        setDate(pDate);
        setAuthor(pAuthor);
        setWebPage(pPage);
        setCommentText(pText);
        formatAttributesValuesAsHTML();
    }

    /**
     *
     * @return
     */
    String getDate() {
        return mDate;
    }

    /**
     *
     * @param mDate
     */
    private void setDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     *
     * @return
     */
    String getAuthor() {
        return mAuthor;
    }

    /**
     *
     * @param mAuthor
     */
    private void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    /**
     *
     * @return
     */
    String getWebPage() {
        return mWebPage;
    }

    /**
     *
     * @param mWebPage
     */
    private void setWebPage(String mWebPage) {
        this.mWebPage = mWebPage;
    }

    /**
     *
     * @return text of comment
     */
    public String getCommentText() {
        return mCommentText;
    }

    /**
     *
     * @param mCommentText
     */
    private void setCommentText(String mCommentText) {
        this.mCommentText = mCommentText;
    }

    @Override
    public DefaultElement toElement() {

        // this is element Declaration in complete form.
        // QName lCommentTextDecl = new QName("CommentText", mNamespace);
        // QName lReturnelementdecl = new QName("Comment", mNamespace);
        DefaultElement lReturnElement = null;
        DefaultElement lCommentText   = new DefaultElement("CommentText");

        lCommentText.addText(mCommentText);
        lReturnElement = new DefaultElement("Comment");

        // lReturnElement.add(mNamespace);
        lReturnElement.add(new DefaultAttribute("entryID", getEntryID()));
        lReturnElement.add(new DefaultAttribute("ID", getEntityID()));
        lReturnElement.add(new DefaultAttribute("date", mDate));
        lReturnElement.add(new DefaultAttribute("author", mAuthor));
        lReturnElement.add(new DefaultAttribute("webPage", mWebPage));
        lReturnElement.add(lCommentText);

        return lReturnElement;
    }

    @Override
    void formatAttributesValuesAsHTML() {
        setCommentText(StringUtil.toHTMLString(mCommentText));
        setAuthor(StringUtil.toHTMLString(mAuthor));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(19, 1001).append(getAuthor()).append(getCommentText()).append(getDate()).append(
            getWebPage()).toHashCode();
    }

    /**
     * @return the EntryID
     */
    public String getEntryID() {
        return mEntryID;
    }

    /**
     * @param EntryID the EntryID to set
     * @param mEntryID
     */
    void setEntryID(String mEntryID) {
        this.mEntryID = mEntryID;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com


/*
 * blogEntry.java
 *
 * Created on 5 mai 2007, 14:35
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
 * Object representing a blog entry
 *
 * @author Laurent Morissette
 *
 */
public final class BlogEntry extends AbstractBlogEntity {

    private static final long serialVersionUID = 1790652709303847978L;
    private String mAllowComments = "false";
    private String mCatID = null;
    private String mDate = null;
    private String mResume = null;
    private String mTexte = null;

    /**
     * default constructor
     */
    public BlogEntry() {
    }

    /**
     * Creates a new instance of blogComment from xml node
     *
     * @param pElement l'ï¿½lï¿½ment XML ï¿½ partir duquel contruire le billet
     */
    public BlogEntry(DefaultElement pElement) {
        mAassociatedElement = pElement;
        setAllowComments(pElement.valueOf("@allowComments"));
        setDate(pElement.valueOf("@date"));
        setCatID(pElement.valueOf("@categoryID"));
        setEntityID();
        setTexte(pElement.element("Text").getText());

        if (pElement.element("Resume") == null) {
            setResume("");
        } else {
            setResume(pElement.element("Resume").getTextTrim());
        }

        formatAttributesValuesAsHTML();
    }

    /**
     *
     * @param pDate the date of entry redaction
     * @param pTexte text content of entry
     * @param pCatID category ID under which the entry is
     * @param pResume a shot abstract of the entry
     * @param pAllowComments are comments permitted ?
     */
    public BlogEntry(String pDate, String pTexte, String pCatID, String pResume, String pAllowComments) {
        setCatID(pCatID);
        setDate(pDate);
        setTexte(pTexte);
        setResume(pResume);
        setAllowComments(pAllowComments);
        formatAttributesValuesAsHTML();
    }

    /**
     *
     * @return XML node representation of entry
     */
    @Override
    public DefaultElement toElement() {

        // this is element Declaration in complete form.
        // QName lElementDecl = new QName("Entry", mNamespace);
        DefaultElement lReturnElement = new DefaultElement("Entry");

        lReturnElement.add(new DefaultAttribute("date", getDate()));
        lReturnElement.add(new DefaultAttribute("categoryID", getCatID()));
        lReturnElement.add(new DefaultAttribute("allowComments", getAllowComments()));
        lReturnElement.add(new DefaultAttribute("ID", getEntityID()));

//      this is element Declaration in complete form.
        // QName lEntryTextDecl = new QName("Text", mNamespace);
        DefaultElement lEntryText = new DefaultElement("Text");

        lEntryText.addText(mTexte);
        lReturnElement.add(lEntryText);

        // QName lResumeDecl = new QName("Resume", mNamespace);
        DefaultElement lEntryResume = new DefaultElement("Resume");

        lEntryResume.addText(mResume);
        lReturnElement.add(lEntryResume);

        return lReturnElement;
    }

    /**
     *
     * @return unique category ID of entry
     */
    public String getCatID() {
        return mCatID;
    }

    /**
     * Set unique category ID of entry
     *
     * @param pCatID related category ID
     *
     */
    private void setCatID(String pCatID) {
        this.mCatID = pCatID;
    }

    /**
     * @return entry text
     */
    public String getTexte() {
        return mTexte;
    }

    /**
     *
     * @param pTexte the text content of the entry
     */
    private void setTexte(String pTexte) {
        this.mTexte = pTexte;
    }

    /**
     *
     * @return the date of redaction of entry
     */
    public String getDate() {
        return mDate;
    }

    /**
     *
     * @param mDate the date of entry redaction
     */
    private void setDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     *
     * @return comment permission in XML node
     */
    public String getAllowComments() {
        return mAllowComments;
    }

    /**
     *
     * @param mAllowComments
     */
    private void setAllowComments(String mAllowComments) {
        this.mAllowComments = mAllowComments;
    }

    /**
     *
     * @return are comments permitted or not ?
     */
    public boolean canComment() {
        if (mAllowComments.equalsIgnoreCase("true")) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return short abstract of entry
     */
    public String getResume() {
        return mResume;
    }

    /**
     *
     * @param mResume
     * @param pResume
     */
    private void setResume(String pResume) {
        if ((pResume == null) || pResume.equalsIgnoreCase("")) {
            this.mResume = "No resume";
        } else {
            this.mResume = pResume;
        }
    }

    /**
     *
     */
    @Override
    void formatAttributesValuesAsHTML() {
        setResume(StringUtil.toHTMLString(mResume));
        setTexte(StringUtil.toHTMLString(mTexte));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(15, 587).append(getResume()).append(getTexte()).append(getCatID()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlogEntry other = (BlogEntry) obj;
        if ((this.mAllowComments == null) ? (other.mAllowComments != null) : !this.mAllowComments.equals(other.mAllowComments)) {
            return false;
        }
        if ((this.mCatID == null) ? (other.mCatID != null) : !this.mCatID.equals(other.mCatID)) {
            return false;
        }
        if ((this.mDate == null) ? (other.mDate != null) : !this.mDate.equals(other.mDate)) {
            return false;
        }
        if ((this.mResume == null) ? (other.mResume != null) : !this.mResume.equals(other.mResume)) {
            return false;
        }
        if ((this.mTexte == null) ? (other.mTexte != null) : !this.mTexte.equals(other.mTexte)) {
            return false;
        }
        return true;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

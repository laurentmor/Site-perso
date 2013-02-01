
/*
* blogCategory.java
*
* Created on 5 mai 2007, 14:37
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
 * Object representing a blog category
 *
 * @author Laurent Morissette
 *
 */
public final class BlogCategory extends AbstractBlogEntity  {
    private static final long serialVersionUID = 7735878793954382143L;

    /** category name */
    private String mCatName = null;

    /**
     * category description
     */
    private String mDescription = null;

    /**
     * default constructor
     */
    public BlogCategory() {}

    /**
     *
     * @param pElement XML node to create a category from
     */
    public BlogCategory(DefaultElement pElement) {
        mAassociatedElement = pElement;
        setCatName(mAassociatedElement.valueOf("@name"));
        setEntityID();
        setDescription(mAassociatedElement.valueOf("@description"));
        formatAttributesValuesAsHTML();
    }

    /**
     * contruct a category in a blog
     *
     * @param pCatName name of the category
     */
    public BlogCategory(String pCatName) {
        setCatName(pCatName);
        setDescription(null);
        formatAttributesValuesAsHTML();
    }

    /**
     * contruct a category in a blog
     * @param pCatName name of the category
     * @param pDesc description of category
     */
    public BlogCategory(String pCatName, String pDesc) {
        setCatName(pCatName);
        setDescription(pDesc);
        formatAttributesValuesAsHTML();
    }

    /**
     *
     * @return mCatname name of the category
     */
    public String getCatName() {
        return mCatName;
    }

    /**
     *
     * @param mCatName name of the category
     */
    private void setCatName(String mCatName) {
        this.mCatName = mCatName;
    }

    /**
     *
     * @param o
     * @return 1 if both categoy are the same -1 if not
     */
    public int compareTo(Object o) {
        BlogCategory lCatComp = (BlogCategory) o;

        if (getEntityID().equalsIgnoreCase(lCatComp.getEntityID())) {
            return 1;
        }

        return -1;
    }

    /**
     *
     * @return category description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     *
     *
     * @param pDescription the description to  set it to
     */
    private void setDescription(String pDescription) {
        this.mDescription = pDescription;
    }

    /**
     * a-like as {@link #toString() }
     *
     * @return an XML representation of element
     */
    @Override
    public DefaultElement toElement() {

        // QName lElementDecl = new QName("Category", mNamespace);
        DefaultElement lReturnElement = new DefaultElement("Category");

        // Attribute= a=new Attribute("", mCatName)
        lReturnElement.add(new DefaultAttribute("ID", getEntityID()));
        lReturnElement.add(new DefaultAttribute("name", getCatName()));
        lReturnElement.add(new DefaultAttribute("description", getDescription()));

        return lReturnElement;
    }

    @Override
    void formatAttributesValuesAsHTML() {
        setCatName(StringUtil.toHTMLString(mCatName));
        setDescription(StringUtil.toHTMLString(mDescription));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(15, 745).append(getCatName()).append(getDescription()).toHashCode();
    }

    @Override
    public String toString() {
        return mDescription;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

/** * Copyright 2021 Laurent * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */package com.mor.blogengine.model;//~--- non-JDK imports --------------------------------------------------------import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;import lombok.*;import org.apache.commons.lang.builder.HashCodeBuilder;import org.dom4j.tree.DefaultAttribute;import org.dom4j.tree.DefaultElement;/** * Object representing a blog entry * * @author Laurent Morissette * */@Getter @Setter@RequiredArgsConstructor()@ToString@EqualsAndHashCode(callSuper = false)public final class BlogEntry extends AbstractBlogEntity {    private static final long serialVersionUID = 1790652709303847978L;    private String mAllowComments = "false";    private String mCatID = null;    private String mDate = null;    private String mResume = null;    private String mTexte = null;    /**     * Creates a new instance of blogComment from xml node     *     * @param pElement l'ï¿½lï¿½ment XML ï¿½ partir duquel contruire le billet     */    public BlogEntry(@NonNull DefaultElement pElement) {        super();        mAassociatedElement = pElement;        setMAllowComments(pElement.valueOf("@allowComments"));        setMDate(pElement.valueOf("@date"));        setMCatID(pElement.valueOf("@categoryID"));        setEntityID();        setMTexte(pElement.element("Text").getText());        if (pElement.element("Resume") == null) {            setMResume("");        } else {            setMResume(pElement.element("Resume").getTextTrim());        }        formatAttributesValuesAsHTML();    }    /**     *     * @param pDate the date of entry redaction     * @param pTexte text content of entry     * @param pCatID category ID under which the entry is     * @param pResume a shot abstract of the entry     * @param pAllowComments are comments permitted ?     */    public BlogEntry(String pDate, String pTexte, String pCatID, String pResume, String pAllowComments) {        setMCatID(pCatID);        setMDate(pDate);        setMTexte(pTexte);        setMResume(pResume);        setMAllowComments(pAllowComments);        formatAttributesValuesAsHTML();    }    /**     *     * @return XML node representation of entry     */    @Override    public DefaultElement toElement() {        // this is element Declaration in complete form.        // QName lElementDecl = new QName("Entry", mNamespace);        DefaultElement lReturnElement = new DefaultElement("Entry");        lReturnElement.add(new DefaultAttribute("date", getMDate()));        lReturnElement.add(new DefaultAttribute("categoryID", getMCatID()));        lReturnElement.add(new DefaultAttribute("allowComments", getMAllowComments()));        lReturnElement.add(new DefaultAttribute("ID", getEntityID()));//      this is element Declaration in complete form.        // QName lEntryTextDecl = new QName("Text", mNamespace);        DefaultElement lEntryText = new DefaultElement("Text");        lEntryText.addText(getMTexte());        lReturnElement.add(lEntryText);        // QName lResumeDecl = new QName("Resume", mNamespace);        DefaultElement lEntryResume = new DefaultElement("Resume");        lEntryResume.addText(getMResume());        lReturnElement.add(lEntryResume);        return lReturnElement;    }    /**     *     * @return are comments permitted or not ?     */    public boolean canComment() {        return mAllowComments.equalsIgnoreCase("true");    }    /**     *     */    @Override    void formatAttributesValuesAsHTML() {        mResume=escapeHtml(mResume);        mTexte=escapeHtml(mTexte);    }}//~ Formatted by Jindent --- http://www.jindent.com
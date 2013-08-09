package com.mor.blogengine.text;

//~--- non-JDK imports --------------------------------------------------------
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Lsurent
 */
public class StringUtil {

    /**
     * Apply well formed HTML conversion of a string for correct display
     *
     *
     * @param pStr
     * @return HTML display version of string
     */
    public static String toHTMLString(String pStr) {
        return StringEscapeUtils.escapeHtml(pStr);
    }

    /**
     *
     * @param S
     * @return
     */
    public static String toStringHTML(String S) {
        return StringEscapeUtils.unescapeHtml(S);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

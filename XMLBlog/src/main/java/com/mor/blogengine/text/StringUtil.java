/**
 * Copyright 2021 Laurent
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
 */
package com.mor.blogengine.text;

//~--- non-JDK imports --------------------------------------------------------
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Lsurent
 */
@UtilityClass
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

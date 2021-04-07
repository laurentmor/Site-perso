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
package com.mor.blogengine.exception;

/**
 *
 * @author Laurent
 */
public class NoMatchesFoundException extends Exception {

    static final long serialVersionUID = 111234430097541L;

    /**
     *
     */
    public NoMatchesFoundException() {
    }

    /**
     *
     * @param pSearchedTerm
     * @param doDebug
     */
    public NoMatchesFoundException(String pSearchedTerm, boolean doDebug) {
        super("No matches of " + pSearchedTerm
                + " were found during search process - redefine your search");
        if (doDebug == true) {
            printStackTrace();
        }

        System.out.println("No matches of " + pSearchedTerm
                + " were found during search process - redefine your search");

    }
}


//~ Formatted by Jindent --- http://www.jindent.com

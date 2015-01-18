/* 
 * The MIT License
 *
 * Copyright 2015 Laurent Morissette.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
        if (doDebug == true) {
            printStackTrace();
        }

        System.out.println("No matches of " + pSearchedTerm
                + " were found during search process - redefine your search");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

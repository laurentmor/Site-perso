/*
 # Copyright (c) 2008 Laurent Morissette
 #
 # Permission is hereby granted, free of charge, to any person obtaining a copy
 # of this software and associated documentation files (the "Software"), to deal
 # in the Software without restriction, including without limitation the rights
 # to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 # copies of the Software, and to permit persons to whom the Software is
 # furnished to do so, subject to the following conditions:
 #
 # The above copyright notice and this permission notice shall be included in
 # all copies or substantial portions of the Software.
 #
 # THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 # IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 # FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 # AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 # LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 # OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 # THE SOFTWARE.
 */
package com.mor.blogengine.exception;

/**
 * Exception pour d�montrer l'existence d'un �l�ment dans le tree DOM
 *
 * @author Laurent
 * @version 1.1
 * @since 1.2
 *
 */
public class ElementExistingException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of
     * <code>ElementExistingExceptionTest</code> without detail message.
     */
    public ElementExistingException() {
    }

    /**
     * Constructs an instance of
     * <code>ElementExistingExceptionTest</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public ElementExistingException(String msg) {
        System.out.println("Un élément identique existe déjà pour ce blog - " + msg);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

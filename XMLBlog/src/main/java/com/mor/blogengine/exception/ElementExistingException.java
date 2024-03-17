/**
 * Copyright 2021 Laurent
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
        super("Un élément identique existe déjà pour ce blog - " + msg);
        System.out.println("Un élément identique existe déjà pour ce blog - " + msg);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

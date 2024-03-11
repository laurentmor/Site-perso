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

import java.util.stream.IntStream;

/**
 *
 * @author laurent
 */
public class IncorrectPropertyValueException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of
     * <code>IncorrectPropertyValueException</code> without detail message.
     */
    public IncorrectPropertyValueException() {
    }

    /**
     * Constructs an instance of
     * <code>IncorrectPropertyValueException</code> with the specified detail
     * message.
     *
     */
    public IncorrectPropertyValueException(String propertyName,String currentValue,Enum<?>... choices ) {
         System.err.print(propertyName+"is set incorrectly to : "+currentValue+ " it should be one of these values: ");
        IntStream.range(0, choices.length - 1).mapToObj(i -> choices[i].name()).map(choice -> choice + ",").forEach(System.err::print);
         System.err.println(choices[choices.length-1]);
    }
}

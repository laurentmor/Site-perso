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
package com.mor.blogengine.xpath;

//~--- JDK imports ------------------------------------------------------------
import com.mor.blogengine.exception.NoMatchesFoundException;
import java.util.List;

/**
 * Specific interface for any BlogSearchEngine<br/>
 *
 * @param <returnType>
 * @author Laurent
 * @version 0.1 initial
 * @version 1.0 changed visibility according to arvhitecture plan arch_plan.pdf
 * @version 2.0 made all mehods private due to the introduction of
 * searchCriteria concept public access no longer needed
 */
public interface IBlogSearchEngine<returnType> {

    /**
     *
     * @param elementType
     * @param criteria
     * @param criteriaValue
     * @return list of found elements
     * @throws com.mor.blogengine.exception.NoMatchesFoundException
     */
    public List<returnType> getElementsForCriteria(String elementType, SearchCriteria criteria, String criteriaValue) throws NoMatchesFoundException;
}


//~ Formatted by Jindent --- http://www.jindent.com

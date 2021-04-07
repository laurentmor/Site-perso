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
 * @version 1.0 changed visibility according to architecture plan arch_plan.pdf
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
    List<returnType> getElementsForCriteria(String elementType, SearchCriteria criteria, String criteriaValue) throws NoMatchesFoundException;
}


//~ Formatted by Jindent --- http://www.jindent.com

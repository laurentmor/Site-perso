/*
 * Copyright (c) 2024
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
 *
 *
 */
package com.mor.blogengine.xpath;

// ~--- JDK imports ------------------------------------------------------------

import com.mor.blogengine.exception.NoMatchesFoundException;
import java.util.List;

/**
 * Specific interface for any BlogSearchEngine.
 *
 * @param <R> Result data type.
 * @author Laurent
 * @version 2.0
 */
public interface IBlogSearchEngine<R> {

  /**
   * Search method.
   *
   * @param c Search criteria type
   * @param e wheat element type to search for
   * @param cv value to search for
   * @return list of found elements
   * @throws NoMatchesFoundException when search found nothing
   */
  List<R> getElementsForCriteria(String e, SearchCriteria c, String cv)
      throws NoMatchesFoundException;
}

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

/**
 * Enum for parametrized searching i.e. search post of certain date or category.
 *
 * @author laurent
 */
public enum SearchCriteria {

  /**
   * Get All elements.
   */
  ALL {
  },
  /**
   * Get elements for Date.
   */
  DATE {
  },
  /**
   * Get elements for month.
   */
  MONTH {
  },
  /**
   * Get element for category.
   */
  CATEGORY {
  },
  /**
   * Get Element for author.
   */
  AUTHOR {
  },
  /**
   * Get a single element.
   */
  SINGLE {
  },
  /**
   * Get single element with parent.
   */
  SINGLE_WITH_PARENT {
  },
  /**
   * Get element by Entry id.
   */
  BY_ENTRY_ID {
  }
}

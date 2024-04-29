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
package com.mor.blogengine.exception;

import java.io.Serial;
import java.util.logging.Level;
import lombok.extern.java.Log;

/**
 * exception when a given search returns nothing.
 *
 * @author Laurent
 */
@Log
public class NoMatchesFoundException extends Exception {

  // private @Log4j l;
  @Serial private static final long serialVersionUID = 1L;

  /**
   * Create exception with stacktrace if debug is on.
   *
   * @param doDebug if true, stacktrace is printed
   * @param pSearchedTerm what term is NotFound
   */
  public NoMatchesFoundException(final String pSearchedTerm, final boolean doDebug) {
    super(
        "No matches of "
            + pSearchedTerm
            + " were found during search process - redefine your search");
    if (doDebug) {
      log.log(
          Level.INFO,
          "No matches of "
              + pSearchedTerm
              + " were found during search process - redefine your search",
          getStackTrace());
    }
  }
}

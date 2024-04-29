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
import lombok.extern.slf4j.Slf4j;

/**
 * exception when config is missing a property.
 *
 * @author laurent
 */
@Slf4j
public class MissingPropertyException extends Exception {

  @Serial private static final long serialVersionUID = 1L;

  /**
   * Create a named property.
   *
   * @param property the missing property name
   */
  public MissingPropertyException(final String property) {

    log.error("Property missing : {}{}", property, getStackTrace());
  }
}

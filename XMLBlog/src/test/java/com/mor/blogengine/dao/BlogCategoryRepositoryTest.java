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

package com.mor.blogengine.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.xpath.SearchCriteria;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.tree.DefaultElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings ("unchecked")
public class BlogCategoryRepositoryTest extends AbstractBlogRepositoryTest {

  @BeforeEach
  public void beforeTest() {
    repository = new BlogCategoryRepository(mConfig, getBlogDocument());
  }

  @Test
  public void BlogCategoryRepository() {
    assertNotNull(repository);
  }

  //@Test
  public void add() {

    BlogCategory t = new BlogCategory("");
    boolean expected = true;
    boolean actual = false;
    try {
      assertTrue(actual = repository.add(t));
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }

    assertEquals(expected, actual);
  }

  //@Test
  public void remove() throws NoMatchesFoundException {
    BlogCategoryRepository b = new BlogCategoryRepository(null, null);
    BlogCategory t = null;
    boolean expected = true;
    boolean actual = b.remove(t);

    assertEquals(expected, actual);
  }

  //@Test
  public void edit() throws NoMatchesFoundException {
    BlogCategoryRepository b = new BlogCategoryRepository(null, null);
    BlogCategory t = null;
    BlogCategory t2 = null;
    boolean expected = true;
    boolean actual = b.edit(t, t2);

    assertEquals(expected, actual);
  }

  //@Test
  public void getElementsForCriteria() throws NoMatchesFoundException {
    BlogCategoryRepository b = new BlogCategoryRepository(null, null);
    SearchCriteria searchParam = SearchCriteria.ALL;
    String paramValue = "abc";
    List<DefaultElement> expected = new ArrayList<>();
    List<DefaultElement> actual = b.getElementsForCriteria(searchParam, paramValue);

    assertEquals(expected, actual);
  }


}

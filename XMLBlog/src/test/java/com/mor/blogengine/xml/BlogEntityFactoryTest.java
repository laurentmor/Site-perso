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

package com.mor.blogengine.xml;

import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.dom4j.tree.DefaultElement;
import org.junit.jupiter.api.Test;

public class BlogEntityFactoryTest {

  @Test
  public void createEntryMap() {
    BlogEntityFactory b = new BlogEntityFactory();
    List<DefaultElement> pList = new ArrayList<>();
    Map<String, BlogEntry> expected = null;
    Map<String, BlogEntry> actual = b.createEntryMap(pList);

    //assertEquals(expected, actual);
  }

  @Test
  public void createCategoryMap() {
    BlogEntityFactory b = new BlogEntityFactory();
    List<DefaultElement> pList = new ArrayList<>();
    Map<String, BlogCategory> expected = null;
    Map<String, BlogCategory> actual = b.createCategoryMap(pList);

    //assertEquals(expected, actual);
  }

  @Test
  public void createCommentMap() {
    BlogEntityFactory b = new BlogEntityFactory();
    List<DefaultElement> pList = new ArrayList<>();
    Map<String, BlogComment> expected = null;
    Map<String, BlogComment> actual = b.createCommentMap(pList);

    //assertEquals(expected, actual);
  }
}

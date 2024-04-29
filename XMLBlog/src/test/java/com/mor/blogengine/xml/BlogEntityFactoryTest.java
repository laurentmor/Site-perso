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

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;
import com.mor.blogengine.model.BlogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.junit.jupiter.api.Test;

/**
 * Factory tests.
 */
@Slf4j
public class BlogEntityFactoryTest {

  /**
   * Default constructor.
   */
  public BlogEntityFactoryTest() {
    super();
  }

  /**
   * EntryMap creation test.
   */
  @Test
  public void createEntryMap() {
    IBlogEntityFactory<DefaultElement> b = new BlogEntityFactory();
    List<DefaultElement> pList = new ArrayList<>();
    Map<String, BlogEntry> expected = new HashMap<String, BlogEntry>();
    BlogEntry entry = new BlogEntry("2024-04-28", "Text", "ID_-1633494413", "Short desc", "true");
    expected.put(entry.getId(), entry);
    pList.add(entry.toElement());
    Map<String, BlogEntry> actual = b.createEntryMap(pList);

    assertEquals(expected.values().iterator().next().getId(), actual.keySet().iterator().next());
  }

  /**
   * categoryMap creation test.
   */
  @Test
  public void createCategoryMap() {
    IBlogEntityFactory<DefaultElement> b = new BlogEntityFactory();
    List<DefaultElement> pList = new ArrayList<>();
    DefaultElement category = new DefaultElement("category");
    category.add(new DefaultAttribute("name", "test"));
    category.add(new DefaultAttribute("description", "test"));
    category.add(new DefaultAttribute("ID", "ID_-1633494413"));
    pList.add(category);
    Map<String, BlogCategory> expected = new HashMap<String, BlogCategory>();
    expected.put("ID_-1633494413", new BlogCategory("test", "test"));
    Map<String, BlogCategory> actual = b.createCategoryMap(pList);

    assertEquals(expected.keySet().iterator().next(),
      actual.entrySet().iterator().next().getValue().getId());
  }

  /**
   * commentMap creation test.
   */
  @Test
  public void createCommentMap() {
    BlogEntry entry = new BlogEntry("2024-04-28", "Text", "ID_-1633494413", "Short desc", "true");
    BlogComment blogComment = new BlogComment(entry.getId(), "2024-04-28", "Me", "q",
      "Comment text");
    IBlogEntityFactory<DefaultElement> b = new BlogEntityFactory();
    List<DefaultElement> pList = new ArrayList<>();
    pList.add(blogComment.toElement());

    Map<String, BlogComment> expected = new HashMap<>();
    expected.put(blogComment.getId(), blogComment);
    Map<String, BlogComment> actual = b.createCommentMap(pList);

    assertEquals(expected.keySet().iterator().next(),
      actual.entrySet().iterator().next().getValue().getId());
  }
}

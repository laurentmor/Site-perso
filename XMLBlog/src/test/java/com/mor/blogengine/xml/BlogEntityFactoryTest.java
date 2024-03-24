package com.mor.blogengine.xml;

import com.mor.blogengine.model.BlogEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.dom4j.tree.DefaultElement;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.model.BlogComment;

public class BlogEntityFactoryTest {
	@Test
	public void createEntryMap() {
		BlogEntityFactory b = new BlogEntityFactory();
		List<DefaultElement> pList = new ArrayList<>();
		Map<String, BlogEntry> expected = null;
		Map<String, BlogEntry> actual = b.createEntryMap(pList);

		assertEquals(expected, actual);
	}

	@Test
	public void createCategoryMap() {
		BlogEntityFactory b = new BlogEntityFactory();
		List<DefaultElement> pList = new ArrayList<>();
		Map<String, BlogCategory> expected = null;
		Map<String, BlogCategory> actual = b.createCategoryMap(pList);

		assertEquals(expected, actual);
	}

	@Test
	public void createCommentMap() {
		BlogEntityFactory b = new BlogEntityFactory();
		List<DefaultElement> pList = new ArrayList<>();
		Map<String, BlogComment> expected = null;
		Map<String, BlogComment> actual = b.createCommentMap(pList);

		assertEquals(expected, actual);
	}
}

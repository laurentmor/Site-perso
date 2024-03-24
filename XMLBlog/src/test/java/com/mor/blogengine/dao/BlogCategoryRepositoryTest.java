package com.mor.blogengine.dao;

import java.util.Properties;

import com.mor.blogengine.model.AbstractBlogEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.mor.blogengine.exception.ElementExistingException;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.exception.NoMatchesFoundException;
import com.mor.blogengine.xpath.SearchCriteria;
import java.util.List;
import org.dom4j.tree.DefaultElement;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class BlogCategoryRepositoryTest extends AbstractBlogRepositoryTest {
	@BeforeEach
	public  void beforeTest(){
		repository=new BlogCategoryRepository(mConfig,getBlogDocument());
	}
	@Test
	public void BlogCategoryRepository() {
		assertNotNull(repository);
	}

	@Test
	public void add() throws ElementExistingException {

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

	@Test
	public void remove() throws NoMatchesFoundException {
		BlogCategoryRepository b = new BlogCategoryRepository(null, null);
		BlogCategory t = null;
		boolean expected = true;
		boolean actual = b.remove(t);

		assertEquals(expected, actual);
	}

	@Test
	public void edit() throws NoMatchesFoundException {
		BlogCategoryRepository b = new BlogCategoryRepository(null, null);
		BlogCategory t = null;
		BlogCategory t2 = null;
		boolean expected = true;
		boolean actual = b.edit(t, t2);

		assertEquals(expected, actual);
	}

	@Test
	public void getElementsForCriteria() throws NoMatchesFoundException {
		BlogCategoryRepository b = new BlogCategoryRepository(null, null);
		SearchCriteria searchParam = SearchCriteria.ALL;
		String paramValue = "abc";
		List<DefaultElement> expected = new ArrayList<>();
		List<DefaultElement> actual = b.getElementsForCriteria(searchParam, paramValue);

		assertEquals(expected, actual);
	}

	@Test
	public void append() {
		BlogCategoryRepository b = new BlogCategoryRepository(null, null);
		BlogCategory what = null;
		boolean expected = true;
		boolean actual = b.append(what);

		assertEquals(expected, actual);
	}
}

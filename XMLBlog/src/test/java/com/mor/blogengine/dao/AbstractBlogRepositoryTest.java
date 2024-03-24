package com.mor.blogengine.dao;

import com.mor.blogengine.model.AbstractBlogEntity;
import com.mor.blogengine.model.BlogCategory;
import com.mor.blogengine.xpath.SearchCriteria;
import com.mor.test.XMLConsumingTestCase;
import org.dom4j.DocumentException;
import org.dom4j.tree.DefaultElement;

public abstract  class AbstractBlogRepositoryTest extends XMLConsumingTestCase {

    protected IRepository repository;
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

import com.mor.blogengine.test.common.PropertiesConsumingTestCase;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;
import static org.junit.Assert.*;

/**
 *
 * @author laurent
 */
public class SearchEngineConfiguratorTest extends PropertiesConsumingTestCase {

    @Rule
    public TestName name = new TestName();
    private static Document document = null;

    public SearchEngineConfiguratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        setupTestSettings();
        document = DocumentHelper.createDocument();
        document.add(new DefaultElement("root"));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("test : " + name.getMethodName());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findContent method, of class SearchEngineConfigurator.
     */
    @Test
    public void testFindContent() {


        SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<List<DefaultElement>>(properties, document);
        List fnd = configurator.findContent("/");
        assertEquals(1, fnd.size());

    }
}

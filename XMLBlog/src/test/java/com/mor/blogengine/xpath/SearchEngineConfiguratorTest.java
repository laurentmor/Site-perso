/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

import java.util.List;
import java.util.Properties;
import org.dom4j.tree.DefaultElement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author laurent
 */
public class SearchEngineConfiguratorTest {

    @Rule
    public TestName name = new TestName();

    public SearchEngineConfiguratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
        Properties p = new Properties();
        SearchEngineConfigurator<List<DefaultElement>> configurator = new SearchEngineConfigurator<List<DefaultElement>>(p, null);
        configurator.findContent("/");
        //33
    }
}

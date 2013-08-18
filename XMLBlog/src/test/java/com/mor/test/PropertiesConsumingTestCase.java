/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.test;

import com.mor.common.PropertiesUserObject;
import java.util.Properties;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author laurent
 */
public abstract class PropertiesConsumingTestCase extends PropertiesUserObject{

    @Rule
    public TestName name = new TestName();

    static Properties properties = null;

    @Before
    public void logTestName() {
        System.out.println(name.getMethodName());
    }

    @BeforeClass
    public static void setupTestSettings() {
        properties = new Properties();
        properties.put("application.mode", "test");
        properties.put("application.debug", "On");
        properties.put("log.level", "INFO");
        properties.put("xpath.version", "1.0f");
       
    }

    public final static Properties getProperties() {
        return properties;
    }

}

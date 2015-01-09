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
    public static TestName name = new TestName();

  

    public static  void  logTestName() {
        System.out.println(name.getMethodName());
    }

    @Before
    public  void setupTestSettings() {
        logTestName();
        mConfig=new Properties();
        mConfig.put("application.mode", "test");
        mConfig.put("application.debug", "On");
        mConfig.put("log.level", "INFO");
        mConfig.put("xpath.version", "1.0f");
       
    }

    public   Properties getProperties() {
        return mConfig;
    }

}

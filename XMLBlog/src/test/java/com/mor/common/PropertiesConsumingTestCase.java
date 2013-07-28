/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.common;

import java.util.Properties;

/**
 *
 * @author laurent
 */
public class PropertiesConsumingTestCase {

    protected static Properties properties = null;

    protected static void setupTestSettings() {
        properties = new Properties();
        properties.put("application.mode", "test");
        properties.put("application.debug", "On");
        properties.put("log.level", "INFO");
    }
}

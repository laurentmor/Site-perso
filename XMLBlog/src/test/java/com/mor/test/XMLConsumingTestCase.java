package com.mor.test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class XMLConsumingTestCase extends PropertiesConsumingTestCase {

     static Document document = null;

    @BeforeClass
    public static void initialise() {
        setupTestSettings();
        document = DocumentHelper.createDocument();
        document.add(new DefaultElement("root"));
    }

    @Before
    public void logTestName() {
    }

    public final static Document getDocument() {
        return document;
    }
    

}

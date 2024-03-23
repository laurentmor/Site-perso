package com.mor.blogengine.xml;

import com.mor.test.XMLConsumingTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class XMLHandlerImplTest extends XMLConsumingTestCase {

    @BeforeEach
    void clean() {
        try {
            Field instance = XMLHandlerImpl.class.getDeclaredField("mInstance");
            instance.setAccessible(true);
            instance.set(null, null);
            getLog().info("Nullified XMLHandlerImpl mInstance" +instance);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(XMLHandlerImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    void getInstanceForDoc() {

    assertNotNull(XMLHandlerImpl.getInstanceForDoc(mConfig,getBlogDocument()));
    }
    @Test
    void getInstanceForDocSingleton() {
        XMLHandlerImpl.getInstanceForDoc(mConfig,getBlogDocument());
        assertNotNull(XMLHandlerImpl.getInstanceForDoc(mConfig,getBlogDocument()));
    }


    @Test
    void add() {
    //XMLHandlerImpl.getInstanceForDoc(mConfig,getBlogDocument()).add(null)
    }

    @Test
    void remove() {
    }

    @Test
    void append() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void testRemove1() {
    }

    @Test
    void testRemove2() {
    }
}
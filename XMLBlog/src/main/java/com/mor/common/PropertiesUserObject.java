/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.common;

//~--- JDK imports ------------------------------------------------------------
import com.mor.blogengine.xpath.XPathVersion;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to give access to properties file to sub-class and enabling some
 * properties value interrogation<br/>
 *
 * @author laurent
 */
public abstract class PropertiesUserObject {

    /**
     *
     */
    protected Properties mConfig = null;
    final static boolean YES = true;
    final static boolean NO = false;

    /**
     * Does some logging if application is in test mode and debug is set to On
     *
     * @param traceMessage - what to trace
     */
    protected void trace(String traceMessage) {
        if (isDebugPropertiesSet()) {
            if (isInTestModeWithDebugOn()) {
                Logger.getLogger(getClass().getName()).log(getLoggingLevel(), traceMessage);
            }
        } else {
            throw new MissingResourceException(
                    "Properties application.[mode,debug] not set correctly - pleas check config..  ", getClass().getName(),
                    "application.[mode,debug]");
        }
    }

    /**
     *
     * @return YES if we have to save change in file
     */
    protected boolean isPersistingNecessary() {
        if (isDebugPropertiesSet()) {
            return isInTestMode()
                    ? NO
                    : YES;
        }

        throw new MissingResourceException(
                "Properties application.[mode,debug] not set correctly - please check config...  ", getClass().getName(),
                "application.[mode,debug]");
    }

    /**
     *
     * @return the supported xpath version of specs
     */
    protected final XPathVersion getSupportedXpathVersion() {
        if (2.0f == Float.parseFloat(mConfig.getProperty("xpath.version"))) {
            return XPathVersion.typed;
        }
        return (XPathVersion.typeLess);
    }

    /**
     *
     * @return does the config file have all the necessary properties ?
     */
    protected boolean isDebugPropertiesSet() {
        return mConfig.containsKey("application.mode") && mConfig.containsKey("application.debug");
    }

    private Level getLoggingLevel() {
        return Level.parse(mConfig.getProperty("log.level"));
    }

    protected String getFileEncoding() {
        return mConfig.getProperty("file.encoding");
    }

    /**
     *
     * @return true if application is in test mode with debug flag set to on
     */
    protected boolean isInTestModeWithDebugOn() {
        return (mConfig.getProperty("application.mode").equalsIgnoreCase("test")
                && mConfig.getProperty("application.debug").equalsIgnoreCase("on"));
    }

    protected final URL getSchema() {
        String s = mConfig.getProperty("datasource.xsd");
        URL url = this.getClass().getResource(s);
        trace("Retrieving schema URL at : " + url.getFile());
        return url;
    }

    /**
     *
     * @return
     */
    protected final URL getXml() {
        String s = mConfig.getProperty("datasource.xml");
        URL url = getClass().getResource(s);
        trace("Retrieving XML URL at : " + url.getFile());

        return url;

    }

    private boolean isInTestMode() {
        return (mConfig.getProperty("application.mode").equalsIgnoreCase("test"));
    }
}
//-
//~ Formatted by Jindent --- http://www.jindent.com


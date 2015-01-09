/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.common;

//~--- JDK imports ------------------------------------------------------------
import com.mor.blogengine.exception.IncorrectPropertyValueException;
import com.mor.blogengine.exception.MissingPropertyException;
import com.mor.blogengine.xpath.XPathVersion;
import java.net.URL;
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
    final static String MODE_PROPERTY = "application.mode";
    final static String DEBUG_PROPERTY = "application.debug";
    final static String LEVEL_PROPERTY = "log.level";
    final static String ENCODING_PROPERTY = "file.encoding";
    final static String XPATH_PROPERTY = "xpath.version";
    final static String XML_PROPERTY = "datasource.xml";
    final static String XSD_PROPERTY = "datasource.xsd";

    /**
     * Does some logging if application is in test mode and debug is set to On
     *
     * @param traceMessage - what to trace
     * @throws MissingPropertyException when debug property is missing
     * @throws com.mor.blogengine.exception.IncorrectPropertyValueException
     */
    protected void trace(String traceMessage) throws MissingPropertyException, IncorrectPropertyValueException {

        if (isModePropertySet()) {
            if (areTestConditionsMet()) {

                if (areDebugConditionsMet()) {

                    if (isDebugOn()) {
                        Logger.getLogger(getClass().getName()).
                                log(getLoggingLevel(), traceMessage);
                    }

                }

            }
        }

    }

    private boolean areDebugConditionsMet() throws MissingPropertyException, IncorrectPropertyValueException {
        return isDebugPropertiesSet() && isInKnownDebugState();
    }

    private boolean areTestConditionsMet() throws IncorrectPropertyValueException, MissingPropertyException {
        return isInKnownMode() && isInTestMode();
    }

    /**
     *
     * @return YES if we have to save change in file
     * @throws com.mor.blogengine.exception.MissingPropertyException
     */
    protected boolean isPersistingNecessary() throws MissingPropertyException {
        if (isModePropertySet()) {
            return isInTestMode()
                    ? NO
                    : YES;
        }

        throw new MissingPropertyException(MODE_PROPERTY);
    }

    /**
     *
     * @return the supported xpath version of specs
     */
    protected final XPathVersion getSupportedXpathVersion() {
        if (2.0f == Float.parseFloat(getPropertyValue(XPATH_PROPERTY))) {
            return XPathVersion.typed;
        }
        return (XPathVersion.typeLess);
    }

    /**
     *
     * @return does the config file have all the necessary properties ?
     * @throws com.mor.blogengine.exception.MissingPropertyException
     */
    protected boolean isModePropertySet() throws MissingPropertyException {
        return isProptertyExistant(MODE_PROPERTY);
    }

    private Level getLoggingLevel() {
        return Level.parse(getPropertyValue(LEVEL_PROPERTY));
    }

    protected String getFileEncoding() {
        return getPropertyValue(ENCODING_PROPERTY);
    }

    /**
     *
     * @return true if application is in test mode with debug flag set to on
     */
    protected boolean isInTestMode() throws MissingPropertyException {
        if (isModePropertySet()) {
            return getPropertyValue(MODE_PROPERTY).equalsIgnoreCase("test");
        }
        return false;
    }

    private boolean isProptertyExistant(String prop) throws MissingPropertyException {
        if (mConfig.getProperty(prop) != null) {
            return true;
        }
        throw new MissingPropertyException(prop);
    }

    protected boolean isDebugOn() {
        return getPropertyValue(DEBUG_PROPERTY).equalsIgnoreCase(DebugMode.On
                .toString());
    }

    protected final URL getSchema() throws MissingPropertyException, IncorrectPropertyValueException {
        String s = getPropertyValue(XSD_PROPERTY);
        URL url = this.getClass().getResource(s);
        trace("Retrieving schema URL at : " + url.getFile());
        return url;
    }

    /**
     *
     * @return @throws com.mor.blogengine.exception.MissingPropertyException
     * @throws com.mor.blogengine.exception.IncorrectPropertyValueException
     */
    protected final URL getXml() throws MissingPropertyException, IncorrectPropertyValueException {
        String s = getPropertyValue(XML_PROPERTY);
        URL url = getClass().getResource(s);
        trace("Retrieving XML URL at : " + url.getFile());

        return url;

    }

    private boolean isDebugPropertiesSet() throws MissingPropertyException {
        return isProptertyExistant(DEBUG_PROPERTY);
    }

    public final String getPropertyValue(String property) {
        return mConfig.getProperty(property);
    }

    protected boolean notInProductionMode() throws MissingPropertyException {
        if (isModePropertySet()) {
            return getPropertyValue(MODE_PROPERTY).equalsIgnoreCase("production");
        }
        return true;
    }

    private boolean isInKnownMode() throws IncorrectPropertyValueException {
        String mode = getPropertyValue(MODE_PROPERTY);
        String test = ApplicationMode.Test.toString();
        String production = ApplicationMode.Production.toString();
        boolean isKnownMode = mode.equalsIgnoreCase(test)
                || mode.equalsIgnoreCase(production);
        if (isKnownMode) {
            return YES;
        }

        throw new IncorrectPropertyValueException(MODE_PROPERTY,
                getPropertyValue(MODE_PROPERTY),
                ApplicationMode.Test,
                ApplicationMode.Production);
    }

    private boolean isInKnownDebugState() throws IncorrectPropertyValueException {
        String state = getPropertyValue(DEBUG_PROPERTY);
        String on = DebugMode.On.toString();
        String off = DebugMode.Off.toString();

        boolean isKnownState = state.equalsIgnoreCase(on)
                || state.equalsIgnoreCase(off);
        if (isKnownState) {
            return YES;
        }

        throw new IncorrectPropertyValueException(MODE_PROPERTY,
                getPropertyValue(MODE_PROPERTY),
                DebugMode.Off,
                DebugMode.On);
    }
}
//-
//~ Formatted by Jindent --- http://www.jindent.com


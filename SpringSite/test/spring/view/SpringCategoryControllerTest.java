/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spring.view;

import com.mor.blogengine.controllers.CategoryController;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lsurent
 */
public class SpringCategoryControllerTest {

    public SpringCategoryControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of handleRequest method, of class SpringCategoryController.
     */
    @Test
    public void testHandleRequest() throws Exception {
        System.out.println("handleRequest");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        SpringCategoryController instance = new SpringCategoryController();
        ModelAndView expResult = null;
        ModelAndView result = instance.handleRequest(request, response);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDomainController method, of class SpringCategoryController.
     */
    @Test
    public void testSetDomainController() {
        System.out.println("setDomainController");
        CategoryController domainController = null;
        SpringCategoryController instance = new SpringCategoryController();
        instance.setDomainController(domainController);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDomainController method, of class SpringCategoryController.
     */
    @Test
    public void testGetDomainController() {
        System.out.println("getDomainController");
        SpringCategoryController instance = new SpringCategoryController();
        CategoryController expResult = null;
        CategoryController result = instance.getDomainController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getControllerConfigFile method, of class SpringCategoryController.
     */
    @Test
    public void testGetControllerConfigFile() {
        System.out.println("getControllerConfigFile");
        SpringCategoryController instance = new SpringCategoryController();
        Properties expResult = null;
        Properties result = instance.getControllerConfigFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setControllerConfigFile method, of class SpringCategoryController.
     */
    @Test
    public void testSetControllerConfigFile() {
        System.out.println("setControllerConfigFile");
        Properties controllerConfigFile = null;
        SpringCategoryController instance = new SpringCategoryController();
        instance.setControllerConfigFile(controllerConfigFile);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
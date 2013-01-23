/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.view;

import com.mor.blogengine.controllers.CategoryController;
import com.mor.blogengine.model.BlogCategory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import spring.common.URLResolver;

/**
 *
 * @author Lsurent
 */
public class SpringCategoryController implements Controller {

    private CategoryController domainController;
    private Properties controllerConfigFile;
    protected  final Logger logger = Logger.getLogger(getClass().getName());

    public SpringCategoryController() {
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            

            //URL url0 = request.getServletContext().getResource();
            //URL url1 = request.getServletContext().getResource();
            String[] urls={
                controllerConfigFile.getProperty("datasource.xml"),
                controllerConfigFile.getProperty("datasource.xsd")}; 
            urls=URLResolver.resolve(request.getServletContext(),urls);
            String[] keys ={"datasource.xml","datasource.xsd"}; 
             controllerConfigFile=URLResolver.updateURLSProprties(controllerConfigFile, keys,urls );
            domainController = new CategoryController(controllerConfigFile);
            Map<String,BlogCategory> m = domainController.getAllElements();
       

            //myModel.put("categories", m);
            //myModel.entrySet().
            ModelAndView modelAndView = new ModelAndView("CategoryDisplay", "data", m);

            return modelAndView;
        } catch (Exception e) {
        }
        return null;
    }

    public void setDomainController(CategoryController domainController) {
        this.domainController = domainController;
    }

    public CategoryController getDomainController() {
        return domainController;
    }

    public Properties getControllerConfigFile() {
        return controllerConfigFile;
    }

    public void setControllerConfigFile(Properties controllerConfigFile) {
        this.controllerConfigFile = controllerConfigFile;
    }
}

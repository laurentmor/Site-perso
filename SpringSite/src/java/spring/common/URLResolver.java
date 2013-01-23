/**
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package spring.common;

import java.net.MalformedURLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author Lsurent
 */
public class URLResolver {
    private static ServletContext mContext=null;
    
    public static String[] resolve(ServletContext context, String[] paths) {
        if (context == null || paths == null) {
            return null;
        } else {
            mContext=context;
            return resolvePathsFromContext(paths);
        }
    }
    
    public static Properties updateURLSProprties(Properties p, String[] keys, String[] values) {
        for (int i = 0; i < keys.length; i++) {
            p.put(keys[i], values[i]);
        }
        return p;
        
    }    
    
    private static String[] resolvePathsFromContext(String[] paths) {
        for (int i = 0; i < paths.length; i++) {
            try {
                paths[i] = resovePath(paths[i]);
            } catch (MalformedURLException ex) {
                Logger.getLogger(URLResolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paths;
    }

    private static String resovePath(String path) throws MalformedURLException {
        return mContext.getResource(path).getFile();
    }
}

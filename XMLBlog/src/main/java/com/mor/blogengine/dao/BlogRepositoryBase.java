/* 

 * The MIT License

 *

 * Copyright 2015 Laurent Morissette.

 *

 * Permission is hereby granted, free of charge, to any person obtaining a copy

 * of this software and associated documentation files (the "Software"), to deal

 * in the Software without restriction, including without limitation the rights

 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell

 * copies of the Software, and to permit persons to whom the Software is

 * furnished to do so, subject to the following conditions:

 *

 * The above copyright notice and this permission notice shall be included in

 * all copies or substantial portions of the Software.

 *

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR

 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,

 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE

 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER

 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,

 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN

 * THE SOFTWARE.

 */


package com.mor.blogengine.dao;

import com.mor.blogengine.xml.IXMLHandler;
import com.mor.blogengine.xml.XMLHandlerImpl;
import com.mor.blogengine.xpath.IBlogSearchEngine;
import com.mor.blogengine.xpath.SearchEngine;
import com.mor.common.PropertiesUserObject;
import java.util.Properties;
import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;

/**
 *
 * @author laurent
 */
public class BlogRepositoryBase extends PropertiesUserObject {

    IXMLHandler<DefaultElement> handler = null;
    IBlogSearchEngine<DefaultElement> searchEngine=null;
    Document doc = null;

    public BlogRepositoryBase(Document d, Properties p) {
        doc = d;
        mConfig = p;
        handler = XMLHandlerImpl.getInstanceForDoc(mConfig, doc);
        searchEngine=SearchEngine.getInstanceForDoc(mConfig, doc);
    }

}

/**
 * Copyright 2021 Laurent
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mor.blogengine.dao;

import com.mor.blogengine.xml.IXMLHandler;
import com.mor.blogengine.xml.XMLHandlerImpl;
import com.mor.blogengine.xpath.IBlogSearchEngine;
import com.mor.blogengine.xpath.SearchEngine;
import com.mor.common.PropertiesUserObject;
import lombok.NonNull;
import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;

import java.util.Properties;

/**
 * @author laurent
 */
public class BlogRepositoryBase extends PropertiesUserObject {

    final IXMLHandler<DefaultElement> handler;
    final IBlogSearchEngine<DefaultElement> searchEngine;


    public BlogRepositoryBase(Document d, @NonNull Properties p) {
        super(p);
        handler = XMLHandlerImpl.getInstanceForDoc(mConfig, d);
        searchEngine = SearchEngine.getInstanceForDoc(mConfig, d);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

/**
 *
 * @author laurent
 */
public enum XPathVersion {

    typeLess(1.0f), typed(2.0f);
    private float version = 0.0f;

    XPathVersion(float v) {
        version = v;
    }

    public float getVersion() {
        return this.version;
    }
}

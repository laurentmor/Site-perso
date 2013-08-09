
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mor.blogengine.xpath;

/**
 *
 * Enum for parametrized searching i.e search post of certain date or category
 *
 * @author laurent
 *
 */
public enum SearchCriteria {

    /**
     * Get All elements
     */
    ALL {
    },
    /**
     * Get elements for Date
     */
    DATE {
    },
    /**
     *
     */
    MONTH {
    },
    /**
     * Get element for category
     */
    CATEGORY {
    },
    /**
     * Get Element for author
     */
    AUTHOR {
    },
    /**
     * Get a single element
     */
    SINGLE {
    },
    /**
     *
     */
    SINGLE_WITH_PARENT {
    },
    /**
     *
     */
    BY_ENTRY_ID {
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

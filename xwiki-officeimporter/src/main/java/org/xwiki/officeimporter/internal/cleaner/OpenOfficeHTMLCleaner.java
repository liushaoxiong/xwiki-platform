/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.officeimporter.internal.cleaner;

import java.io.Reader;
import java.util.Collections;
import java.util.Map;

import org.w3c.dom.Document;
import org.xwiki.component.logging.AbstractLogEnabled;
import org.xwiki.xml.html.HTMLCleaner;
import org.xwiki.xml.html.filter.HTMLFilter;
import org.xwiki.xml.internal.html.DefaultHTMLCleaner;

/**
 * {@link HTMLCleaner} for cleaning html generated from an openoffice server.
 * 
 * @version $Id$
 * @since 1.8M1
 */
public class OpenOfficeHTMLCleaner extends AbstractLogEnabled implements HTMLCleaner
{  
    /**
     * The {@link DefaultHTMLCleaner} used internally. 
     */
    private HTMLCleaner defaultHtmlCleaner;
    
    /**
     * {@link HTMLFilter} for stripping various tags.
     */
    private HTMLFilter stripperFilter;
    
    /**
     * {@link HTMLFilter} filtering styles.
     */
    private HTMLFilter styleFilter;
    
    /**
     * {@link HTMLFilter} for stripping redundant tags.
     */
    private HTMLFilter redundancyFilter;
    
    /**
     * {@link HTMLFilter} for cleaning empty paragraphs.
     */
    private HTMLFilter paragraphFilter;
    
    /**
     * {@link HTMLFilter} for filtering image tags.
     */
    private HTMLFilter imageFilter;
    
    /**
     * {@link HTMLFilter} for filtering html links.
     */
    private HTMLFilter linkFilter;
    
    /**
     * {@link HTMLFilter} for filtering html anchors.
     */
    private HTMLFilter anchorFilter;
    
    /**
     * {@link HTMLFilter} for filtering lists.
     */
    private HTMLFilter listFilter;
    
    /**
     * {@link HTMLFilter} for filtering tables.
     */
    private HTMLFilter tableFilter;
    
    /**
     * {@link HTMLFilter} for filtering line breaks.
     */
    private HTMLFilter lineBreakFilter;
    
    /**
     * {@inheritDoc}
     */
    public Document clean(Reader originalHtmlContent)
    {
        return clean(originalHtmlContent, Collections.singletonMap("filterStyles", "strict"));
    }

    /**
     * {@inheritDoc}
     */
    public Document clean(Reader originalHtmlContent, Map<String, String> cleaningParams)
    {
        // Default cleaning.
        Document document = defaultHtmlCleaner.clean(originalHtmlContent);
        // Apply filters.
        stripperFilter.filter(document, cleaningParams);
        styleFilter.filter(document, cleaningParams);
        redundancyFilter.filter(document, cleaningParams);
        paragraphFilter.filter(document, cleaningParams);
        imageFilter.filter(document, cleaningParams);
        linkFilter.filter(document, cleaningParams);
        anchorFilter.filter(document, cleaningParams);
        listFilter.filter(document, cleaningParams);
        tableFilter.filter(document, cleaningParams);
        lineBreakFilter.filter(document, cleaningParams);
        return document;
    }
}

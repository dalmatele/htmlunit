/*
 * Copyright (c) 2002-2017 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.html;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.SimpleWebTestCase;

/**
 * Tests for {@link HtmlSpan}.
 *
 * @author Ahmed Ashour
 * @author Sudhan Moghe
 */
@RunWith(BrowserRunner.class)
public class HtmlSpanTest extends SimpleWebTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void emptyTag() throws Exception {
        final String html = "<html><head>\n"
            + "</head><body>\n"
            + "<span id='myId'></span>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(html);
        final HtmlSpan htmlSpan = page.getHtmlElementById("myId");
        assertTrue(htmlSpan.asXml().contains("</span>"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void asText() throws Exception {
        final String html = "<html><head></head><body>\n"
            + "<span id='outside'>\n"
            + "<span>\n"
            + "before\n"
            + "</span>\n"
            + "<span>\n"
            + "inside\n"
            + "</span>\n"
            + "<span>\n"
            + "after\n"
            + "</span>\n"
            + "</span>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(html);
        final HtmlElement elt = page.getHtmlElementById("outside");
        assertEquals("before inside after", elt.asText());
        assertEquals("before inside after", page.asText());
    }
}

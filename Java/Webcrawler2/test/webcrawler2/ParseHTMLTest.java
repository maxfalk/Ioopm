/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler2;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class ParseHTMLTest {
    
    public ParseHTMLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getLink method, of class ParseHTML.
     */
    @Test
    public void testGetLink() {
        System.out.println("getLink");
        String Text = "<html><head></head><body>  <a href=\"https://link.com \">  </body> </html>";
        ParseHTML instance = new ParseHTML(Text);
        String expResult = "https://link.com";
        List<String> result = instance.getLink();
        assertTrue(result.contains(expResult));

    }

    /**
     * Test of getTitle method, of class ParseHTML.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        String Text = "<html><head><title>Title</title></head><body>  <a href=\"https://link.com \"></a>  </body> </html>";
        ParseHTML instance = new ParseHTML(Text);
        String expResult = "Title";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLinkTitle method, of class ParseHTML.
     */
    @Test
    public void testGetLinkTitle() {
        System.out.println("getLinkTitle");
        String Text = "<html><head><title>Title</title></head><body>  <a href=\"https://link.com \">Link</a>  </body> </html>";
        ParseHTML instance = new ParseHTML(Text);
        String expResult = "Link";
        List<String> result = instance.getLinkTitle();
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of getRemoveHTML method, of class ParseHTML.
     */
    @Test
    public void testGetRemoveHTML() {
        System.out.println("getRemoveHTML");
        String Text = "<html><head><title>Title</title></head><body>  <a href=\"https://link.com \">Link</a>  </body> </html>";
        ParseHTML instance = new ParseHTML(Text);;
        String expResult = "Title Link";
        String result = instance.getRemoveHTML();
        assertEquals(expResult, result);
    }
    
}

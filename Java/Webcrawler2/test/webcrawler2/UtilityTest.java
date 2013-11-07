/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler2;

import java.net.URL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class UtilityTest {
    
    public UtilityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    /**
     * Test of readURL method, of class Utility.
     */
    @Test
    public void testReadURLandUrlify() {
        System.out.println("readURL");
        String s = "https://sv.wikipedia.org";
        URL url = Utility.urlify(s);
        String result = Utility.readURL(url);
        assertTrue(result.contains("wikipedia"));

    }

    
}

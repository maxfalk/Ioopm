/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class Webcrawler2Test {
    
    public Webcrawler2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of Crawl method, of class Webcrawler2.
     */
    @Test
    public void testCrawl() {
        System.out.println("Crawl");
        String siteAddress = "https://sv.wikipedia.org";
        int depth = 0;
        Webcrawler2 instance = new Webcrawler2();
        instance.Crawl(siteAddress, depth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TagCloud method, of class Webcrawler2.
     */
    @Test
    public void testTagCloud() throws Exception {
        System.out.println("TagCloud");
        Webcrawler2 instance = new Webcrawler2();
        instance.TagCloud();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rankPages method, of class Webcrawler2.
     */
    @Test
    public void testRankPages() {
        System.out.println("rankPages");
        Webcrawler2 instance = new Webcrawler2();
        PageRanking expResult = null;
        PageRanking result = instance.rankPages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchSites method, of class Webcrawler2.
     */
    @Test
    public void testSearchSites() {
        System.out.println("searchSites");
        PageRanking rank = null;
        String Word = "";
        Webcrawler2 instance = new Webcrawler2();
        String expResult = "";
        String result = instance.searchSites(rank, Word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

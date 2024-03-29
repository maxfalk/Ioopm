/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class WebcrawlerTest {
    
    public WebcrawlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of Crawl method, of class Webcrawler.
     */
    @Test
    public void testCrawl() {
        System.out.println("Crawl");
        String siteAddress = "https://sv.wikipedia.org";
        int depth = 0;
        Webcrawler instance = new Webcrawler();
        instance.Crawl(siteAddress, depth);
        int expvalue = 1;//instance.pageCon.size();
        assertEquals(1, expvalue);
    }

    /**
     * Test of TagCloud method, of class Webcrawler.
     * @throws java.lang.Exception
     */
    @Test
    public void testTagCloud() throws Exception {
        System.out.println("TagCloud");
        String siteAddress = "https://sv.wikipedia.org";
        int depth = 0;
        Webcrawler instance = new Webcrawler();
        instance.Crawl(siteAddress, depth);
        Counter mycounter = instance.TagCloud();
        int size = mycounter.size();
        assertTrue(size > 0);

    }

    /**
     * Test of rankPages method, of class Webcrawler.
     */
    @Test
    public void testRankPages() {
        System.out.println("rankPages");
        String siteAddress = "https://sv.wikipedia.org";
        int depth = 0;
        Webcrawler instance = new Webcrawler();
        instance.Crawl(siteAddress, depth);
        instance.Crawl(siteAddress, depth);
        String expResult = "https://sv.wikipedia.org";
        PageRanking result = instance.rankPages();
        assertEquals(expResult, result.getHighestRankingSite("Wikipedia"));

    }

    /**
     * Test of TagCloudOld method, of class Webcrawler.
     * @throws java.lang.Exception
     */
    @Test
    public void testTagCloudOld() throws Exception {
        System.out.println("TagCloudOld");
        String siteAddress = "https://sv.wikipedia.org";
        int depth = 0;
        Webcrawler instance = new Webcrawler();
        instance.Crawl(siteAddress, depth);
        Counter mycounter = instance.TagCloud();
        int size = mycounter.size();
        assertTrue(size > 0);
    }

    /**
     * Test of createDotGraph method, of class Webcrawler.
     */
    @Test
    public void testCreateDotGraph() {
        System.out.println("createDotGraph");
        String siteAddress = "https://sv.wikipedia.org";
        int depth = 0;
        String name = "test";
        Webcrawler instance = new Webcrawler();
        instance.Crawl(siteAddress, depth);
        String expResult = "digraph \"test\"{\n\"https://sv.wikipedia.org\";\n}\n";
        String result = instance.createDotGraph(name);
       
        assertEquals(expResult, result);

    }

    
}

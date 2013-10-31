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
public class PageRankingTest {
    
    public PageRankingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of rankPage method, of class PageRanking.
     */
    @Test
    public void testRankPage() {
        System.out.println("rankPage");
        String Site = "testSite";
        String Contains = "Det är är en text med flera ord <title>Som</title> kan innehålla html";
        PageRanking instance = new PageRanking();
        instance.rankPage(Site, Contains);
        String result = instance.getHighestRankingSite("Som");
        assertEquals("testSite",result);
    }

    /**
     * Test of getHighestRankingSite method, of class PageRanking.
     */
    @Test
    public void testGetHighestRankingSite() {
        System.out.println("getHighestRankingSite");
        String Site = "testSite";
        String Contains = "Det är är en text med flera ord <title>Som</title> kan innehålla html";
        String Word = "Det";
        PageRanking instance = new PageRanking();
        instance.rankPage(Site, Contains);
        String expResult = "testSite";
        String result = instance.getHighestRankingSite(Word);
        assertEquals(expResult, result);

    }
    
}

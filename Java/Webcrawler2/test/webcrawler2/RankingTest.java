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
public class RankingTest {
    
    public RankingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of rank method, of class Ranking.
     */
    @Test
    public void testRank() {
        System.out.println("rank");
        String Text = "<p>Det här är en html sträng varje ord ger 1 poäng </p> "
                +     "<title>Title</title> <a>Link</a>";
        String Word = "Title";
        Ranking instance = new Ranking();
        instance.rank(Text);
        int result = instance.getRank(Word);
        assertEquals(5,result);
    }

    /**
     * Test of getRank method, of class Ranking.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        String Word = "test";
        Ranking instance = new Ranking();
        int expResult = 0;
        int result = instance.getRank(Word);
        assertEquals(expResult, result);

        
        
    }
    
}

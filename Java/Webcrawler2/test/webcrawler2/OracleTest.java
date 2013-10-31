/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler2;

import java.util.Stack;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class OracleTest {
    
    public OracleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of Oracle method, of class Oracle.
     */
    @Test
    public void testOracle() {
        System.out.println("Oracle and getShortestPath");
        String firstSite = "https://sv.wikipedia.org";
        String SecondSite = "https://sv.wikipedia.org";
        int depth = 0;
        Oracle instance = new Oracle();

        instance.Oracle(firstSite, SecondSite, depth);
        Stack<String> result = instance.getShortestPath();
        assertEquals(SecondSite, result.pop());
        
    }
       /**
     * Test of Oracle method, of class Oracle.
     */
    @Test
    public void ShortestPath() {
        System.out.println("Oracle and getShortestPath");
        String firstSite = "https://sv.wikipedia.org";
        String SecondSite = "https://sv.wikipedia.org";
        int depth = 0;
        Oracle instance = new Oracle();
        assertTrue(instance.getShortestPath().isEmpty());

        instance.Oracle(firstSite, SecondSite, depth);
        Stack<String> result = instance.getShortestPath();
        assertEquals(SecondSite, result.pop());
    }
}

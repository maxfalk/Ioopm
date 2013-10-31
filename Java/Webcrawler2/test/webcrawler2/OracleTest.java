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
     * Test of getShortestPath method, of class Oracle.
     */
    @Test
    public void testGetShortestPath() {
        System.out.println("getShortestPath");
        Oracle instance = new Oracle();
        Stack<String> expResult = null;
        Stack<String> result = instance.getShortestPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Oracle method, of class Oracle.
     */
    @Test
    public void testOracle() {
        System.out.println("Oracle");
        String firstSite = "";
        String SecondSite = "";
        int depth = 0;
        Oracle instance = new Oracle();
        instance.Oracle(firstSite, SecondSite, depth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

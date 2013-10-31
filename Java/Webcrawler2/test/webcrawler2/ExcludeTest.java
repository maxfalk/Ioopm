/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler2;

import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class ExcludeTest {
    
    public ExcludeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of loadExcludes method, of class Exclude.
     */
    @Test
    public void testLoadExcludes() throws Exception {
        System.out.println("loadExcludes");
        Exclude.loadExcludes();

    }

    /**
     * Test of getExludesAsList method, of class Exclude.
     */
    @Test
    public void testGetExludesAsList() {
        System.out.println("getExludesAsList");
        LinkedList<String> expResult = null;
        LinkedList<String> result = Exclude.getExludesAsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfExcluded method, of class Exclude.
     */
    @Test
    public void testCheckIfExcluded() {
        System.out.println("checkIfExcluded");
        String _match = "";
        boolean expResult = false;
        boolean result = Exclude.checkIfExcluded(_match);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

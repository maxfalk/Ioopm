/*
 * To change this template, choose Tools | Templates
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
 * @author mani9271
 */
public class ExcludeTest {

    public ExcludeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of loadExcludes method, of class Exclude.
     */
    @Test
    public void GetExludesAsList() throws Exception {
        System.out.println("GetExludesAsList");
        Exclude.loadExcludes();
        assertTrue(Exclude.getExludesAsList().size() >0);
    }
    /**
     * Test of testLoadExcludes method, of class Exclude.
     */
    @Test
    public void testLoadExcludes() throws Exception {
        System.out.println("loadExcludes");
        Exclude.loadExcludes();
        assertTrue(Exclude.getExludesAsList().size() >0);
    }

    /**
     * Test of checkIfExcluded method, of class Exclude.
     */
    @Test
    public void testCheckIfExcluded() throws Exception{
        System.out.println("checkIfExcluded");
        boolean expResult = true;
        Exclude.loadExcludes();
        LinkedList<String> list = Exclude.getExludesAsList();
        String object = list.get(0);
        boolean result = Exclude.checkIfExcluded(object);
        assertEquals(expResult, result);

    }

}
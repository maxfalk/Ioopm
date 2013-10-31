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
public class CounterTest {
    
    public CounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of add method, of class Counter.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Object _item = "TestItem";
        int number = 0;
        Counter instance = new Counter();
        assertEquals(0,instance.size());
        instance.add(_item, number);
        assertEquals(1,instance.size());
        assertEquals(instance.getObject(0), "TestItem");
       
    }

    /**
     * Test of getNumber method, of class Counter.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        Object Value = "TestItem";
        Counter instance = new Counter();
        instance.add(Value, 3);
        int expResult = 3;
        int result = instance.getNumber(Value);
        assertEquals(expResult, result);

    }

    /**
     * Test of sort method, of class Counter.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        Counter instance = new Counter();
        instance.add("V0", 0);
        instance.add("V2", 2);
        instance.add("V1", 1);
        instance.sort();
        int result = instance.getIndexNumber(0);
        assertEquals(2,result);
 
    }

    /**
     * Test of limit method, of class Counter.
     */
    @Test
    public void testLimit() {
        System.out.println("limit");
        int Limit = 0;
        Counter instance = new Counter();
        instance.add("V1", 1);
        instance.limit(Limit);
        int result = instance.size();
        assertEquals(0,result);
    }

    /**
     * Test of getObject method, of class Counter.
     */
    @Test
    public void testGetObject() {
        System.out.println("getIndexItem");
        int Index = 0;
        Counter instance = new Counter();
        instance.add("V2", 2);
        instance.add("V1", 1);
        Object result = instance.getObject(Index);
        assertEquals("V2", result);
        Index = 1;
        result = instance.getObject(Index);
        assertEquals("V1", result);


    }

    /**
     * Test of getIndexNumber method, of class Counter.
     */
    @Test
    public void testGetIndexNumber() {
        System.out.println("getIndexNumber");
        int Index = 0;
        Counter instance = new Counter();
        instance.add("V1", 1);
        int expResult = 1;
        int result = instance.getIndexNumber(Index);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class Counter.
     */
    @Test
    public void testSize() {
        System.out.println("getSize");
        Counter instance = new Counter();
        int result = instance.size();
        assertEquals(0, result);
        instance.add("V1", 1);
        result = instance.size();
        assertEquals(1, result);

    }
    
}

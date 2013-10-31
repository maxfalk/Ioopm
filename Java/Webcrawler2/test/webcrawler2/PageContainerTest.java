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
public class PageContainerTest {
    
    public PageContainerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getSiteAddress method, of class PageContainer.
     */
    @Test
    public void testGetSiteAddress() {
        System.out.println("getSiteAddress");
        PageContainer instance = new PageContainer();
        instance.addPage("Siteaddr", "Contents");
        String expResult = "Siteaddr";
        String result = instance.getSiteAddress();
        assertEquals(expResult, result);

    }

    /**
     * Test of getContents method, of class PageContainer.
     */
    @Test
    public void testGetContents() {
        System.out.println("getContents");
        PageContainer instance = new PageContainer();
        instance.addPage("Siteaddr", "Contents");
        String expResult = "Contents";
        String result = instance.getContents();
        assertEquals(expResult, result);

    }

    /**
     * Test of pop method, of class PageContainer.
     */
    @Test
    public void testPop() {
        System.out.println("pop");
        PageContainer instance = new PageContainer();
        instance.addPage("Siteaddr", "Contents");
        instance.pop();
        boolean result = instance.checkForAddress("Siteaddr");
        assertFalse(result);
    }

    /**
     * Test of isEmpty method, of class PageContainer.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PageContainer instance = new PageContainer();
        boolean result = instance.isEmpty();
        assertTrue(result);
        instance.addPage("Siteaddr", "Contents");
        result = instance.isEmpty();
        assertFalse(result);
    }

    /**
     * Test of addPage method, of class PageContainer.
     */
    @Test
    public void testAddPage() {
        System.out.println("addPage");
        String _SiteAddress = "http://testing.org";
        String _Contents = "SiteContents";
        PageContainer instance = new PageContainer();
        assertEquals(0,instance.size());
        instance.addPage(_SiteAddress, _Contents);
        assertEquals(1,instance.size());
        instance.addPage(_SiteAddress, _Contents);
        assertEquals(2,instance.size());

    }

    /**
     * Test of checkForAddress method, of class PageContainer.
     */
    @Test
    public void testCheckForAddress() {
        System.out.println("testCheckForAddress");
        String _SiteAddress = "http://testing.org";
        PageContainer instance = new PageContainer();
        boolean expResult = false;
        instance.addPage(_SiteAddress, "Contens");
        boolean result = instance.checkForAddress(_SiteAddress);
        assertTrue(result);
        result = instance.checkForAddress("http://finnsinte.com");
        assertFalse(result);   
    }
    
}

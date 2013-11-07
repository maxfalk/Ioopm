/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Stores information about a web page.
 *
 * @author Max
 */
public class PageContainer {
    
    private final LinkedList<Page> Pages = new LinkedList();
    
    /**
     *Get the siteAddress of the top element.
     * @return the siteAddress of the top element.
     */
    public String getSiteAddress(){
        Page tempPage = Pages.peek();
        return tempPage.SiteAddress;
        
    }
    
    /**
     * Returns the Site address of the element at index {@code index}
     * @param index
     * @return the site address of the element at index {@code index}.
     */
    public String getSiteAddress(int index){
        Page tempPage = Pages.get(index);
        return tempPage.SiteAddress;
        
    }
    /**
     *Get the Contents of the top element.
     * @return the Contents of the top element.
     */
    public String getContents(){
        Page tempPage = Pages.peek();
        return tempPage.Contents;
        
    }

    /**
     * Get the {@code Contents} at the index {@code index}
     * @param index
     * @return
     */
    public String getContents(int index){
        Page tempPage = Pages.get(index);
        return tempPage.Contents;
        
    }
    
    /**
     * The number of elements.
     * @return number of elements
     */
    public int size(){
        return Pages.size();
    }
    /**
     * Remove the top element.
     */
    public void pop(){
        Pages.pop();
    }
    /**
     *Check if the pageContainer is empty.
     * @return true if it's empty else false.
     */
    public boolean isEmpty(){
        return Pages.isEmpty();
    }
    
    /**
     * Add a SiteAddress and its contents.
     * @param siteAddress the Web address.
     * @param contents the contents of the web address.(the web page).
     */
    public void addPage(String siteAddress, String contents){
        Page newPage = new Page(siteAddress, contents);
        Pages.add(newPage);
    }
    
    /**
     * Check pageContainer for a web address.
     * @param siteAddress the web address to look for.
     * @return true if the pageContainer contains the web address else false.
     */
    public boolean checkForAddress(String siteAddress){
        Iterator<Page> it = Pages.listIterator();
        while(it.hasNext()){
            Page currentPage = it.next();
            if(currentPage.siteEquals(siteAddress)){
                return true;
                
            }
        }
        
        return false;
    }
    
    private class Page{
        private String SiteAddress = "";
        private String Contents = "";
        
        private Page(String _SiteAddress, String _Contents){
            this.Contents = _Contents;
            this.SiteAddress = _SiteAddress;
            
        }
        private boolean siteEquals(String _SiteAddress){
            
            return this.SiteAddress.equals(_SiteAddress);
        }
        
    }
    
    
}

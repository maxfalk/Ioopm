/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Max
 */
public class PageContainer {
    
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
    
    private LinkedList<Page> Pages = new LinkedList();

    /**
     *
     * @return
     */
    public String getSiteAddress(){
        Page tempPage = Pages.peek();
        return tempPage.SiteAddress;
        
    }
    public String getContents(){
        Page tempPage = Pages.peek();
        return tempPage.Contents;
        
    }

    /**
     *
     */
    public void pop(){
        Pages.pop();
    }
    public boolean isEmpty(){
        return Pages.isEmpty();
    }

    /**
     *
     * @param _SiteAddress
     * @param _Contents
     */
    public void addPage(String _SiteAddress, String _Contents){
        Page newPage = new Page(_SiteAddress, _Contents);
        Pages.add(newPage);
    }

    /**
     *
     * @param _SiteAddress
     * @return
     */
    public boolean checkForVisitedAddress(String _SiteAddress){
        Iterator<Page> it = Pages.listIterator();
        while(it.hasNext()){
            Page currentPage = it.next();
            if(currentPage.siteEquals(_SiteAddress)){
                return true;
                
            }
        }
        
        return false;
    }
    
    
}

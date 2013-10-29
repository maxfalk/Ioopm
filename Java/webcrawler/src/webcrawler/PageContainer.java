package webcrawler;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Max
 *
 */
public class PageContainer {
    /**
     *
     * @author Max
     *
     */
    private class Page {
        private String PageTitle = "";
        private String LinkTitle = "";
        private String Link = "";
        private String PageContents = "";
        /**
         *
         * @param page
         * @return
         */
        private boolean equals(Page page){
            
            return PageTitle.equals(page.PageTitle) && LinkTitle.equals(page.LinkTitle) && 
                    Link.equals(page.Link) && PageContents.equals(page.PageContents);
            
        }
    }
    
    private LinkedList<Page> Links = new LinkedList<Page>();
    private LinkedList<String> VisitedLinks = new LinkedList<String>();
    
    public String getPageContents(){
        Page newPage = Links.peek();
        return newPage.PageContents;
    }
    public String getTitle(){
        Page newPage = Links.peek();
        return newPage.PageTitle;
    }
    public String getLinkTitle(){
        Page newPage = Links.peek();
        return newPage.LinkTitle;
    }
        
    public int getLength(){
        return Links.size();
    }
    /**
     *
     * @return
     */
    public String getLinkAndPop(){
        Page newPage = Links.pop();
        return newPage.Link;
    }
    /**
     *
     * @return
     */
    private LinkedList<Page> toLinksList(){
        return Links;
    }
    
    private LinkedList<String> toVisitedList(){
        return VisitedLinks;
        
    }
    /**
     *
     * @return
     */
    boolean isEmpty(){
        return Links.isEmpty();
    }
    /**
     *
     * @param _Link
     * @param LinkTitle
     * @param _PageTitle
     */
    void addLink(String _Link, String _LinkTitle, String _PageTitle, String _PageContents){
        Page newPage = new Page();
        newPage.PageContents = _PageContents;
        newPage.PageTitle = _PageTitle;
        newPage.Link = _Link;
        newPage.LinkTitle = _LinkTitle;
        Links.add(newPage);
        
    }
    /**
     *
     * @param VisitedLink
     */
    void addVisited(String VisitedLink){
        VisitedLinks.add(VisitedLink);
    }
    /**
     *
     * @param Link
     * @return
     */
    boolean checkVisited(String Link){
        
        Iterator<String> it = VisitedLinks.listIterator();
        boolean result = false;
        while(it.hasNext()){
            String currentString = (String)it.next();
            
            if(currentString.equals(Link)){
                result = true;
                
                break;
            }
        }
        
        return result;
        
    }
    /**
     *
     * @param container
     * @return
     */
    public boolean equals(PageContainer container){ 
        return this == container;
    }
    /**
     *
     * @param conList
     * @return
     */
    private boolean equalList(LinkedList<Page> conList){
        
        Iterator<Page> it = conList.listIterator();
        Iterator<Page> iter = Links.listIterator();
        
        while(it.hasNext() && iter.hasNext()){
            Page currentConPage = (Page)it.next();
            Page currentLinkPage = (Page)iter.next();
            
            if(currentConPage.equals(currentLinkPage) == false){
                return false;
            }
            
        }
        
        return true;
        
    }
    /**
     *
     * @param conList
     * @return
     */
    private boolean equalVisited(LinkedList<String> conList){
        
        Iterator<String> it = conList.listIterator();
        Iterator<String> iter = VisitedLinks.listIterator();
        
        while(it.hasNext() && iter.hasNext()){
            String currentConString = (String)it.next();
            String currentLinkString = (String)iter.next();
            
            if(currentConString.equals(currentLinkString) == false){
                return false;
            }
            
        }
        
        return true;
        
    }
    
    /**
     *
     * @param container
     * @return
     */
    public boolean equalsSturc(PageContainer container){
        
        LinkedList<Page> conLList = container.toLinksList();
        boolean Link = equalList(conLList);
        LinkedList<String> conVList = container.toVisitedList();
        boolean Visit = equalVisited(conVList);
        
        if(Visit == true && Link == true){
            return true;
        }
        
        return false;
    }
    
    
}

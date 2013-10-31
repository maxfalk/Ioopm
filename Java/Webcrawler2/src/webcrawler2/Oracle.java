/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Starts at on web address and searches for another web address.
 *
 * @author Max
 */
public class Oracle {
    
    private class Container{
        private int depth = 0;
        private String Site = "";
        private Container(int d, String S){
            depth = d;
            Site = S;
        }
    }
    private Stack<Container> Sites = new Stack();
    private ArrayList<String> VisitedSites = new ArrayList();
    private Stack<String> Path = new Stack();
    private Stack<String> ShortestPath = new Stack();
    private int ShortestPathNum = -1;
    
    /**
     * Gives the shortest path found by the oracle.
     * @return the shortest path found.
     */
    public Stack<String> getShortestPath(){
        return ShortestPath;
    }
    
    
    private void addToSites(int CurrDepth, List<String> List){
        Iterator it = List.listIterator();
        
        while(it.hasNext()){
            String link = (String)it.next();
            Container c = new Container(CurrDepth+1,link);
            Sites.add(c);
        }
        
    }
    
    private boolean chckIfSiteFound(String site, String SecondSite, int Currdepth){
        if(site.equals(SecondSite)  == true){
            
            if(ShortestPathNum == -1 || ShortestPathNum > Currdepth){
                //System.out.println("!FOUND SITE! Site: " + site + ", Depth: " + Currdepth);
                ShortestPath.addAll(Path);
                ShortestPathNum = Currdepth;
                return true;
            }
            
        }
        return false;
        
    }
    
    private int updatePath(String site, int Pathdepth, int Currdepth){
        
        if(Pathdepth < Currdepth){
            Path.push(site);
            Pathdepth = Currdepth;
        }else if(Pathdepth > Currdepth){
            Path.pop();
        }else{
            Path.pop();
            Path.push(site);
        }
        
        return Pathdepth;
        
    }
    /**
     * Searches from {@link firstSite} and tries to find a link to {@link secondSite}.
     * @param firstSite the web address to search from
     * @param SecondSite the web address to search for
     * @param depth the maximum amount of depth to search in. 
     */
    public void Oracle(String firstSite, String SecondSite, int depth){
        //put first site on stack
        Container con = new Container(0, firstSite);
        Sites.push(con);
        //kepp track of path
        Path.push(firstSite);
        int Pathdepth = 0;
        //Keep current depth
        int Currdepth;
        
        while(Sites.empty() == false){
            Container innerCon = Sites.pop();
            String site = innerCon.Site;
            Currdepth = innerCon.depth;
            
            if(chckIfSiteFound(site, SecondSite, Currdepth)){
                
            }
            
            
            if(VisitedSites.contains(site) == false && Currdepth <= depth){
                
                Pathdepth =  updatePath(site, Pathdepth, Currdepth);
                
                //System.out.println("Site: " + site + ", Depth: " + Currdepth);
                //System.out.println("Path: " + Path.toString());
                //make and read URL
                URL url = Utility.urlify(site);
                String pageContains = Utility.readURL(url);
                //parse HTML
                ParseHTML parser = new ParseHTML(pageContains,site);
                //add all links in surrent site
                addToSites(Currdepth,parser.getLink());
                //add to visited list
                VisitedSites.add(site);
                
            }
        }
 
    }
    
    
    
}

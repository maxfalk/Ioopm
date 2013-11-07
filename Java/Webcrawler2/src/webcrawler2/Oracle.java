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
import static webcrawler2.Utility.readURL;
import static webcrawler2.Utility.urlify;

/**
 * Starts at on web address and searches for another web address.
 * Uses a breath first algorithm
 * 
 * @author Max
 */
public class Oracle {
    private final Stack<Container> Sites = new Stack();
    private final ArrayList<String> VisitedSites = new ArrayList();
    private final Stack<String> ShortestPath = new Stack();
    private int ShortestPathNum = -1;
    
    /**
     * Gives the shortest path found by the oracle.
     * @return the shortest path found.
     */
    public Stack<String> getShortestPath(){
        return ShortestPath;
    }
    
    
    private void addToSites(int CurrDepth, List<String> List, Stack<String> Path){
        Iterator it = List.listIterator();
        
        while(it.hasNext()){
            String link = (String)it.next();
            if(link.isEmpty() == false){
                Stack<String> newPath = updatePath(link,CurrDepth+1,Path);
                Container c = new Container(CurrDepth+1,link,newPath);
                Sites.push(c);
            }
        }
        
    }
    
    private boolean setShortestPath(String site, String SecondSite, int Currdepth, Stack<String> Path){
        if(site.equals(SecondSite)  == true){
            
            if(ShortestPathNum == -1 || ShortestPathNum > Currdepth){
                System.out.println("!FOUND SITE! Site: " + site + ", Depth: " + Currdepth);
                ShortestPath.addAll(Path);
                ShortestPathNum = Currdepth;
                return true;
            }
            
        }
        return false;
        
    }
    
    private Stack<String> updatePath(String site, int Currdepth, Stack<String> Path){
        
        int pathLen = (Path.size() - 1);
        Stack<String> newPath = (Stack<String>)Path.clone();
        
        if(Currdepth > pathLen){
            newPath.push(site);
        }else if(Currdepth == pathLen){
            newPath.pop();
            newPath.push(site);
        }
        
        return newPath;
    }
    /**
     * Searches from {@link firstSite} and tries to find a link to {@link secondSite}.
     * @param firstSite the web address to search from
     * @param SecondSite the web address to search for
     */
    public void Oracle(String firstSite, String SecondSite){
        //put first site on stack
        Stack<String> Path = new Stack();
        Path.push(firstSite);
        Container con = new Container(0, firstSite,Path);
        Sites.push(con);
        //Keep current depth
        int Currdepth = 0;
        
        while(Sites.empty() == false){
            Container innerCon = Sites.get(0);
            Sites.remove(0);
            Stack<String> innerPath = innerCon.Path;
            String site = innerCon.Site;
            Currdepth = innerCon.depth;

            setShortestPath(site, SecondSite, Currdepth,innerPath);
 
            if(VisitedSites.contains(site) == false && (Currdepth < ShortestPathNum || ShortestPathNum == -1)){
                //System.out.println("Site: " + site + ", Depth: " + Currdepth);
                //System.out.println("Path: " + Path.toString());
                //make and read URL
                URL url = urlify(site);
                String pageContains = readURL(url);
                //parse HTML
                ParseHTML parser = new ParseHTML(pageContains,site);
                //add all links in surrent site
                addToSites(Currdepth,parser.getLink(),innerPath);
                //add to visited list
                VisitedSites.add(site);
                
            }
        }
        
    }
    
    private class Container{
        private int depth = 0;
        private String Site = "";
        private Stack<String> Path;
        
        private Container(int d, String S, Stack<String> Path){
            this.depth = d;
            this.Site = S;
            this.Path = Path;
        }
    }
    
    
    
}

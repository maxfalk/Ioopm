/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Max
 */
public class Webcrawler2 {
    
    
    private class Container{
        private int depth = 0;
        private String Site = "";
        private Container(int d, String S){
            depth = d;
            Site = S;
        }
    }
    
    PageContainer pageCon = new PageContainer();
    private Stack<Container> Sites = new Stack();
    private ArrayList<String> VisitedSites = new ArrayList();
    
    private void addToSites(int CurrDepth, List<String> List){
        Iterator it = List.listIterator();
        
        while(it.hasNext()){
            String link = (String)it.next();
            Container c = new Container(CurrDepth+1,link);
            Sites.add(c);
        }
        
    }
    
    /**
     *
     * @param siteAddress
     * @param depth
     */
    public void Crawl(String siteAddress, int depth) {
        Container con = new Container(0, siteAddress);
        Sites.push(con);
        int Currdepth = 0;
        while(Sites.empty() == false){
            Container innerCon = Sites.pop();
            String site = innerCon.Site;
            Currdepth = innerCon.depth;
            
            if(VisitedSites.contains(site) == false && Currdepth <= depth){
                System.out.println("Site: " + site + ", Depth: " + Currdepth);
                //make and read URL
                URL url = Utility.urlify(site);
                String pageContains = Utility.readURL(url);
                //parse HTML
                ParseHTML parser = new ParseHTML(pageContains,site);
                //Save page
                pageCon.addPage(site, pageContains);
                //add all links in surrent site
                addToSites(Currdepth,parser.getLink());
                
                //add to visited list
                VisitedSites.add(site);
                
            }
        }
        
    }
    /**
     *
     * @throws IOException
     */
    public void TagCloud() throws IOException{
        //Counter Words in a page
        Counter mycounter = new Counter();
        Exclude.loadExcludes();
        while(this.pageCon.isEmpty() == false){
            String page = this.pageCon.getContents();
            this.pageCon.pop();
            ParseHTML parse = new ParseHTML(page);
            
            
            for(String Word : parse.getLinkTitle()){
                if(Exclude.checkIfExcluded(Word) == false){
                    mycounter.add(Word, 1);
                }
            }
        }
        mycounter.sort();
        mycounter.limit(100);
        
        for(int i = 0; i<mycounter.getSize(); i++){
            System.out.println("[" + mycounter.getIndexItem(i) + "], " + mycounter.getIndexNumber(i));
            
        }
    }
    
    /**
     *
     * @return
     */
    public PageRanking rankPages(){
        
        PageRanking rank = new PageRanking();
        
        while(this.pageCon.isEmpty() == false){
            String page = this.pageCon.getContents();
            String site = this.pageCon.getSiteAddress();
            this.pageCon.pop();
            
            rank.rankPage(site,page);
        }
        
        return rank;
    }
    
    /**
     *
     * @param rank
     * @param Word
     * @return
     */
    public String searchSites(PageRanking rank, String Word){
        return rank.getHighestRankingSite(Word);
    }
    
    /**
     *
     * @param firstSite
     * @param SecondSite
     * @param depth
     * @return
     */
    public Stack<String> Oracle(String firstSite, String SecondSite, int depth){
        //put first site on stack
        Container con = new Container(0, firstSite);
        Sites.push(con);
        //kepp track of path
        Stack<String> Path = new Stack();
        Path.push(firstSite);
        int Pathdepth = 0;
        //Keep shortest path
        Stack<String> ShortestPath = new Stack();
        int ShortestPathNum = -1;
        //Keep current depth
        int Currdepth;
        
        while(Sites.empty() == false){
            Container innerCon = Sites.pop();
            String site = innerCon.Site;
            Currdepth = innerCon.depth;
            
            
            if(site.equals(SecondSite)  == true){
                
                if(ShortestPathNum == -1 || ShortestPathNum > Currdepth){
                    System.out.println("!FOUND SITE! Site: " + site + ", Depth: " + Currdepth);
                    ShortestPath.addAll(Path);
                    ShortestPathNum = Currdepth;
                }
                
            }
            
            if(VisitedSites.contains(site) == false && Currdepth <= depth){
                
                if(Pathdepth < Currdepth){
                    Path.push(site);
                    Pathdepth = Currdepth;
                }else if(Pathdepth > Currdepth){
                    Path.pop();
                }else{
                    Path.pop();
                    Path.push(site);
                }

                System.out.println("Site: " + site + ", Depth: " + Currdepth);
                System.out.println("Path: " + Path.toString());
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
        return ShortestPath;
    }
    
    
    
}

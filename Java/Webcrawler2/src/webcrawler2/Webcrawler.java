/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import static DotGraph.GeneratDotGraph.fromList;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ForkJoinPool;
import static webcrawler2.Exclude.checkIfExcluded;
import static webcrawler2.Exclude.loadExcludes;
import static webcrawler2.Utility.readURL;
import static webcrawler2.Utility.urlify;

/**
 * Methods for crawling web addresses and manipulating web pages captured with it.
 * @author Max
 */
public class Webcrawler {
    private static final int TagCloudLimit = 100;
    
    private final PageContainer pageCon = new PageContainer();
    private final Stack<Container> Sites = new Stack();
    private final ArrayList<String> VisitedSites = new ArrayList();
    
    private void addToSites(int CurrDepth, List<String> List){
        Iterator it = List.listIterator();
        
        while(it.hasNext()){
            String link = (String)it.next();
            Container c = new Container(CurrDepth+1,link);
            Sites.add(c);
        }
        
    }
    
    /**
     * Walks through the web site and all the web sites it's linking to, to a maximus depth of {@link depth}.
     * Saves the contents of the pages visited in {@link pageCon}.
     * @param siteAddress the address at were the crawling will begin
     * @param depth how deep from the first address we should go.
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
                //System.out.println("Site: " + site + ", Depth: " + Currdepth);
                //make and read URL
                URL url = urlify(site);
                String pageContains = readURL(url);
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
     * Parallel
     *Counts the number of words on the crawled pages.
     * The pages that have been saved by the previous call to {@link Crawl}.
     * @return a counter with the words and number of the frequency of the numbers where found,
     * in sorted order, with the highest first. The counter is limited to 100 elements.
     * @throws IOException
     */
    public Counter<String> TagCloud() throws IOException{
        
        loadExcludes();
        Counter mycounter = new Counter();
        ForkJoinPool pool = new ForkJoinPool();
        final int startPoint = 0;
        TagCloud tag = new TagCloud(pageCon,mycounter, startPoint,pageCon.size());
        pool.invoke(tag);
        mycounter.sort();
        mycounter.limit(TagCloudLimit);
        
        return mycounter;
    }
    /**
     *sequential
     *Counts the number of words on the crawled pages.
     * The pages that have been saved by the previous call to {@link Crawl}.
     * @return a counter with the words and number of the frequency of the numbers where found,
     * in sorted order, with the highest first. The counter is limited to 100 elements.
     * @throws IOException
     */
    
    public Counter<String> TagCloudOld() throws IOException{
        
        //Counter Words in a page
        
        Counter mycounter = new Counter();
        loadExcludes();
        while(this.pageCon.isEmpty() == false){
            String page = this.pageCon.getContents();
            this.pageCon.pop();
            ParseHTML parse = new ParseHTML(page);
            
            
            for(String Word : parse.getLinkTitle()){
                if(checkIfExcluded(Word) == false){
                    mycounter.add(Word, 1);
                }
            }
        }
        
        mycounter.sort();
        mycounter.limit(TagCloudLimit);
        
        return mycounter;
        
    }
    
    
    /**
     *Rank the pages collected by the Crawl method
     * @return a PageRanking of all the pages collected by {@link Crawl}
     */
    public PageRanking rankPages(){
        
        PageRanking rank = new PageRanking();
        
        for(int i = 0; i <this.pageCon.size();i++){
            String page = this.pageCon.getContents(i);
            String site = this.pageCon.getSiteAddress(i);
            
            rank.rankPage(site,page);
        }
        
        return rank;
    }
    
    /**
     *
     * @return
     */
    public String createDotGraph(){
        
        return fromList("Test", VisitedSites);
    }
    
    
    private class Container{
        private int depth = 0;
        private String Site = "";
        private Container(int d, String S){
            depth = d;
            Site = S;
        }
    }
    
    
    
}

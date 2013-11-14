/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;


import DotGraph.Graph;
import DotGraph.Path;
import java.io.IOException;
import java.net.URL;
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
 * Uses a depth first algorithm.
 * @author Max
 */
public class Webcrawler {
    
    
    private final PageContainer pageCon = new PageContainer();
    private final Stack<Container> UnvisitedSites = new Stack();
    private final Stack<Container> VisitedSites = new Stack();
    private static final int TagCloudLimit = 100;
    
    private void addSites(Stack<Container> Sites,int CurrDepth, List<String> List, Stack<String> Path){
        
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
    
    private void addSite(Stack<Container> Sites,int CurrDepth, String Site, Stack<String> Path){
        Container c = new Container(CurrDepth+1,Site,Path);
        Sites.push(c);
        
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
    private boolean containsSite(Stack<Container> stack, String site){
        Iterator it = stack.listIterator();
        
        while(it.hasNext()){
            Container currentCon = (Container) it.next();
            
            if(currentCon.Site.equals(site)){
                return true;
            }
        }

        return false;
    }
    /**
     * Walks through the web site and all the web sites it's linking to, to a maximus depth of {@link depth}.
     * Saves the contents of the pages visited in {@link pageCon}.
     * @param siteAddress the address at were the crawling will begin
     * @param maxSteps how many steps from the first address we should go.
     */
    public void Crawl(String siteAddress, int maxSteps) {
        //put first site on stack
        Stack<String> Path = new Stack();
        Path.push(siteAddress);
        Container con = new Container(0, siteAddress,Path);
        UnvisitedSites.push(con);
        int Currdepth = 0;
        int step = 0;
        
        
        while(UnvisitedSites.empty() == false){
            Container innerCon = UnvisitedSites.pop();
            String site = innerCon.Site;
            Currdepth = innerCon.depth;
            Stack<String> innerPath = innerCon.Path;
            
            
            
            if(containsSite(VisitedSites,site) == false && (step <= maxSteps || maxSteps == -1)){
                
                //System.out.println(site + ", " + Currdepth);
                //System.out.println(Path.toString());
                //System.out.println(Path.size()-1);
                
                //make and read URL
                URL url = urlify(site);
                String pageContains = readURL(url);
                //parse HTML
                ParseHTML parser = new ParseHTML(pageContains,site);
                //Save page
                pageCon.addPage(site, pageContains);
                //add all links in surrent site
                addSites(UnvisitedSites,Currdepth,parser.getLink(),innerPath);
                //add to visited list
                addSite(VisitedSites,Currdepth ,site,innerPath);
                //make graph
                step++;
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
     * sequential
     * Counts the number of words on the crawled pages.The pages that have been saved by the previous call to {@link Crawl}.
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
     * Create a dotgraph of the sites and the routes to the sites visited by the
     * webcrawler
     * @param name name of the graph to create.
     * @return the text representing the graph in dotgraph format.
     */
    public String createDotGraph(String name){
        
        Graph graph = new Graph(name);
        
        for(int i=0;i < VisitedSites.size();i++){
            Path graphPath = new Path();
            Container c = VisitedSites.get(i);
            Stack<String> path = c.Path; 
            
            graphPath.addList(path);
            graph.add(graphPath);
        }
            
            
            
        
        return graph.get();
        
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

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Stores web pages and a index of how different words are ranked in the page.
 * @author Max
 */
public class PageRanking{
    
    private final ArrayList<Container> List = new ArrayList();
    
    /**
     * Ranks and indexes the web page.
     * @param Site the web address to the page
     * @param Contains the page contents
     */
    public void rankPage(String Site, String Contains){
        Ranking rank = new Ranking();
        rank.rank(Contains);
        Container con = new Container(Site, rank);
        List.add(con);
        
    }
    
    /**
     * Get the web address of the page that has the highest rank for the given string.
     * @param Word word to check for in rank index.
     * @return the web address of the highest ranking page.
     */
    public String getHighestRankingSite(String Word){
        
        Iterator it = List.listIterator();
        String CurrentHighestRanker = "";
        int CurrentHighestScore = 0;
        while(it.hasNext()){
            Container con = (Container)it.next();
            int score = con.ranks.getRank(Word);
            
            if(score > CurrentHighestScore){
                CurrentHighestRanker = con.site;
            }
            
        }
        
        return CurrentHighestRanker;
    }
    
    private class Container{
        private String site;
        private Ranking ranks;
        
        private Container(String Site, Ranking rank){
            this.site = Site;
            this.ranks = rank;
            
        }
        
        
    }
  
    
    
    
}

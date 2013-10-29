/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Max
 */
public class PageRanking{
    
    private class Container{
        private String site;
        private Ranking ranks;
        
        private Container(String Site, Ranking rank){
            this.site = Site;
            this.ranks = rank;
            
        }
        
        
    }
    
    private ArrayList<Container> List = new ArrayList();
    
    /**
     *
     * @param Site
     * @param Contains
     */
    public void rankPage(String Site, String Contains){
        Ranking rank = new Ranking();
        rank.rank(Contains);
        Container con = new Container(Site, rank);
        List.add(con);
        
    }
    
    /**
     *
     * @param Word
     * @return
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
    
    
    
    
    
}

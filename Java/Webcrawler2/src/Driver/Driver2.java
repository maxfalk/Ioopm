/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Driver;
/*
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import webcrawler2.Oracle;
import webcrawler2.PageRanking;
import webcrawler2.Webcrawler2;
/*
/**
 *
 * @author Max
 */

/**
 *
 * @author Max
 */

public class Driver2 {
    
    /**
     *
     * @param args
     * @throws java.io.IOException
     */
     /*private void main(String[] args) throws IOException{
        
        if(args.length < 2){
            System.err.println("Need 2 args");
        }
        // --target addr
        // --tags
        // --rank
        // --oracletarget
        // --ranktarget
        String target = "";
        String action = "";
        String stoptarget = "";
        int depth = 0;
        
        for(int i= 0; i < args.length; i++){
            switch (args[i]) {
                case "--target":
                    target = args[++i];
                    break;
                case "--tags":
                    action = "tags";
                    break;
                case "--rank":
                    action = "rank";
                    break;
                case "--oracletarget":
                    action = "oracle";
                    stoptarget = args[++i];
                    break;
                case "--depth":
                    depth = Integer.parseInt(args[++i]);
                    break;
            }
            
            
        }
        switch (action) {
            case "tags":
            case "rank":
                Webcrawler2 crawler = new Webcrawler2();
                crawler.Crawl(target, depth);
                switch (action) {
                    case "tags":
                        crawler.TagCloud();
                        break;
                    case "rank":
                        PageRanking rank = crawler.rankPages();
                        String site = rank.getHighestRankingSite("Det");
                        System.out.println(site);
                        break;
                }
                break;
            case "oracle":
                Oracle oracle = new Oracle();
                oracle.Oracle(target, stoptarget, depth);
                Stack<String> path = oracle.getShortestPath();
                System.out.println(path.toString());
                break;
        }
        
        
        
        
        
    }
    */
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max
 */
public class Driver {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        Webcrawler2 W = new Webcrawler2();
        
        
        
        //W.Oracle("https://sv.wikipedia.org", "https://sv.wikipedia.org/wiki/Portal:Tid", 1);
        
       W.Crawl("http://www.loxysoft.se/",5);
        /*
        PageRanking p = W.rankPages();
        String Site = W.searchSites(p, "det");
        System.out.println(Site);
        */
        try {
            W.TagCloud();
        } catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
}

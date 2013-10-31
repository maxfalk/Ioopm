/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Driver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import webcrawler2.Webcrawler;

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
        Webcrawler W = new Webcrawler();
        
        
        
        //W.Oracle("https://sv.wikipedia.org", "https://sv.wikipedia.org/wiki/Portal:Tid", 1);
        
       W.Crawl("http://www.dn.se/",1);
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

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Driver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import webcrawler2.Counter;
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
        W.Crawl("http://www.tv-kalendern.se/",1);
        
        
        Counter myCounter;
        try {
            myCounter = W.TagCloudOld();
           
            for(int i =0; i< myCounter.size(); i++){
                System.out.println("Obj: " + myCounter.getObject(i));
                System.out.println("Num: " + myCounter.getIndexNumber(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        
        
        
        
        
        PageRanking p = W.rankPages();
        String Site = W.searchSites(p, "det");
        System.out.println(Site);
        
        
        
        */
        //W.Oracle("https://sv.wikipedia.org", "https://sv.wikipedia.org/wiki/Portal:Tid", 1);
        
        
        /*
        String contents = W.createDotGraph();
        String FileName = "Foo.gv";
        
        try{
        File file = new File("E:" + FileName);
        if(file.exists() == false){
        
        file.createNewFile();
        
        }
        
        
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(contents);
        bw.close();
        }catch(IOException e){
        e.printStackTrace();
        
        }
        
        
        
        */
        
        
        
    }
    
}

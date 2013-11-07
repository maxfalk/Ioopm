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
import webcrawler2.Counter;
import webcrawler2.PageRanking;
import webcrawler2.Webcrawler;
import webcrawler2.Oracle;

/**
 *
 * @author Max
 */
public class Driver {
    

    private static final String OracleFrom = "https://sv.wikipedia.org";
    private static final String OracleTo = "https://sv.wikipedia.org/wiki/Intel";
    private static final String CrawlFrom = "http://www.tv-kalendern.se/";
    private static final String Word = "det";
    
    /**
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        webCrawl();
        //runOracle();
      
        
    }
    
    private static void makeGraph(Webcrawler W)throws IOException{
        
        String FileName = "E:/test.gv";
        String Contents = W.createDotGraph(CrawlFrom);
        File file = new File(FileName);
        if(file.exists() == false){
            file.createNewFile();
        }
        
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(Contents);
        bw.close();
        
    }

    private static void webCrawl() throws IOException {
        Webcrawler W = new Webcrawler();
        
        W.Crawl(CrawlFrom,1);
        Counter<String> myCounter;
        myCounter = W.TagCloud();
        Counter<String> counterProc = myCounter.makeProcent();
       
        
        PageRanking p = W.rankPages();
        String highestRankingSite = p.getHighestRankingSite(Word);
        makeGraph(W);
      


    printTags(counterProc);
        System.out.println("Highest ranking site: " + highestRankingSite);
        
        
    }

    private static void printTags(Counter<String> count){
    
        for(int i=0;i < count.size();i++){
            System.out.println("Obj: " + count.getObject(i));
            System.out.println("Proc: " +count.getIndexNumber(i));    
        }
        
    
    }
    

    private static void runOracle() {
        Oracle oracle = new Oracle();
        oracle.Oracle(OracleFrom, OracleTo);
        System.out.println(oracle.getShortestPath());
    }

    
}

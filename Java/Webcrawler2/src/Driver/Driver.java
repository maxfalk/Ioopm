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
    

   // private static final String OracleFrom = "http://nova/index.php";
   // private static final String OracleTo = "file://///Anton/Serier 2/Being.Human.US.S02E07.The.Ties.That.Blind.HDTV.XviD-FQM.avi";
    //private static final String CrawlFrom = "http://nova/index.php";
    //private static final String Word = "webserver";
    /**
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        
        int Action = 0;
        int steps = -1;
        int depth = -1;
        String From = "";
        String To = "";
        boolean tags = false;
        boolean ranking = false;
        boolean graph = false;
        String Word = "";
        String filename = "";
        
        
        System.out.println("Running Webcrawler/oracle");
        for(int i=0; i< args.length;i++){
        
            if(args[i].equals("-webcrawl") && Action == 0) Action = 1;
            if(args[i].equals("-steps")) steps = Integer.parseInt(args[++i]);
            if(args[i].equals("-from") && From.isEmpty()) From = args[++i];
            if(args[i].equals("-tags") && tags == false) tags = true;
            if(args[i].equals("-ranking") && ranking == false) ranking = true;
            if(args[i].equals("-word") && Word.isEmpty()) Word = args[++i];
            if(args[i].equals("-depth")) depth = Integer.parseInt(args[++i]);
            if(args[i].equals("-graph") && graph == false){
                graph = true; 
                filename = args[++i];
            }
            
            if(args[i].equals("-oracle") && Action == 0) Action = 2;
            if(args[i].equals("-to") && To.isEmpty()) To = args[++i];
        
        }
        
        if(Action == 1 && From.isEmpty() == false){
            Webcrawler W = webCrawl(From, steps,depth);
            if(tags == true) TagCloud(W);     
            if(ranking == true && Word.isEmpty() == false) Ranking(W, Word);
            if(graph == true && filename.isEmpty() == false) makeGraph(W, From, filename);
            
        }else if(Action == 2 && From.isEmpty() == false && To.isEmpty() == false){
            runOracle(From,To);
        }
        
    }
    
    private static void makeGraph(Webcrawler W, String CrawlFrom, String Filename)throws IOException{
        
        String Contents = W.createDotGraph(CrawlFrom);
        File file = new File(Filename);
        if(file.exists() == false){
            file.createNewFile();
        }
        
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(Contents);
        bw.close();
        
    }

    private static Webcrawler webCrawl(String CrawlFrom, int steps, int depth) throws IOException {
        Webcrawler W = new Webcrawler();
        
        W.Crawl(CrawlFrom,steps,depth);
        

        return W;
        
    }

    private static void Ranking(Webcrawler W,String Word) {
        PageRanking p = W.rankPages();
        String highestRankingSite = p.getHighestRankingSite(Word);
        System.out.println("Highest ranking site: " + highestRankingSite);
    }

    private static void TagCloud(Webcrawler W) throws IOException {
        Counter<String> myCounter;
        myCounter = W.TagCloud();
        Counter<String> counterProc = myCounter.makeProcent();
        printTags(counterProc);
    }

    private static void printTags(Counter<String> count){
    
        for(int i=0;i < count.size();i++){
            System.out.println("Tag: " + count.getObject(i));
            System.out.println("Procent: " +count.getIndexNumber(i)+ "%");    
        }
        
    
    }
    

    private static void runOracle(String OracleFrom, String OracleTo) {
        Oracle oracle = new Oracle();
        oracle.Oracle(OracleFrom, OracleTo);
        System.out.println("Shortest path : " + oracle.getShortestPath());
    }

    
}

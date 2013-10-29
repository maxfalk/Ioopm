package webcrawler;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Max
 *
 */
public class Wordcounter {
    /**
     *
     * @author Max
     *
     */
    private class Wordcontainer implements Comparable<Wordcontainer>{
        
        private int number = 0;
        private String word = "";
        /**
         *
         */
        //--------------------------------------------------------
        @Override
        public int compareTo(Wordcontainer compareWord){
            
            if(compareWord.number > number){
                return 1;
                
            }else if(compareWord.number == number){
                return 0;
            }
            
            return -1;
            
        }
    }
    //--------------------------------------------------------
    private final LinkedList<Wordcontainer> List = new LinkedList<Wordcontainer>();
    private final LinkedList<Wordcontainer> ListWithProcent = new LinkedList<Wordcontainer>();
    private final int ListLimit = 100;
    //----------------------------------------------------------------------------------
    public void add(String Word){
        
        if(addToExistingWord(Word) == false){
            //new word
            Wordcontainer newContainer = new Wordcontainer();
            newContainer.word = Word;
            newContainer.number += 1;
            List.add(newContainer);
        }
        limitList(ListLimit);
    }
    /**
     *
     */
    //--------------------------------------------------------
    public void getListWithProcent(){
        setListWithProcent();
        printListWithProcent();
    }
    /**
     *
     */
    //--------------------------------------------------------
    private void setListWithProcent(){
        
        Iterator<Wordcontainer> it = List.listIterator();
        int total = getNumberSum();
        while(it.hasNext()){
            Wordcontainer currentContainer = (Wordcontainer)it.next();
            
            Wordcontainer newContainer = new Wordcontainer();
            newContainer.word = currentContainer.word;
            double proc = ((double)currentContainer.number/(double)total);
            proc = proc*100;
            
            newContainer.number = (int)proc;
            
            ListWithProcent.add(newContainer);
            
        }
        
    }
    /**
     *
     * @param Word
     * @return
     */
    //-----------------------------------------------------------
    private boolean addToExistingWord(String Word){
        
        Iterator<Wordcontainer> it = List.listIterator();
        while(it.hasNext()){
            Wordcontainer currentContainer = (Wordcontainer)it.next();
            String currentWord = currentContainer.word;
            if(currentWord.equals(Word)){
                currentContainer.number++;
                return true;
                
            }
            
        }
        return false;
    }
    /**
     *
     * @param LIMIT
     */
    //-----------------------------------------------------------
    private void limitList(int LIMIT){
        Collections.sort(List);
        removeAllAfter(LIMIT);
        
    }
    /**
     *
     * @param Start
     */
    //--------------------------------------------------------
    private void removeAllAfter(int Start){
        
        Iterator<Wordcontainer> it = List.listIterator();
        int at = 0;
        while(it.hasNext()){
            it.next();
            if(Start <= at){
                it.remove();
            }
            at++;
        }
        
        
    }
    /**
     *
     * @return
     */
    //--------------------------------------------------------
    private int getNumberSum(){
        Iterator<Wordcontainer> it = List.listIterator();
        int total = 0;
        while(it.hasNext()){
            Wordcontainer currentContainer = (Wordcontainer)it.next();
            total += currentContainer.number;
        }
        
        return total;
    }
    /**
     *
     */
    //--------------------------------------------------------
    public void printList(){
        
        Iterator<Wordcontainer> it = List.listIterator();
        while(it.hasNext()){
            Wordcontainer currentContainer = (Wordcontainer)it.next();
            System.out.println(currentContainer.word + " | " + currentContainer.number);
            
        }
        
    }
    /**
     *
     */
    //--------------------------------------------------------
    private void printListWithProcent(){
        
        Iterator<Wordcontainer> it = ListWithProcent.listIterator();
        while(it.hasNext()){
            Wordcontainer currentContainer = (Wordcontainer)it.next();
            System.out.println(currentContainer.word + " | " + currentContainer.number);
            
        }
        
    }
    
}

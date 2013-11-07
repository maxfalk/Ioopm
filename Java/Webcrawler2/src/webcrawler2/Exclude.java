/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Loads the specific excludes file and handles checking against those strings.
 *
 * @author Max
 *
 */
public class Exclude {
    private static final LinkedList<String> List = new LinkedList();
    /**
     * Loads the file excludes.txt at source location
     */
    public static void loadExcludes()throws NullPointerException{
        try{
            InputStream instream = Exclude.class.getResourceAsStream("exclude.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(instream));
            
            String currentLine;
            
            while((currentLine = br.readLine()) != null){
                List.add(currentLine); 
            }
            
            if(List.isEmpty() == true){
                System.err.println("Nothing found in exclude.txt");
            }
            
            br.close();
            
        }catch(IOException ignore){
            
            
        }
    }
    /**
     * Gives the list of items in the excluded list.
     * @return list of the items to be excluded.
     */
    public static LinkedList<String> getExludesAsList(){
        return List;
    }
    /**
     * Checks if a given string is in the excluded list
     * @param object the string to check for.
     * @return true if the string is excluded else false.
     */
    public static boolean checkIfExcluded(String object){
        Iterator<String> it = List.listIterator();
        
        while(it.hasNext()){
            String currentString = it.next();
            if(currentString.equals(object) == true){
                return true;
            }
            
        }
        return false;
        
    }
    
    private Exclude() {
    }
    
    
}

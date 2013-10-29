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
 *
 * @author Max
 *
 */
public class Exclude {
    private static LinkedList<String> List = new LinkedList();
    /**
     *
     * @throws IOException
     */
    public static void loadExcludes() throws IOException, NullPointerException{
        InputStream instream = Exclude.class.getResourceAsStream("exclude.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(instream));
        
        String currentLine;
        
        while((currentLine = br.readLine()) != null){
            List.add(currentLine);
            
        }
     
        br.close();
    }
    /**
     *
     * @return
     */
    public static LinkedList<String> getExludesAsList(){
        return List;
    }
    /**
     *
     * @param _match
     * @return
     */
    public static boolean checkIfExcluded(String _match){
        Iterator<String> it = List.listIterator();
        
        while(it.hasNext()){
            String currentString = it.next();
            if(currentString.equals(_match) == true){
                return true;
            }
            
        }
        return false;
        
    }
    
    
}

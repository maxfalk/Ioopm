/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DotGraph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Max
 */
public class Path {
    
    private ArrayList<String> Path = new ArrayList();
    
    /**
     *
     * @param name
     */
    public void addNode(String name){
        Path.add("\"" + name + "\"");
    }
    
    /**
     *
     * @return
     */
    public String get(){
        Iterator it = Path.listIterator();
        String result = "";
        while(it.hasNext() == true){
            String currentNode = (String) it.next();
            if(result.isEmpty() == false){
                result = result + " -> " + currentNode;  
            }else{
                result = currentNode; 
            }
        }
        result += ";";
        
        return result;
    
    }
    
    
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package DotGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Creates a path in dotgraph format.
 * @author Max
 */
public class Path {
    
    private ArrayList<String> Path = new ArrayList();
    
    /**
     * Add a node to the path
     * @param name the name of the node to be added.
     */
    public void addNode(String name){
        Path.add("\"" + name + "\"");
    }
    
    /**
     * Gives the path in a dotgraph format.
     * @return The path in dotgraph format.
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
    
    /**
     * Adds a list of nodes to the path, as a single path with the same order as the list.
     * @param list List of node to add in the path.
     */
    public void addList(List<String> list){
        
        Iterator it = list.listIterator();
        while(it.hasNext() == true){
            String currentNode = (String) it.next();
            addNode(currentNode);
            
        }
        
        
    }
    
    
}

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
public class GeneratDotGraph {
 
    /**
     *
     * @param name
     * @param list
     * @return
     */
    public static String fromList(String name, ArrayList list){
        Graph graph = new Graph(name);
        Path path = new Path();
        Iterator it = list.listIterator();
        while(it.hasNext() == true){
            String currentPath = (String) it.next();
            path.addNode(currentPath);
            
        }
        graph.add(path);
        String result = graph.get();
  
        return result;
    }
    
    
    
}

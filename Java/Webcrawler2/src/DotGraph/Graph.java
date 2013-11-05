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
public class Graph {
    
    private String start = "digraph";
    private ArrayList<Path> Paths = new ArrayList();
    private String end = "}";
    
    Graph(String name){
        this.start = this.start + " " + name + "{\n";
    }
    
    /**
     *
     * @param path
     */
    public void add(Path path){
        Paths.add(path);
    }
    
    /**
     *
     * @return
     */
    public String get(){
        Iterator it = Paths.listIterator();
        String result = start;
        while(it.hasNext() == true){
            Path currentPath = (Path) it.next();
            result += currentPath.get();
            result += "\n";
        }
        result += end + "\n";
        
        return result;

    }
    
    
    
    
    
}

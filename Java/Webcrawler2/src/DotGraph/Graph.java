/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DotGraph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Creates a dot graph.
 * @author Max
 */
public class Graph {
    
    private String start = "digraph";
    private ArrayList<Path> Paths = new ArrayList();
    private String end = "}";
    
    /**
     * Create a new graph with the name {@code name}
     * @param name the name of the graph
     */
    public Graph(String name){
        this.start = this.start + " \"" + name + "\"{\n";
    }
    
    /**
     * Add a path to the graph.
     * @param path the path to add to the graph.
     */
    public void add(Path path){
        Paths.add(path);
    }
    
    /**
     * Gives the graph as text, in dotgraph formate.
     * @return the graph in dotgraph format.
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

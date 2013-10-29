/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler;
import java.util.*;
import java.lang.Integer;
/**
 *
 * @author Max
 */
public class Ranking {
   
    private HashMap<String, Integer> Container = new HashMap<String, Integer>();
    
    public void addSite(String _site){
        Container.put(_site, 0);
    }
    
    public void addPoints(String _site, int points){
        Container.put(_site, points);
    }
    
    
}

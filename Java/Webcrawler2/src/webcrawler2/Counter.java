/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Max
 * @param <T>
 */
public class Counter<T>{
    
    private class Box implements Comparable<Box>{
        private int Number = 0;
        private T Item = null;
        
        private Box(T _item, int increment){
            this.Number = increment;
            this.Item = _item;
        }
        
        @Override
        public int compareTo(Box compareWord){
            
            if(compareWord.Number > Number){
                return 1;
                
            }else if(compareWord.Number == Number){
                return 0;
            }
            
            return -1;
            
        }
        
        
    }
    
    
    private ArrayList<Box> List = new ArrayList();
    //Kolla om item redan finns i listan
    private boolean checkForItem(T checkValue){
        Iterator it = List.listIterator();
        while(it.hasNext()){
            Box currentBox  = (Box) it.next();
            T _item = currentBox.Item;
            if(_item.equals(checkValue) ){
                return true;
            }
        }
        return false;
    }
    
    private void increaceExistingValue(T Value, int increment){
        Iterator it = List.listIterator();
        while(it.hasNext()){
            Box currentBox  = (Box) it.next();
            T _item = currentBox.Item;
            if(_item.equals(Value) ){
                currentBox.Number += increment;
                break;
            }
        }
        
    }
    
    /**
     *
     * @param _item
     * @param number
     */
    public void add(T _item, int number){
        Box newBox = new Box(_item, number);
        if(checkForItem(_item) == false){
            List.add(newBox);
        }else{
            increaceExistingValue(_item,number);
        }
        
    }
    
    //Kan hämta ut godtyckligt element

    /**
     *
     * @param Value
     * @return
     */
        public int getNumber(T Value){
        
        Iterator it = List.listIterator();
        while(it.hasNext()){
            Box currentBox  = (Box) it.next();
            T _item = currentBox.Item;
            if(_item.equals(Value) ){
                return currentBox.Number;
                
            }
        }
        return 0;
    }
    
    /**
     *
     */
    public void sort(){
        Collections.sort(List);
    }

    /**
     *
     * @param Limit
     */
    public void limit(int Limit){
        removeAllAfter(Limit);
        
    }
    //--------------------------------------------------------
    private void removeAllAfter(int Start){
        
        Iterator<Box> it = List.listIterator();
        int at = 0;
        while(it.hasNext()){
            it.next();
            if(Start <= at){
                it.remove();
            }
            at++;
        }
        
        
    }
    //--------------------------------------------------------

    /**
     *
     * @param Index
     * @return
     */
        public T getIndexItem(int Index){
        Box myBox = List.get(Index);
        return myBox.Item;
    }
    
    /**
     *
     * @param Index
     * @return
     */
    public int getIndexNumber(int Index){
        Box myBox = List.get(Index);
        return myBox.Number;
    }

    /**
     *
     * @return
     */
    public int getSize(){
        return List.size();
    }
    
    
    
}

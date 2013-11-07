
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package webcrawler2;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Stores an item and keeps track of the number of times you have added an item
 * with the same name(item name).
 *
 * @author Max
 * @param <T> the type of items you want to store in your counter
 */
public class Counter<T> {
    private final ArrayList<Box> List = new ArrayList();
    
    // Kolla om item redan finns i listan
    private boolean checkForItem(T checkValue) {
        Iterator it = List.listIterator();
        
        while (it.hasNext()) {
            Box currentBox = (Box) it.next();
            T   _item      = currentBox.object;
            
            if (_item.equals(checkValue)) {
                return true;
            }
        }
        
        return false;
    }
    
    private void increaceExistingValue(T Value, int increment) {
        Iterator it = List.listIterator();
        
        while (it.hasNext()) {
            Box currentBox = (Box) it.next();
            T   _item      = currentBox.object;
            
            if (_item.equals(Value)) {
                currentBox.Number += increment;
                
                break;
            }
        }
    }
    
    /**
     * Add a (object, number) par to the Counter.
     *
     * @param object the object you want to add
     * @param number the number you want to go with the object
     */
    public void add(T object, int number) {
        Box newBox = new Box(object, number);
        
        if (checkForItem(object) == false) {
            List.add(newBox);
        } else {
            increaceExistingValue(object, number);
        }
    }
    
    /**
     * Get the number that the input parameter object is associated with.
     * Compares using the equals method of the given object.
     * @param object the object of the number too look for.
     * @return the number that your object is associated with.
     */
    public int getNumber(T object) {
        Iterator it = List.listIterator();
        
        while (it.hasNext()) {
            Box currentBox = (Box) it.next();
            T   obj        = currentBox.object;
            
            if (obj.equals(object)) {
                return currentBox.Number;
            }
        }
        
        return 0;
    }
    
    /**
     * Sort the objects in the counter with the highest number first.
     */
    public void sort() {
        Collections.sort(List);
    }
    
    /**
     * Limits the counters objects to the input parameters amount.
     * Removes all objects at position > Limit.
     * @param Limit the maximum number of elements in the {@link counter}.
     */
    public void limit(int Limit) {
        removeAllAfter(Limit);
    }
    
    // --------------------------------------------------------
    private void removeAllAfter(int Start) {
        Iterator<Box> it = List.listIterator();
        int           at = 0;
        
        while (it.hasNext()) {
            it.next();
            
            if (Start <= at) {
                it.remove();
            }
            
            at++;
        }
    }
    
    /**
     * Get the object at index Index.
     * @param Index position of the object.
     * @return the object at position Index.
     */
    public T getObject(int Index) {
        Box myBox = List.get(Index);
        
        return myBox.object;
    }
    
    /**
     * Get the number at index Index.
     * @param Index position of the object.
     * @return the number at position Index
     */
    public int getIndexNumber(int Index) {
        Box myBox = List.get(Index);
        
        return myBox.Number;
    }
    
    /**
     * Get the size of the counter.
     * @return size of the counter
     */
    public int size() {
        return List.size();
    }

    /**
     *Make a {@link Counter} with percentages of each occurrence of the words, in the {@link Counter}  
     * @return a new counter with percentages.
     */
    public Counter makeProcent(){
        Counter count = new Counter();
        
        Iterator it = List.listIterator();
        int total = getNumberSum();
        while(it.hasNext()){
            Box currentContainer = (Box)it.next();
            
            double proc = ((double)currentContainer.Number/(double)total);
            int procInt = (int)(proc*100);
            
            Box newContainer = new Box(currentContainer.object, procInt);

            count.List.add(newContainer);         
            
        }
           
        
        return count;
    }
    
    private int getNumberSum(){
        Iterator<Box> it = List.listIterator();
        int total = 0;
        while(it.hasNext()){
            Box currentContainer = (Box)it.next();
            total += currentContainer.Number;
        }
        
        return total;
    }
    private class Box implements Comparable<Box> {
        private int Number = 0;
        private T   object = null;
        
        private Box(T obj, int increment) {
            this.Number = increment;
            this.object = obj;
        }
        
        @Override
        public int compareTo(Box compareWord) {
            if (compareWord.Number > Number) {
                return 1;
            } else if (compareWord.Number == Number) {
                return 0;
            }
            
            return -1;
        }
    }
}




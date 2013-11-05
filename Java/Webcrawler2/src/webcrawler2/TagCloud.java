/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.util.concurrent.RecursiveAction;
import static webcrawler2.Exclude.checkIfExcluded;

/**
 *
 * @author Max
 */
public class TagCloud extends RecursiveAction{
    
    private final Counter mycounter;
    private final PageContainer pageCon;
    private final int start;
    private final int end;
    private final int LIMIT = 5;
    
    TagCloud(PageContainer data,Counter mycounter,int start,int end){ 
        this.mycounter = mycounter;
        this.pageCon = data;
        this.start = start;
        this.end = end;
        
    }
   
    private void computeDirectly(){
        //Counter Words in a page
        for(int i = start; i <end; i++){
            String page = this.pageCon.getContents(i);
            ParseHTML parse = new ParseHTML(page);
            
            for(String Word : parse.getLinkTitle()){
                if(checkIfExcluded(Word) == false){
                    mycounter.add(Word, 1);
                }
            }
        }
        
    }
    
    
    @Override
    protected void compute(){
        int length = start - end;
        if(length <= LIMIT){
            computeDirectly();
        }else{
            int mid = (start+end)/2;
            
            TagCloud left = new TagCloud(pageCon,mycounter,start,mid);
            TagCloud right = new TagCloud(pageCon,mycounter,mid,end);
            invokeAll(left,right);
  
          
            
        }
        
        
    }
    
   
}

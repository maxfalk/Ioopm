/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

import java.util.List;

/**
 * Ranks a html text by specific attributes and stores a index of it.
 * @author Max
 */
public class Ranking{
    
    private Counter<String> Rankings = new Counter();
    
    //söka igenom en text och se förekomster av all t i denna text
    //Gå igenom strängen leta efter t, varje t ger 1p
    private void givePointsPerWord(String Text){
        ParseHTML parse = new ParseHTML(Text);
        String text = parse.getRemoveHTML();
        String[] Words = text.split(" ");
        int NumberOfPoints = 1;
        for(String Word : Words){
            Rankings.add(Word, NumberOfPoints);
        }
        
    }
    
    //Söka igenom en text och se förkomster av t i <title></title>
    //ParseHTML för title ger 4p
    private void givePointsForTitle(String Text){
        ParseHTML parse = new ParseHTML(Text);
        String title = parse.getTitle();
        int NumberOfPoints = 4;
        Rankings.add(title, NumberOfPoints);
        
    }
    //Söka igenom texten och leta efter förekomster av t i länkar
    //PaseHTML för länkar varje länk ger 5p
    private void givePointPerLinkTitle(String Text){
        ParseHTML parse = new ParseHTML(Text);
        List<String> linkTitle = parse.getLinkTitle();
        int NumberOfPoints = 5;
        for(int i = 0; i< linkTitle.size();i++){
            String title = linkTitle.get(i);
            Rankings.add(title, NumberOfPoints);
        }
        
    }
    
    /**
     * Rank the html text in Text according to specific rules.
     * @param Text the text to be ranked.
     */
    public void rank(String Text){
        givePointsPerWord(Text);
        givePointsForTitle(Text);
        givePointPerLinkTitle(Text);
    }
    
    /**
     * Gives the rank of a specific word in the ranked text.
     * @param Word the word to lookup.
     * @return the number of points for the word.
     */
    public int getRank(String Word){
        return Rankings.getNumber(Word);
    }
    //kunna hitta den högst rankade
    
    
    
    
    
    
}

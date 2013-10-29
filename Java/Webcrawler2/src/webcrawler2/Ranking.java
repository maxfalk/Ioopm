/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;

/**
 *
 * @author Max
 */
public class Ranking{
    
    //Spara rank i en arraylista
    //kan sortera lätt
    //Kan letareda i listan lätt
    Counter<String> Rankings = new Counter();
    
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
        
        int NumberOfPoints = 5;
        for(String title : parse.getLinkTitle()){    
            Rankings.add(title, NumberOfPoints);
        }
        
    }
    
    /**
     *
     * @param Text
     */
    public void rank(String Text){
        givePointsPerWord(Text);
        givePointsForTitle(Text);
        givePointPerLinkTitle(Text);
    }
    //kunna slå upp ranking för en sida.

    /**
     *
     * @param Word
     * @return
     */
        public int getRank(String Word){
        return Rankings.getNumber(Word);
    }
    //kunna hitta den högst rankade
    
    
    
    
    
    
}

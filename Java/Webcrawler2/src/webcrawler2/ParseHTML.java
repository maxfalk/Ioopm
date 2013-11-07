/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;


import java.util.LinkedList;
import java.util.List;
import static org.jsoup.Jsoup.parse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Parse a string of html text.
 *
 * @author Max
 */
public class ParseHTML {
    
    private Document parse = null;
    
    /**
     *Create a parse for HTML without a baseurl but with the page contents
     * @param contents the web page's contents
     */
    public ParseHTML(String contents){
        parse = parse(contents);
     
        
    }
    
    /**
     *Create a parse for HTML with a baseurl but with the page contents
     * @param contents the contents of the web page
     * @param baseurl the baseurl of the web page.
     */
    public ParseHTML(String contents,String baseurl){
        parse = parse(contents, baseurl);
     
        
    }
    
    /**
     *Get all the urls from links from the contents.
     * @return a list of all the links in the web page.
     */
    public List<String> getLink(){
        final Elements links = parse.select("a[href]");
        List<String> list = new LinkedList();
        for(Element e : links){
             list.add(e.attr("abs:href"));
        } 
       
        return list;

    }
    
    /**
     * Get the title of the web page(
     * @return the title of the web page.
     */
    public String getTitle(){
        return parse.title();
    }
        
    /**
     * Get all the link titles from the web page.
     * @return All the link titles.
     */
    public List<String> getLinkTitle(){
        final Elements links = parse.select("a");
        List<String> list = new LinkedList();
        for(Element e : links){
             list.add(e.text());
        } 
       
        return list;

    }
    
    /**
     * Get all the text in the web page without the html tags
     * @return All the text in the web page, without the html tags.
     */
    public String getRemoveHTML(){
        return parse.text();
    }
    
}

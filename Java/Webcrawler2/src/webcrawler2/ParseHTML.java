/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package webcrawler2;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Max
 */
public class ParseHTML {
    
    private Document parse = null;
    
    /**
     *
     * @param _contents
     */
    public ParseHTML(String _contents){
        parse = Jsoup.parse(_contents);
     
        
    }
    
    /**
     *
     * @param _contents
     * @param baseurl
     */
    public ParseHTML(String _contents,String baseurl){
        parse = Jsoup.parse(_contents, baseurl);
     
        
    }
    
    /**
     *
     * @return
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
     *
     * @return
     */
    public String getTitle(){
    return parse.title();
    
    }
        
    /**
     *
     * @return
     */
    public List<String> getLinkTitle(){
        final Elements links = parse.select("a[href]");
        List<String> list = new LinkedList();
        for(Element e : links){
             list.add(e.text());
        } 
       
        return list;

    }
    
    /**
     *
     * @return
     */
    public String getRemoveHTML(){
        return parse.text();
    }
    
}

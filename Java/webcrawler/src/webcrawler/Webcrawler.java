package webcrawler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Webcrawler{
    
    private PageContainer container = null;
    private Wordcounter counter = new Wordcounter();
    private int depth = 0;
    private int currentDepth = 0;
    private String bottomSite = null;
    
    public Webcrawler(String _bottomSite, int _depth){
        
        
        container = new PageContainer();
        bottomSite = _bottomSite;
        
        URL url = Utility.urlify(bottomSite);
        String pageContains = Utility.readURL(url);
        getAllStingsRegExp(pageContains,container);
        
        depth = _depth;
        
        
    }
    
    private Webcrawler(String _bottomSite, int _depth, PageContainer _container){
        
        container = _container;
        depth = _depth;
        bottomSite = _bottomSite;
    }
    
    private String getStingRegExp(Matcher ContainsMatcher){
        String Result = null;
        if(ContainsMatcher.find()){
            Result = ContainsMatcher.group(1);
        }
        return Result;
    }
    private void getAllStingsRegExp(String pageContents, PageContainer container){
        //Title pattern
        Pattern Link = Pattern.compile("<a href=\"(/[a-zA-Z0-9].*?)\"",Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher linkMatcher = Link.matcher(pageContents);
        //Link pattern
        Pattern TITLE = Pattern.compile("<title>(.*?)</title>",Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher titleMatcher = TITLE.matcher(pageContents);
        //Link Title
        Pattern linkTitle = Pattern.compile(">([a-z0-9][a-z0-9]*?)</a>",Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher linkTitleMatcher = linkTitle.matcher(pageContents);
        
        
        String TitleResult = getStingRegExp(titleMatcher);
        String LinkResult = getStingRegExp(linkMatcher);
        String linkTitleResult = getStingRegExp(linkTitleMatcher);
        
        while(LinkResult != null){
            container.addLink(LinkResult,linkTitleResult,TitleResult,pageContents);
            LinkResult = getStingRegExp(linkMatcher);
            linkTitleResult = getStingRegExp(linkTitleMatcher);
        }
        
        
    }
    private void addCurrDepth(){
        currentDepth = currentDepth+1;
    }
    private void addRemaningLinkTitles(){
    
        while(container.isEmpty() == false){
        
           //add linkstitle to our worcounter
           String LinkTitle = container.getLinkTitle();
           if(Exclude.checkIfExcluded(LinkTitle) == false){
            counter.add(LinkTitle); 
            
           }
            container.getLinkAndPop();
        }
    
    }
    
    private String compute() {
        
        String resultVisited = "";
        
        
        for(;container.isEmpty() == false && currentDepth < depth ;){
            
            String newSite = bottomSite + container.getLinkAndPop();
            
            if(container.checkVisited(newSite) == false){
                //add counter
                addCurrDepth();

                //get new site
                URL url = Utility.urlify(newSite);
                String pageContains = Utility.readURL(url);
                getAllStingsRegExp(pageContains,container);
                //add linkstitle to our worcounter
                String LinkTitle = container.getLinkTitle();
                if(Exclude.checkIfExcluded(LinkTitle) == false){
                    System.out.println("Linktitle: " + LinkTitle);
                    counter.add(LinkTitle);      
                 }
                
                container.addVisited(newSite);
                
                
                resultVisited += "Visited: " + newSite + "\n";
                
            }
            
            
        }
        
        return resultVisited;
        
        
        
    }
    
    public void run(){
        try{
            Exclude.loadExcludes();
        }catch(Exception e){
            e.printStackTrace(System.err);;
        }
        
        
        String result = compute();
        addRemaningLinkTitles();
        System.out.println(result);
        counter.printList();
        
        
    }
    
    
}

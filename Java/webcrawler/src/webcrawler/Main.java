package webcrawler;



import java.util.regex.Pattern;
/**
 * 
 * @author Max
 *
 */
public class Main {


	public static void main(String[] args) {
                //countwords
                //Pattern Link = Pattern.compile(">([a-z0-9][a-z0-9]*?)</a>",Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
                // myCounter.countwords(pageContains,Link);
		//Pattern Link = Pattern.compile(">([a-z0-9][a-z0-9]*?)</a>",Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);	
		Webcrawler crawl = new Webcrawler("https://sv.wikipedia.org", 20);
		
		crawl.run();

	}

}

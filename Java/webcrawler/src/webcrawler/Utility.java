package webcrawler;

import java.net.*;
import java.io.*;
/**
 * 
 * @author Max
 *
 */
public class Utility {
   /**
    * 
    * @param s
    * @return
    */
	
	public static URL urlify(String s) {
        // Crazy ugly coding
        try { return new URL(s); } catch (Exception e) {}
        return null;
    }
	/**
	 * 
	 * @param url
	 * @return
	 */
    public static String readURL(URL url) {
        String inputLine;
        StringBuffer result = new StringBuffer();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            
            while ((inputLine = in.readLine()) != null)
                result.append(inputLine);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace(System.err);
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        } finally {
            try { if (in != null) in.close(); } catch (Exception ignore) {}
        }
        
        return result.toString();
    }
    
    
    
    
}
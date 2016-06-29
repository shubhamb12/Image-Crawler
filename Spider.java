import java.io.IOException;
import java.util.*;
public class Spider {
	  // Fields
    private static final int MAX_PAGES_TO_SEARCH = 100000;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
	private String kurl;
    private String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }
    public void search(String url, String searchWord) throws IOException
    {
    	
    	while(this.pagesToVisit.size()<MAX_PAGES_TO_SEARCH)
    	{
    		String currentUrl;
    		SpiderLeg leg= new SpiderLeg();
    		DownloadImages img=new DownloadImages();
    		if(this.pagesToVisit.isEmpty())
    		{
    			currentUrl= url;
    			this.pagesVisited.add(url);
    		}
    		else 
    		{
    			currentUrl= this.nextUrl();
    		}
    		System.out.println(currentUrl);
    		leg.crawl(currentUrl);
    		img.images(currentUrl,searchWord);
    		boolean success=leg.searchForWord(searchWord);
    		if(success)
    		{
    			 System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                
    		}
    		this.pagesToVisit.addAll(leg.getLinks());
    		
    	}
    	 System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
    }
    
}

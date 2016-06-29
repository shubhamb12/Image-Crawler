
public class SpiderTest
{
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)throws Exception
    {
        Spider spider = new Spider();
        spider.search("http://www.santabanta.com", "jpg");
    }
}
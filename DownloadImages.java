import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class DownloadImages {

  //The path of the folder that you want to save the images to
    private static final String folderPath = "D:\\images\\";

    public void images(String webSiteUrl,String search) {

        try {

            //Connect to the website and get the html
            Document doc = Jsoup.connect(webSiteUrl).get();

            //Get all elements with img tag ,
            Elements img = doc.getElementsByTag("img");

            for (Element el : img) {

                //for each element get the srs url
                String src = el.absUrl("src");

                //System.out.println("Image Found!");
               //System.out.println("src attribute is : "+src);

                getImages(src,search);

            }

        } catch (IOException ex) {
            System.err.println("There was an error");
            Logger.getLogger(DownloadImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void getImages(String src,String search) throws IOException {

        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");
        if(indexname<0)
        	return;
        if (indexname == src.length()) {
            src = src.substring(0, indexname-1);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());
        if(name.toLowerCase().contains(search.toLowerCase())){

        
        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream( folderPath+ name));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();
        }

    }
}
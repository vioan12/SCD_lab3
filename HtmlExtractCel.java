import com.sun.org.apache.xerces.internal.dom.ChildNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by ioan on 10/31/17.
 */
public class HtmlExtractCel {
    private String HTML1 = "";
    private URL url;

    public void Set_url(String valueofurl)
    {
        try {
            url = new URL(valueofurl);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void extract_all_products()
    {
        try {
            URLConnection spoof;
            //Spoof the connection so we look like a web browser
            BufferedReader in;
            String strLine;
            Document doc;
            Elements element;
            spoof = url.openConnection();
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            HTML1 = "";
            HTML1.replaceAll("[^a-zA-Z0-9 ]", "");
            strLine = "";
            //Loop through every line in the source
            while ((strLine = in.readLine()) != null) {
                HTML1 += strLine;
            }
            doc = Jsoup.parse(HTML1);
            element= doc.select("div[cat_nam]");
            for(int i=0;i<element.size();i++)
                product_special_attributes(element.eq(i));
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void product_special_attributes(Elements product)
    {
        try {
            Elements element;
            int id, pret;
            String nume, url,temp,temp2;

            //pret
            element = product.select("b[itemprop]");
            pret=Integer.parseInt(element.select("b").first().text());

            //id
            element= product.select("div.stoc_list");
            temp=element.select("span").attr("id").toString();
            temp2="";
            for(int i=1;temp.charAt(i)!='-';i++)
                temp2=temp2+temp.charAt(i);
            id=Integer.parseInt(temp2);

            //url si nume
            element=product.select("h4.productTitle");

            //url
            url=element.select("a").attr("href").toString();

            //nume
            nume=element.select("span").first().text();

            System.out.println(id);
            System.out.println(nume);
            System.out.println(pret+" lei");
            System.out.println(url);
            System.out.println("");

            //Add function

        } catch (Exception e) {
            System.err.println(e);
        }
    }

}

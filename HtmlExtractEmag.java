import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ioan on 11/5/17.
 */
public class HtmlExtractEmag {
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

    public Elements load_page(String Interrogation)
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
            element= doc.select(Interrogation);
            return element;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    public void extract_all_products()
    {
        Elements element;
        element=load_page("div.card-item.js-product-data");
        for(int i=0;i<element.size();i++)
            product_special_attributes(element.eq(i));

    }

    public void product_special_attributes(Elements product)
    {
        try {
            Elements element;
            int sw;
            float pret;
            String nume, url, id, temp, temp2;
            HtmlExtractEmag Temp;

            //pret
            element = product.select("p.product-new-price");
            temp=element.select("p").first().text();
            temp2="";
            for(int i=0;temp.charAt(i)!=' ';i++)
                if(temp.charAt(i)!='.')
                    temp2=temp2+temp.charAt(i);
            sw=0;
            for(int i=0;i<temp2.length() && sw==0;i++){
                if(temp2.charAt(i)<48)
                    sw=1;
                if(temp2.charAt(i)>57)
                    sw=1;
            }
            if(sw==0) {
                pret = Float.valueOf(temp2);
                pret = pret / 100;
            }else {
                pret=-1;
            }

            //url si nume
            element=product.select("h2.card-body.product-title-zone");

            //url
            url=element.select("a").attr("href").toString();

            //nume
            nume=element.select("a").first().text();

            //id
            Temp= new HtmlExtractEmag();
            Temp.Set_url(url);
            element=Temp.load_page("span.product-code-display.pull-left");
            temp=element.select("span").first().text();
            temp2="";
            int i=0;
            while (temp.charAt(i)!=':'){
                i++;
            }
            i++;
            while (i<temp.length()-1){
                i++;
                temp2=temp2+temp.charAt(i);
            }
            id=temp2;
            id=id.toUpperCase();

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

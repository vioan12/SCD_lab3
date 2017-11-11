import org.jsoup.select.Elements;

import java.util.concurrent.locks.Lock;
/**
 * Created by ioan on 10/21/17.
 */

public class MyThread extends Thread
{
    HtmlExtractCel HECel;
    HtmlExtractEmag HEEmag;
    private String cel_url;
    private String emag_url;
    MyThread(String value_cel, String value_emag)
    {
        HECel = new HtmlExtractCel();
        HEEmag = new HtmlExtractEmag();
        cel_url=value_cel;
        emag_url=value_emag;
        if(value_cel!=null)
            HECel.Set_url(cel_url);
        if(value_emag!=null)
            HEEmag.Set_url(emag_url);
    }


    public void run()
    {
        try {

            if(cel_url!=null) {
                sleep(1000);
                HECel.extract_all_products();
            }
            if(emag_url!=null) {
                sleep(1000);
                HEEmag.extract_all_products();
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

import org.jsoup.select.Elements;

import java.util.Vector;
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
    private Vector<Casca> ListaCasti_Emag,ListaCasti_Cel;
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

            int sw;

            if(cel_url!=null) {
                sleep(2000);
                ListaCasti_Cel=HECel.extract_all_products();
            }

            if(emag_url!=null) {
                sleep(2000);
                ListaCasti_Emag=HEEmag.extract_all_products();
            }


            for(int i=0;i<ListaCasti_Cel.size();i++) {
                sw=0;
                for(int j=0;j<MainThread.ListaCasti.size();j++)
                    if(ListaCasti_Cel.elementAt(i).Get_id().equals(MainThread.ListaCasti.elementAt(j).Get_id())) {
                        MainThread.ListaCasti.elementAt(j).Add(ListaCasti_Cel.elementAt(i).Get_id(), ListaCasti_Cel.elementAt(i).Get_nume(), ListaCasti_Cel.elementAt(i).Get_pret1(), ListaCasti_Cel.elementAt(i).Get_url1());
                        sw = 1;
                    }
                if(sw==0)
                    MainThread.ListaCasti.add(ListaCasti_Cel.elementAt(i));
            }

            for(int i=0;i<ListaCasti_Emag.size();i++) {
                sw=0;
                for(int j=0;j<MainThread.ListaCasti.size();j++)
                    if(ListaCasti_Emag.elementAt(i).Get_id().equals(MainThread.ListaCasti.elementAt(j).Get_id())) {
                        MainThread.ListaCasti.elementAt(j).Add(ListaCasti_Emag.elementAt(i).Get_id(), ListaCasti_Emag.elementAt(i).Get_nume(), ListaCasti_Emag.elementAt(i).Get_pret1(), ListaCasti_Emag.elementAt(i).Get_url1());
                        sw = 1;
                    }
                if(sw==0)
                    MainThread.ListaCasti.add(ListaCasti_Emag.elementAt(i));
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

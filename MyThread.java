import org.jsoup.select.Elements;

import java.util.Vector;
import java.util.concurrent.locks.Lock;
/**
 * Created by ioan on 10/21/17.
 */

public class MyThread extends Thread
{
    private HtmlExtractCel HECel;
    private HtmlExtractEmag HEEmag;
    private String cel_url;
    private String emag_url;
    private Vector<Casca> SubListaCasti_Emag,SubListaCasti_Cel;
    private int mythread_id;
    private static boolean flag[] = new boolean[MainThread.NR_OF_THREADS];
    private static int turn;
    MyThread(String value_cel, String value_emag, Integer value_mythreadid)
    {
        HECel = new HtmlExtractCel();
        HEEmag = new HtmlExtractEmag();
        cel_url=value_cel;
        emag_url=value_emag;
        mythread_id=value_mythreadid;
        if(value_cel!=null)
            HECel.Set_url(cel_url);
        if(value_emag!=null)
            HEEmag.Set_url(emag_url);
    }


    public void run()
    {
        try {
            SubListaCasti_Cel=new Vector<Casca>();
            if(cel_url!=null) {
                sleep(2000);
                SubListaCasti_Cel=HECel.extract_all_products();
            }

            SubListaCasti_Emag=new Vector<Casca>();
            if(emag_url!=null) {
                sleep(2000);
                SubListaCasti_Emag=HEEmag.extract_all_products();
            }

            flag[mythread_id]=true;
            for(int j=0;j<MainThread.NR_OF_THREADS;j++){
                if(j!=mythread_id) {
                    turn =j;
                    while ((flag[j]==true) && (turn==j)){
                    }
                }
            }

            //Sectiune critica - BEGIN
            for(int i=0;i<SubListaCasti_Cel.size();i++) {
                int sw=0;
                for(int j=0;j<MainThread.ListaCasti.size();j++)
                    if(SubListaCasti_Cel.elementAt(i).Get_id().equals(MainThread.ListaCasti.elementAt(j).Get_id())) {
                        MainThread.ListaCasti.elementAt(j).Add(SubListaCasti_Cel.elementAt(i).Get_id(), SubListaCasti_Cel.elementAt(i).Get_nume(), SubListaCasti_Cel.elementAt(i).Get_pret1(), SubListaCasti_Cel.elementAt(i).Get_url1());
                        sw = 1;
                    }
                if(sw==0)
                    MainThread.ListaCasti.add(SubListaCasti_Cel.elementAt(i));
            }
            for(int i=0;i<SubListaCasti_Emag.size();i++) {
                int sw=0;
                for(int j=0;j<MainThread.ListaCasti.size();j++)
                    if(SubListaCasti_Emag.elementAt(i).Get_id().equals(MainThread.ListaCasti.elementAt(j).Get_id())) {
                        MainThread.ListaCasti.elementAt(j).Add(SubListaCasti_Emag.elementAt(i).Get_id(), SubListaCasti_Emag.elementAt(i).Get_nume(), SubListaCasti_Emag.elementAt(i).Get_pret1(), SubListaCasti_Emag.elementAt(i).Get_url1());
                        sw = 1;
                    }
                if(sw==0)
                    MainThread.ListaCasti.add(SubListaCasti_Emag.elementAt(i));
            }
            flag[mythread_id]=false;
            //Sectiune critica - END

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

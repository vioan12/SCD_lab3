/**
 * Created by ioan on 11/6/17.
 */
public class MainThread extends Thread {

    private static boolean done = false;
    private HtmlExtractCel HEC;
    private HtmlExtractEmag HEE;

    MainThread()
    {
        HEC = new HtmlExtractCel();
        HEE= new HtmlExtractEmag();
    }

    public void run()
    {
        if (done == false) {

            for(int i=1;i<=24;i++) {
                HEC.Set_url("http://www.cel.ro/casti/0a-"+i);
                HEC.extract_all_products();
                try {
                    this.sleep(30000);
                } catch (Exception e) {
                    System.err.println(e);
                }

            }


            for(int i=1;i<=11;i++) {
                HEE.Set_url("https://www.emag.ro/casti-pc/p"+i+"/c");
                HEE.extract_all_products();
                try {
                    this.sleep(30000);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            setDone();
        }
    }

    public void setDone()
    {
        done = true;
    }
}

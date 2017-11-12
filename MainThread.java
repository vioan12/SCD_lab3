import org.jsoup.select.Elements;

import java.util.Vector;

/**
 * Created by ioan on 11/6/17.
 */
public class MainThread extends Thread {

    private static boolean done = false;
    public static Vector<Casca> ListaCasti = new Vector<Casca>();

    private Thread[] thread;
    MainThread()
    {
        thread = new Thread[1];
    }

    public void run()
    {
        if (done == false){
            try {

                for (int i = 0; i < thread.length; i++) {
                    if(i>10 && i<25)
                        thread[i] = new MyThread("http://www.cel.ro/casti/0a-"+(i+1),null);
                    if(i>=25)
                        thread[i] = new MyThread(null,null);
                    if(i<=10)
                        thread[i] = new MyThread("http://www.cel.ro/casti/0a-"+(i+1),"https://www.emag.ro/casti-pc/p"+(i+1)+"/c");
                }
                for (int i = 0; i < thread.length; i++) {
                    thread[i].start();
                }
                for(int i = 0; i < thread.length; i++){
                    thread[i].join();
                }

            } catch (Exception e) {
                    System.err.println(e);
            }

            setDone();
        }
    }

    public void setDone()
    {
        done = true;
    }
}

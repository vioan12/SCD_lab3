import org.jsoup.select.Elements;

import java.util.Vector;

/**
 * Created by ioan on 11/6/17.
 */
public class MainThread extends Thread {

    private static boolean done = false;
    public static Vector<Casca> ListaCasti = new Vector<Casca>();
    public static final int NR_OF_THREADS=121;

    private Thread[] thread;
    MainThread()
    {
        thread = new Thread[NR_OF_THREADS];
    }

    public void run()
    {
        if (done == false){
            try {

                for (int i = 0; i < thread.length; i++) {
                    if(i>10 && i<25)
                        thread[i] = new MyThread("http://www.cel.ro/casti/0a-"+(i+1),null,i);
                    if(i>=25)
                        thread[i] = new MyThread(null,null,i);
                    if(i<=10)
                        thread[i] = new MyThread("http://www.cel.ro/casti/0a-"+(i+1),"https://www.emag.ro/casti-pc/p"+(i+1)+"/c",i);
                }
                for (int i = 0; i < thread.length; i++) {
                    thread[i].start();
                }
                for(int i = 0; i < thread.length; i++){
                    thread[i].join();
                }
                for (int i=0;i<ListaCasti.size();i++){
                    System.out.println("");
                    System.out.println(ListaCasti.elementAt(i).Get_id());
                    System.out.println(ListaCasti.elementAt(i).Get_nume());
                    if(ListaCasti.elementAt(i).Get_n()==2){
                        if(ListaCasti.elementAt(i).Get_pret1()<ListaCasti.elementAt(i).Get_pret2()){
                            System.out.println("Pretul cel mai bun: ");
                            System.out.print(ListaCasti.elementAt(i).Get_pret1());
                            System.out.println(ListaCasti.elementAt(i).Get_url1());
                        } else {
                            if(ListaCasti.elementAt(i).Get_pret2()<ListaCasti.elementAt(i).Get_pret1()) {
                                System.out.println("Pretul cel mai bun: ");
                                System.out.print(ListaCasti.elementAt(i).Get_pret2());
                                System.out.println(ListaCasti.elementAt(i).Get_url2());
                            } else {
                                System.out.println("Preturi egale: ");
                                System.out.print(ListaCasti.elementAt(i).Get_pret1());
                                System.out.println(ListaCasti.elementAt(i).Get_url1());
                                System.out.println(ListaCasti.elementAt(i).Get_url2());
                            }
                        }
                    } else {
                        System.out.println(ListaCasti.elementAt(i).Get_pret1());
                        System.out.println(ListaCasti.elementAt(i).Get_url1());
                    }

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

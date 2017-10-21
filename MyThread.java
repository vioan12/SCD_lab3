import java.util.concurrent.locks.Lock;
/**
 * Created by ioan on 10/21/17.
 */

public class MyThread extends Thread
{
    private Lock mutex = new LockDekker();
    private static boolean done = false;
    private String text;
    MyThread(String value)
    {
        text=value;
    }


    public void run()
    {
        if (done == false) {
            mutex.lock();
            for(int i=0;i<20;i++)
                System.out.println(text);
            mutex.lock();
            setDone();
        }
    }

    public void setDone()
    {
        done = true;
    }

}

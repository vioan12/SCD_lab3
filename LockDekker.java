import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by ioan on 10/21/17.
 */

public class LockDekker implements Lock
{
    private volatile boolean[] want = {false, false};
    private volatile int turn = 0;

    public void lock()
    {
        int i = ThreadID.get();
        int j = 1 - i;
        want[i] = true;
        while (want[j]) {
            if (turn == j) {
                want[i] = false;
                while (turn != i) {
                }
                want[i] = true;
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock()
    {
        int i = ThreadID.get();
        turn = 1-i;
        want[i] = false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
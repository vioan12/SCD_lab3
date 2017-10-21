public class Main {

    private static Thread[] thread=new Thread[2];
    public static void main(String[] args) {
        thread[0]=new MyThread("x");
        thread[1]=new MyThread("y");
        for(int i=0;i< thread.length;i++)
            thread[i].start();
    }
}

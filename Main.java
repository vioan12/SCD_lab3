public class Main {

    private static Thread[] thread=new Thread[1];
    public static void main(String[] args) {

        try {
            for (int i = 0; i < thread.length; i++) {
                thread[i] = new MainThread();
            }
            for (int i = 0; i < thread.length; i++) {
                thread[i].start();
            }
            for (int i = 0; i < thread.length; i++) {
                thread[i].join();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

public class NoVisibility {
    private static boolean ready;
    private static int number;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            new ReaderThread().start();
        }
        ready = true;
        Thread.sleep(1);
        number = 42;
    }

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) Thread.yield();
            System.out.println(number);
        }
    }
}

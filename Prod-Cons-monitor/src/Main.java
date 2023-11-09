public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10); // Shared buffer with a maximum capacity

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}

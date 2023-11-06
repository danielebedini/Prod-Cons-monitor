public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Creiamo un buffer con una capacità massima di 5

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}

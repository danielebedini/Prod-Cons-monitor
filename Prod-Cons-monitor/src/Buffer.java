class Buffer {
    private int[] data;
    private int size;
    private int count;

    public Buffer(int capacity) {
        data = new int[capacity];
        size = capacity;
        count = 0;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (count == size) {
            // if the buffer is full, then producer waits
            wait();
        }

        data[count] = item;
        count++;
        System.out.println("Produced: " + item);
        notify(); // wakes up a waiting thread (consumer)
    }
    
    public synchronized int consume() throws InterruptedException {
        while (count == 0) {
            // if the buffer is empty, then consumer waits
            wait();
        }

        int item = data[count - 1];
        count--;
        System.out.println("Consumed: " + item);
        notify(); // wakes up a waiting thread (producer)
        return item;
    }
}
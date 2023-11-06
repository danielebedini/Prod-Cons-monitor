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
            // Il buffer è pieno, il produttore attende
            wait();
        }

        data[count] = item;
        count++;
        System.out.println("Produced: " + item);
        notify(); // Sveglia un thread in attesa (consumatore)
    }
    
    public synchronized int consume() throws InterruptedException {
        while (count == 0) {
            // Il buffer è vuoto, il consumatore attende
            wait();
        }

        int item = data[count - 1];
        count--;
        System.out.println("Consumed: " + item);
        notify(); // Sveglia un thread in attesa (produttore)
        return item;
    }
}
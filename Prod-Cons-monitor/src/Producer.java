import java.util.Random;

class Producer implements Runnable {
    private Buffer buffer;
    int min = 10;
    int max = 1000;
    Random random = new Random();

    int randomNumber = random.nextInt((max - min) + 1) + min;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(randomNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


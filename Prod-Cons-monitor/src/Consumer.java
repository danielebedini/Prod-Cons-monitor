import java.util.Random;

class Consumer implements Runnable {
    private Buffer buffer;
    int min = 500;
    int max = 3000;
    Random random = new Random();

    int randomNumber = random.nextInt((max - min) + 1) + min;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int item = buffer.consume();
                Thread.sleep(randomNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


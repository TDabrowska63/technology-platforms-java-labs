import java.io.FileWriter;
import java.io.IOException;

public class Task implements Runnable{

    private SharedQueue queue;
    private String file_to_save;
    private Object monitor = new Object();

    public Task(SharedQueue queue, String file_to_save) {
        this.queue = queue;
        this.file_to_save = file_to_save;
    }

    @Override
    public void run(){
        while (!Thread.interrupted()) {
            try {
                Long number = queue.take();
                String divisors = findDivisors(number);
                synchronized (monitor) {
                    try (FileWriter writer = new FileWriter(file_to_save, true)) {
                        writer.write(number + ": " + divisors + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String findDivisors(Long number) {
        StringBuilder sb = new StringBuilder();
        for (Long i = 1L; i <= number; i++) {
            if (number % i == 0) {
                sb.append(i).append(", ");
            }
            // simulate some computational complexity
//            try {
//                //Thread.sleep(1);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
        // remove the last comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}

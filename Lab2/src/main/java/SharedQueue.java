import java.util.LinkedList;
import java.util.List;

public class SharedQueue {
    private List<Long> big_numbers = new LinkedList<Long>();

    public synchronized void put(List<Long> numbers) {
        this.big_numbers.addAll(numbers);
        notifyAll();
    }

    public synchronized Long take() throws InterruptedException {
        while (big_numbers.isEmpty()) {
            wait();
        }
        return big_numbers.remove(0);
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        //reading from file
        FileHandler file = new FileHandler();
        List<Long> big_numbers = file.readFile("liczby.txt");

        //new shared queue
        SharedQueue queue = new SharedQueue();

        //file form threads to save output in
        String file_to_save = "dzielniki.txt";
        try (FileWriter writer = new FileWriter(file_to_save, false)) {
            // not writing anything to the file its just clearing
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating threads
        String num_of_threads = args[0];
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < Integer.parseInt(num_of_threads); i+=1) {
            threads.add(new Thread(new Task(queue, file_to_save)));
            threads.get(i).start();
        }

        //main loop of the program, checking if there are new numbers to add
        // or if program needs to be terminated
        Scanner myObj = new Scanner(System.in);
        String status = "running";
        while(!Objects.equals(status, "exit")){
            if (Objects.equals(status, "add")) {
                System.out.println("Type new number to add: ");
                String new_number = myObj.nextLine();
                big_numbers.add(Long.parseLong(new_number));
            }
            try {
                if(!big_numbers.isEmpty()){
                    System.out.println("New big numbers added");
                    queue.put(big_numbers);
                    big_numbers.clear();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                System.out.println("Failed to add to queue");
            }
            status = myObj.nextLine();
        }
        //interrupting all threads after reading exit command
        for (Thread thread : threads) {
            thread.interrupt();
        }
        System.exit(0);
    }
}

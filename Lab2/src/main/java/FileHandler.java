import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public List<Long> readFile(String file_name){
        List<Long> big_numbers = new ArrayList<>();
        try {
            File myObj = new File(file_name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String number = myReader.nextLine();
                big_numbers.add(Long.parseLong(number));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return big_numbers;
    }

}

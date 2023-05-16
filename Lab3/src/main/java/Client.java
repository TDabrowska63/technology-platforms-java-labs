import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Socket clientSock;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Scanner sc;
    private String nickname;
    private Integer n;

    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }

    @Override
    public void run() {

    }
}

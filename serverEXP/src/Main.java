import java.io.IOException;
import java.net.DatagramSocket;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InterruptedException {
        String filename ="Recieved";

        DatagramSocket socket = new DatagramSocket(Integer.parseInt(args[0]));
        Recipient recipient = new Recipient(socket);
        while (true) {
            recipient.getMessage(filename);
            Handler handler = new Handler(filename, recipient,socket);
            Thread.currentThread();
            Thread.sleep(5L);
        }
    }
}





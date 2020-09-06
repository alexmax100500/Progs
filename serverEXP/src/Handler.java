import java.io.IOException;
import java.net.DatagramSocket;

public class Handler extends Thread{
    Deserializator deserializator;
    Serializator serializator;
    Bridge bridge;
    String filename;
    DatagramSocket socket;
    Recipient recipient;
public Handler(String filename, Recipient recipient, DatagramSocket socket) throws IOException, NoSuchMethodException {
    this.deserializator = new Deserializator(recipient);
    this.serializator = new Serializator(filename);
    this.bridge = new Bridge();
    this.socket= socket;
    this.recipient= recipient;
    this.filename=filename;
    this.start();
}
    @Override
    public void run() {
        try {
            bridge.doYourJob(deserializator.deserialize(filename));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Sender sender = new Sender(socket);
        try {
            sender.sendData(serializator.readFromFile(), recipient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

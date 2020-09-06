import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        Scanner sc = new Scanner(System.in);
        DatagramChannel channel = DatagramChannel.open();
        setup(channel,port);
        Sender sender = new Sender();
        Serializator serializator = new Serializator("toSend");

        String line;
        line = sc.nextLine();
        while(!line.equals("exit")) {
            serializator.serialize(line);
            sender.sendMessage("toSend", channel);
            Recipient recipient = new Recipient();
            System.out.println(recipient.getMessage(channel));
            line=sc.nextLine();
        }
        serializator.serialize("exit");
        sender.sendMessage("toSend",channel);
    }
    private static void setup(DatagramChannel channel, int port) throws UnknownHostException {

        channel.socket().connect(InetAddress.getLocalHost(),port);
    }
}

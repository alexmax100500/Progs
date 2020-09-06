import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender {
    private DatagramSocket socket;

    public Sender(DatagramSocket socket){
        this.socket=socket;
    }



    public void sendData(String message, Recipient recipient) throws IOException {
        DatagramPacket dp = new DatagramPacket(message.getBytes(),message.getBytes().length,recipient.getData().getAddress(),recipient.getData().getPort());
        socket.send(dp);
    }


}

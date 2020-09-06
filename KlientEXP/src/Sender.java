/*import java.net.*;

import java.io.*;

class Sender{

    private String host;
    private int port;
    private InetAddress addr;
    private DatagramSocket ds;
    Sender(String host, int port) throws IOException {
        this.addr = InetAddress.getByName(host);
        this.host = host;
        this.port = port;
        ds = new DatagramSocket(port,addr);


    }


    private void sendMessage(String mes){

        try{
            byte[] data = mes.getBytes();

            DatagramPacket pack =
                    new DatagramPacket(data, data.length, addr ,port);
            System.out.println(ds.isBound());
            ds.connect(addr, port);
            System.out.println(ds.isConnected());
            ds.send(pack);
            System.out.println("хуй");


        }catch(IOException e){

            System.err.println(e);

        }

    }

    public static void main(String[] args) throws IOException {

            String line = "hui";

            Sender sndr = new Sender("localhost",1050);
            while (true){
        sndr.sendMessage(line);
    }
    }

}*/

import java.net.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

class Sender{

    private ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
    void sendMessage(String filename, DatagramChannel channel) throws IOException {


        byteBuffer.put(Files.readAllBytes(Paths.get(filename)));
        byteBuffer.flip();
        channel.write(byteBuffer);
        byteBuffer.clear();

    }





    }

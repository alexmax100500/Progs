
import java.net.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

class Recipient {
    private byte[] b;
    private DatagramSocket socket;
    private DatagramPacket data;

    public Recipient( DatagramSocket socket) throws SocketException {
        this.socket=socket;
        b = new byte[8192];
    }

        public void getMessage(String filename) throws IOException {
            this.data = new DatagramPacket(b, b.length);
            this.socket.receive(data);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename));
            bos.write(data.getData());
            bos.flush();
            bos.close();
        }

        public DatagramPacket getData(){ return this.data; }


    }

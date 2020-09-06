import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Recipient {
   public String getMessage(DatagramChannel channel) throws IOException {
       ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
      try {
           byteBuffer.clear();
           channel.read(byteBuffer);
       }
       catch (PortUnreachableException e){
           System.out.println("Сервер временно недоступен");
       }

       return new String(byteBuffer.array(),0,byteBuffer.limit());

   }
}

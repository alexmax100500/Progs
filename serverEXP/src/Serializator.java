import java.io.*;

public class Serializator {
    private String filename;


    public Serializator(String filename) throws IOException {
        this.filename = filename;
    }
    public void serialize() throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(readFromFile());
        out.close();
    }
    public String readFromFile() throws IOException {
        FileInputStream in = new FileInputStream("Bridged");
        byte[] b=new byte[8192];

        in.read(b,0,b.length);


        return new String(b);
    }
}

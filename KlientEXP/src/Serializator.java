import java.io.*;

public class Serializator {
    private String filename;

    public Serializator(String filename) throws IOException {
        this.filename = filename;
    }
    public void serialize(String str) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(str);
        out.close();
    }
}

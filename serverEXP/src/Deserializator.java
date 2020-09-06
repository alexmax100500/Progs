import java.io.*;

public class Deserializator {

Recipient recipient;

    public Deserializator(Recipient recipient){
            this.recipient=recipient;
    }

    public String deserialize(String filename) throws IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        return (String) in.readObject();
    }
}

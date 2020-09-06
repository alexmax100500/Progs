import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class Bridge {

        File file = new File("savedObj");
        HashMap<String, Method> funcmap = new HashMap();
        Collection ku = new Collection();
        String[] com;
        FileFiller filler = new FileFiller();


        public Collection getKu(){
            return this.ku;
        }

        public Bridge() throws NoSuchMethodException {
            fill(funcmap, ku);
            filler.read(ku, file);

        }


        public void doYourJob(String line) {
            try {
                PrintStream ps = new PrintStream("Bridged");
                System.setOut(ps);
                com=line.replaceAll("^\\s+","").split(" ",2);
                funcmap.get(com[0]).invoke(ku,com[com.length-1]);
                ps.close();
            }
            catch (NullPointerException a) {
                System.out.println("Неверный формат команды");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    private void fill(HashMap h, Collection k) throws NoSuchMethodException {
        h.put("remove", k.getClass().getDeclaredMethod("remove", String.class));
        h.put("show", k.getClass().getDeclaredMethod("show", String.class));
        h.put("add_if_max", k.getClass().getDeclaredMethod("add_if_max", String.class));
        h.put("save", k.getClass().getDeclaredMethod("save", String.class));
        h.put("insert", k.getClass().getDeclaredMethod("insert", String.class));
        h.put("remove_lower", k.getClass().getDeclaredMethod("remove_lower", String.class));
        h.put("info", k.getClass().getDeclaredMethod("info", String.class));
        h.put("import", k.getClass().getDeclaredMethod("importe", String.class));
        h.put("add", k.getClass().getDeclaredMethod("add", String.class));
        h.put("sort",k.getClass().getDeclaredMethod("sort",String.class));
        h.put("test",k.getClass().getDeclaredMethod("test",String.class));
    }
}
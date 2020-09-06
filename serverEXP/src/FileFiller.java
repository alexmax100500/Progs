import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileFiller {


    public void read(Collection ku, File file){
        String[] name;
        String instring;
        String line="";
        Scanner sc = null;
        try {
            sc = new Scanner(file);



            while (!line.equals("[")) {
                line = sc.nextLine();
            }
            line=sc.nextLine();
            while (!line.equals("]")) {

                name = sc.nextLine().trim().replaceAll(",", "").replaceAll("\"","").split(" ", 2);
                instring=name[1]+",";
                name = sc.nextLine().trim().replaceAll(",", "").replaceAll("\"","").split(" ", 2);
                instring=instring+name[1]+",";
                sc.nextLine();
                name = sc.nextLine().trim().replaceAll(",", "").replaceAll("\"","").split(" ", 2);
                instring=instring+name[1]+",";


                ku.fill(instring);
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                line=sc.nextLine();


            }
            System.out.println("извините, нопрограмма проигнорирует тот факт, что все сущности не у себя дома");
        }
        catch (FileNotFoundException ef){
            System.out.println("Файла нет");
        }
       // catch(Exception e){
       //     System.out.println("Очень жаль, вы проиграли, файл неверно записан, невозможно загрузить обЬект(ы)");
        }


    }

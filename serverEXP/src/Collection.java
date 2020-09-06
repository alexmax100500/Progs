import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collection {
    String[] buf;
    static CopyOnWriteArrayList<Entitie> arr = new CopyOnWriteArrayList<>();
    Date date = new Date();

    boolean quit = true;

    /**
     * Выводит все элементы коллекции
     * @param e - игнорирующаяся строка
     */
    public  void show(String e){
        StringBuffer buffer=new StringBuffer();
        arr.forEach(ent -> System.out.println((ent.getName()+",\tнаходится в:\t"+ent.getLocation().getName()+",\tвладелец:\t"+ent.getLocation().getOwner().getName()+"\tвремя создания\t"+ent.getDate()+"\n")));
    }

    /**
     * Добавляет элемент в коллекцию, если <b>Длина имени</b> добавляемого объекта больше длины имен содержащихся объектов
     *
     * @param e Имя,Локация
     */
    public void add_if_max(String e) {
        ListIterator<Entitie> iter = arr.listIterator();
    int max=0;

    String s=e.split(",")[0];
    if(e.split(",").length>1){
       while (iter.hasNext()){
            Entitie ent = iter.next();
            if (ent.getName().length()>max)
                max=ent.getName().length();
        }
        if (s.length()>max){
            add(e);
            System.out.println("объект добавлен");
        }
    }
    else System.out.println("Неверный аргумент");
    }

    /**
     * Удаляет элемент из коллекции сначала поиск идет по имени, потом по порядковому номеру
     * @param name - имя или порядковый номер
     */
    public void remove (String name) {
        if(
            !arr.removeIf(s -> s.getName().equals(name))
        )
        {
            try {
                int a = Integer.parseInt(name);
                try {
                    arr.remove(a).getName();
                    System.out.println("Объект удален");
                } catch (Exception exc) {
                    System.out.println("Невозможно удалить такой объект");
                }
            }
            catch (Exception e){
                System.out.println("Невозможно удалить такой объект");
            }

        }
        else System.out.println("Объект удален");
        }

    /**
     * Сохраняет коллекцию в указанный файл
     * @param e - путь к файлу
     */
    public void save (String e) {
        try {
            FileWriter fw = new FileWriter(e);
            ParseToJSON parser = new ParseToJSON();
            fw.write("[\r\n");
            arr.forEach(ent -> {
                try {
                    fw.write("\t{\r\n"
                            +"\t"+parser.parseE(ent)+",\r\n"
                            +"\t"+parser.parseD(ent)+",\r\n"+"\t"
                            +parser.parseH(ent.getLocation())+"{\r\n"
                            +"\t\t"+parser.parseff(ent.getLocation())+",\r\n"
                            +"\t\t"+parser.parsesf(ent.getLocation())+"\r\n"
                            +"\t}\r\n"
                            +"}");
                    if(arr.indexOf(ent)!=arr.size()-1){
                        fw.write(",");
                    }
                    fw.write("\r\n");


                } catch (IOException e1) {
                    System.out.println("Нет такого файла");
                }
                catch (NullPointerException e2){
                    System.out.println("Проблема записи элемента "+arr.indexOf(ent));
                }
            });
            System.out.println("Файл сохранен");
            fw.write("]");
            fw.close();
        }
        catch (IOException ex){
            System.out.println("Нет такого файла");
        }


    }

    /**
     * вставляет элемент в заданную позицию, позиция не больше чем (длина коллекции)+1
     * @param e - номер позиции [пробел] Имя,Локация
     */
    public void insert(String e){
        String[]com;
        com=e.split(" ");
        try {
            arr.add(Integer.parseInt(com[0]), create(com[1]));
            System.out.println("Объект вставлен");
        }
        catch (Exception ex){
            System.out.println("Вероятно, неверный формат команды");
        }
    }

    /**
     * Удаляет все элементы с именем лексикографически меньше заданного
     * @param e - имя сравнения
     */
    public void remove_lower(String e){
        ListIterator<Entitie> iter =arr.listIterator();
        if(e.length()==0)
            System.out.println("аргумент не найден, используем как аргумент команду");
        while(iter.hasNext()){
            Entitie ent = iter.next();
            if (ent.getName().length()<e.length()){
                iter.remove();
                System.out.println("Объект удален");
            }
        }
    }

    /**
     * Выводит информацию о коллекции
     * @param e - игнорирующаяся строка
     */
    public void info(String e){
        System.out.println("Дата инициализации "+this.date+"\n"+arr.size()+" элементов\n"+"тип коллекции "+arr.getClass().toString());
    }

    /**
     * Вызывается по команде <b>import</b>
     * Вносит в коллекцию элементы из файла
     * @param path - путь к файлу
     */
    public void importe(String path) {
        File file=new File(path);
        new FileFiller().read(this,file);
        System.out.println("Коллекция пополнена");
    }

    /**
     * добавляет элемент в коллекцию
     * @param e - имя,локация
     */
    public void add(String e){

        try {
            arr.add(inTimeCreate(e));
            System.out.println("Объект добавлен");
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Неверный аргумент(скорее всего)");
            buf=e.split(",");
            arr.remove(buf[0]);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * Сортировка коллекции
     * @param e - игнорирующаяся строка
     */
    public void sort(String e){
    arr.sort(Comparator.comparing(Entitie::getName));
        System.out.println("Коллекция отсортирована лексикографически");
    }
    public void fill(String e){

        try {
            arr.add(create(e));
            System.out.println("Объект добавлен");
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Неверный аргумент(скорее всего)");
            buf=e.split(",");
            arr.remove(buf[0]);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

    }

    public Entitie inTimeCreate(String s) throws ParseException{
        buf=s.split(",");
        Entitie ent= new Entitie(buf[0]);
        House house = new House(ent, buf[1]);
        return ent;
    }

    public Entitie create(String s) throws ParseException {

            buf = s.split(",");
            Entitie ent = new Entitie(buf[0],buf[1]);
            House house = new House(ent, buf[0]);


        return ent;
    }
    public void test(String s) {
        System.out.println("Затестили "+s);
    }
    private void exsave(File file){
        String s=file.toString();
        save(s);

    }


}

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entitie extends Nameble implements Comparable<Nameble>{
    public Entitie(String s){
        this.Name=s;
        this.date = new Date();
        this.zonedDateTime.now();
    }
    public Entitie(String s,String sdate) throws ParseException {
        this.Name=s;
        SimpleDateFormat format=new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");
        this.date = format.parse(sdate);


    }
    private Date date;
    private House Location;
    public Date getDate(){
        return this.date;
    }
    public void setLocation(House h){
        this.Location = h;
    }
    public void say(String s){
        System.out.println(s);
    }
    public House getLocation(){
        return this.Location;
    }
    public void invite(){
        System.out.println(this.getName()+" пригласил всех войти");
    }
    public boolean equals(Nameble subject) {
        return this.getName() == subject.getName();}

}



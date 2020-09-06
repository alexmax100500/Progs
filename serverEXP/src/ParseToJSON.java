import java.text.SimpleDateFormat;

public class ParseToJSON {
    SimpleDateFormat format = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");
    public String parseE(Entitie e){
        return "  \"name\": \""+e.getName()+"\"";
    }
    public String parseD(Entitie e) {
        return "  \"date\": " +format.format(e.getDate());}
    public String parseH(House h){
        return "\"House\": {";
    }
    public String parseff(House h){
        return "\"name\": \""+h.getName()+"\"";
    }
    public String parsesf(House h){
        return "\"Owner\": \""+h.getOwner().getName()+"\"";
    }

}

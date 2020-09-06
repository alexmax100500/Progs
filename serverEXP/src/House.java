

public class House extends Nameble {
    private Entitie Owner;
    private String Name;
    public House(Entitie p, String name){
        Owner=p;
        Name=name;
        p.setLocation(this);
    }
    public House(String name, String name1){
        Owner = new Entitie(name1);
        Name = name;
    }
    public  String getName(){
        return this.Name;
    }
    public Entitie getOwner(){
        return this.Owner;
    }

}

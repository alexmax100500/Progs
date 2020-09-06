import java.time.ZonedDateTime;

public abstract class Nameble implements Comparable<Nameble> {
    protected String Name;
    public String getName(){
        return this.Name;
    }
    ZonedDateTime zonedDateTime;
    public String toString() {
        return this.getClass().toString() + "_" + this.getName();
    }
    public int hashCode() {
        return this.toString().hashCode();
    }
    public boolean equals(Nameble subject) {
        return this.hashCode() == subject.hashCode();
    }
    public int compareTo(Nameble other) {
        return this.getName().length()-other.getName().length();
    }
}

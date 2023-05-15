import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power, String sortingType) {
        this.name = name;
        this.level = level;
        this.power = power;

        switch(sortingType) {
            case "natural" -> {this.apprentices = new TreeSet<>();}
            case "alternative" -> {this.apprentices = new TreeSet<>(new MageComparator());}
            case "none" -> {this.apprentices = new HashSet<>();}
        }
    }

    //Getters
    public String getName(){ return this.name; }
    public int getLevel(){ return this.level; }
    public double getPower(){ return this.power; }
    public Set<Mage> getApprentices(){ return this.apprentices; }

    public void addApprentices(Mage mage){
        this.apprentices.add(mage);
    }

    public void printApprentices(int depth){
        String showDepth = "";
        for(int i = 0; i < depth; i++){
            showDepth += "-";
        }
        int children = this.countChildren();
        String showMage = showDepth + this.toString() + "   Below him: " + children;
        System.out.println(showMage);
        depth += 1;
        for (Mage app : this.apprentices){
            app.printApprentices(depth);
        }
    }

    public int countChildren(){
        int children = 0;
        for(Mage mage : this.apprentices){
            children += 1 + mage.countChildren();
        }
        return children;
    }

    @Override
    public int compareTo(Mage other) {
        int nameCompare = this.name.compareToIgnoreCase(other.getName());
        if (nameCompare != 0) {
            return nameCompare;
        } else if (this.level != other.getLevel()) {
            return Integer.compare(this.level, other.getLevel());
        } else {
            return Double.compare(this.power, other.getPower());
        }
    }

    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return (mage.getName() == this.name && mage.getLevel() == this.level &&
                mage.getPower() == this.power && mage.getApprentices().equals(this.apprentices));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + level;
        long powerBits = Double.doubleToLongBits(power);
        result = 31 * result + (int) (powerBits ^ (powerBits >>> 32));
        result = 31 * result + apprentices.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Mage{name='" + name + "', level=" + level + ", power=" + power + "}";
    }
}

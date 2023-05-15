import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args){
        // pobieranie parametru z linii poleceń
        String sortingType = args[0];
        //String sortingType = "natural";
        Set<Mage> mageSet = null;

        if (sortingType.equals("natural")) {
            mageSet = new TreeSet<>();
        } else if (sortingType.equals("alternative")) {
            mageSet = new TreeSet<>(new MageComparator());
        } else if (sortingType.equals("none")){
            mageSet = new HashSet<>();
        }

        // Dodawanie elementów do zbioru testowego
        Mage merlin = new Mage("Merlin", 10, 100.0, sortingType);
        Mage gandalf = new Mage("Gandalf", 10, 95.0, sortingType);
        Mage dumbledore = new Mage("Dumbledore", 9, 99.0, sortingType);
        Mage saruman = new Mage("Saruman", 9, 90.0, sortingType);
        Mage morgoth = new Mage("Morgoth", 8, 120.0, sortingType);
        Mage voldemort = new Mage("Voldemort", 8, 110.0, sortingType);
        Mage raistlin = new Mage("Raistlin", 7, 80.0, sortingType);
        Mage elminster = new Mage("Elminster", 7, 85.0, sortingType);
        Mage drizzt = new Mage("Drizzt", 6, 70.0, sortingType);
        Mage pug = new Mage("Pug", 6, 75.0, sortingType);


        merlin.addApprentices(gandalf);
        gandalf.addApprentices(dumbledore);
        merlin.addApprentices(saruman);
        merlin.addApprentices(morgoth);
        mageSet.add(merlin);
        voldemort.addApprentices(raistlin);
        voldemort.addApprentices(elminster);
        elminster.addApprentices(drizzt);
        drizzt.addApprentices(pug);
        mageSet.add(voldemort);

        // Wyświetlenie zawartości zbioru testowego
        for (Mage mage : mageSet) {
            mage.printApprentices(1);
        }
    }
}

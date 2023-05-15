import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage mage1, Mage mage2) {
        int levelCompare = Integer.compare(mage1.getLevel(), mage2.getLevel());
        if (levelCompare != 0) {
            return levelCompare;
        } else {
            int nameCompare = mage1.getName().compareToIgnoreCase(mage2.getName());
            if (nameCompare != 0) {
                return nameCompare;
            } else {
                return Double.compare(mage1.getPower(), mage2.getPower());
            }
        }
    }
}

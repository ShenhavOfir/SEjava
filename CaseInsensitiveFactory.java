import java.io.File;
import java.util.*;

/**
 * This class extends the AbstractInvertedIndexFactory. It is in charge to create the mapping
 * of each word (without distinguishing low case and high case letters) to the list of files than contains it.
 * @param <E> Generic variable (replace strings)
 */
public class CaseInsensitiveFactory<E> extends AbstractInvertedIndexFactory{
    HashMap<String, List<String>> map;
    private CaseInsensitiveFactory loc;

    /***
     * ctor
     */
    public CaseInsensitiveFactory(){
        map = new HashMap<String, List<String>>();
    }

    /**
     * Singleton design pattern implementation
     * @return the class instance
     */
    public CaseInsensitiveFactory createInvertedIndex() {
        if (loc == null) {
            loc = new CaseInsensitiveFactory();
            System.out.println("New CaseInsensitive index is created");
        }
        else
            System.out.println("You already have a CaseInsensitive index");
        return loc;
    }
    /**
     * This method gets to each file a list of words it has.
     * Then calls the hashmap method builder.
     * @param files array of files on which the operation will run
     */
    public void buildInvertedIndex(File[] files) {
        for(File file : files) {
            HashMap<String, List<String>> temp = super.retrieveNameAndMatches(file);
            Map.Entry<String,List<String>> entry = temp.entrySet().iterator().next();
            E key = (E) entry.getKey();
            fillMap((List<E>) temp.get(key), key);
        }
    }

    /**
     * This methods fills the Hashmap by assigning to each word all the files in which it appears.
     * all the words are changed to lower case.
     * @param collection list of words from a single file
     * @param file_name
     */
    private void fillMap(List<E> collection, E file_name) {
        for(E elem: collection){
            if (map.containsKey(elem.toString().toLowerCase())){
                if(!map.get(elem.toString().toLowerCase()).contains(file_name))
                    map.get(elem.toString().toLowerCase()).add(file_name.toString());
            }
            else {
                List<E> newElemList = new ArrayList<E>();
                newElemList.add(file_name);
                map.put(elem.toString().toLowerCase(), (List<String>) newElemList);
            }
        }
    }
}

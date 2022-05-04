import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is in charge of matching each file to the list of words it holds
 * @param <E> Generic variable (replace strings)
 */
public class AbstractInvertedIndexFactory<E extends Comparable<E>> extends AbstractInvertedIndex {
    public AbstractInvertedIndexFactory loc;

    /**
     * Singleton design pattern implementation
     * @return the class instance
     */
    public AbstractInvertedIndexFactory createInvertedIndex() {
        if (loc == null) {
            loc = new AbstractInvertedIndexFactory();
        }
        return loc;
    }

    /**
     * This method retrieves all the words from the file it got and match them to the fileName
     * @param file file on which to operate
     * @return mapping of the fileName and a list of the words it holds
     */
    protected HashMap<E, ArrayList<String>> retrieveNameAndMatches(File file){
        HashMap<E, ArrayList<String>> hash = new HashMap<E, ArrayList<String>>();
        String rows = Utils.readLines(file).toString().replace(",", "");
        Matcher fileNameMatcher = Pattern.compile("<DOCNO>(.*?)</DOCNO>").matcher(rows);
        fileNameMatcher.find();
        ArrayList<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("<TEXT>(.*?)</TEXT>").matcher(rows);

        while (m.find()) {
            ArrayList<String> match = new ArrayList<String>(Arrays.asList(Utils.splitBySpace(m.group())));
            allMatches.addAll(match.subList(1, match.size()-1));
        }
        E fileName = (E) fileNameMatcher.group().substring(8, 21);
        hash.put(fileName, allMatches);
        return hash;
    }



}

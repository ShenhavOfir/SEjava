package part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * EfficientMarkovModel super class is AbstractMarkovModel as its' name indicates it's an efficient
 * not greedy (CPU low cost) class running the Markov principle using and hashMap, permitting
 * it to seek for each keys only once at the initiation of the class.
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> map;

    /**
     * EfficientMarkovModel is the ctor, initiates Markov condition length and the class hashMap
     * attribute.
     * @param n Markov condition length
     */
    public EfficientMarkovModel(int n) {
        super.n = n;
        map = new  HashMap<String, ArrayList<String>>();
    }

    /**
     * setTraining sets the s param to myText class attribute and generates and sets the hashMap values.
     * @param s the string to set
     */
    @Override
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
    }

    /**
     * setSeed sets myRandom attribute seed value.
     * @param seed int to assign to myRandom attribute seed.
     */
    public void setSeed(int seed){
        myRandom = new Random(seed);
    }


    @Override
    public String toString()
    {
        return "EfficientMarkovModel of order " +n;
    }

    /**
     * buildMap builds an hashMap for each key containing (string in length of n)
     * a list of the following characters in myText
     */
    public void buildMap(){
        for (int i = 0; i < super.myText.length()-n; i++) {
            String temp = super.myText.substring(i, i+n);
            if (map.get(temp) == null) {
                ArrayList<String> lst = new ArrayList<String>();
                lst.add(Character.toString(super.myText.charAt(i+n)));
                map.put(temp, lst);
            }
            else
                map.get(temp).add(Character.toString(super.myText.charAt(i+n)));

        }
    }

    /**
     * getFollows returns an ArrayList of Strings (chars), which follow the key argument in myText attribute.
     * @param key char to seek for in myText attribute.
     * @return ArrayList of Strings containing researched values
     */
    @Override
    protected ArrayList<String> getFollows(String key) {
        return map.get(key);
    }


    /**
     * getRandomText Generates a random text using markov principle, with a condition length
     * of n attribute. implemented from IMarkovModel interface.
     * @param numChars length of the predictable word generated.
     * @return Predicted generated text (String).
     */
    @Override
    public String getRandomText (int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        sb.append(myText, index, index+n);
        ArrayList<String> followers = getFollows(sb.toString());
        for (int k = this.n; k < numChars; k++) {
            if (followers.size() == 0)
                continue;
            index = myRandom.nextInt(followers.size());
            sb.append(followers.get(index));
            followers = getFollows(sb.toString().substring(k -(this.n)+1, k + 1));
        }
        return sb.toString();
    }
}

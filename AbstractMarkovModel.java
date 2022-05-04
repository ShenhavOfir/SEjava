package part2;

import java.util.*;

/**
 * AbstractMarkovModel is the super class of all Markov's classes, it implements IMarkovModel
 * methods. In charge of bases of sons class methods.
 */
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int n;

    /** ctor of class initiates myRandom attribute. */
    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    /**
     * sets the s param to myText class attribute.
     * @param s the string to set
     */
    public void setTraining(String s) { myText = s.trim();}

    /**
     * sets myRandom attribute seed value.
     * @param seed int to assign to myRandom attribute seed.
     */
    public void setSeed(int seed){
        this.myRandom = new Random(seed);
    }
    /**
     * getRandomText Generates a random text using markov principle, with a condition length
     * of n attribute. implemented from IMarkovModel interface.
     * @param numChars length of the predictable word generated.
     * @return Predicted generated text (String).
     */
    abstract public String getRandomText(int numChars);

    @Override
    public String toString()
    {
        return "MarkovModel of order " +n;
    }

    /**
     * getFollows returns an ArrayList of Strings (chars), which follow the key argument in myText attribute.
     * @param key char to seek for in myText attribute.
     * @return ArrayList of Strings containing researched values
     */
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> followers = new ArrayList<String>();
        String temp = myText;
        if (key.length() ==1)
        {for (int i = 0; i < myText.length(); i++) {
            try {
                String current_substring = myText.substring(i, i+key.length());
                if (key.contains(current_substring))
                    followers.add(Character.toString(myText.charAt(i+key.length())));
            }
            catch (IndexOutOfBoundsException e)
            {
                return followers;
            }
        }}
        else{
        while (temp.contains(key))
        {
            int index = temp.indexOf(key);
            int num = index + key.length();
            if (num >= temp.length())
                break;
            if (num < temp.length()) {
                followers.add(Character.toString(temp.charAt(num)));
                temp = temp.substring(num-key.length()+1);
            }
        }
        }
        return followers;
    }
}

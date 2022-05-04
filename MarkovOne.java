package part2;

import java.util.ArrayList;

/**
 * MarkovOne class Generates a predictable text using Markov principle with a length condition on one.
 */
public class MarkovOne extends AbstractMarkovModel{

    /** ctor of class sets super class attribute.*/
    public MarkovOne() {
        super.n = 1;
    }
    /**
     * getRandomText Generates a random text using markov principle, with a condition length
     * of one.
     * @param numChars length of the predictable word generated.
     * @return Predicted generated text (String).
     */
    public String getRandomText(int numChars){
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1);
        sb.append(key);
        for (int i=0 ; i<numChars - 1; i++){
            ArrayList<String> follows = this.getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String nextKey = follows.get(index);
            sb.append(nextKey);
            key = nextKey;
        }
        return sb.toString();
    }

}

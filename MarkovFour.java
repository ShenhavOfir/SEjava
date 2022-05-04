package part2;

import java.util.ArrayList;

/**
 * MarkovFour class Generates a predictable text using Markov principle with a length condition on four.
 */
public class MarkovFour extends AbstractMarkovModel {
    /** ctor of class sets super class attribute.*/
    public MarkovFour() {
        super.n = 4;
    }

    /**
     * getRandomText Generates a random text using markov principle, with a condition length
     * of four.
     * @param numChars length of the predictable word generated.
     * @return Predicted generated text (String).
     */
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        sb.append(myText, index, index+4);
        ArrayList<String> followers = getFollows(sb.toString());
        for(int k=4; k < numChars; k++){
            if (followers.size() == 0)
                continue;
            index = myRandom.nextInt(followers.size());
            sb.append(followers.get(index));
            followers = getFollows(sb.toString().substring(k - 3, k + 1));
        }
        return sb.toString();
    }
}

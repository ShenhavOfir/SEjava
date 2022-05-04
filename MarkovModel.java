package part2;

import java.util.ArrayList;

/**
 * MarkovModel class Generates a predictable text using Markov principle with a configurable length condition.
 */
public class MarkovModel extends AbstractMarkovModel {
    public MarkovModel(int n) {
        super.n = n;
    }

    /**
     * getRandomText Generates a random text using markov principle, with a condition length
     * of configurable length condition (N attribute).
     * @param numChars length of the predictable word generated.
     * @return Predicted generated text (String).
     */

    public String getRandomText (int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        sb.append(myText, index, index + n);

        ArrayList<String> followers = getFollows(sb.toString());
        for (int k = this.n; k < numChars; k++) {
            if (followers.size() == 0 || followers == null)
                continue;
            index = myRandom.nextInt(followers.size());
            sb.append(followers.get(index));
            followers = getFollows(sb.toString().substring(k -(this.n)+1, k+1));
        }
        return sb.toString();
    }
}

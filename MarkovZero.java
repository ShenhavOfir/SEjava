package part2;

/**
 * MarkovZero class Generates a predictable text using Markov principle without any
 * condition.
 */
public class MarkovZero extends AbstractMarkovModel{
	/** ctor of class sets super class attribute.*/
	public MarkovZero() {
		super.n = 0;
	}

	/**
	 * getRandomText Generates a random text using markov principle, without any condition.
	 * It is basically generating random numbers in the range of myText attribute and
	 * adding them to the returned String.
	 * @param numChars length of the predictable word generated.
	 * @return Predicted generated text (String).
	 */
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}
}
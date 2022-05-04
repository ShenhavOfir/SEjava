package part2;

import util.SEFileUtil;

/**
 * MarkovRunnerWithInterface class orchestrates the calls to the Markov class's
 * and outputs the predictable strings.
 */
public class MarkovRunnerWithInterface {
	/**
	 * runMarkovModel serialize the text found in the file given in the args.
	 * creates an instance of markovModel, generates three predictable texts using
	 * the getRandom method and transfer them to the printOut method.
	 * @param markov instance of markov class.
	 * @param text value of text on which Markov principle will run.
	 * @param size length determined to the Markov probability.
	 * @param seed int set to random seed.
	 */
	public void runModel(IMarkovModel markov, String text, int size, int seed) {
		markov.setTraining(text);
		markov.setSeed(seed);
		System.out.println("running with " + markov);
		for (int k = 0; k < 3; k++) {
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}

	/**
	 * runMarkov orchestrates the running of markov classes with the probabilities
	 * of zero, one, three and four.
	 * @param trainingFilePath path to text on which Markov principle will run.
	 * @param seed int set to random seed.
	 */
	public void runMarkov(String trainingFilePath, int seed) {
		SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		int size = 200;

		MarkovZero mz = new MarkovZero();
		runModel(mz, st, size, seed);

		MarkovOne mOne = new MarkovOne();
		runModel(mOne, st, size, seed);

		MarkovModel mThree = new MarkovModel(3);
		runModel(mThree, st, size, seed);

		MarkovFour mFour = new MarkovFour();
		runModel(mFour, st, size, seed);
	}
	/**
	 * printOut arranges string given in arg and prints it.
	 * @param s predicted text using Markov Principle.
	 */
	private void printOut(String s) {
		try {
			String[] words = s.split("\\s+");
			int psize = 0;
			System.out.println("----------------------------------");
			for (int k = 0; k < words.length; k++) {
				System.out.print(words[k] + " ");
				psize += words[k].length() + 1;
				if (psize > 60) {
					System.out.println();
					psize = 0;
				}
			}
			System.out.println("\n----------------------------------");
		}
		catch (NullPointerException e)
		{
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Please pass two arguments: 1.input_file 2.seed");
			System.exit(1);
		}
		try {Integer.parseInt(args[1]);}
		catch (NumberFormatException e) {
			System.out.println("The second argument must be an integer");
			System.exit(1);
		}
		MarkovRunnerWithInterface markovRunner = new MarkovRunnerWithInterface();
		markovRunner.runMarkov(args[0], Integer.parseInt(args[1]));
	}
}
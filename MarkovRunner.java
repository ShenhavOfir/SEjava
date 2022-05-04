package part1;

import util.*;

/**
 * MarkovRunner class orchestrates the calls to the Markov class's and outputs the predictable
 * strings.
 */
public class MarkovRunner {

	/**
	 * runMarkovZero serialize the text found in the file given in the args.
	 * creates an instance of markovZero, generates three predictable texts using
	 * the getRandom method and transfer them to the printOut method.
	 * @param trainingFilePath path of the file in which the text is found.
	 */
    public void runMarkovZero(String trainingFilePath) {
		SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	/**
	 * printOut arranges string given in arg and prints it.
	 * @param s predicted text using Markov Principle.
	 */
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	/**
	 * runMarkovOne serialize the text found in the file given in the args.
	 * creates an instance of markovOne, generates three predictable texts using
	 * the getRandom method and transfer them to the printOut method.
	 * @param trainingFilePath path of the file in which the text is found.
	 */
	public void runMarkovOne(String trainingFilePath) {
		SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	/**
	 * runMarkovFour serialize the text found in the file given in the args.
	 * creates an instance of markovFour, generates three predictable texts using
	 * the getRandom method and transfer them to the printOut method.
	 * @param trainingFilePath path of the file in which the text is found.
	 */
	public void runMarkovFour(String trainingFilePath) {
		SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	/**
	 * runMarkovModel serialize the text found in the file given in the args.
	 * creates an instance of markovModel, generates three predictable texts using
	 * the getRandom method and transfer them to the printOut method.
	 * @param trainingFilePath path of the file in which the text is found.
	 */
	public void runMarkovModel(String trainingFilePath){
		SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel(6);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.runMarkovModel(500);
			printOut(text);
		}
	}
	public static void main(String[] args) {
//		MarkovRunner markovRunner = new MarkovRunner();
//		markovRunner.runMarkovZero(args[0]);
//		markovRunner.runMarkovOne(args[0]);
//		markovRunner.runMarkovFour(args[0]);
//		markovRunner.runMarkovModel(args[0]);

	}

	
}

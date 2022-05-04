package part2;

import util.SEFileUtil;

public class MarkovRunnerWithInterfaceEfficient {
    /**
     * runModel serialize the text found in the file given in the args.
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

    /**
     * testHashMap runs markov class with the probability of five.
     * @param trainingFilePath path to text on which Markov principle will run.
     * @param seed int set to random seed.
     */
    public void testHashMap(String trainingFilePath, int seed){
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        EfficientMarkovModel emFive = new EfficientMarkovModel(5);
        runModel(emFive, st, size, seed);
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
        MarkovRunnerWithInterfaceEfficient markovRunner = new MarkovRunnerWithInterfaceEfficient();
        markovRunner.testHashMap(args[0], Integer.parseInt(args[1]));
    }
}
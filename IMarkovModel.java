package part2;

/**
 * IMarkovModel is the interface Markov Classes's follow.
 */
public interface IMarkovModel {
    /**
     * setTraining is responsible to set vase text to a Class attribute.
     * @param text the test to assign.
     */
    public void setTraining(String text);

    /**
     * getRandomText is responsible to generates the predictable markov product.
     * @param numChars length of predictable text to generate.
     * @return
     */
    public String getRandomText(int numChars);

    /**
     * setSeed is responsible to sen the random attribute its' seed.
     * @param seed number to set to random.
     */
    public void setSeed(int seed);
    
}

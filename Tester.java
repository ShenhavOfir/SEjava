package part1;

/** Testing class of MarkovOne */
public class Tester {
    /** testGetFollows tests MarkovOne getFollow method */
    public void testGetFollows (){
        MarkovOne object = new MarkovOne();
        object.setTraining("this is a test yes this is a test.");
       System.out.println(object.getFollows('t').toString());
    }
}

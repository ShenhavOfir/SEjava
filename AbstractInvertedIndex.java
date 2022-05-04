import java.io.File;
import java.util.*;

/**
 * this class is in charge to run the user queries and to returns it with the desired response.
 * @param <E> Generic variable (replace strings)
 */
public class AbstractInvertedIndex<E>{

    /**
     * This method calls the sub-methods which are in charge of creating the mapping according
     * to the sub-class type (caseSensitive and caseInsensitive)
     * @param files files on which to process the mapping.
     */
   public  void buildInvertedIndex(File[] files){
       try{
            CaseInsensitiveFactory.class.cast(this);
            ((CaseInsensitiveFactory)this).buildInvertedIndex(files);
        }
        catch (NullPointerException e){
            ((CaseSensitiveFactory)this).buildInvertedIndex(files);
        }
    }

    /**
     * This method run the query sent by the user on the data-set we got.
     * the queries are supposed to be written in a reverse polish notation.
     * We have distinguished between a single word query and a poly-words query.
     * @param query the query sent by the user
     * @return the matching files that replay to the query request
     * @throws ClassCastException inner need of that exception, won't be thrown otherwise
     */
   public TreeSet<String> runQuery(String query) throws ClassCastException {
       ArrayList<String> operations = new ArrayList<String>(Arrays.asList("NOT", "OR", "AND"));
       List<E> req = (List<E>) Arrays.asList(Utils.splitBySpace(query));
       Stack<List<E>> stack = new Stack<List<E>>();

       HashMap<E, List<E>> map;
       try{
           map = ((CaseSensitiveFactory)this).map;
       }catch (ClassCastException e){
           map = ((CaseInsensitiveFactory)this).map;
       }
       if (req.size() == 1) {
           if(this.getClass().getName().equals("CaseInsensitiveFactory"))
               stack.push(map.get(req.get(0).toString().toLowerCase()));
           else
               stack.push(map.get(req.get(0)));
       }

       for (E t : req) {
            if(!operations.contains(t) && req.size() >1) {
                if(this.getClass().getName().equals("CaseInsensitiveFactory"))
                    t = (E)t.toString().toLowerCase();
                stack.push(Arrays.asList(t));
            }
            else if (req.size()>1) {//pop numbers from stack if it is an operator
                List<E> temp = getList(map, stack);
                List<E> sec = getList(map, stack);

                switch ((String) t) {
                    case "AND":
                        temp.retainAll(sec);
                        stack.push(temp);
                        break;
                    case "OR":
                        temp.addAll(sec);
                        stack.push(temp);
                        break;
                    case "NOT":
                        sec.removeAll(temp);
                        stack.push(sec);
                        break;
                }
            }
        }
        TreeSet<String> TSet = new TreeSet<String>();
        for (E x : stack.pop())
            TSet.add(x.toString());
        return TSet;
    }

    /**
     * this method helps us reaching the stack content correctly.
     * @param map the current map on which the operation is held
     * @param stack the stack on which the algorithm currently works
     * @return a List of matches held in the stack or the next word on which to calling method needs.
     */
   private List<E> getList(HashMap<E, List<E>> map, Stack<List<E>> stack){
       List<E> lst = new ArrayList<E>();
       if(((List)stack.peek()).size() == 1 && map.get(stack.peek().get(0)) == null)
           return stack.pop();
       if (map.get(stack.peek().get(0)) != null || ((List)stack.peek()).size()>1) {
           try {
               if (((List) stack.peek()).size() > 1){
                   lst.addAll(stack.pop());
               }
               else {throw new ClassCastException(); }
           }
           catch (ClassCastException e) {
               lst = new ArrayList<E>(map.get(stack.pop().get(0)));
           }
       }
       return lst;
    }

}




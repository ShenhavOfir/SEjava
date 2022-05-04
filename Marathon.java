import java.lang.reflect.Array;
import java.lang.Exception;

public class Marathon {

    static int fastestRunner(int[] times)
    {
        if (times.length == 0)
            return -1;
        int min_time = times[0];
        int place = 0;
        for (int i = 0; i<times.length; i++)
        {
            if (times[i] <= min_time)
            {
                place = i;
                min_time = times[i];
            }
        }
        return place;
    }

    public static void main(String[] args) {
        String[] runnerNames = {"Dani", "Eithan", "Dana", "Ben", "David", "Alina",
                                "Suzie", "Kate", "Ellen", "Nofar", "Yuval",
                                "Noam", "Aaron"};
        int[] times = {342, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 394, 299};
        int place = -1;
        try {
            place = fastestRunner(times);
        }
        catch (Exception err)
            { System.out.print("times array isn't an array of int");}
        if (place < 0)
            System.out.print("times array is empty");
        else
            System.out.printf("fastest runner is: %s, runned: %s", runnerNames[place], times[place]);
    }
}

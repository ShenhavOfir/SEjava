import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int N =0;
        if (args != null && args.length != 0)
            N = args[0].length();

        int[] temp = new int[N];
//      w is the iterator for the temp list
        int w = 0;
        int counter = 0;
        for (int i=0; i < N; i++)
        {
            if (args[0].charAt(i) == '-')
                counter++;
            if (args[0].charAt(i) == '-' && i < args[0].length() - 1) {
                    if (Character.isDigit(args[0].charAt(i + 1))) {
                        temp[w] = Integer.parseInt(String.format("-%s", args[0].charAt(i + 1)));
                        i++;
                    }
                }
            else if(i < args[0].length() - 1)
                temp[w] = Character.getNumericValue(args[0].charAt(i));
            w++;
        }

        int[] arr = Arrays.copyOfRange(temp, 0, N-counter);

        int i = 1;
        while (i < arr.length) {
            int x = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > x) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = x;
            i = i + 1;
        }

        if (arr.length > 0) {
            for (int j = 0; j < arr.length; j++) {
                if (arr.length == 1)
                    System.out.print(String.format("[%s]", arr[j]));
                else {
                    if (j == arr.length - 1)
                        System.out.print(String.format("%s]", arr[j]));

                    else if (j == 0)
                        System.out.print(String.format("[%s, ", arr[j]));

                    else
                        System.out.print(String.format("%s, ", arr[j]));
                }
            }
        }
        else System.out.print("[]");
    }
}

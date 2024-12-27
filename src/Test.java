import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        int[] testArray = {1, 2, 3};
//        List<int[]> testArrayList = Arrays.asList(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
//
//        if (testArrayList.contains(testArray)) {
//            System.out.println("TRUE!");
//        }
        int[] testArray = {1, 2, 3};
        int[] testArray2 = {1, 2, 3};
        System.out.println(Arrays.equals(testArray, testArray2));
    }
}

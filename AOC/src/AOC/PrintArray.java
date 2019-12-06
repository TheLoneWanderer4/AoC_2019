package AOC;

public class PrintArray {
    public static String printArray(int[] array){
        String ret = "[";
        for(int x : array){
            ret += x + ",";
        }
        return ret + "]";
    }
}

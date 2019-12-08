package AOC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Util {
    public static String  printArray(int[] array){
        String ret = "[";
        for(int x : array){
            ret += x + ", ";
        }
        ret = ret.substring(0,ret.length()-2)+"]";
        return ret;
    }
    public static String  printArray(Integer[] array){
        String ret = "[";
        for(Integer x : array){
            ret += x + ", ";
        }
        ret = ret.substring(0,ret.length()-2)+"]";
        return ret;
    }
    public static String  printArray(String[] array){
        String ret = "[";
        for(String x : array){
            ret += x + ", ";
        }
        ret = ret.substring(0,ret.length()-2)+"]";
        return ret;
    }
    public static String  printArray(char[] array){
        String ret = "[";
        for(char x : array){
            ret += x + ", ";
        }
        ret = ret.substring(0,ret.length()-2)+"]";
        return ret;
    }


    public static class Input {
        /*
         * Purpose: automate the construction of a valid scanner, reduce redundant
         * code.
         *
         * @Param fileName
         *
         * @Return Scanner fileReader
         */

        public static Scanner buildScanner(String fileName) {
            Scanner fileReader = null;

            try {
                fileReader = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return fileReader;
        }

        public static String[] getData(){
            Scanner input = buildScanner("src/input.txt");
            List data = new ArrayList<String>();
            while(input.hasNextLine()){
                data.add(input.nextLine());
            }
            return Arrays.stream(data.toArray()).toArray(String[]::new);

        }

    }
}

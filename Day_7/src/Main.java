import AOC.Input;
import AOC.IntCode;
import AOC.PrintArray;

import java.util.*;

public class Main {
    public static List<Amp> getAmps(String input, char mode){
        List<Amp> amps = new ArrayList<>();
        for(int i = 0; i<5;i++){
            amps.add(new Amp(input, mode));
        }
        return amps;
    }

    public static List<Integer[]> getAllPhases(int lower, int upper){
        List<Integer[]> ret = new ArrayList<>();

        for(int A = lower; A <= upper; A++){
            for(int B = lower; B <= upper; B++){
                for(int C = lower; C <= upper; C++){
                    for(int D = lower; D <= upper; D++){
                        for(int E = lower; E <= upper; E++){
                            Integer[] sequence = new Integer[]{A,B,C,D,E};
                            Set<Integer> test = new HashSet<Integer>(Arrays.asList(sequence));
                            if(sequence.length != test.size()){
                                continue;
                            }
                            ret.add(sequence);
                        }
                    }
                }
            }
        }
        return ret;
    }

    public static void partOne(String input){
        List<Amp> amps = getAmps(input, 'S');

        int maxOutput = Integer.MIN_VALUE;

        List<Integer[]> sequences = getAllPhases(0,4);

        for(Integer[] sequence : sequences){
            int ampInput  = 0;
            for(int i = 0; i<5; i++){
                ampInput = amps.get(i).run(sequence[i],ampInput);
            }
            if(ampInput > maxOutput){
                maxOutput = ampInput;
            }
        }

        System.out.println(maxOutput);
    }

    public static int runProgramPartTwo(Integer[] sequence, List<Amp> amps){
        int output = 0;
        int ret = 0;
        int i = 0;
        while(output != -1){
            ret = output;
            output = amps.get(i).run(sequence[i],output);

            i++;
            if(i >= amps.size()){
                i = 0;
            }
        }
        return ret;
    }

    public static void partTwo(String input){
        List<Amp> amps = getAmps(input, 'C');

        List<Integer[]> sequences = getAllPhases(5,9);

        int max = Integer.MIN_VALUE;

        for(Integer[] sequence: sequences){
            int output = runProgramPartTwo(sequence, amps);

            String seqStr = "";
            for(Integer x : sequence){
                seqStr += x;
            }
            // very useful debugging print statement
            //System.out.println(output + " | " + seqStr);

            amps = getAmps(input, 'C');
            if(output > max){
                max = output;
            }
        }

        System.out.println(max);
    }

    public static void main(String[] args){
        String[] input = Input.getData();

        partOne(input[0]);
        partTwo(input[0]);
    }
}

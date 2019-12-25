import AOC.Util;

import java.util.HashMap;
import java.util.Map;

public class Reaction {
    public Map<String, Integer> input;
    public int outputAmount;
    public String outputType;

    private void parseInput(String inputs){
        String[] hold = inputs.split(", ");
        for(String line : hold){
            String[] chem = line.split(" ");

            input.put(chem[1], Integer.parseInt(chem[0]));
        }
    }

    private void parseOutput(String out){
        String[] hold = out.split(" ");
        outputAmount = Integer.parseInt(hold[0]);
        outputType = hold[1];
    }

    public Reaction(String inputs, String output){
        input = new HashMap<>();

        parseInput(inputs);
        parseOutput(output);
    }

    @Override
    public String toString() {
        String ret = "";
        ret += input + " => ";
        ret += outputAmount + " " + outputType;
        return ret;
    }
}

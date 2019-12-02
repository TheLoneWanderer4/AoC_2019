import AOC.*;

public class Main {
    public static String[] parseInput(String input){
        return input.split(",");
    }

    public static void add(String[] input, int indexOne, int indexTwo, int placeIndex){
        input[placeIndex] = ((Integer)(Integer.parseInt(input[indexOne]) + Integer.parseInt(input[indexTwo]))).toString();
    }

    public static void multiply(String[] input, int indexOne, int indexTwo, int placeIndex){
        input[placeIndex] = ((Integer)(Integer.parseInt(input[indexOne]) * Integer.parseInt(input[indexTwo]))).toString();
    }

    public static String run(String[] memory, String noun, String verb){

        String[] input = memory.clone();
        input[1] = noun;
        input[2] = verb;

        for(int i = 0; i<input.length-1; i+=4){
            int command = Integer.parseInt(input[i]);

            int indexOne = Integer.parseInt(input[i+1]);
            int indexTwo = Integer.parseInt(input[i+2]);
            int placeIndex = Integer.parseInt(input[i+3]);

            if(command == 99){
                break;
            }

            switch (command){
                case 1:
                    add(input, indexOne, indexTwo, placeIndex);
                    break;
                case 2:
                    multiply(input, indexOne, indexTwo, placeIndex);
                    break;
            }
        }
        return input[0];
    }

    public static int bruteForceInputs(String[] input, int output){
        for(int i = 0; i<100;i++){
            for(int j = 0; j<100;j++){
                String noun = String.valueOf(i);
                String verb = String.valueOf(j);
                String result = run(input, noun, verb);
                if(Integer.parseInt(result) == output){
                    return 100 * Integer.parseInt(noun) + Integer.parseInt(verb);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        String[] input = parseInput(Input.getData()[0]);
        System.out.println(bruteForceInputs(input, 19690720));
    }
}


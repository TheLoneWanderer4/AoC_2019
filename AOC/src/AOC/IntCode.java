package AOC;

import java.util.Scanner;

public class IntCode {
    private int[] workingMemory;
    private int[] originalMemory;

    private int i = 0;
    // memory pointer, the most important of all the things


    public int[] getWorkingMemory(){
        return workingMemory.clone();
    }

    public int[] getOriginalMemory(){
        return originalMemory.clone();
    }

    public IntCode(int[] input){
        // clone so was to not change other computers memory
        this.workingMemory = input.clone();
        this.originalMemory = input.clone();
    }

    private static int[] parseInput(String input){
        String[] toUpdate = input.split(",");
        int[] ret = new int[toUpdate.length];
        for(int i = 0; i<ret.length; i++){
            ret[i] = Integer.parseInt(toUpdate[i]);
        }
        return ret;
    }

    public IntCode(String input){
        this(IntCode.parseInput(input));
    }

    public IntCode(IntCode other){
        workingMemory = other.workingMemory.clone();
        originalMemory = other.originalMemory.clone();
    }

    private int get(int i, int opp){
        if(opp == 1){;
            return i;
        }else{
            return workingMemory[i];
        }
    }

    private int[] parseParamCode(int i, int length){
        int[] ret = new int[length];
        int index = 0;
        while(index < ret.length){
            ret[index] = i%10;
            index++;
            i /= 10;
        }
        return ret;
    }

    private int add(int i, int command){

        int[] ops = parseParamCode(command, 3);

        int indexOne = get(i+1, ops[0]);
        int indexTwo = get(i+2, ops[1]);
        int placeIndex = get(i+3, ops[2]);


        workingMemory[placeIndex] = workingMemory[indexOne] + workingMemory[indexTwo];
        return 4;
    }

    private int multiply(int i, int command){
        int[] ops = parseParamCode(command, 3);

        int indexOne = get(i+1, ops[0]);
        int indexTwo = get(i+2, ops[1]);
        int placeIndex = get(i+3, ops[2]);

        workingMemory[placeIndex] = workingMemory[indexOne] * workingMemory[indexTwo];
        return 4;
    }

    private int input(int i, int command){
        Scanner reader = new Scanner(System.in);

        int index = get(i+1, command);
        workingMemory[index] = Integer.parseInt(reader.nextLine());
        return 2;
    }

    private int input(int i, int command, int input){
        int index = get(i+1, command);
        workingMemory[index] = input;
        return 2;
    }

    private int print(int i, int command){
        int index = get(i+1, command);
        System.out.println(workingMemory[index]);

        return 2;
    }

    private int returnPrint(int i, int command){
        int index = get(i+1, command);
        return workingMemory[index];
    }

    private int jumpIfTrue(int i, int command){
        int[] ops = parseParamCode(command, 2);
        int param = workingMemory[get(i+1, ops[0])];
        if(param != 0){
            return workingMemory[get(i+2, ops[1])];
        }
        return i+3;
    }

    private int jumpIfFalse(int i, int command){
        int[] ops = parseParamCode(command, 2);
        int param = workingMemory[get(i+1, ops[0])];
        if(param == 0){
            return workingMemory[get(i+2, ops[1])];
        }
        return i+3;
    }

    private int lessThan(int i, int command){
        int[] ops = parseParamCode(command, 3);

        int itemOne = workingMemory[get(i+1, ops[0])];
        int itemTwo = workingMemory[get(i+2, ops[1])];

        int placeIndex = get(i+3, ops[2]);

        workingMemory[placeIndex] = itemOne < itemTwo ? 1 : 0;

        return 4;
    }

    private int equal(int i, int command){
        int[] ops = parseParamCode(command, 3);
        int itemOne = workingMemory[get(i+1, ops[0])];
        int itemTwo = workingMemory[get(i+2, ops[1])];
        int placeIndex = get(i+3, ops[2]);

        workingMemory[placeIndex] = itemOne == itemTwo ? 1 : 0;

        return 4;
    }

    /**
     *
     * @param mode, Okay, there are three settings.
     *
     *              N, normal, input will prompt the commandline, output will
     *                  print.
     *
     *              S, sequences, give the function a list of integers
     *                  containing, in order, the inputs your program needs.
     *                  As each input is called it will grab the next element
     *                  from the list.
     *
     *              C, single, grabs the last element from the passed inputs
     *                  array and uses it for all input calls.
     *
     *              Both S and C will return rather than print
     *
     * @param inputs, a list of integers, the inputs
     * @return, if mode is true, then will return in response to the 4 command
     *          otherwise, will return the value at memory index 0
     */

    public int run(char mode, int[] inputs){
        int inputIndex = 0;

        while(i < workingMemory.length){
            int command = workingMemory[i];

            if(command == 99){
                this.workingMemory = originalMemory.clone();
                i = 0;
                break;
            }

            int operation = (command%10>0?command%10:command);

            command = command%10>0? command /= 100: command;

            if(operation == 1){
                i += add(i, command);
            }else if(operation == 2){
                i += multiply(i, command);
            }else if(operation == 3){
                if(mode == 'N'){
                    i += input(i, command);
                }else if(mode == 'S'){
                    i += input(i, command, inputs[inputIndex]);
                    inputIndex += 1;
                }else if(mode == 'C'){
                    i += input(i, command, inputs[inputs.length-1]);
                }
            }else if(operation == 4){
                if(mode == 'N'){
                    i += print(i, command);
                }else if(mode == 'S' || mode == 'C'){
                    int pass = i;
                    i += 2;
                    return returnPrint(pass, command);
                }
            }else if(operation == 5){
                i = jumpIfTrue(i, command);
            }else if(operation == 6){
                i = jumpIfFalse(i, command);
            }else if(operation == 7){
                i += lessThan(i, command);
            }else if(operation == 8){
                i += equal(i, command);
            }else{
                System.out.println("Unknown Command: " + operation);
                i+=1;
            }
        }
        // return -1 if the program completes but doesn't return anything
        return -1;
    }

    public int bruteForceInputs(int output){
        for(int i = 0; i<100;i++){
            for(int j = 0; j<100;j++){
                // run the program with the given inputs
                workingMemory[1] = i;
                workingMemory[2] = j;
                int result = run('N', null);

                if(result == output){
                    // case specific for day two test, likely to change in the future
                    return 100 * i + j;
                }

            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String ret = "[";
        for(int i : workingMemory){
            ret += i + ", ";
        }
        return ret + "]";
    }
}

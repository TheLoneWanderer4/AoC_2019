package AOC;

public class IntCode {
    private int[] workingMemory;
    private int[] originalMemory;

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

    private void add(int indexOne, int indexTwo, int placeIndex){
        workingMemory[placeIndex] = workingMemory[indexOne] + workingMemory[indexTwo];
    }

    private void multiply(int indexOne, int indexTwo, int placeIndex){
        workingMemory[placeIndex] = workingMemory[indexOne] * workingMemory[indexTwo];
    }

    public int run(int noun, int verb){
        workingMemory[1] = noun;
        workingMemory[2] = verb;

        for(int i = 0; i< workingMemory.length-1; i+=4){
            int command = workingMemory[i];

            int indexOne = workingMemory[i+1];
            int indexTwo = workingMemory[i+2];
            int placeIndex = workingMemory[i+3];

            if(command == 99){
                break;
            }

            switch (command){
                case 1:
                    add(indexOne, indexTwo, placeIndex);
                    break;
                case 2:
                    multiply(indexOne, indexTwo, placeIndex);
                    break;
            }
        }
        //reset the memory back to stock and return
        int ret = workingMemory[0];
        this.workingMemory = originalMemory.clone();
        return ret;
    }

    public int bruteForceInputs(int output){
        for(int i = 0; i<100;i++){
            for(int j = 0; j<100;j++){
                // run the program with the given inputs
                int result = run(i, j);

                if(result == output){
                    // case specific for day two test, likely to change in the future
                    return 100 * i + j;
                }

            }
        }
        return -1;
    }
}

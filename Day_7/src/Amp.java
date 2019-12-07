import AOC.IntCode;

public class Amp {
    private IntCode computer;
    private char mode;
    private boolean hasPhase;

    public Amp(String memory, char mode){
        computer = new IntCode(memory);
        this.mode = mode;
        hasPhase = false;
    }
    public int run(int phase, int signal){
        if(hasPhase){
            return computer.run(mode, new int[]{phase, signal});
        }else{
            hasPhase = true;
            return computer.run('S', new int[]{phase, signal});
        }
    }
}

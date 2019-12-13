import AOC.IntCode;

public class IntGame {
    private String baseState;
    private IntCode computer;
    private int score;

    public IntGame(String memory){
        baseState = memory;
        computer = new IntCode(baseState);
        score = 0;
    }

    public int getStartingBlocks(){
        int ret= 0;
        int x = 0;
        int y = 0;
        int piece = 0;

        while (x != - 1 && y != -1 && piece != -1) {
            int[] input = new int[]{0};
            x = (int) computer.run('C', input);
            y = (int) computer.run('C', input);
            piece = (int) computer.run('C', input);

            if(piece == 2){
                ret += 1;
            }

        }
        reset();

        return ret;
    }

    public void run(){
        int ball = 0;
        int paddle = 0;

        int[] input = new int[]{0};

        int x = 0;
        int y = 0;
        int piece = 0;

        while (piece != -1) {
            x = (int) computer.run('C', input);
            y = (int) computer.run('C', input);
            piece = (int) computer.run('C', input);

            if(piece == 4){
                ball = x;
            }else if(piece == 3){
                paddle = x;
            }

            if(x == -1 && y == 0 && piece != 0) {
                score = piece;
            }

            if(ball < paddle){
                input[0] = -1;
            }else if(ball > paddle){
                input[0] = 1;
            }else{
                input[0] = 0;
            }
        }
    }

    public void reset(){
        computer = new IntCode(baseState);
        score = 0;
    }

    public int score(){
        return score;
    }

}

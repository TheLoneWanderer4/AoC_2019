import AOC.*;

public class Main {
    public static void main(String[] args){
        String[] input = Input.getData();

        IntCode computer = new IntCode(input[0]);

        computer.run(0,0);
    }
}

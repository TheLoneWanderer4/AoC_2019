import AOC.IntCode;
import AOC.Util;

public class Main {
    public static void main(String[] args){
        String[] input = Util.Input.getData();

        IntCode computer = new IntCode(input[0]);
        computer.run('N', null);
    }
}

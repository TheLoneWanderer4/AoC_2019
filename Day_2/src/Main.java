import AOC.*;

public class Main {
    public static void main(String[] args){
        IntCode computer = new IntCode(Input.getData()[0]);

        System.out.println(computer.run(12,2));

        System.out.println(computer.bruteForceInputs(19690720));
    }
}


import AOC.Image;
import AOC.Util;

public class Main {
    public static void main(String[] args){
        String[] input = Util.Input.getData();
        Image password = new Image(input[0], 25, 6);

        // part one
        System.out.println(password.verify());
        // part two
        System.out.println(password.finalImage());

    }
}

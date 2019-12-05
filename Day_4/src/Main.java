import AOC.Input;
import AOC.Numbers;

public class Main {
    public static void main(String[] args){
        String[] input = Input.getData()[0].split("-");


        int ret = 0;


        for(int i = Integer.parseInt(input[0]); i<=Integer.parseInt(input[1]); i++){
            if(Numbers.isIncreasing(i) && Numbers.hasAdjacent(i)){
                ret += 1;
            }
        }

        System.out.println(ret);
    }
}

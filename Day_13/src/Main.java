import AOC.Util;

public class Main {

    private static void partOne(IntGame game){
        System.out.println(game.getStartingBlocks());
    }

    private static void partTwo(IntGame game){
        game.run();
        System.out.println(game.score());
    }

    public static void main(String[] args) {
        String[] input = Util.Input.getData();
        IntGame game = new IntGame(input[0]);
        partOne(game);
        partTwo(game);
    }
}

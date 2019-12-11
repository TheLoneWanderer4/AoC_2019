import AOC.Util;

public class Main {
    public static void main(String[] args){
        String[] input = Util.Input.getData();

        PainterBot bot = new PainterBot(input[0]);

        bot.run();
        bot.paint();
    }
}

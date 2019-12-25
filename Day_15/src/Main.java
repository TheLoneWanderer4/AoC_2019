import AOC.Directions.cardinal;
import AOC.IntCode;
import AOC.Math.Cord;
import AOC.Structures.UnDGraph;
import AOC.Util;

import java.util.*;

public class Main {

    private static UnDGraph maze = new UnDGraph();

    private static Map<Cord, Long> maze2 = new HashMap<>();

    private static Set<Cord> visited = new HashSet<>();
    private static Cord oxygenSystem = null;
    private static List<Integer> directions = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    private static int[] botInput = new int[1];

    public static void paint(){
        int lowestWidth = Integer.MAX_VALUE;
        int highestWidth = Integer.MIN_VALUE;

        int lowestHeight = Integer.MAX_VALUE;
        int highestHeight = Integer.MIN_VALUE;

        for(Cord cord : maze2.keySet()){
            if(cord.x > highestWidth){
                highestWidth = cord.x;
            }else if(cord.x < lowestWidth){
                lowestWidth = cord.x;
            }

            if(cord.y > highestHeight){
                highestHeight = cord.y;
            }else if(cord.y < lowestHeight){
                lowestHeight = cord.y;
            }
        }

        // as seen the lowest height/width is used as an offset, to properly define the hull in the first quadrant
        // when writing to the list, this value should be used as an offset again.
        int width = highestWidth + Math.abs(lowestWidth);
        int height = highestHeight + Math.abs(lowestHeight);

        char[][] hull = new char[height+1][width+1];

        for (char[] chars : hull) {
            Arrays.fill(chars, ' ');
        }

        for(Cord cord : maze2.keySet()){
            // the y looks like this so as to flip the image right side up
            int y = hull.length - (cord.y + Math.abs(lowestHeight)) - 1;
            int x = cord.x + Math.abs(lowestWidth);

            if(maze2.get(cord) == 0){
                hull[y][x] = '0';
            }else if(maze2.get(cord) == 1){
                hull[y][x] = '.';
            }else if(maze2.get(cord) == 2){
                hull[y][x] = 'X';
            }
        }

        System.out.println(Util.toString2dArray(hull));

    }

    public static Cord getNewCord(Cord curr, int dir){
        switch (dir){
            case 1:
                return curr.up(1);
            case 2:
                return curr.down(1);
            case 3:
                return curr.left(1);
            case 4:
                return curr.right(1);
        }
        return null;
    }

    public static void buildMaze(IntCode bot, Cord curr){
        if(visited.contains(curr)){
            return;
        }else{
            visited.add(curr);
            for(Integer direction : directions){
                botInput[0] = direction;

                long code = bot.run('C', botInput);

                Cord newCurr = getNewCord(curr, direction);
                maze2.put(newCurr, code);
                if(code != 0){
                    if(code == 2){
                        oxygenSystem = newCurr;
                    }
                    buildMaze(bot, newCurr);
                }
            }
            //visited.remove(curr);
        }
    }

    public static void main(String[] args) {
        String[] input = Util.Input.getData();
        IntCode repairBot = new IntCode(input[0]);

        maze2.put(new Cord(0,0), 2L);

        buildMaze(repairBot, new Cord(0,0));
        System.out.println(maze2);
        paint();
        System.out.println(oxygenSystem);
    }
}

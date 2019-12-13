package AOC;

import AOC.Directions.cardinal;
import AOC.Directions.Directions;
import AOC.IntCode;
import AOC.Math.Cord;
import AOC.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PainterBot {

    IntCode brain;

    cardinal direction;
    Cord location;

    Map<Cord, Integer> visited;


    public PainterBot(String memory){
        brain = new IntCode(memory);
        direction = cardinal.NORTH;
        location = new Cord(0,0);
        visited = new HashMap<>();

        visited.put(location,1);
    }

    public void run(){
        while(true){
            int color = visited.getOrDefault(location, 0);
            long newColor = brain.run('C', new int[]{color});
            long turn = brain.run('C', new int[]{color});

            if(newColor == -1 || turn == -1){
                break;
            }
            visited.put(location, (int) newColor);
            turn(turn);
        }
    }

    private void turn(long way){
        direction = way == 0 ? Directions.rotateLeft(direction) : Directions.rotateRight(direction);
        move();
    }

    public void move(){
        switch (direction){
            case NORTH:
                location = new Cord(location.x, location.y + 1);
                break;
            case SOUTH:
                location = new Cord(location.x, location.y - 1);
                break;
            case WEST:
                location = new Cord(location.x - 1, location.y);
                break;
            case EAST:
                location = new Cord(location.x + 1, location.y);
                break;
        }
    }

    public Map<Cord, Integer> getPainted(){
        return visited;
    }

    public void paint(){
        int lowestWidth = Integer.MAX_VALUE;
        int highestWidth = Integer.MIN_VALUE;

        int lowestHeight = Integer.MAX_VALUE;
        int highestHeight = Integer.MIN_VALUE;

        for(Cord cord : visited.keySet()){
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

        for(Cord cord : visited.keySet()){
            if(visited.get(cord) != 0){

                // the y looks like this so as to flip the image right side up
                int y = hull.length - (cord.y + Math.abs(lowestHeight)) - 1;
                int x = cord.x + Math.abs(lowestWidth);

                hull[y][x] = '#';
            }
        }

        System.out.println(Util.toString2dArray(hull));

    }

}

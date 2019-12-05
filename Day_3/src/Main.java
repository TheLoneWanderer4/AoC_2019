import AOC.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {

    public static Wire parseWire(String path){
        String[] wirePath = path.split(",");

        Wire wire = new Wire();

        for(String turn : wirePath){
            char direction = turn.charAt(0);
            int distance = Integer.parseInt(turn.substring(1));

            Cord start = wire.getEnd();
            Cord end = null;

            switch (direction){
                case 'R':
                    end = start.right(distance);
                    break;
                case 'L':
                    end = start.left(distance);
                    break;
                case 'D':
                    end = start.down(distance);
                    break;
                case 'U':
                    end = start.up(distance);
                    break;
            }

            wire.add(end);

        }
        return wire;
    }

    public static void main(String[] args){
        String[] input = Input.getData();

        Wire one = parseWire(input[0]);
        Wire two = parseWire(input[1]);

        List<Cord> intersections = one.calcIntersections(two);

        int min = Integer.MAX_VALUE;
        int minSteps = Integer.MAX_VALUE;

        for(Cord x : intersections){
            int dist = x.distFrom0();
            if(dist < min){
                min = dist;
            }

            int steps = one.getStepsTo(x) + two.getStepsTo(x);

            if(steps < minSteps){
                minSteps = steps;
            }

        }
        System.out.println(min);
        System.out.println(minSteps);
    }
}

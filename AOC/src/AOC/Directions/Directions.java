package AOC.Directions;

import java.util.Arrays;
import java.util.List;

public class Directions {
    private static List<cardinal> directions = Arrays.asList(cardinal.WEST,
            cardinal.NORTH,
            cardinal.EAST,
            cardinal.SOUTH);

    private static cardinal get(int i, int next){
        if(next < directions.size() && next >= 0){
            return directions.get(next);
        }else if(next >= directions.size()){
            return directions.get(next - directions.size());
        }else{
            return directions.get(directions.size() + next);
        }
    }

    public static cardinal rotateLeft(cardinal curr){
        int index = directions.indexOf(curr);

        return get(index, index-1);
    }

    public static cardinal rotateRight(cardinal curr){
        int index = directions.indexOf(curr);

        return get(index, index+1);
    }
}
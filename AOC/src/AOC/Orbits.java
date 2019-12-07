package AOC;

import java.util.ArrayList;
import java.util.List;

public class Orbits {
    public static int distanceFromXtoY(String x, String y, UnDGraph orbits){
        List<String> ret = new ArrayList<>();
        fromXtoYHelp(x, y, orbits, ret);
        return ret.size() - 1;
    }

    private static boolean fromXtoYHelp(String x, String y, UnDGraph orbits, List<String> soFar){
        if(orbits.getNeighbors(x).contains(y)){
            return true;
        }else{
            for(String choice : orbits.getNeighbors(x)){
                if(!(soFar.contains(choice))){
                    soFar.add(choice);
                    if(fromXtoYHelp(choice, y, orbits, soFar)){
                        return true;
                    };
                    soFar.remove(choice);
                }
            }
        }
        return false;
    }
}

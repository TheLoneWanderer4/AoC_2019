import AOC.UnDGraph;
import AOC.Orbits;
import AOC.Input;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String[] input = Input.getData();

        UnDGraph orbits = new UnDGraph();

        for(String line : input) {
            String[] hold = line.split(("\\)"));
            orbits.addEdge(hold[0], hold[1]);
        }

        int distance = Orbits.distanceFromXtoY("YOU", "SAN", orbits);
        System.out.println(distance);
    }
}

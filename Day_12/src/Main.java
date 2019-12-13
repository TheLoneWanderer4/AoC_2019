import AOC.Body;
import AOC.Math.Vector;
import AOC.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Body> parseInput(String[] input){
        List<Body> ret = new ArrayList<>();

        for(String x : input){
            String[] comps = x.split(",");

            int[] components = new int[3];
            int i = 0;

            for(String comp : comps){
                String[] data = comp.split("=");
                int component = Integer.parseInt(data[1].replace(">", ""));
                components[i] = component;
                i+=1;
            }

            Vector startPos = new Vector(components[0], components[1], components[2]);
            Body toAdd = new Body(startPos, new Vector(0,0,0));

            ret.add(toAdd);

            components = new int[3];
            i = 0;

        }
        return ret;
    }

    public static int getTotalEnergy(List<Body> system){
        int ret = 0;
        for(Body b : system){
            ret += b.getEnergy();
        }

        return ret;
    }

     public static long computeReturnToBase(List<Body> system){

        List<Body> baseState = new ArrayList<>();
        for(Body b : system){
            baseState.add(new Body(b));
        }

        int finalI = 0;
        int finalJ = 0;
        int finalK = 0;

        int index = 0;

        while(finalI == 0 || finalJ == 0 || finalK == 0){
            for(Body b : system){
                b.applyGravity(system);
            }
            for(Body b : system){
                b.applyVelocity();
            }

            index+=1;

            if(finalI == 0 && Body.compareSystemI(system, baseState)){
                finalI = index;
            }

            if(finalJ == 0 && Body.compareSystemJ(system, baseState)){
                finalJ = index;
            }

            if(finalK == 0 && Body.compareSystemK(system, baseState)){
                finalK = index;
            }
        }

        return AOC.Math.Numbers.lcm(AOC.Math.Numbers.lcm(finalI, finalJ), finalK);
    }

    public static void partOne(String[] input){
        List<Body> bodies = parseInput(input);

        int i = 0;

        while(i < 1000){
            for(Body b : bodies){
                b.applyGravity(bodies);
            }
            for(Body b : bodies){
                b.applyVelocity();
            }

            i+=1;
        }

        System.out.println(getTotalEnergy(bodies));
    }

    public static void partTwo(String[] input){
        List<Body> bodies = parseInput(input);

        System.out.println(computeReturnToBase(bodies));
    }

    public static void main(String[] args) {
        String[] input = Util.Input.getData();
        partOne(input);
        partTwo(input);
    }
}

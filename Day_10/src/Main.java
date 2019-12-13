import AOC.Math.Cord;
import AOC.Math.Vector;
import AOC.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static char[][] parseMap(String[] input){
        int width = input[0].length();
        int height = input.length;

        char[][] ret = new char[height][width];

        for(int row = 0; row < input.length; row++){
            for(int col = 0; col < input[row].length(); col++){
                ret[row][col] = input[row].charAt(col);
            }
        }

        return ret;
    }

    public static List<Cord> getCordsOfAllChar(char toGet, char[][] map){
        List<Cord> ret = new ArrayList<>();
        for(int row = map.length-1; row >= 0; row--){
            for(int col = 0; col < map[row].length; col++){
                if(map[row][col] == toGet){
                    ret.add(new Cord(row, col));
                }
            }
        }
        return ret;
    }

    public static List<Vector> getDisplacements(Cord c, List<Cord> cords){
        List<Vector> ret = new ArrayList<>();
        for(Cord cord : cords){
            if(!cord.equals(c)){
                ret.add(new Vector(cord.x - c.x, cord.y - c.y));
            }
        }
        return ret;
    }

    public static List<Vector> calcViable(Cord toCheck, List<Cord> asteroids){
        int ret = 0;

        List<Vector> pathToAllAsteroids = getDisplacements(toCheck, asteroids);

        List<Vector> goodVectors = new ArrayList<>();

        for(Vector v : pathToAllAsteroids) {
            boolean test = true;
            for (Vector q : pathToAllAsteroids) {
                if (v.sameDirection(q) && !q.equals(v)) {
                    test = false;
                }
            }
            if (test) {
                goodVectors.add(v);
            } else {
                boolean test2 = true;
                Vector toRemove = null;
                for (Vector x : goodVectors) {
                    if (v.sameDirection(x)) {
                        if (v.length() < x.length()){
                            toRemove = x;
                        }else{
                            test2 = false;
                        }
                    }
                }
                if (test2) {
                    goodVectors.add(v);
                }
                if(toRemove != null){
                    goodVectors.remove(toRemove);
                }
            }
        }

        return goodVectors;
    }

    public static Cord mostDetectableAsteroids(List<Cord> asteroids){
        int ret = Integer.MIN_VALUE;
        Cord max = null;
        for(Cord c : asteroids){
            List<Vector> hold = calcViable(c, asteroids);
            if(hold.size() > ret){
                ret = hold.size();
                max = c;
            }
        }
        return max;
    }

    public static Cord zap(Vector laser, Cord station, List<Cord> asteroids, char[][] map){
        Cord asteroid = new Cord(station.x - laser.getJ(), station.y + laser.getI());
        asteroids.remove(asteroid);
        map[station.x - laser.getJ()][station.y + laser.getI()] = '.';
        return asteroid;
    }

    public static Cord partOne(char[][] map){
        List<Cord> allAsteroids = getCordsOfAllChar('#', map);

        Cord newStation = mostDetectableAsteroids(allAsteroids);
        System.out.println(calcViable(newStation, allAsteroids).size());

        return newStation;
    }

    public static Cord partTwo(char[][] map, Cord station, int x){
        List<Cord> asteroids = getCordsOfAllChar('#', map);
        asteroids.remove(station);

        int i = 0;

        while(!asteroids.isEmpty()){
            List<Vector> lasers = calcViable(station, asteroids);
            Collections.sort(lasers);
            for(Vector v : lasers){
                Cord dead = zap(v, station, asteroids, map);
                i += 1;
                if(i == x){
                    return dead;
                }
            }
        }

        // return null if X asteroid is never reached
        return null;
    }


    public static void main(String[] args) {
        String[] input = Util.Input.getData();
        char[][] map = parseMap(input);

        Cord newStation = partOne(map);
        Cord xAsteroid = partTwo(map, newStation, 200);
        System.out.println(xAsteroid.y *100 + xAsteroid.x);
    }



}


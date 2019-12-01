import AOC.*;
public class Main {


    public static void main(String[] args){
        String[] input = Input.getData();
        int ret = 0;
        for(String x : input){
            ret += calcRequiredFuel(Integer.valueOf(x));
        }
        System.out.println(ret);

    }

    public static int calcRequiredFuel(int mass){
        int fuel = calcFuelMass(mass);
        int fuelForFuel = calcFuelFuel(fuel);
        return fuel + fuelForFuel;
    }

    public static int calcFuelMass(int mass){
        return mass / 3 - 2;
    }
    public static int calcFuelFuel(int fuelMass){
        int fuel = calcFuelMass(fuelMass);
        System.out.println(fuel);
        if(fuel <= 0){
            return 0;
        }else{
            return fuel + calcFuelFuel(fuel);
        }
    }

}

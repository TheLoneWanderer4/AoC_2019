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
        int fuelForInitMass = calcFuelForMass(mass);
        int fuelForFuel = calcFuelForFuel(fuelForInitMass);
        return fuelForInitMass + fuelForFuel;
    }

    public static int calcFuelForMass(int mass){
        return mass / 3 - 2;
    }

    public static int calcFuelForFuel(int fuelMass){
        int currFuel = calcFuelForMass(fuelMass);
        System.out.println(currFuel);
        if(currFuel <= 0){
            return 0;
        }else{
            return currFuel + calcFuelForFuel(currFuel);
        }
    }

}

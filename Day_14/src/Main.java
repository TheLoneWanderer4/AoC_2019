import AOC.Util;

import java.util.*;

public class Main {

    private static Map<Component, List<Component>> reactions = new HashMap<>();
    private static Map<String, Long> available = new HashMap<>();
    private static long oreMade = 0L;

    public static void parse(String[] input){
        for(String recipe : input)
        {
            String[] parts = recipe.split("=>");

            String[] inputsSplit = parts[0].trim().split(",");
            List<Component> inputs = new ArrayList<>();
            for(String in : inputsSplit)
            {
                String[] inComponentSplit = in.trim().split(" ");
                Component inComponent = new Component(inComponentSplit[1], Integer.parseInt(inComponentSplit[0]));
                inputs.add(inComponent);
            }

            String[] output = parts[1].trim().split(" ");
            Component outComponent = new Component(output[1], Integer.parseInt(output[0]));
            reactions.put(outComponent, inputs);
        }
    }

    public static void request(String chem, long num){
        Component output = null;
        for(Component key : reactions.keySet()){
            if(key.name.equals(chem)){
                output = key;
            }
        }

        long multi = 0;

        long made = available.computeIfAbsent(output.name, k -> 0L);

        while(made < num){
            multi++;
            made += output.amount;
        }

        for(Component input : reactions.get(output)){
            long needed = input.amount * multi;

            if(input.name.equals("ORE")){
                oreMade += needed;
            }else{
                request(input.name, needed);
                available.put(input.name, available.get(input.name) - needed);
            }
        }

        available.put(chem, made);
    }

    public static void main(String[] args) {
        String[] input = Util.Input.getData();
        parse(input);

        request("FUEL", 1);
        System.out.println("PART 1: " + oreMade);

        long low = 0;
        long high = 9917776L;
        while(low < high)
        {
            long mid = (low + high) / 2;
            oreMade = 0;
            available.clear();

            request("FUEL", mid);

            if(oreMade > 1000000000000L)
                high = mid - 1;
            else if(oreMade < 1000000000000L)
                low = mid + 1;

            System.out.println("Apart: " + (high - low));
        }

        System.out.println("PART 2: " + low + " " + high);
    }
}

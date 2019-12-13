package AOC.Math;

public class Numbers {

    public static long gcf(long a, long b) {
        while (a != b) // while the two numbers are not equal...
        {
            // ...subtract the smaller one from the larger one

            if (a > b) a -= b; // if a is larger than b, subtract b from a
            else b -= a; // if b is larger than a, subtract a from b
        }

        return a; // or return b, a will be equal to b either way
    }

    public static long lcm(long a, long b) {
        // the lcm is simply (a * b) divided by the gcf of the two

        return (a * b) / gcf(a, b);
    }


    public static boolean hasAdjacent(int num){
        return hasAdjacentHelp(num/10,num%10);
    }
    private static boolean hasAdjacentHelp(int num, int past){
        if(num % 10 == past){
            int hold = past;
            int count = 0;
            System.out.println(num + "-> " + hold + " " + count + " " + past);
            while(hold == past){
                num = num / 10;
                hold = num % 10;
                count += 1;
                if(num == 0){
                    return count < 2;
                }
                System.out.println(num + "-> " + hold + " " + count + " " + past);
            }
            System.out.println();
            return count < 2 || hasAdjacentHelp(num/10,num%10);
        }else if(num < 10){
            return false;
        }
        else{
            return hasAdjacentHelp(num/10,num%10);
        }
    }

    public static boolean isIncreasing(int num){
        return isIncreasingHelp(num/10,num%10);
    }
    private static boolean isIncreasingHelp(int num, int past){
        if(num < 10) {
            return num <= past;
        }else{
            return num%10 <= past && isIncreasingHelp(num/10,num%10);
        }
    }
}

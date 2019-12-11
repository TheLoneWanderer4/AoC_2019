package AOC.Math;

public class Numbers {
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

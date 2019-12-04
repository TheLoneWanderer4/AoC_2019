import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wire {
    private List<Cord> path;
    private Cord end;

    public Wire(){
        path = new ArrayList<>();
        end = new Cord(0,0);
    }

    public List<Cord> getList(){
        return path;
    }

    private List<Cord> genLineBetweenCords(Cord one, Cord two){
        List<Cord> ret = new ArrayList<>();

        int start = 0;
        int end = 0;

        boolean horizontal = false;

        if(one.x == two.x){
            start = one.y;
            end = two.y;
        }else if(one.y == two.y){
            horizontal = true;
            start = one.x;
            end = two.x;
        }else{
            System.out.println("Unknown path");
        }

        for(int i = start; (start>end ? i>=end : i<=end); i = (start>end ? i-1 : i+1)){
            Cord newCord = horizontal ? new Cord(i,one.y) : new Cord(one.x,i);
            if(!path.contains(newCord)){
                ret.add(newCord);
            }
        }

        return ret;
    }

    public void add(Cord c){
        path.addAll(genLineBetweenCords(end,c));
        end = c;
    }

    public Cord getEnd(){
        return end;
    }

    public List<Cord> calcIntersections(Wire other){
        List<Cord> ret = new ArrayList<>();
        for(Cord x : path){
            if(other.getList().contains(x)){
                ret.add(x);
            }
        }
        return ret;
    }

    public int getStepsTo(Cord c){
        if(c.equals(new Cord(0,0))){
            return Integer.MAX_VALUE/2;
        }else{
            int ret = 0;
            for(Cord x : path){
                if(x.equals(c)){
                    return ret;
                }
                ret += 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String ret = "";
        for(Cord x : path){
            ret += x;
        }
        return ret;
    }
}

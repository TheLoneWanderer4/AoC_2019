package AOC.Math;

public class Cord {
    public int x;
    public int y;

    public Cord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cord right(int distance){
        return new Cord(x + distance, y);
    }

    public Cord left(int distance){
        return new Cord(x - distance, y);
    }

    public Cord up(int distance){
        return new Cord(x, y + distance);
    }

    public Cord down(int distance){
        return new Cord(x, y - distance);
    }

    public int distFrom0(){
        if(x == 0 && y == 0){
            return Integer.MAX_VALUE;
        }
        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public String toString() {
        return "(" +
                x +
                "," + y +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cord cord = (Cord) o;
        return x == cord.x && y == cord.y;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x) * Integer.hashCode(y);
    }
}

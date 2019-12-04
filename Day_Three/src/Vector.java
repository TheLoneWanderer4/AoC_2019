public class Vector {
    private int start;
    private int end;

    public Vector(int start, int end){
        this.start = start;
        this.end = end;
    }

    public double getLength(){
        return (Math.sqrt(start * start + end * end));
    }

    public Cord intersect(Vector other){
        return null;
    }

    @Override
    public String toString() {
        return "<" +
                start +
                "," + end +
                '>';
    }
}

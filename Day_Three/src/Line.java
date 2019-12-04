public class Line {
    private Cord start;
    private Cord end;

    public Cord getEnd(){
        return end;
    }

    public Line(Cord start, Cord end){
        this.start = start;
        this.end = end;
    }
    public double length(){
        int x = end.x - start.x;
        int y = end.y - start.y;
        return Math.sqrt(x * x + y * y);
    }

    public boolean isHorz(){
        return start.y == end.y;
    }

    public boolean isVert(){
        return start.x == end.x;
    }

    public boolean contains(Line other){
        if(isHorz() && other.isVert()){
            int otherStart = Math.max(other.start.y, other.end.y);
            int otherEnd = Math.min(other.start.y, other.end.y);

            return start.y >= otherStart|| end.y <= otherEnd;
        }else if(isVert() && other.isHorz()){
            int otherStart = Math.max(other.start.x, other.end.x);
            int otherEnd = Math.min(other.start.x, other.end.x);

            return start.x >= otherEnd|| end.x <= otherStart;
        }else{
            return false;
        }
    }

    public Cord intersect(Line other){
        if(!contains(other)){
            return null;
        }

        if(isHorz() && other.isVert()){
            return new Cord(other.start.x, start.y);
        }else if(isVert() && other.isHorz()){
            return new Cord(start.x, other.start.y);
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

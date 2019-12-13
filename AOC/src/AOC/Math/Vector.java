package AOC.Math;

public class Vector implements Comparable{
    private int i;
    private int j;
    private int k;

    /*
    This constructor is used for the lasers, at some point fix that program
    public Vector(int j, int i){
        this.i = i;
        this.j = j*-1;
    }
     */

    public Vector(int i, int j, int k){
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Vector(Vector v){
        i = v.getI();
        j = v.getJ();
        k = v.getK();
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }

    public int getK(){
        return k;
    }

    public void setI(int newI){
        i = newI;
    }

    public void setJ(int newJ){
        j = newJ;
    }

    public void setK(int newK){
        k = newK;
    }

    public boolean sameDirection(Vector vector){
        return (i == vector.i ? Integer.signum(j) == Integer.signum(vector.j) :
                Integer.signum(i) == Integer.signum(vector.i)) && isParallel(vector);
    }

    public boolean isParallel(Vector other){
        return this.i * other.j == this.j * other.i;
    }

    public double length(){
        return Math.sqrt((i*i) + (j*j));
    }

    public boolean equals(Vector v){
        return i == v.i && j == v.j && k == v.k;
    }

    public String toString(){
        return  "<" + i + "," + j + "," + k +">";
    }

    public double angleWithJ(){
        if(i == 0 && j < 0){
            return Math.PI;
        }else {
            double ret = 0;
            if(i < 0){
                ret = 2 * Math.PI - Math.acos((j) / (length()));
            }else{
                ret = Math.acos((j) / (length()));
            }
            return ret;
        }
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Vector)){
            return  Integer.MIN_VALUE;
        }else{
            Vector other = (Vector) o;

            return (int) ((angleWithJ() - other.angleWithJ())*100000);
        }
    }
}

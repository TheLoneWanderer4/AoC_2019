package AOC;

import AOC.Math.Vector;

import java.util.List;

public class Body {
    Vector p;
    Vector v;

    public Body(Vector p, Vector v){
        this.p = p;
        this.v = v;
    }

    public Body(Body b){
        p = new Vector(b.p);
        v = new Vector(b.v);
    }

    private void applyGravityHelperI(Body b){
        if(p.getI() > b.p.getI()){
            v.setI(v.getI() - 1);
        } else if (p.getI() < b.p.getI()) {
            v.setI(v.getI() + 1);
        }
    }

    private void applyGravityHelperJ(Body b){
        if(p.getJ() > b.p.getJ()){
            v.setJ(v.getJ() - 1);
        } else if (p.getJ() < b.p.getJ()) {
            v.setJ(v.getJ() + 1);
        }
    }

    private void applyGravityHelperK(Body b){
        if(p.getK() > b.p.getK()){
            v.setK(v.getK() - 1);
        } else if (p.getK() < b.p.getK()) {
            v.setK(v.getK() + 1);
        }
    }

    public void applyGravity(List<Body> otherBodies){
        for(Body b : otherBodies){
            if(!equals(b)){
                applyGravityHelperI(b);
                applyGravityHelperJ(b);
                applyGravityHelperK(b);
            }
        }
    }

    public boolean compareI(Body two){
        return p.getI() == two.p.getI()
                && v.getI() == two.v.getI();
    }


    public boolean compareJ(Body two){
        return p.getJ() == two.p.getJ()
                && v.getJ() == two.v.getJ();
    }


    public boolean compareK(Body two){
        return p.getK() == two.p.getK()
                && v.getK() == two.v.getK();
    }

    public void applyVelocity(){
        p.setI(p.getI() + v.getI());
        p.setJ(p.getJ() + v.getJ());
        p.setK(p.getK() + v.getK());
    }

    public int getEnergy(){
        int potential = Math.abs(p.getI()) +
                        Math.abs(p.getJ()) +
                        Math.abs(p.getK());

        int kinetic = Math.abs(v.getI()) +
                      Math.abs(v.getJ()) +
                      Math.abs(v.getK());

        return potential * kinetic;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Body)){
            return false;
        }else{
            Body o = (Body) obj;
            return o.v.equals(v) && o.p.equals(p);
        }
    }

    @Override
    public String toString() {
        return p + " " + v;
    }

    // Static methods for use in related problems
    public static boolean compareSystemI(List<Body> one, List<Body> two){
        for(int body = 0; body < Math.min(one.size(), two.size()); body++){

            Body toTest = one.get(body);
            Body base = two.get(body);

            if(!(toTest.compareI(base))){
                return false;
            }
        }
        return true;
    }

    public static boolean compareSystemJ(List<Body> one, List<Body> two){
        for(int body = 0; body < Math.min(one.size(), two.size()); body++){

            Body toTest = one.get(body);
            Body base = two.get(body);

            if(!(toTest.compareJ(base))){
                return false;
            }
        }
        return true;
    }

    public static boolean compareSystemK(List<Body> one, List<Body> two){
        for(int body = 0; body < Math.min(one.size(), two.size()); body++){

            Body toTest = one.get(body);
            Body base = two.get(body);

            if(!(toTest.compareK(base))){
                return false;
            }
        }
        return true;
    }
}

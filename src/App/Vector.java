package App;

import java.awt.*;

public class Vector {
    public static double x;
    public static double y;

    public Vector(double gx, double gy){
        x = gx;
        y = gy;
    }


    public Vector add(Vector givenVector){return new Vector (x+ givenVector.x, y- givenVector.y);}
    public Vector sub(Vector givenVector){return new Vector (x- givenVector.x, y+ givenVector.y);}
    public Object distance(Vector givenVector){
        return null;
    }
}

package App;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import static java.lang.Math.*;


public class Debris extends GameWindow {
    double G = 6.674 * Math.pow(10, -11);
    double mass;
    Point central;
    Point origin;
    double radius;
    double acceleration;
    double velocity;
    Vector currentVector = new Vector(-10,0);

    public Debris(Point gCentral, double gmass, double gradius) {
        central = gCentral;
        mass = gmass;
        radius = gradius;
        origin = new Point((int) (central.getX() - radius), (int) (central.getY() - radius));
    }


    public Ellipse2D getDebris() {
        return new Ellipse2D.Double(origin.getX(), origin.getY(), radius * 2, radius * 2);
    }

    public void calculateLocation(Planet[] planets){
        /*
            F = G * m1 * m2 / r^2
            F = ma
            a = Gm2/r^2
         */
       for(Planet planet: planets){
           double xDist = Math.abs(origin.getX() - planet.getX());
           double yDist = Math.abs(origin.getY() - planet.getY());
           double dist = Math.sqrt(xDist * xDist + yDist * yDist) / FPS;
           double angle = Math.atan(xDist/yDist);
           double xConst = dist*Math.sin(angle);
           double yConst = dist*Math.cos(angle);

           System.out.println("X: "+xDist+"     Y:"+dist*Math.cos(angle));
           Vector changeVector = new Vector(xConst*planet.getMass()/(dist*dist), yConst*planet.getMass()/(dist*dist));
           currentVector.add(changeVector);

       }
       origin = new Point((int)(origin.getX()+ currentVector.x),(int)( origin.getY()+ currentVector.y));
    }


    public double getMass() {
        return mass;
    }

    public Point getOrigin() {
        return origin;
    }

    public double getRadius() {
        return radius;
    }
}

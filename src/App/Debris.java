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


    public Debris(Point gCentral, double gmass, double gradius) {
        central = gCentral;
        mass = gmass;
        radius = gradius;
        origin = new Point((int) (central.getX() - radius), (int) (central.getY() - radius));
    }


    public Ellipse2D getDebris() {
        return new Ellipse2D.Double(central.getX() - radius, central.getY() - radius, radius * 2, radius * 2);
    }

    public void calculateLocation(Planet[] planets){
        /*
            F = G * m1 * m2 / r^2
            F = ma
            a = Gm2/r^2
         */

        for(Planet planet: planets){
            double x1 = planet.getOrigin().getX();
            double y1 = planet.getOrigin().getY();
            double x2 = origin.getX();
            double y2 = origin.getY();

            double distance =Math.pow( Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)), 100000);

            double acceleration = G*planet.getMass() / (distance * distance);
            acceleration = acceleration/60;
            velocity += acceleration;

            double xChange = 0;
            double yChange = -(velocity);

            origin = new Point((int) (origin.getX()+ xChange), (int)(origin.getY() + yChange));

        }
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

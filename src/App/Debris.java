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
    Vector currentVector;

    public Debris(Point gCentral, double gmass, double gradius, double gvelocity, Vector gVector) {
        central = gCentral;
        mass = gmass;
        radius = gradius;
        origin = new Point((int) (central.getX() - radius), (int) (central.getY() - radius));
        velocity = gvelocity;
        double xChange = gvelocity * Math.sin(gVector.getAngle());
        double yChange = gvelocity * Math.cos(gVector.getAngle());
        currentVector = new Vector(xChange, yChange);
    }

    public Ellipse2D getDebris() {
        return new Ellipse2D.Double(origin.getX(), origin.getY(), radius * 2, radius * 2);
    }

    public void calculateLocation(Planet[] planets) {
        /*
            F = G * m1 * m2 / r^2
            F = ma
            a = Gm2/r^2
         */
        for (Planet planet : planets) {
            double xDist = (planet.getOrigin().getX() - central.getX());
            double yDist = (planet.getOrigin().getY() - central.getY());
            double angle = Math.atan2(xDist, yDist);

            double dist = Math.sqrt(xDist * xDist + yDist * yDist) * Math.pow(10, 7);
            double acceleration = (planet.mass * G) / (dist * dist);

            double xChange = acceleration * Math.sin(angle) / FPS;
            double yChange = acceleration * Math.cos(angle) / FPS;


            if(xDist == 0 ){
                xChange = 0;
            }
            if(yDist == 0 ){
                yChange = 0;
            }
            if(dist < (planet.radius + radius)*Math.pow(10, 7)){
                xChange = 0;
                yChange = 0;
            }


            System.out.println(
                            "     YDist: " + yDist+
                            "     XDist:"+xDist+
                            "     xChange:"+xChange+
                            "     yChange:"+yChange+
                            //"     Angle:"+Math.toDegrees(angle) +
                            //"     Radius:"+(planet.radius+radius)+
                            //"     DISTANCE:"+dist+
                            "     xVelocity:"+currentVector.getX()+
                            "     yVelocity:"+currentVector.getY()
            );


            currentVector.add(new Vector(xChange, yChange));
        }
        //System.out.println("   XChange:"+currentVector.getX() + "   YChange:"+currentVector.getY());
        origin = new Point((int)(origin.getX() + currentVector.getX()), (int)(origin.getY() + currentVector.getY()));
        central = new Point((int)(origin.getX() + radius),(int)(origin.getY() + radius));
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
    public Vector getVector(){
        return currentVector;
    }
}

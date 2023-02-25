package App;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class Debris extends GameWindow {
    //Init all the required variables for future use
    double G = 6.674 * Math.pow(10, -11);
    double mass;
    Point central;
    Point origin;
    double radius;
    double velocity;
    Vector currentVector;
    String name;
    boolean crashed = false;
    boolean isplanet;

    //Creating the instance once called
    public Debris(Point gCentral, double gmass, double gradius, double gvelocity, Vector gVector, String gname, boolean gisplanet) {
        central = gCentral;
        mass = gmass;
        radius = gradius;
        origin = new Point((int) (central.getX() - radius), (int) (central.getY() - radius));
        velocity = gvelocity;
        double xChange = gvelocity * Math.sin(gVector.getAngle());
        double yChange = gvelocity * Math.cos(gVector.getAngle());
        currentVector = new Vector(xChange, yChange);
        name = gname;
        isplanet = gisplanet;
    }

    //Returning the actual particle itself in order to be drawn properly
    public Ellipse2D getDebris() {
        return new Ellipse2D.Double(origin.getX(), origin.getY(), radius * 2, radius * 2);
    }

    public void calculateLocation(Debris[] planets) {
        /*
            Equations that were used:
            F = G * m1 * m2 / r^2
            F = ma
            a = Gm2/r^2
         */
        for (Debris planet : planets) {
            if (planet != this && !crashed) {

                //Variables used for the calculation
                double xDist = (planet.getOrigin().getX() - central.getX());
                double yDist = (planet.getOrigin().getY() - central.getY());
                double angle = Math.atan2(xDist, yDist);

                double dist = Math.sqrt(xDist * xDist + yDist * yDist) * Math.pow(10, 7);
                double acceleration = (planet.mass * G) / (dist * dist);

                double xChange = acceleration * Math.sin(angle) / FPS;
                double yChange = acceleration * Math.cos(angle) / FPS;

                //Checking if the objects are within the radius and crashing them if they get too close
                if(dist < (50)*Math.pow(10, 7) && !isplanet){
                    xChange = 0;
                    yChange = 0;
                    currentVector.x = 0;
                    currentVector.y = 0;
                    central = planet.getOrigin();
                    origin = planet.getCentral();
                    radius = 0;
                    if(!crashed){
                        System.out.println("Oh no!   "+name+"   crashed!!!");
                        crashed = true;
                    }
                }

                //Adding the velocity vector to the change vector :)
                currentVector = currentVector.add(new Vector(xChange, yChange));
            }

            //Setting the updated location :)
            origin = new Point((int) (origin.getX() + currentVector.getX()), (int) (origin.getY() + currentVector.getY()));
            central = new Point((int) (origin.getX() + radius), (int) (origin.getY() + radius));
        }
    }

    private Point getCentral() {return central;}
    public double getMass() {return mass;}
    public Point getOrigin() {return origin;}
    public double getRadius() {return radius;}
    public Vector getVector(){
        return currentVector;
    }
}

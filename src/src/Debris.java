package src;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;


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
    double Z;
    Color currentColor;
    Random rand = new Random();
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
        currentColor = new Color(rand.nextInt(0,255),rand.nextInt(0,255),rand.nextInt(0,255));
    }

    //Returning the actual particle itself in order to be drawn properly
    public Ellipse2D getDebris() {
        return new Ellipse2D.Double(origin.getX() - xOffset, origin.getY() - yOffset, radius * 2, radius * 2);
    }
    public void calculateLocation(Debris[] planets) {
        /*
            Equations that were used:
            F = G * m1 * m2 / r^2
            F = ma
            a = Gm2/r^2
         */
        if(!crashed){
            for (Debris planet : planets) {
                if (planet != this && !crashed) {

                    //Variables used for the calculation
                    double xDist = (planet.getOrigin().getX() - origin.getX() + planet.getRadius());
                    double yDist = (planet.getOrigin().getY() - origin.getY()) + planet.getRadius();
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
                            crashed = true;
                        }
                    }

                    //Adding the velocity vector to the change vector :)
                    currentVector = currentVector.add(new Vector(xChange, yChange));
                }

                //Setting the updated location :)

                int RedValue;

                if(planets.length <= 1){
                    RedValue =(int) Math.min((float) 20 * Math.sqrt(currentVector.getX()*currentVector.getX() + currentVector.getY()*currentVector.getY()), 255);
                }else{
                    RedValue =(int) Math.min((float) 15 * Math.sqrt(currentVector.getX()*currentVector.getX() + currentVector.getY()*currentVector.getY()), 255);

                }

                int GreenValue = Math.abs(255-RedValue);
                int BlueValue = (int) Math.min(Math.max(0,(RedValue-225) * 100),225);

                currentColor = new Color(RedValue, GreenValue, BlueValue);

                origin = new Point((int) (origin.getX() + currentVector.getX()), (int) (origin.getY() + currentVector.getY()));
                central = new Point((int) (origin.getX() + radius), (int) (origin.getY() + radius));
            }
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

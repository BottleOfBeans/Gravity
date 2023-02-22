package App;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Planet extends GameWindow {
    double mass;
    Point central;
    Point origin;
    double radius;


    public Planet(Point gCentral, double gmass, double gradius){
        central = gCentral;
        mass = gmass;
        radius = gradius;
        origin = new Point((int) (central.getX()-radius), (int) (central.getY() - radius));
    }




    public Ellipse2D getPlanet(){
        return new Ellipse2D.Double(origin.getX(), origin.getY(), radius*2, radius*2);
    }




    public double getMass() {
        return mass;
    }
    public Point getOrigin(){
        return central;
    }
    public double getRadius(){
        return radius;
    }
}

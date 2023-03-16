package src;
// =================================
// INNER CLASSES TO CREATE LISTENERS
// =================================

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Creates mouse input listener for panel.
 */
public class MouseInputListener implements MouseListener
{
    public int counter = GameWindow.spawnobjects;
    @Override
    public void mouseClicked(MouseEvent e)
    {

        // Create graphics object to paint figures on it.
        Random rand = new Random();
        int SOffset = rand.nextInt(15);
        int DOffset = rand.nextInt(-2,2);
        double Mass = 1.0*Math.pow(10,24);
        GameWindow.debriss[counter] = new Debris(new Point(e.getX(), e.getY()),Mass, 5,SOffset, new Vector(DOffset,0), "Object "+counter, false);
        counter ++;
        GameWindow.debriss[counter] = new Debris(new Point(e.getX(), e.getY()),Mass, 5,SOffset, new Vector(DOffset,0), "Object "+counter, false);
        counter++;
        GameWindow.debriss[counter] = new Debris(new Point(e.getX(), e.getY()),Mass, 5,SOffset, new Vector(DOffset,0), "Object "+counter, false);
        counter++;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ;
    }
}

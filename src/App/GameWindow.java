package App;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class GameWindow extends JPanel  implements Runnable{
    /*
        Game Tile Size shows the tile that each of the images on screen should be in pixels, this is not the amount that are displayed in each tile but rather the orignal one
        Scalable value shows the value that is used to upscale the original images on the screen
        Game Column and Row amounts show the amount of rows and columns that can be displayed on screen at one time
     */
    static int GameTileSize = 16;
    static int ScalableValue = 3;
    static int gameColumnAmount = 16;
    static int gameRowAmount = 12;


    /*
        ActualTileSize uses the variables from above to calculate how big each tile actually should be
        Game Width and Height takes into account the columns and rows and calculates the window heights and widths using all the factors provided above
     */
    static int ActualTileSize = GameTileSize * ScalableValue;
    static int gameWidth = gameColumnAmount*ActualTileSize;
    static int gameHeight = gameRowAmount*ActualTileSize;

    Thread gameThread;

    //Game Values
    int FPS = 60;

    //Planetary Object Values
    Planet Sun = new Planet(new Point(gameWidth/2, gameHeight/2), 10, 50 );
    Debris Object = new Debris(new Point(gameWidth/2, gameHeight/2 + 100), 10, 10);


    public GameWindow(){
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startWindowThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update(){
        ;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;


        graphics.setColor(Color.yellow);
        graphics.fill(Sun.getPlanet());


        graphics.setColor(Color.white);
        Object.calculateLocation(new Planet[]{
                Sun
        });
        graphics.fill(Object.getDebris());




        graphics.dispose();
    }
}
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
    static Debris Sun = new Debris(new Point(gameWidth/2-300, gameHeight/2), 2*Math.pow(10,30), 50, 0, new Vector(0,0), "Sun", true);
    static Debris Sun2 = new Debris(new Point(gameWidth/2+300, gameHeight/2), 2*Math.pow(10,30), 50, 0, new Vector(0,0), "Sun", true);

    //Debris Object Values
    static Debris Object0 = new Debris(new Point(gameWidth/2, gameHeight/2 + 100), 6.0*Math.pow(10,24), 10,11, new Vector(1,0), "Object 0", false);
    static Debris Object1 = new Debris(new Point(gameWidth/2+100, gameHeight/2 + 100), 6.0*Math.pow(10,24), 10,11, new Vector(1,0), "Object 1", false);
    static Debris Object2 = new Debris(new Point(gameWidth/2+150, gameHeight/2 + 75), 6.0*Math.pow(10,24), 10,11, new Vector(1,0), "Object 2", false);
    static Debris Object3 = new Debris(new Point(gameWidth/2+90, gameHeight/2 + 100+85), 6.0*Math.pow(10,24), 10,8, new Vector(1,0), "Object 3", false);

    //Arrays containing all of the debris and the planets
    static Debris[] planets = {Sun, Sun2};
    static Debris[] debriss = {Object0, Object1, Object2, Object3};

    //Creating the game windows and setting up the settings
    public GameWindow(){
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    //Starting thread, managing frame updates
    public void startWindowThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Loop that runs the thread, allows for it to sleep and start and ensures proper frame speed
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

    //Function that paints the updated version of the frame {FPS} times a second.
    public void paintComponent(Graphics g){

        //Quick definition of varibles to use with the G2D library
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;

        //Filling in the SUN
        graphics.setColor(Color.darkGray);
        for(Debris objectt: planets){
            objectt.calculateLocation(planets);
            graphics.fill(objectt.getDebris());
        }


        //Filling in the debris
        graphics.setColor(Color.white);
        for(Debris objectt: debriss){
            objectt.calculateLocation(planets);
            graphics.fill(objectt.getDebris());
        }

        //Stopping the use of the library to ensure that no more processing power than needed is used
        graphics.dispose();
    }
}
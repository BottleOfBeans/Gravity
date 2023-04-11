package src;

import javax.swing.*;
import java.util.Scanner;


public class Main {
    public static void main(String args []){
        /*
            Setting up the JFrame Window
            Resizeable --> False
            Close Operation --> Exit On Close
            Window Name --> (WindowName)
            Window Visibility --> True
         */
        double planets = 0;
        double suns = 0;

        Scanner obj  = new Scanner(System.in);
        System.out.println("Enter the amount of planets you want between 1 and 1000000 (You can also click later to add more!) ");
        while(planets <= 0){
            System.out.print("Enter Here:");
            planets = obj.nextInt();
            if(planets >= GameWindow.maxobjects){
                planets = 0;
            }
        }

        GameWindow.spawnobjects = (int) planets;

        System.out.println("Enter the amount of suns you want (1 or 2) ");
        while(suns <= 0){
            System.out.print("Enter Here:");
            suns = obj.nextInt();
            if(suns > 2){
                suns = 0;
            }
        }
        if(suns == 2){
            GameWindow.planets = new Debris[]{GameWindow.Sun1, GameWindow.Sun2};
        }else{
            GameWindow.planets = new Debris[]{GameWindow.Sun0};
        }


        if(planets > 0) {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setResizable(true);
            window.setTitle("Fun Basic Gravity Simluation Thingy");
            GameWindow gameWindow = new GameWindow();
            window.add(gameWindow);
            window.setUndecorated(true);
            window.pack();
            window.setVisible(true);
            gameWindow.startWindowThread();
        }
    }

}

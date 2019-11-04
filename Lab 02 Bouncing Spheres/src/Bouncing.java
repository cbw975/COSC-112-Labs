
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Bouncing extends JPanel{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;
    double positionX;
    double positionY;
    	
    double posx, posy, velx, vely, accelx, accely; //for the secodn sphere!!

    //Note: The following are not used yet, you should use them in writing your code.
    double velocityX;
    double velocityY;

    double accelerationX;
    double accelerationY;
    
    class Runner implements Runnable{
        public Runner()
        {
            //Feel free to change these default values
            positionX = 275;
            positionY = HEIGHT - 275;
            velocityX = 10;
            velocityY = -10;
            accelerationY = -9.8;
            //your code here for adding the second sphere
            posx=59;   posy=83;   velx=25;   vely=25;   accelx=0;  accely=-9.8;
        }
        public void run()
        {
            while(true){
                //your code here
            	
                //Implement Movement  here
            	positionX += velocityX;
                positionY += velocityY;
                
                posx += velx;   posy+=vely;
        
                //Implement bouncing here
                boolean wall = true;    boolean wall2=true;
                if((wall) && ((positionY<=0)||(positionY>=HEIGHT))) {velocityY*= -1; wall=false; } //hits top/bottom, velocityY becomes negative
                if((wall) && (positionX<=0)||(positionX >= WIDTH)) {velocityX *= -1; wall=false; } //hits sides, velocityX
                
                if((wall2)&&(posy<=0 || posy>=HEIGHT)) { vely *= -1; wall2=false; }
                if((wall2)&&(posx<=0 || posx>=WIDTH)) { velx *= -1; wall2=false; }
                
                //Implement gravity here (Bonus) - half of gravity is subtracted
                

                //don't mess too much with the rest of this method
                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }
        }    
    }
    
    public Bouncing(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }
    public static void main(String[] args){
        JFrame frame = new JFrame("Physics!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Bouncing world = new Bouncing();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);            

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //The cannon you see is actually *not* a photograph of a real cannon.
        //It's drawn by the following. 
        g.setColor(Color.ORANGE);
        int xpts[] = {75, 275, 275, 350, 325, 150};
        int ypts[] = {HEIGHT-50, HEIGHT-250, HEIGHT-275, HEIGHT- 175, HEIGHT-175, HEIGHT-25};
        g.fillPolygon(xpts, ypts, 6);
                
        g.setColor(Color.BLUE);
        g.fillOval(150, HEIGHT-200, 200, 200);

        //this is where the sphere is drawn. As a bonus make it draw something else
        // (e.g., your object from the previous lab).
        g.setColor(Color.WHITE);
        g.drawOval((int)positionX, (int)positionY,  RADIUS,  RADIUS);
        //your code here for drawing the second sphere
        g.setColor(Color.GREEN);
        g.drawOval((int)posx, (int)posy,  RADIUS,  RADIUS);
    }    
}
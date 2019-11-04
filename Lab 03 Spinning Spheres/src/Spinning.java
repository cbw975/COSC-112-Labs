//Chloe Wohlgemuth   COSC 112   Lab 02
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;

public class Spinning extends JPanel{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 25;
    public static final int RADIUS2 = 10;
    public static final int RADIUS3 = 50;
    public static final double CENTERX = WIDTH / 2.0;
    public static final double CENTERY = HEIGHT / 2.0;
    
    //Right now spheres isn't being used
    public static Sphere[] spheres;
    
public Spinning(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    public void run(){

	while(true){
		for (Sphere s : spheres){
		      s.update(1.0 / (double)FPS);
		    }
	    //don't mess too much with the rest of this method
	    repaint();
	    try{
		Thread.sleep(1000/FPS);
	    }
	    catch(InterruptedException e){}
	}
    }
    
    public static void main(String[] args){
	int numSpheres = 60;
	if (args.length < 1){
	    System.out.println("When you run this, you can specifiy the number of spheres.");
	    System.out.println("e.g., java Spinning 10");
	}
	else{
	    System.out.println("You specified that there should be " + args[0] + " spheres.");
	    numSpheres = Integer.parseInt(args[0]);
	}

	//here you'll set up the spheres array
	spheres = new Sphere[numSpheres];
	for(int i=0; i<spheres.length; i++) { //fills array
		spheres[i]= new Sphere(); }
	
        JFrame frame = new JFrame("Spinning Spheres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Spinning world = new Spinning();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
	world.run();
    }

public void paintComponent(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    for (Sphere s : spheres){
      s.draw(g); }
	}
}
//You'll implement this class
class Sphere{
    //Put fields here:
    private double posX, posY, velX, velY, deltaX, deltaY;
    private int radius, clockwise;
    private Color color;
    
    Random rand = new Random();
    //Color class takes 3 floats, from 0-1, to make color
    float r=rand.nextFloat();    float b=rand.nextFloat();    float g=rand.nextFloat();
    
    public Sphere() { //This is the constructor
    posX= (int)(Math.random()*650+300);  posY=(int)(Math.random()*700+275); 
    velX= (int)(Math.random()*30-15); velY= (int)(Math.random()*31-15); //rand num bw -15 and 15
    radius= (int)(Math.random()*21+5); //radius is set to random number bw 5-25 
    color = new Color(r,b,g); 
    deltaX=0; deltaY=0;
    clockwise = (int) (Math.pow(-1, (int)(Math.random()*2))); //either -1 or 1 --> determines direction or rotations
    }

    
    //Put methods (update, draw, and whatever else you decide to implement) here:
    public void setColor(Color c) { color=c; }
    public void setRadius(int r) { radius=r; }
    public void setPosX(double x) { posX=x; }
    public void setPosY(double y) { posY=y; }
    public void setVelX(double vx) { velX=vx; }
    public void setVelY(double vy) { velY=vy; }
    public void setDeltaX(double dx) { deltaX=dx; }
    public void setDeltaY(double dy) { deltaY=dy; }
    
    public Color getColor() { return color; }
    public int getRadius() { return radius; }
    public double getPosX() { return posX; }
    public double getPosY() { return posY; }
    public double getVelX() { return velX; }
    public double getVelY() { return velY; }
    public double getDeltaX() { return deltaX; }
    public double getDeltaY() { return deltaY; }
    
    public void update(double time) {
    	deltaX = posX - Spinning.CENTERX; //deltaX = posX - CENTERX; from original
    	deltaY = posY - Spinning.CENTERY; //deltaY = posY - CENTERY; from original
    	velX = -clockwise * deltaY; //velocityX = -deltaY; from original
    	velY = clockwise * deltaX; 		//velX and velY are opposite for circular motions
    	posX += velX * time; //positionX += velocityX * 1.0/(double)FPS; from original
    	posY += velY * time; 
    }
    public void draw(Graphics g) {
    	g.setColor(color);
    	g.fillOval((int)(posX),(int)(posY), 2*radius,2*radius); 
    }
}

//Chloe Wohlgemuth   COSC 112    Prof. Alfeld   S19
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
class Pair{
    public double x;
    public double y;
     
    public Pair(double initX, double initY){
    x = initX;
    y = initY;
    }

    public Pair add(Pair toAdd){
    return new Pair(x + toAdd.x, y + toAdd.y);
    }
 
    public Pair divide(double denom){
    return new Pair(x / denom, y / denom);
    }
 
    public Pair times(double val){
    return new Pair(x * val, y * val);
    }
 
    public void flipX(){
    x = -x;
    }
     
    public void flipY(){
    y = -y;
    }
}
 
class Sphere{
    Pair position;
    Pair velocity;
    Pair acceleration;
    double radius;
    double dampening;
    Color color;
    public Sphere()
    {
    Random rand = new Random(); 
    position = new Pair(500.0, 500.0);
    velocity = new Pair((double)(rand.nextInt(1000) - 500), (double)(rand.nextInt(1000) - 500));
    acceleration = new Pair(0.0, 200.0);
    radius = 25;
    dampening = 1.3;
    color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }
    public void update(World w, double time){
    position = position.add(velocity.times(time));
    velocity = velocity.add(acceleration.times(time));
    bounce(w);
    }
     
    public void setPosition(Pair p){
    position = p;
    }
    public void setVelocity(Pair v){
    velocity = v;
    }
    public void setAcceleration(Pair a){
    acceleration = a;
    } 
    public void draw(Graphics g){
    Color c = g.getColor();
     
    g.setColor(color);
    g.drawOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
    g.setColor(c);
    }
    private void bounce(World w){
    Boolean bounced = false;
    if (position.x - radius < 0){
        velocity.flipX();
        position.x = radius;
        bounced = true;
    }
    else if (position.x + radius > w.width){
        velocity.flipX();
        position.x = w.width - radius;
        bounced = true;
    }
    if (position.y - radius < 0){
        velocity.flipY();
        position.y = radius;
        bounced = true;
    }
    else if(position.y + radius >  w.height){
        velocity.flipY();
        position.y = w.height - radius;
        bounced = true;
    }
    if (bounced){
        velocity = velocity.divide(dampening);
    }
    }
     
}
 
class World{
    int height;
    int width;
     
    int numSpheres;
    Sphere spheres[];
 
    public World(int initWidth, int initHeight, int initNumSpheres){
    width = initWidth;
    height = initHeight;
 
    numSpheres = initNumSpheres;
    spheres  = new Sphere[numSpheres];
     
    for (int i = 0; i < numSpheres; i ++)
        {
        spheres[i] = new Sphere();
        }
 
    }
 
    public void drawSpheres(Graphics g){
    for (int i = 0; i < numSpheres; i++){
        spheres[i].draw(g);
    }
    }
 
    public void updateSpheres(double time){
    for (int i = 0; i < numSpheres; i ++)
        spheres[i].update(this, time);
    }
 
}
 
public class KeyboardSpheres extends JPanel implements KeyListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;
    
    //ADDITIONAL FIELDS:
    private static char accDir = 's'; //store direction that called methods before inc/dec accel
    private static double accMag = 100; //store strength of acceleration 
    
    class Runner implements Runnable{
    public void run()
    {
        while(true){
        world.updateSpheres(1.0 / (double)FPS);
        repaint();
        try{
            Thread.sleep(1000/FPS);
        }
        catch(InterruptedException e){}
        }
 
    }
     
    }
 
 
    public void keyPressed(KeyEvent e) {
        //char c=e.getKeyChar(); //System.out.println("You pressed down: " + c);
    }
    public void keyReleased(KeyEvent e) {
        char c=e.getKeyChar(); //System.out.println("\tYou let go of: " + c);
        setAccel(c);
        if(((int)(c) >= 48) && ((int)(c) <= 57)) setColors(c);
        additionalEffects(c);
    }

 
    public void keyTyped(KeyEvent e) {
    //char c = e.getKeyChar(); //System.out.println("You typed: " + c);
    }
     public void addNotify() {
        super.addNotify();
        requestFocus();
    }

//ADDED METHODS
    public void setAccel(char v){ 
    //wsad keys each set gravity (ALL) to be one of the four cardinal directions
        for(int i=0; i<world.spheres.length;i++){
            if(v=='w'){ world.spheres[i].acceleration = new Pair(0,-accMag); KeyboardSpheres.accDir = v; }
            if(v=='s'){ world.spheres[i].acceleration = new Pair(0,accMag); KeyboardSpheres.accDir = v; }
            if(v=='a'){ world.spheres[i].acceleration = new Pair(-accMag,0); KeyboardSpheres.accDir = v; }
            if(v=='d'){ world.spheres[i].acceleration = new Pair(accMag,0); KeyboardSpheres.accDir = v; } } }
    public void additionalEffects(char v){
    //CHALLENGE: Make ijkl control a single sphere directly
        int in = 0; //index of sphere to be controlled individually
        if(v=='i') world.spheres[in].acceleration = new Pair(0,-accMag);
        if(v=='k') world.spheres[in].acceleration = new Pair(0,accMag);
        if(v=='j') world.spheres[in].acceleration = new Pair(-accMag,0);
        if(v=='l') world.spheres[in].acceleration = new Pair(accMag,0);
    //CHALLENGE: e increase gravity, q decrease gravity
        if(v=='e') accMag += 9.8;
        if(v=='q') accMag -= 9.8;
            if(v=='e' || v=='q') this.setAccel(KeyboardSpheres.accDir); //because need to update accel
        if(accMag<0) accMag=0;
        }
    public void setColors(char v){
        Color col = Color.BLACK;
        Random r = new Random(v);
        for (int i = 0; i < world.spheres.length; i++){
            col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            if(col != Color.BLACK) world.spheres[i].color = col;
        }
    } 

    public KeyboardSpheres(){
    world = new World(WIDTH, HEIGHT, 50);
    addKeyListener(this);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
    }
     
    public static void main(String[] args){
    JFrame frame = new JFrame("Physics!!!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    KeyboardSpheres mainInstance = new KeyboardSpheres();
    frame.setContentPane(mainInstance);
    frame.pack();
    frame.setVisible(true);
    }
 
 
    public void paintComponent(Graphics g) {
    super.paintComponent(g);        
 
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
 
    world.drawSpheres(g);
 
    }
}
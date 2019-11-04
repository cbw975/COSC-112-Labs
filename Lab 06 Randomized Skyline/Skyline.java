
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;

public class Skyline extends JPanel{
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1024;
    public static final int HEIGHT=768;
    public static void main(String[] args){
        JFrame frame = new JFrame("Skyline");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Skyline());
        frame.pack();
        frame.setVisible(true);
    }
    public Skyline(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    @Override
    public void paintComponent(Graphics gOri){
        Graphics2D g = (Graphics2D) gOri;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint sunSet= new GradientPaint(0, 0, Color.BLACK, 0, HEIGHT, new Color(55, 0, 0));
        g.setPaint(sunSet);
        g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));

        Random rand = new Random();
        //Your code here

    //The horizon - column by column
        Color col_H = Color.PINK; //horizon color
        Color col_S = Color.YELLOW; //Star color
        g.setColor(col_H); //color of the horizon
        int height = HEIGHT-(rand.nextInt(11)+95); 
        int starPos = rand.nextInt(height)+1;
        
        //Horizon
        g.setColor(col_H);
        g.drawLine(0, height, 0, HEIGHT); //leftmost horizon col - height bw 95-105
        for(int col=rand.nextInt(4); col<WIDTH; col++){
            height = height + (rand.nextInt(11) - 5); //horizon heights
            g.drawLine(col, height, col, HEIGHT); } //horizon lines
        
        //Stars
        g.setColor(col_S);
        //potentially make a star in every 4th column after (randomly) 1st/2nd/3rd column
        for(int col=rand.nextInt(4); col<WIDTH; col+=4){ 
            starPos = rand.nextInt(height);
            //0-2 pixel wide star per col:
            g.drawLine(col, starPos, col+rand.nextInt(2), starPos+rand.nextInt(2)); }
        //Star cluster 
            //--> center point is within  13 pixels of actual center
            // stars in cluster are within 13 pixels of the cluster center
        int cenX = (int)((WIDTH/2)+rand.nextInt(27)-13); 
        int cenY = (int)((HEIGHT/2)+rand.nextInt(27)-13);
        int xPos, yPos;
        g.setColor(Color.ORANGE); //the star cluster color
        for(int s=1; s<=100; s++){ 
            xPos = cenX + (int)(rand.nextGaussian()*27 - 13);
            yPos = cenY + (int)(rand.nextGaussian()*27 - 13);
            g.drawLine(xPos, yPos, xPos + rand.nextInt(2), yPos+rand.nextInt(2));
        }        
        
    }
}
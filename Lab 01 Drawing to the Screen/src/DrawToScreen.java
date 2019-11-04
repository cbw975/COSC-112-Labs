//Chloe Wohlgemuth COSC 112 Lab 01
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class DrawToScreen extends JPanel{
    public static final int BOX_WIDTH = 1000; //each drawing in grid=200
    public static final int BOX_HEIGHT = 600; //each drawing in grid=120

    public DrawToScreen(){
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
    }
    
    //Your code here, if you want to define additional methods.

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);            
        //Your code here: feel free to remove what is below

        int x=0;
        int y = 0;  //Top-Left cursor for where to start each drawing
        //Making 5x5 grid of drawing:
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
        for(int r=0;r<5;r++){ //each row of grid
            for(int c=0;c<5;c++){
                g.setColor(Color.GREEN);
                g.fillOval(x+20, y+17, 7,9); //left eye
                g.fillOval(x+38, y+17, 7,9); //right eye

                g.setColor(Color.RED);
                g.fillRect(x+5, y+5, 7, 15); //left ear
                g.fillRect(x+55, y+5, 7, 15); //right ear

                g.setColor(Color.BLUE);
                g.drawRoundRect(x+11, y+4, 45, 50, 5, 10); //face
                
                x+=70;
            }
            x=0;
            y += 70;
        }
    }
    
    public static void main(String args[]){
        JFrame frame = new JFrame("DrawToScreen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new DrawToScreen());
        frame.pack();
        frame.setVisible(true);
    }
}
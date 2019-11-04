
import java.util.Random;
import java.util.ArrayList;

class Circle{
    double centerX;
    double centerY;
    double radius;
    public Circle(double x, double y, double r){
        this.centerX = x;
        this.centerY = y;
        this.radius = r;
    }
}

class Field{
    public static double MIN_WIDTH, MIN_HEIGHT, MAX_WIDTH, MAX_HEIGHT, AREA; //the minimum and maximum widths and heights
    public static int positiveSamples; }

public class MonteCarlo{
    private static ArrayList<Circle> circs = new ArrayList<Circle>();
    static Random rand = new Random();
    public static void main(String[] args){
        //ArrayList<Circle> circs = new ArrayList<Circle>();
        circs.add(new Circle(1, 1, 1));
        circs.add(new Circle(1, 2, 1));
        System.out.println("Estimated area:");
        System.out.println(estimateArea(circs, 100));

        setMinMax(); //set the dimensions of the field
    }

    private static void setMinMax(){
        //Setting the min width and height
        for(int i = 0; i < circs.size(); i++) {
    		if (i == 0) {
                Field.MIN_HEIGHT = circs.get(i).centerY - circs.get(i).radius;
                Field.MIN_WIDTH = circs.get(i).centerX - circs.get(i).radius;
                Field.MAX_WIDTH = circs.get(i).centerX + circs.get(i).radius;
                Field.MAX_HEIGHT = circs.get(i).centerY + circs.get(i).radius;
            }else if(circs.get(i).centerX - circs.get(i).radius < Field.MIN_WIDTH) //min width
                Field.MIN_WIDTH = circs.get(i).centerX - circs.get(i).radius;
            else if(circs.get(i).centerY - circs.get(i).radius < Field.MIN_HEIGHT) //min height
                Field.MIN_HEIGHT = circs.get(i).centerY - circs.get(i).radius;
            else if(circs.get(i).centerX + circs.get(i).radius > Field.MAX_WIDTH) //max width
                Field.MAX_WIDTH = circs.get(i).centerX + circs.get(i).radius;
            else if(circs.get(i).centerY + circs.get(i).radius > Field.MAX_HEIGHT) //max height
                Field.MAX_HEIGHT = circs.get(i).centerY + circs.get(i).radius; }
        }
    
    private static double sample(double min, double max){
        //This method returns a random number between min and max
        return min + (max - min) * rand.nextDouble();
    }
    private static boolean isIn(double x, double y, Circle c){
        //This method returns true if the point (x, y) is within the circle c, and false otherwise
        double dist = Math.sqrt(Math.pow(x - c.centerX, 2) + Math.pow(y - c.centerY, 2));
        return dist <= c.radius;
    }
    public static double estimateArea(ArrayList<Circle> circles, int numSamples){
    //YOUR CODE HERE
    for(int i = 0; i < numSamples; i++) {
        double sampleX = MonteCarlo.sample(Field.MIN_WIDTH, Field.MAX_WIDTH);
        double sampleY = MonteCarlo.sample(Field.MIN_HEIGHT, Field.MAX_HEIGHT);
        for(Circle circle : circles) {
            if(MonteCarlo.isIn(sampleX, sampleY, circle)) {
                Field.positiveSamples++;
                break; } } }
    Field.AREA = (Field.MAX_WIDTH - Field.MIN_WIDTH) * (Field.MAX_HEIGHT - Field.MIN_HEIGHT);
    double areaBlob = ((double) Field.positiveSamples)/((double)numSamples)*Field.AREA;
    //Remove double-coutning when paintComponent does
    Field.positiveSamples = 0;
    return areaBlob; }
}
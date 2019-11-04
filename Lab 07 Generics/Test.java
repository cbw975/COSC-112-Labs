
public class Test {
    public static void main(String args[]){
        MyGenericDS<MyGenericDS> ds = new MyGenericDS<MyGenericDS>();
        //DS 1:
        MyGenericDS<String> one = new MyGenericDS<String>();
            one.append("Zero");     one.append("Two");  one.append("Three");
        //DS 2:
        MyGenericDS<Sphere> two = new MyGenericDS<Sphere>();
            two.append(new Sphere(1)); two.append(new Sphere(2)); two.append(new Sphere(3));

        MyGenericDS<MyGenericDS> three = new MyGenericDS<MyGenericDS>();
            three.append(one);  three.append(two);
        
        System.out.println(three);
    }
    public static void testGenericDS(GenericOrderedCollection<MyGenericDS> ds){

    }
    public static void makeDS(GenericOrderedCollection<MyGenericDS> ds){
        
    }
}

public class MyGenericDS<E> implements GenericOrderedCollection<E>{
    private node<E> first;
    private int length;
    
    public MyGenericDS(){ //constructor
        this.first=null; this.length=0; }
    
    public node<E> last(){
        node<E> last = this.first;
        while(last.next != null) last=last.next; 
        return last;}

    public void append(E toAppend){ //takes value and inserts it at end of collection
        if(this.length==0){ 
            this.first = new node<E>(toAppend); 
            this.length++;
        }else{
            this.last().next = new node<E>(toAppend);
		    this.length++; } } //each time add entry, increment length counter
            
    public void remove(int index){ //removes the index-th element from the collection (0 to length-1)
        if(index >= this.length){ System.out.println("INVALID INDEX TO DELETE"); return; }
        if(this.length==0) return;//If empty, return 0 and leave unchanged
        if(this.length==1){ length--; return; } //works because logical size =0, and toString depends on logical size
        if(index==this.length-1){ this.pop(); 
        }else{
            node<E> temp = this.first;
            for(int i=0; i<index-1; i++) temp=temp.next; //getting to node before the remove
            //System.out.println("Should be value before index: "+temp.value);
            temp.next=temp.next.next; //skip over the node at the index
            //System.out.println("Should be value after removed one: "+temp.next.value);
            temp=temp.next;
            //System.out.println("Hopefully, same as above:"+temp.value);
            if(temp.next != null){
                while(temp.next != null){ 
                    temp=temp.next; }//shifting everything over
            length--; }
            else{ length--; return; } }
    }
        
    public E peek(){ //returns value at end of collection. if empty, return null
        if(this.length==0){ return null; //if empty, return null
        }else{ return this.last().value; } } //return the entry's value

    public E pop(){ //returns value at end of collection, and removes it. If empty, return 0 and leave unchanged
        if(this.length <=0){ return null;//If empty, return 0 and leave unchanged
        }else{
            E old = last().value;
            node<E> temp = this.first;
            while(temp.next != null) temp=temp.next;
            length--;
            return old; } }
        
    public int length(){ return this.length; }//returns the number of elements in collection

    public String toString(){ // returns String, each int in collection (in the order added) separated by spaces
        String result="";
        node<E> i = this.first;
        if(this.length == 0) return ""; //if empty collection, the return nothing
        for(int c=0; c<length; c++){
            //System.out.print(i.value+" & ");
            result += i.value+" ";
                i = i.next; }
        return result; }

}

class node<V>{
    public V value;
    public node<V> next;

    public node(){ next=null; value=null; }
    public node(V v){ //constructor
        next=null; //node after this one - this line occurs by default, so don't need it
        value=v; } //the value for this node
    public String toString(){
        return value+"";
    }
}

class Sphere{
    private int radius;
    public Sphere(){ this.radius=5; }
    public Sphere(int r){ this.radius = r; }
    public int getRadius(){ return radius; }
    public double getVol(){ return (4.0/3.0)*Math.PI*Math.pow(radius,3); }
    public String toString(){ 
        return "Sphere(r="+this.getRadius()+", v= "+this.getVol()+") | "; }

}
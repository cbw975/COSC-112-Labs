public class MyDS extends OrderedCollection{
    private NodeInt first;
    private int length;
    
    public MyDS(){
        this.first=null; this.length=0; }
    
    public NodeInt getLast(){
        NodeInt p = this.first;
        while(p.next != null){
            p=p.next; } //get to last value
        return p; }

    public void append(int toAppend){ //takes int and inserts it at end of collection
        if(length==0){ 
            this.first = new NodeInt(toAppend);
            length++; 
            return; 
        }else{
            this.getLast().next = new NodeInt(toAppend); 
            this.length++;} //each time add entry, increment length counter

        //Once you append a given entry, and the length is then at least 6, check if it is first 6 digits of pi
        if(this.length >= 6 && this.containsPI()){ 
            System.out.println("Who has pi on their face now, Pr0HaX0r?"); } }

            
    public int peek(){ //returns int at end of collection. if empty, return 0
        if(this.length==0){ return 0; //if empty, return 0
        }else{ return this.getLast().value; } //return the entry's value
    } 

    public int pop(){ //returns int at end of collection, and removes it. If empty, return 0 and leave unchanged
        if(this.length <=0){ return 0;//If empty, return 0 and leave unchanged
        }else{ 
            int old = this.getLast().value; //store old value temporarily
            NodeInt temp = this.first;
            boolean flag = true; //true when the last element has not been removed yet

            while(flag==true){ //while current entry being checked is non-null
                // System.out.println("before increment: "+temp.value); //DEBUGGING
                if(temp.next.next==null){
                        //NOTE: cannot just check temp.next and then change (last) temp to null. 
                        //B/c won't change the next of the "this" collection's values
                    // System.out.println("entry to be null is the last: "+(old == temp.next.value)+" which is "+temp.next.value); //DEBUGGING
                    temp.next = null;//since temp.next would be holding the last int
                    flag=false; 
                }else{ temp=temp.next; } } //end while
            this.length--; //decremeent the length after removing a value
            return old; } 
        }
    
    public boolean containsPI(){
        boolean len6; //will make sure that there are still enough values left to check for PI
            int lenRemain = this.length;
            if(lenRemain < 6){ len6=false; }else{ len6=true; } //if length is initially at least 6 (because 314159 requires 6 values)
        for(NodeInt i=this.first; i != null && len6; i=i.next){
            //if the value is 3, then check if the nexts are 1,4,1,5,9
            if((i.value==3)&&(i.next.value==1)&&(i.next.next.value==4)&&(i.next.next.next.value==1)
                && (i.next.next.next.next.value==5) && (i.next.next.next.next.next.value==9))
                return true;
            //recheck if there are still at least 6 NodeInts to check for PI with
            lenRemain--;
            if(lenRemain < 6){ len6=false; }else{ len6=true; }
        } //if loops through whole collection w/o finding pi...
        return false; } 
            
    public String toString(){ // returns String, each int in collection (in the order added) separated by spaces
        String result="";
        if(length==0) return "";
        for(NodeInt i=this.first; i != null; i=i.next){
            result += Integer.toString(i.value)+" "; }
        return result; }

    public int length(){ return this.length; }//returns the number of ints in collection
}

class NodeInt{
    public int value;
    public NodeInt next;

    public NodeInt(int v){ //constructor
        next=null; //node after this one - this line occurs by default, so don't need it
        value=v; } //the int value for this node
    public NodeInt getNext(){return next; }
}


package auction.system;


public class MaxHeap {
    public double []Heap;
    public int capacity;
    public int pos;
    
    public MaxHeap(int capacity){
        pos=-1;
        this.capacity=capacity;
        Heap = new double[this.capacity];
     
    }
    
    public void insert(double newValue){
        
        Heap[++pos] = newValue;
        
        
        int current = pos;
        
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        
        
    }
    public boolean checkValue(double value){
        if(value==Heap[0]){
            return true;
        }else{
            return false;
        }
    }
    
    private int parent(int poss) {
      return (poss - 1) / 2; 
    }
    
    private void swap(int fpos, int spos)
    {
        double tmp;
        
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
 
    }
    
    public void print()
    {
        for (int i = 0; i <= pos; i++) {
            
            System.out.println("Bid value " +Heap[i]);
           
        }
        System.out.println("------------------");
    }
    
     public double getMax(){
        
        return Heap[0];
        
    }
    
   
     public void removeMax(){
        int n=capacity;//9
        double lastElement = Heap[n-1];//8
        
        Heap[0]=lastElement;
        n=n-1;
        check(n,0);
    }
    
    public void check(int x,int i){
        int largest = i;
        int left = 2*i+1;
        int right = 2*i+2;
        
        if (left < x && Heap[left] > Heap[largest])
            largest = left;
 
        
        if (right < x && Heap[right] > Heap[largest])
            largest = right;
 
       
        if (largest != i) {
            double swap = Heap[i];
            Heap[i] = Heap[largest];
            Heap[largest] = swap;
 
            check( x, largest);
        }
    }
}

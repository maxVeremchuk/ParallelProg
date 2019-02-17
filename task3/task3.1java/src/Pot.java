public class Pot {
    private int cap;
    private int curr;
    Pot(int cap){
        this.cap = cap;
        curr = 0;
    }
    public synchronized   void eatAll(){
        System.out.println("Eat all, pot is empty");
        curr = 0;
        notifyAll();
    }
    public synchronized void addHoney(){
        while(isFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        curr++;
    }
    public synchronized boolean isFull(){

        return curr == cap;
    }
}

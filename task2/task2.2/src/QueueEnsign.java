import java.util.LinkedList;
import java.util.Queue;

public class QueueEnsign {
    private int contents;
   // private boolean available = false;
    private Queue<Integer> queue = new LinkedList<>();
    private int size;
    QueueEnsign(int size){
        this.size = size;
    }
    public synchronized int getSize(){
        return queue.size();
    }
    public synchronized int get() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
       // available = false;
        notifyAll();
        //return contents;
        return queue.poll();
    }
    public synchronized void put(int value) {
        while (queue.size() >= size) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        queue.add(value);
       // contents = value;
       // available = true;
        notifyAll();
    }
}
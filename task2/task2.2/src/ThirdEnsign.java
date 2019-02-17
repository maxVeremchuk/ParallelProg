public class ThirdEnsign extends Thread {
    private QueueEnsign queue;
    private volatile boolean isEnded;
    private int sum;
    public ThirdEnsign(QueueEnsign queue) {
        this.queue = queue;

        sum  = 0;
    }
    public void setEnded(boolean value){
        isEnded = value;
    }
    public int getSum(){
        return sum;
    }
    public void run() {
        int value = 0;
        while(!isEnded || queue.getSize() != 0 || !SecondEnsign.queueIsEmpty) {
            value = queue.get();
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
            System.out.println("Consumer third got: " + value);
            sum += value;
        }
    }
}

public class FirstEnsign extends Thread {
    private QueueEnsign queue;
    private volatile boolean isEnded;
    public FirstEnsign(QueueEnsign queue) {
        this.queue = queue;
    }
    public void setEnded(boolean value){
        isEnded = value;
    }
    public void run() {
        while(!isEnded) {
            int value = (int) (Math.random() * 100) + 1;
            queue.put(value);
            System.out.println("Producer first put: " + value);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }
}

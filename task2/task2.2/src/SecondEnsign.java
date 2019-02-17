public class SecondEnsign extends Thread{
    private QueueEnsign queueFromFirst;
    private QueueEnsign queueToThird;
    private volatile boolean isEnded;
    public static boolean queueIsEmpty;
    public SecondEnsign(QueueEnsign queueFromFirst, QueueEnsign queueToThird) {
        this.queueFromFirst = queueFromFirst;
        this.queueToThird = queueToThird;

    }
    public void setEnded(boolean value){
        isEnded = value;
    }
    public void run() {
        int value = 0;
        while(!isEnded || queueFromFirst.getSize() != 0) {
            value = queueFromFirst.get();
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
            System.out.println("Consumer second got: " + value);
            queueToThird.put(value);
        }
        queueIsEmpty = true;
    }
}

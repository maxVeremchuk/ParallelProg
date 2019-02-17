public class Main {
    public static void main(String[] args) throws InterruptedException {
        QueueEnsign firstQueue = new QueueEnsign(5);
        QueueEnsign secondQueue = new QueueEnsign(5);
        FirstEnsign first = new FirstEnsign(firstQueue);
        SecondEnsign second = new SecondEnsign(firstQueue, secondQueue);
        ThirdEnsign third = new ThirdEnsign(secondQueue);

        first.start();
        second.start();
        third.start();

        Thread.sleep(1000);
        first.setEnded(true);
        second.setEnded(true);
        third.setEnded(true);
        Thread.sleep(500);

        System.out.println("Sum of stolen items: " + third.getSum());
    }
}

package producerconsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    private static LinkedList<Integer> q;

    private static final int CAP = 3;

    private Lock lock;

    private Condition emptyCondition, fullCondition;

    public ProducerConsumer() {
        lock = new ReentrantLock();
        emptyCondition = lock.newCondition();
        fullCondition = lock.newCondition();
    }

    public void produce(int p) throws InterruptedException {
        try {
            lock.lock();
            while (q.size() == CAP) {
                fullCondition.await();
            }
            q.add(p);
            System.out.println("Produced " + p + " !");
            emptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Integer consume() throws InterruptedException {
        try {
            lock.lock();
            while (q.isEmpty()) {
                emptyCondition.await();
            }
            Integer m = q.poll();
            System.out.println("Consumed product " + m + " !");
            return m;
        } finally {
            fullCondition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ProducerConsumer pc = new ProducerConsumer();

        q = new LinkedList<>();

        for (int i = 0; i < 10; ++i) {
            final int k = i;
            Thread producer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        pc.produce(k);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread consumer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Integer m = pc.consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            consumer.start();
            producer.start();
        }

    }
}
/*
    Example output:
    Produced 2 !
    Produced 0 !
    Consumed product 2 !
    Produced 1 !
    Produced 3 !
    Consumed product 0 !
    Consumed product 1 !
    Consumed product 3 !
    Produced 5 !
    Produced 4 !
    Produced 7 !
    Consumed product 5 !
    Consumed product 4 !
    Produced 6 !
    Consumed product 7 !
    Produced 8 !
    Produced 9 !
    Consumed product 6 !
    Consumed product 8 !
    Consumed product 9 !
*/

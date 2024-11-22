
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */

public class Main {

        public static void main(String[] args) {
            int numPhilosophers = 5;
            Philosopher[] philosophers = new Philosopher[numPhilosophers];
            Lock[] forks = new ReentrantLock[numPhilosophers];

            for (int i = 0; i < numPhilosophers; i++) {
                forks[i] = new ReentrantLock();
            }

            for (int i = 0; i < numPhilosophers; i++) {
                Lock leftFork = forks[i];
                Lock rightFork = forks[(i + 1) % numPhilosophers];
                philosophers[i] = new Philosopher(i, leftFork, rightFork);
                philosophers[i].start();
            }

            for (Philosopher philosopher : philosophers) {
                try {
                    philosopher.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Главный поток был прерван.");
                }
            }

            System.out.println("Все философы завершили прием пищи.");
        }


}
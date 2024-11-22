
import java.util.concurrent.locks.Lock;

class Philosopher extends Thread {
        private final int id;
        private final Lock leftFork;
        private final Lock rightFork;
        private int mealsEaten;

        public Philosopher(int id, Lock leftFork, Lock rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
            this.mealsEaten = 0;
        }

        private void think() throws InterruptedException {
            System.out.println("Философ " + id + " размышляет.");
            Thread.sleep((long) (Math.random() * 1000));
        }

        private void eat() throws InterruptedException {
            leftFork.lock();
            rightFork.lock();
            try {
                System.out.println("Философ " + id + " ест.");
                Thread.sleep((long) (Math.random() * 1000));
                mealsEaten++;
                System.out.println("Философ " + id + " закончил есть. Общее количество приемов пищи: " + mealsEaten);
            } finally {
                leftFork.unlock();
                rightFork.unlock();
            }
        }

        @Override
        public void run() {
            try {
                while (mealsEaten < 3) {
                    think();
                    eat();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Философ " + id + " был прерван.");
            }
        }
    }



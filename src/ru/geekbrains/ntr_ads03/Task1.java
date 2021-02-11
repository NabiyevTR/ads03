package ru.geekbrains.ntr_ads03;

import ru.geekbrains.ntr_ads03.library.*;

import java.util.concurrent.ThreadLocalRandom;

public class Task1 {
    private static IStack<Task1> tasksStack = new StackImpl<>(10);
    private static IQueue<Task1> tasksQueue = new QueueImpl<>(1000);
    private static IQueue<Integer> priorityQueue = new PriorityQueueImpl<>(10);
    private static int insertCount;
    private static int removeCount;
    private static Object lock = new Object();

    public static void main(String[] args) {

        //EXAMPLE WITH STACK

        for (int i = 0; i < 3; i++) {
            tasksStack.push(new Task1());
            tasksStack.display();
        }

        while (!tasksStack.isEmpty()) {
            tasksStack.pop();
            tasksStack.display();
        }

        //EXAMPLE WITH QUEUE

        Thread insertThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (lock) {
                        if (tasksQueue.insert(new Task1())) {
                            lock.notifyAll();
                            insertCount++;
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                return;
                            }
                        }
                    }
                }
            }
        });


        Thread removeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (lock) {
                        if (tasksQueue.remove() != null) {
                            lock.notifyAll();
                            removeCount++;
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                return;
                            }
                        }
                    }
                }
            }
        });

        insertThread.start();
        removeThread.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        insertThread.interrupt();
        removeThread.interrupt();

        try {
            insertThread.join();
            removeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Inserted: " + insertCount);
        System.out.println("Removed: " + removeCount);
        System.out.println("Left in queue: " + tasksQueue.size());


        //EXAMPLE WITH PRIORITY QUEUE
        for (int i = 0; i < 10; i++) {
            priorityQueue.insert(ThreadLocalRandom.current().nextInt(10, 100));
        }
        priorityQueue.display();
    }


}

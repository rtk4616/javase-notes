package io.github.dunwu.javase.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock lock() 示例
 *
 * @author Zhang Peng
 * @date 2018/5/11
 */
@SuppressWarnings("all")
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Task service = new Task();
        MyThread tA = new MyThread("Thread-A", service);
        MyThread tB = new MyThread("Thread-B", service);
        MyThread tC = new MyThread("Thread-C", service);
        tA.start();
        tB.start();
        tC.start();
    }

    static class MyThread extends Thread {
        private Task task;

        public MyThread(String name, Task task) {
            super(name);
            this.task = task;
        }

        @Override
        public void run() {
            super.run();
            task.execute();
        }
    }


    static class Task {
        private ReentrantLock lock = new ReentrantLock();

        public void execute() {
            lock.lock();
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName());

                    // 查询当前线程保持此锁的次数
                    System.out.println("\t holdCount: " + lock.getHoldCount());

                    // 返回正等待获取此锁的线程估计数
                    System.out.println("\t queuedLength: " + lock.getQueueLength());

                    // 如果此锁的公平设置为 true，则返回 true
                    System.out.println("\t isFair: " + lock.isFair());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

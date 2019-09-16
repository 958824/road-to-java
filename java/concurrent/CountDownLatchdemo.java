package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
倒计时示例
CountDownLatch(int count) //实例化一个倒计数器，count指定计数个数
countDown() // 计数减一
await() //等待，当计数减到0时，所有线程并行执行

我们先生成了一个CountDownLatch实例。计数数量为10，这表示需要有10个线程来完成任务，
等待在CountDownLatch上的线程才能继续执行。latch.countDown();方法作用是通知CountDownLatch有一个线程已经准备完毕，
倒计数器可以减一了。latch.await()方法要求主线程等待所有10个检查任务全部准备好才一起并行执行。
*/
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch latch=new CountDownLatch(10);
    static final CountDownLatchDemo demo=new CountDownLatchDemo();
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor= Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++)
        {
            executor.submit(demo);
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("fire");

        executor.shutdown();
    }
}

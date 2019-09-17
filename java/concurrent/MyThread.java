package concurrent;
/*
测试volatile
* */
public class MyThread extends Thread {
    volatile public static int count;  //volatile可见性 没有原子性
    synchronized private static void addCount(){
        for (int i = 0; i <100; i++) {
            count++;
        }
        System.out.println("count="+count);
    }
    @Override
    public void run(){
        addCount();
    }

    public static void main(String[] args) {
        MyThread[] thread=new MyThread[100];
        for (int i = 0; i <100; i++) {
            thread[i]=new MyThread();
        }
        for (int i = 0; i <100; i++) {
            thread[i].start();
        }
    }

}

package concurrent;

public class Service {
    private boolean isrunning=true;
    public void runMethod(){
        while(isrunning==true) {
            synchronized ("2"){   //synchronized不仅保证互斥，还可以保证可见性
            }
        }
        System.out.println("已停止");
    }
    public void stop()
    {
        isrunning=false;
    }

    public static void main(String[] args) {
        Service service=new Service();
        threadA a=new threadA(service);
        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB b=new threadB(service);
        b.start();
        System.out.println("停止请求");
    }
}
class threadA extends Thread{
    private Service service;
    threadA(Service service)
    {
        this.service=service;
    }
    @Override
    public void run()
    {
        service.runMethod();
    }
}
class threadB extends Thread{
    private Service service;
    threadB(Service service)
    {
        this.service=service;
    }
    @Override
    public void run()
    {
        service.stop();
    }

}

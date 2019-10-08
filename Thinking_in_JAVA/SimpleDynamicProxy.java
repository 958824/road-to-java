package Thinking_in_java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/*
动态代理
 */
interface Interface{
    void doSomething();
    void somthingElse(String arg);
}
class RealObject implements Interface{

    @Override
    public void doSomething() {
        System.out.println("dosomething");
    }

    @Override
    public void somthingElse(String arg) {
        System.out.println("somethingElse "+arg);
    }
}
class DynamicProxyHandler implements InvocationHandler{
    private Object proxied;
    public DynamicProxyHandler(Object proxied)
    {
        this.proxied=proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy: "+proxy.getClass()+", method: "+method+", args: "+args);
        if(args!=null)
        {
            for(Object arg:args)
                System.out.println(arg);
        }
        return method.invoke(proxied,args);
    }
}
public class SimpleDynamicProxy {
    public static void consumer(Interface iface)
    {
        iface.doSomething();
        iface.somthingElse("banana");
    }
    public static void main(String[] args)
    {
        RealObject real=new RealObject();
        consumer(real);
        Interface proxy= (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),new Class[]{Interface.class},
                new DynamicProxyHandler(real));
        consumer(proxy);
        //dosomething
        //somethingElse banana
        //proxy: class Thinking_in_java.$Proxy0, method: public abstract void Thinking_in_java.Interface.doSomething(), args: null
        //dosomething
        //proxy: class Thinking_in_java.$Proxy0, method: public abstract void Thinking_in_java.Interface.somthingElse(java.lang.String), args: [Ljava.lang.Object;@6d6f6e28
        //banana
        //somethingElse banana

    }
}

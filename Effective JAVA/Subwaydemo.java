package effectiveJava;
/*
服务提供者框架
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//进出地铁接口
interface subwayInterface{
    public boolean in();
    public boolean out();
}
//进出地铁服务实现
class subwayImpl implements subwayInterface{

    @Override
    public boolean in() {
        System.out.println("一卡通进入地铁");
        return false;
    }

    @Override
    public boolean out() {
        System.out.println("一卡通出地铁");
        return false;
    }
}
//地铁进出服务提供者接口
interface subwayProviderInterface{
    public subwayInterface getService();
}
//服务提供者实现类
class subwayProviderImpl implements subwayProviderInterface{

    static{
        ServiceManager.registerProvider("一卡通",new subwayProviderImpl());
    }
    @Override
    public subwayInterface getService() {
        return new subwayImpl();
    }
}
//服务提供者注册类
class ServiceManager{
    private static final Map<String,subwayProviderInterface> providers=new ConcurrentHashMap<String,subwayProviderInterface>();

    public static void registerProvider(String name,subwayProviderInterface p)
    {
        providers.put(name,p);
    }

    public static subwayInterface getService(String name){
        subwayProviderInterface p=providers.get(name);

        if(p==null)
            throw new IllegalArgumentException("no provider name");

        return p.getService();
    }

}
public class Subwaydemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("effectiveJava.subwayProviderImpl");
        subwayInterface sw=ServiceManager.getService("一卡通");
        sw.in();
        sw.out();
    }
}

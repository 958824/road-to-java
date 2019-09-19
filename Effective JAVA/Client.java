package effectiveJava;

import javax.xml.ws.WebServiceClient;
import java.util.HashMap;

/*
享元模式实例
 */
//网站抽象类
abstract class website{
    public abstract void use();
}
//网站具体类
class concreteWebsite extends  website{
    private String name="";

    public concreteWebsite(String name)
    {
        this.name=name;
    }
    @Override
    public void use() {
        System.out.println("网站名称："+name);
    }
}
class websiteFactory{
    private HashMap<String,concreteWebsite> pool=new HashMap<>();
    public website getWebsiteInstance(String key){
        if(!pool.containsKey(key))
        {
            pool.put(key,new concreteWebsite(key));
        }
        return pool.get(key);
    }
    public int getwebsiteCount()
    {
        return pool.size();
    }
}
public class Client {
    public static void main(String[] args) {
        websiteFactory factory=new websiteFactory();
        website a=factory.getWebsiteInstance("web");
        a.use();
        website b=factory.getWebsiteInstance("web");
        b.use();
        website c=factory.getWebsiteInstance("linux");
        c.use();
        website d=factory.getWebsiteInstance("DB");
        d.use();
        website e=factory.getWebsiteInstance("DB");
        e.use();
        System.out.println("pool池总数："+factory.getwebsiteCount()); //3
    }
}

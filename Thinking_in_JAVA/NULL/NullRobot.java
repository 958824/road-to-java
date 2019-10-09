package NULL;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

class NullRobotProxyHandler implements InvocationHandler{
    private String nullname;
    private Robot proxied=new NRobot();
    NullRobotProxyHandler(Class<?> type)
    {
        nullname=type.getSimpleName()+" NullRobot";
    }
    private class NRobot implements Null,Robot{

        @Override
        public String name() {
            return nullname;
        }

        @Override
        public String model() {
            return nullname;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied,args);
    }
}

public class NullRobot {
    public static Robot newNullRobot(Class<? extends Robot> type)
    {
        return (Robot) Proxy.newProxyInstance(NullRobot.class.getClassLoader(),new Class[]{Null.class,Robot.class},new NullRobotProxyHandler(type));
    }

    public static void main(String[] args) {
        Robot[] bots={new SnowRemovalRobot("SnowBee"),newNullRobot(SnowRemovalRobot.class)};
        for(Robot bot:bots)
            Robot.Test.test(bot);
    }

}

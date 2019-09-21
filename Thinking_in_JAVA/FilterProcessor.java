package Thinking_in_java;
/*
复用代码方式
适配器模式
 */
class Apply{
    public static void process(Processor p,Object s){
        System.out.println("Using Processor "+p.name());
        System.out.println(p.process(s));
    }
}
interface Processor{
    String name();
    Object process(Object input);
}

//在使用这种适配器方式中，FilterAdapter的构造器接受你所拥有的Filter，然后生成具有你需要的Processor接口的对象。这里FilterAdapter类用到了代理。
//代理：我们将一个成员对象至于所有构造的类中（就像组合），但同时我们在新类中暴露了该成员对象的所有方法（就像继承）。
class FilterAdapter implements Processor {
    Filter filter;
    public FilterAdapter(Filter filter)
    {
        this.filter=filter;
    }
    public String name() {
        return filter.name();
    }
    public Object process(Object input) {
        return filter.process((waveform) input);
    }
}

public class FilterProcessor {
    public static void main(String[] args) {
        waveform w=new waveform();
        Apply.process(new FilterAdapter(new lowPass(3.0)),w);
        Apply.process(new FilterAdapter(new highPass(5.0)),w);
        w=new waveform();
        Apply.process(new FilterAdapter(new lowPass(3.0)),w);
        Apply.process(new FilterAdapter(new highPass(5.0)),w);
    }
}

class Filter{
    public String name()
    {
        return getClass().getSimpleName();
    }
    public waveform process(waveform input)
    {
        return input;
    }
}

class waveform{
    private static long counter;
    private final long id=counter++;
    public String toString(){
        return "waveform "+id;
    }
}

class lowPass extends Filter{
    double cutoff;
    public lowPass(double cutoff)
    {this.cutoff=cutoff;}
    public waveform process(waveform input){
        return input;
    }
}

class highPass extends Filter{
    double cutoff;
    public highPass(double cutoff)
    {
        this.cutoff=cutoff;
    }
    public waveform process(waveform input)
    {
        return input;
    }
}

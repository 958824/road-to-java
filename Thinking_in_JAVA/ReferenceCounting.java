package Thinking_in_java;
/*
引用计数法
对象的清理
 */
class Shared{
    private int refcount=0;
    private static long counter=0;
    private final long id=counter++;
    public Shared(){
        System.out.println("creating "+this);
    }
    public void addRef(){
        refcount++;
    }
    public void dispose()
    {
        if(--refcount==0)
        {
            System.out.println("dispose "+this);
        }
    }
    public String toString(){
        return "Shared "+id;
    }
}
class Composing{
    private Shared shared;
    private static long counter=0;
    private final long id=counter++;
    public Composing(Shared shared)
    {
        System.out.println("creating "+this);
        this.shared=shared;
        this.shared.addRef();
    }
    protected void dispose()
    {
        System.out.println("dispose "+this);
        shared.dispose();
    }
    public String toString()
    {
        return "Composing "+id;
    }
}
public class ReferenceCounting {
    public static void main(String[] args) {
        Shared shared=new Shared();
        Composing[] composings={new Composing(shared),new Composing(shared),new Composing(shared),new Composing(shared)};
        for(Composing c:composings)
        {
            c.dispose();
            /*creating Shared 0
            creating Composing 0
            creating Composing 1
            creating Composing 2
            creating Composing 3
            dispose Composing 0
            dispose Composing 1
            dispose Composing 2
            dispose Composing 3
            dispose Shared 0*/

        }
    }
}

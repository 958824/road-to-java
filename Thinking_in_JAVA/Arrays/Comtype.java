package Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Comtype implements Comparable<Comtype> {
    int i;
    int j;
    private static int count=1;
    public Comtype(int n1,int n2){
        i=n1;
        j=n2;
    }
    public String toString(){
        String result="[i = "+i+", j = "+j+"]";
        if(count++ %3==0)
        {
            result+="\n";
        }
        return result;
    }
    @Override
    public int compareTo(Comtype o) {
        return (i<o.i?-1:(i==o.i?0:1));
    }
    private static Random r=new Random(48);
    public static Generator<Comtype> generator(){
        return new Generator<Comtype>() {
            @Override
            public Comtype next() {
                return new Comtype(r.nextInt(100),r.nextInt(100));
            }
        };
    }

    public static void main(String[] args) {
        Comtype[] a=Generated.array(new Comtype[12],generator());
        System.out.println("before sorting");
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println("After sorting");
        System.out.println(Arrays.toString(a));
    }
}

class Generated {
    public static <T> T[] array(T[] a,Generator<T> gen)
    {
        return new CollectionData<T>(gen,a.length).toArray(a);
    }
    public static <T> T[] array(Class<T> type,Generator<T> gen,int size)
    {
        T[] a=(T[]) java.lang.reflect.Array.newInstance(type,size);
        return new CollectionData<T>(gen,size).toArray(a);
    }
}

interface Generator<T> {
    public T next();
}

class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> gen,int quantity)
    {
        for(int i=0;i<quantity;i++)
            add(gen.next());
    }
    public static <T> CollectionData<T> list(Generator<T> gen,int quantity){
        return new CollectionData<T>(gen,quantity);
    }
}

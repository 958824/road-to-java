package Thinking_in_java;

import java.util.ArrayList;
import java.util.List;
/*
* 泛型类语法
* */
class CountedInteger{
    private static long counter;
    private final long id=counter++;
    public String toString(){
        return Long.toString(id);
    }
}
public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type){
        this.type=type;
    }
    public List<T> create(int num)  {
        List<T> list=new ArrayList<>();
        try {
            for (int i = 0; i <num; i++) {
                list.add(type.newInstance());//使用newInstance的类必须有默认的构造器
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
            FilledList<CountedInteger> fl=new FilledList<>(CountedInteger.class);
        System.out.println(fl.create(15));  //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]

    }
}

package Thinking_in_java;
/*
实现一个iterable类，添加一种或者多种foreach语句中使用这个类的方法
适配器方法
 */

import java.util.*;

class IterableClass implements Iterable<String>
{
    protected String[] words="we are all lost stars, give me a little more".split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index=0;
            @Override
            public boolean hasNext() {
                return index<words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }
            public void remove(){
                throw  new UnsupportedOperationException();
            }
        };
    }
}
public class MultiIterableClass extends IterableClass {
    public Iterable<String> reversed(){
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    private int index=words.length-1;
                    @Override
                    public boolean hasNext() {
                        return index>-1;
                    }


                    @Override
                    public String next() {
                        return words[index--];
                    }
                    public void remove(){
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    public Iterable<String> randomized(){
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                List<String> shuffled=new ArrayList<String>(Arrays.asList(words));
                Collections.shuffle(shuffled,new Random(4));
                return shuffled.iterator();
            }
        };
    }

    public static void main(String[] args) {
        MultiIterableClass mic=new MultiIterableClass();
        for(String s:mic.reversed())
            System.out.print(s+" ");
        System.out.println();
        for(String s:mic.randomized())
            System.out.print(s+" ");
        System.out.println();
        for(String s:mic)
            System.out.print(s+" ");
    }
}

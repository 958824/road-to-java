package Collection;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
//使用linkedhashset 没有定制set，享元并未实现
public class CountingMapData extends AbstractMap<Integer,String> {
    private int size;
    private static String[] chars="A B C D E F G".split(" ");
    public CountingMapData(int size){
        if(size<0)
            this.size=0;
        this.size=size;
    }
    private static class Entry implements Map.Entry<Integer,String>{
        int index;
        Entry(int index){
            this.index=index;
        }
        @Override
        public Integer getKey() {
            return index;
        }

        @Override
        public String getValue() {
            return chars[index];
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Set<Map.Entry<Integer, String>> entrySet() {
        Set<Map.Entry<Integer,String>> entries=new LinkedHashSet<Map.Entry<Integer, String>>();
        for(int i=0;i<size;i++)
        {
            entries.add(new Entry(i));
        }
        return entries;
    }

    public static void main(String[] args) {
        System.out.println(new CountingIntegerList(60));
    }
}

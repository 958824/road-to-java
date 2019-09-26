package Thinking_in_java;

import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {
        List<Integer> list=new LinkedList<Integer>();
        for (int i = 0; i <10; i++) {
            list.add(i);
        }
        System.out.println("1: "+list);
        List<Integer> list2= new ArrayList<>(Arrays.asList(1,3,5,6,7,8,9));
        System.out.println("2: "+list2);
        Iterator<Integer> it=list2.iterator();
        while(it.hasNext())
        {
            System.out.print(it.next()+" ");
        }
        System.out.println();
        System.out.println("================");
        Set<Integer> set= new HashSet<>(Arrays.asList(50,123,1,55,900,256,331,21,90,1000));
        System.out.println("3: "+set);
        Set<Integer> set2=new TreeSet<>(set);
        System.out.println("4: "+set2);
        System.out.println("================");
        Map<Integer,Collection> map=new HashMap<>();
        map.put(1,list);
        map.put(2,list2);
        map.put(3,set);
        map.put(4,set2);
        System.out.println("5: "+ map.keySet());
        System.out.println("6: "+ map.values());
        System.out.println("================");
        Queue<Integer> queue=new LinkedList<>(Arrays.asList(5,3,2,5,3,65,53,4,7));
        System.out.println(queue);
        System.out.println(queue.offer(90));
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);


    }


}

package Thinking_in_java;

import java.util.*;
/*
递归计数
 */
public class TypeCounter extends HashMap <Class<?>,Integer> {
    private Class<?> basetype;
    public TypeCounter(Class<?> basetype)
    {
        this.basetype=basetype;
    }
    public void count(Object obj)
    {
        Class<?> type=obj.getClass();
        if(!basetype.isAssignableFrom(type))
        {
            throw new RuntimeException(obj+" incorrect type: "+type+", should be type or subtype of "+basetype);
        }
        countClass(type);
    }
    private void countClass(Class<?> type)
    {
        Integer quantity=get(type);
        put(type,quantity==null?1:quantity+1);
        Class<?> superClass=type.getSuperclass();
        if(null!=superClass&&basetype.isAssignableFrom(superClass))
        {
            countClass(superClass);
        }
    }
    public String toString(){
        StringBuilder result=new StringBuilder("{");
        for(Map.Entry<Class<?>,Integer> pair:entrySet())
        {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length()-2,result.length());
        result.append("}");
        return result.toString();
    }

    public static void main(String[] args) {
        TypeCounter Counter=new TypeCounter(Pet.class);
        for(Pet pet:Pets.createArray(20)){
            System.out.print(pet.getClass().getSimpleName()+" ");
            Counter.count(pet);
        }
        System.out.println();
        System.out.print(Counter);
    }
}

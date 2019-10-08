package Thinking_in_java;

import java.lang.reflect.Field;
/*
反射修改private域，final域实际在修改时时安全的，运行时系统不抛出任何异常情况下接受任何修改尝试，但是实际上
不会发生任何修改
 */
class WithPrivateFinalField{
    private int i=1;
    private final String s="Im totally safe";
    private String s2="am I safe?";
    public String toString(){
        return "i= "+i+", "+s+", "+s2;
    }
}
public class ModifyingPrivateFields {
    public static void main(String[] args) throws Exception {
        WithPrivateFinalField pf=new WithPrivateFinalField();
        System.out.println(pf);
        System.out.println("=========");

        Field f=pf.getClass().getDeclaredField("i");
        f.setAccessible(true);
        System.out.println("f.getInt(pf): "+f.getInt(pf));
        f.setInt(pf,9);
        System.out.println(pf);
        System.out.println("=========");

        f=pf.getClass().getDeclaredField("s");
        f.setAccessible(true);
        System.out.println("f.get(pf): "+f.get(pf));
        f.set(pf,"no you are not");
        System.out.println(pf);
        System.out.println("=========");

        f=pf.getClass().getDeclaredField("s2");
        f.setAccessible(true);
        System.out.println("f.get(pf): "+f.get(pf));
        f.set(pf,"no,you are not");
        System.out.println(pf);
    }
}

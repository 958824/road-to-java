package Thinking_in_java;

import java.util.*;


/*
动态的instanceof isInstance提供了一种动态的测量对象的途径。
 */
class Pet{
    String name;
    public Pet(){}
    public Pet(String name)
    {
        this.name=name;
    }
}
class Dog extends Pet{
    public Dog(){super();}
    public Dog(String name){super(name);}
}
class Cat extends Pet{
    public Cat(){super();}
    public Cat(String name){super(name);}
}
class horse extends Pet{
    public horse(){super();}
    public horse(String name){super(name);}
}
class PetCreator{
    private Random rand=new Random(30);
    private static final List<Class<? extends Pet>> types=Arrays.asList(Dog.class,Pet.class,horse.class,Cat.class);
    public Pet randomPet(){
        int n = rand.nextInt(types.size());
        try {
            return types.get(n).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
    public Pet[] createArray(int size){
        Pet[] result=new Pet[size];
        for (int i = 0; i <size; i++) {
            result[i]=randomPet();
        }
        return result;
    }

}
class Pets{
    public static final PetCreator creator=new PetCreator();
    public static Pet randomPet(){
        return creator.randomPet();
    }
    public static Pet[] createArray(int size){
        return creator.createArray(size);
    }

}
public class PetCount {
    static HashMap<Class<? extends Pet>,Integer> initialmap=new LinkedHashMap<Class<? extends Pet>,Integer>();
    static{
        initialmap.put(Pet.class,0);
        initialmap.put(Cat.class,0);
        initialmap.put(Dog.class,0);
        initialmap.put(horse.class,0);
    }
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>,Integer>{
        public PetCounter(){
            super(initialmap);
        }
        public void count(Pet pet)
        {
            for(Map.Entry<Class<? extends Pet>,Integer> pair:entrySet())
            {
                if(pair.getKey().isInstance(pet))
                {
                    put(pair.getKey(),pair.getValue()+1);
                }
            }
        }
        public String toString(){
            StringBuilder result=new StringBuilder("{");
            for(Map.Entry<Class<? extends Pet>,Integer> pair:entrySet())
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
            PetCounter petCount=new PetCounter();
            for(Pet pet:Pets.createArray(20)){
                System.out.print(pet.getClass().getSimpleName()+" ");
                petCount.count(pet);
            }
            System.out.println();
            System.out.print(petCount);
        }
    }
}

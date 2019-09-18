package javacore.Comparator;

import java.util.*;

public class ComparatorTest {
    public static void main(String[] args) {
        List<Person> person=new LinkedList<Person>();

        for (int i = 6; i <=10 ; i++) {
            person.add(new Person(i,"first"+(i-5),"last"+i));
        }
        for (int i = 1; i <=5 ; i++) {
            person.add(new Person(i,"first"+i,"last"+i));
        }
        for(Person p:person)
        {
            System.out.println(p.getId()+" "+p.getFirstname()+" "+p.getLastname());
        }
        System.out.println("=====================");
        //现根据firstname排序，相同再根据lastname排序
        //Collections.sort(person, Comparator.comparing(Person::getFirstname).thenComparing(Person::getLastname));
       Collections.sort(person, new Comparator<Person>() {
           @Override
           public int compare(Person o1, Person o2) {
               return o1.hashCode()-o2.hashCode();
           }
       });
       /* Collections.sort(person,Comparator.comparing(Person::getFirstname, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.charAt(o1.length()-1)-o2.charAt(o2.length()-1);
            }
        }));*/
        for(Person p:person)
        {
            System.out.println(p.getId()+" "+p.getFirstname()+" "+p.getLastname());
        }

    }
}

class Person {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String firstname;
    private String lastname;

    public Person(int id,String firstname,String lastname)
    {
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
    }

    @Override
    public int hashCode() {
        super.hashCode();
        return firstname.charAt(firstname.length()-1);
    }
}


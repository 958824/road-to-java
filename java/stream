import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

enum weekday{
    SUN,MON,TUS,WED,THU,FRI,SAT
/*    MON(1),TUS(2),WED(3),THU(4),FRI(5),SAT(6),SUN(0);
    private int value;
    private weekday()
    {
        this.value=value;
    }*/
}
class weeknode{

    private int id;
    private int type;

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private String name;

}
public class ENUM {
    public static void main(String[] args) {
        int a=0;
        //weeknode temp=new weeknode();
        List<weeknode> list=new ArrayList<>();
        for(weekday week:weekday.values())
        {
            weeknode temp=new weeknode();
            temp.setId(a);
            temp.setName("星期"+a);
            temp.setType(week.ordinal());
            list.add(temp);
            a++;
/*            System.out.println(week);     //SUN MON TUS WED THU FRI SAT
            System.out.println(week.ordinal());  //0 1 2 3 4 5 6*/
        }
        //测试stream流
        //过滤type=4的节点
        List<weeknode> list1=list.stream().filter(node ->node.getType()!=4).collect(Collectors.toList()); //星期0 星期1 星期2 星期3 星期5 星期6
        for(weeknode w:list1)
        {
            System.out.print(w.getName()+" ");
        }
        System.out.println();

        Map<Integer,weeknode> list2=list.stream().collect(Collectors.toMap(weeknode::getId, Function.identity()));
        for(Map.Entry<Integer,weeknode> w:list2.entrySet())
        {
            System.out.print(w.getValue().getName()+" ");
        }
    }
}

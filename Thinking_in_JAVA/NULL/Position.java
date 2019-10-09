package NULL;

public class Position {
    private String title;
    private Person person;

    public Position(String jobTitle,Person employee)
    {
        title=jobTitle;
        person=employee;
        if(person==null)
            person=person.NULL;
    }
    public Position(String jobTitle)
    {
        title=jobTitle;
        person=person.NULL;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        if(this.person==null)
        {
            this.person=person.NULL;
        }
    }

    public String toString(){
        return "Position: "+title+" "+person;
    }
}

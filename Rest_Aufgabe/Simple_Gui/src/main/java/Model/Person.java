package Model;

public class Person {

    private String personId;
    private String name;
    private String surname;

    public Person(String personId, String name, String surname) {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

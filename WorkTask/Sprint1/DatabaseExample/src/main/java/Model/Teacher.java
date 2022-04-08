package Model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

import java.util.UUID;

//TODO Add ORM Notation
@DatabaseTable(tableName = "teacher")
public class Teacher {
    @DatabaseField(id = true)
    private String teacherId;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false)
    private String mail;
    @DatabaseField(canBeNull = false)
    private String office;



    //Note: empty Constructor is necessary for orm
    public Teacher(){
    }

    public Teacher(String name, String mail, String office) {
        this.teacherId = UUID.randomUUID().toString();
        this.name = name;
        this.mail = mail;
        this.office = office;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}

package Model;


import java.util.List;
import java.util.UUID;


public class Student {

    private String studentId;

    private String name;

    private String matrNumber;

    private String degree;

    public Student(String studentId, String name, String matrNumber, String degree) {
        this.studentId = studentId;
        this.name = name;
        this.matrNumber = matrNumber;
        this.degree = degree;
    }

    public Student(String name, String matrNumber, String degree) {
        studentId =    UUID.randomUUID().toString() ;
        this.name = name;
        this.matrNumber = matrNumber;
        this.degree = degree;
    }

    public String getStudentId() {
        return studentId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatrNumber() {
        return matrNumber;
    }

    public void setMatrNumber(String matrNumber) {
        this.matrNumber = matrNumber;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

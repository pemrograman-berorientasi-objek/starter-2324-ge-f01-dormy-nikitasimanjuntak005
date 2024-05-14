package pbo.f01.model;
import javax.persistence.*;

@Entity
@Table(name = "enrollment")

public class Enrollment {
    private static int nextId = 1;
    @Id
    @Column(name = "id", nullable = false, length = 25)
    private int id = 0;

    @Column(name = "Name_Student", nullable = false, length = 25)
    private String nameStudent;
    @Column(name = "Name_Dorm", nullable = false, length = 25)
    private String nameDorm;

    public Enrollment() {
        // empty constructor
    }

    public Enrollment(String nameStudent, String nameDorm) {
        this.id = nextId++;
        this.nameStudent = nameStudent;
        this.nameDorm = nameDorm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return nameStudent;
    }

    public void setStudentId(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getName_Dorm() {
        return nameDorm;
    }

    public void setName_Dorm(String nameDorm) {
        this.nameDorm = nameDorm;
    }

}

package pbo.f01.model;

// /**
//  * 12S22005 - Nikita Simanjuntak
//  * 12S22021 - Krisnia Calysta Siahaan
//  */

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Dorms")
public class Dorm {
    @Id
    @Column(name = "Name_Dorm", nullable = false, length = 255)
    private String nameDorm;

    @Column(name = "Capacity", nullable = false)
    private Integer capacity;

    @Column(name = "Gender", nullable = false, length = 255)
    private String gender;

    @OneToMany(mappedBy = "dorm", cascade = CascadeType.ALL)
    private Set<Student> students;

    public Dorm() {
        //empty
    }

    public Dorm(String nameDorm, Integer capacity, String gender) {
        this.nameDorm = nameDorm;
        this.capacity = capacity;
        this.gender = gender;
    }

    public String getNameDorm() {
        return nameDorm;
    }

    public void setNameDorm(String nameDorm) {
        this.nameDorm = nameDorm;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return nameDorm + "|" + gender + "|" + capacity + "|" + (students != null ? students.size() : 0);
    }
}

package pbo.f01;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
@Id
@Column(name = "Name_Student", nullable = false, length = 255)
private String nameStudent;
@Column(name = "Entrance year", nullable = false, length = 255)
private Integer entranceYear;
@Column(name = "Gender", nullable = false, length = 255)
private String gender;

public Student(){
    //empty
     }
public Student(String nameStudent, Integer entranceYear, String gender){
    this.nameStudent = nameStudent;
    this.entranceYear = entranceYear;
    this.gender = gender;
}

public String getName_Student() {
    return nameStudent;
}

public void setName_Student(String nameStudent) {
    this.nameStudent = nameStudent;
}
public Integer getEntranceyear() {
    return entranceYear;
}

public  void setEntranceyear(Integer entranceYear) {
    this.entranceYear = entranceYear;
}

    public String toString() {
        return nameStudent + "|" + entranceYear + "|" + gender;
    }

}

package pbo.f01.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
@Id
@Column(name = "id", nullable = false, length = 255)
private String id;
@Column(name = "Name_Student", nullable = false, length = 255)
private String nameStudent;
@Column(name = "Entrance year", nullable = false, length = 255)
private Integer entranceYear;
@Column(name = "Gender", nullable = false, length = 255)
private String gender;

public Student(){
    //empty
     }

public Student(String id, String nameStudent, Integer entranceYear, String gender){
    this.id = id;
    this.nameStudent = nameStudent;
    this.entranceYear = entranceYear;
    this.gender = gender;
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
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

public String getGender(){
    return gender;
}

    public String toString() {
        return nameStudent + "|" + entranceYear + "|" + gender;
    }
    public void setDorm(Dorm dorm2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDorm'");
    }

}


// package pbo.f01.model;

// // /**
// //  * 12S22005 - Nikita Simanjuntak
// //  * 12S22021 - Krisnia Calysta Siahaan
// //  */

// import javax.persistence.*;

// @Entity
// @Table(name = "students")
// public class Student {
//     @Id
//     @Column(name = "id", nullable = false, length = 255)
//     private String id;

//     @Column(name = "Name_Student", nullable = false, length = 255)
//     private String nameStudent;

//     @Column(name = "Entrance_Year", nullable = false)
//     private Integer entranceYear;

//     @Column(name = "Gender", nullable = false, length = 255)
//     private String gender;

//     @ManyToOne
//     @JoinColumn(name = "Name_Dorm")
//     private Dorm dorm;

//     public Student() {
//         //empty
//     }

//     public Student(String id, String nameStudent, Integer entranceYear, String gender) {
//         this.id = id;
//         this.nameStudent = nameStudent;
//         this.entranceYear = entranceYear;
//         this.gender = gender;
//     }

//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public String getNameStudent() {
//         return nameStudent;
//     }

//     public void setNameStudent(String nameStudent) {
//         this.nameStudent = nameStudent;
//     }

//     public Integer getEntranceYear() {
//         return entranceYear;
//     }

//     public void setEntranceYear(Integer entranceYear) {
//         this.entranceYear = entranceYear;
//     }

//     public String getGender() {
//         return gender;
//     }

//     public void setGender(String gender) {
//         this.gender = gender;
//     }

//     public Dorm getDorm() {
//         return dorm;
//     }

//     public void setDorm(Dorm dorm) {
//         this.dorm = dorm;
//     }

//     @Override
//     public String toString() {
//         return id + "|" + nameStudent + "|" + entranceYear;
//     }
// }

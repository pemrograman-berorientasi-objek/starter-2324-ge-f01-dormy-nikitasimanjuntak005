package pbo.f01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dorms")
public class Dorm {
@Id
@Column(name = "Name_Dorm", nullable = false, length = 255)
private String nameDorm;
@Column(name = "Capacity", nullable = false, length = 255)
private Integer capacity;
@Column(name = "Gender", nullable = false, length = 255)
private String gender;

public Dorm(){
    //empty
     }
public Dorm(String nameDorm, Integer capacity, String gender){
    this.nameDorm = nameDorm;
    this.capacity = capacity;
    this.gender = gender;
}

public String getName_Dorm() {
    return nameDorm;
}

public void setName_Dorm(String name) {
    this.nameDorm = name;
}
public Integer getCapacity() {
    return capacity;
}

public  void setCapacity(Integer entranceYear) {
    this.capacity = entranceYear;
}

    public String toString() {
        return nameDorm + "|" + capacity + "|" + gender;
    }

}

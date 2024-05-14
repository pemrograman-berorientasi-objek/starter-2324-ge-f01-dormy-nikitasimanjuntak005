package pbo.f01;

import java.util.*;
import javax.persistence.*;
import pbo.f01.model.*;

/**
 *  - 12S22005 - Nikita Simanjuntak
 * 12S22021 - Krisnia Calysta Siahaan
 */
public class App {
private static EntityManagerFactory factory;
private static EntityManager entityManager;

    public static void main(String[] _args) {
        factory = Persistence.createEntityManagerFactory("Student_Dorm_pu");
        entityManager = factory.createEntityManager();
    drivApp.initializeEntityManager();
    drivApp.cleanTableStudent();
    drivApp.cleanTableDorm();
    drivApp.cleanTableEnrollment();

    String str;

    Scanner scanner = new Scanner(System.in);

    while (true){
        str = scanner.nextLine();

        if (str.equals("---")) {
            break;
          } else {
            String split[] = str.split("#");
        
            switch (split[0]){
                case "student-add":
                drivApp.CreateStudent(split[1], Integer.parseInt(split[2]), split[3]);
                break;
              case "student-show-all":
                drivApp.ShowStudent();
                break;
              case "course-add":
                drivApp.CreateDorm(split[1],  Integer.parseInt(split[2]), split[4]);
                break;
              case "course-show-all":   
                drivApp.ShowCourse();
                break;
             case "enroll":
                drivApp.createEnroll(split[1], split[2]);
                break;
              case "student-show":
                drivApp.ShowStudentDetail(split[1]);
    
                break;
    
              default:
                break;
            }
            System.out.println();
    
          }
    
        }
    
      }
    }
    
    
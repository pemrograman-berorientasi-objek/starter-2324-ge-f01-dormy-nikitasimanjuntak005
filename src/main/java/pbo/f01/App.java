package pbo.f01;
// /**
//  * 12S22005 - Nikita Simanjuntak
//  * 12S22021 - Krisnia Calysta Siahaan
//  */

import java.util.*;
import javax.persistence.*;
import pbo.f01.model.*;

public class App {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static void main(String[] _args) {
        factory = Persistence.createEntityManagerFactory("dormy_pu");
        entityManager = factory.createEntityManager();
        initializeEntityManager();
        cleanTableStudent();
        cleanTableDorm();
        cleanTableEnrollment();

        String str;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            str = scanner.nextLine();

            if (str.equals("---")) {
                break;
            } else {
                String split[] = str.split("#");

                switch (split[0]) {
                    case "student-add":
                        createStudent(split[1], split[2], Integer.parseInt(split[3]), split[4]);
                        break;
                    case "dorm-add":
                        createDorm(split[1], Integer.parseInt(split[2]), split[3]);
                        break;
                    case "assign":
                        createEnroll(split[1], split[2]);
                        break;
                    case "display-all":
                        displayAll();
                        break;
                    default:
                        break;
                }
            }
        }

        scanner.close();
        entityManager.close();
        factory.close();
    }

    private static void initializeEntityManager() {
        // Initialization logic if required
    }

    private static void cleanTableStudent() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Student").executeUpdate();
        entityManager.getTransaction().commit();
    }

    private static void cleanTableDorm() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Dorm").executeUpdate();
        entityManager.getTransaction().commit();
    }

    private static void cleanTableEnrollment() {
        // Clean Enrollment table if exists and needed
    }

    private static void createStudent(String id, String name, int year, String gender) {
        entityManager.getTransaction().begin();
        Student student = new Student(id, name, year, gender);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    private static void createDorm(String name, int capacity, String gender) {
        entityManager.getTransaction().begin();
        Dorm dorm = new Dorm(name, capacity, gender);
        entityManager.persist(dorm);
        entityManager.getTransaction().commit();
    }

    private static void createEnroll(String studentId, String dormName) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, studentId);
        Dorm dorm = entityManager.find(Dorm.class, dormName);
        if (student != null && dorm != null) {
            if (student.getGender().equalsIgnoreCase(dorm.getGender()) && dorm.getStudents().size() < dorm.getCapacity()) {
                dorm.getStudents().add(student);
                student.setDorm(dorm);
                entityManager.persist(student);
                entityManager.persist(dorm);
            }
        }
        entityManager.getTransaction().commit();
    }

    private static void displayAll() {
        List<Dorm> dorms = entityManager.createQuery("SELECT d FROM Dorm d ORDER BY d.nameDorm", Dorm.class).getResultList();
        for (Dorm dorm : dorms) {
            System.out.println(dorm);
            List<Student> students = new ArrayList<>(dorm.getStudents());
            students.sort(Comparator.comparing(Student::getNameStudent));
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}

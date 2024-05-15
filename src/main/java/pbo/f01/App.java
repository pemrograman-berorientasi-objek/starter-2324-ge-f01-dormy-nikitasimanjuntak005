package pbo.f01;

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
        cleanTableEnrollment();  // Ensure Enrollment entity exists

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String str = scanner.nextLine();

            if (str.equals("---")) {
                break;
            } else {
                String[] split = str.split("#");

                try {
                    switch (split[0]) {
                        case "student-add":
                            if (split.length == 5) {
                                createStudent(split[1], split[2], Integer.parseInt(split[3]), split[4]);
                            } else {
                                System.err.println("Invalid command format for student-add.");
                            }
                            break;
                        case "dorm-add":
                            if (split.length == 4) {
                                createDorm(split[1], Integer.parseInt(split[2]), split[3]);
                            } else {
                                System.err.println("Invalid command format for dorm-add.");
                            }
                            break;
                        //enrollment
                        case "enroll":
                            if (split.length == 3) {
                                createEnroll(split[1], split[2]);
                            } else {
                                System.err.println("Invalid command format for enroll.");
                            }
                        case "assign":
                            if (split.length == 3) {
                                createEnroll(split[1], split[2]);
                            } else {
                                System.err.println("Invalid command format for assign.");
                            }
                            break;
                        case "display-all":
                            displayAll();
                            break;
                        default:
                            System.err.println("Unrecognized command: " + split[0]);
                            break;
                    }
                } catch (Exception e) {
                    System.err.println("Error processing command: " + e.getMessage());
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
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Enrollment").executeUpdate();
        entityManager.getTransaction().commit();
    }

    private static void createStudent(String id, String name, int year, String gender) {
        entityManager.getTransaction().begin();
        try {
            if (entityManager.find(Student.class, id) != null) {
                System.err.println("Student with ID " + id + " already exists.");
                entityManager.getTransaction().rollback();
                return;
            }
            Student student = new Student(id, name, year, gender);
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.err.println("Error creating student: " + e.getMessage());
        }
    }

    private static void createDorm(String name, int capacity, String gender) {
        entityManager.getTransaction().begin();
        try {
            Dorm dorm = new Dorm(name, capacity, gender);
            entityManager.persist(dorm);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.err.println("Error creating dorm: " + e.getMessage());
        }
    }
    private static void createEnroll(String studentId, String dormName) {
        entityManager.getTransaction().begin();
        try {
            Student student = entityManager.find(Student.class, studentId);
            Dorm dorm = entityManager.find(Dorm.class, dormName);
            if (student == null) {
                System.err.println("Student with ID " + studentId + " not found.");
                entityManager.getTransaction().rollback();
                return;
            }
            if (dorm == null) {
                System.err.println("Dorm " + dormName + " not found.");
                entityManager.getTransaction().rollback();
                return;
            }
            if (dorm.isFull()) {
                System.err.println("Dorm " + dormName + " is full.");
                entityManager.getTransaction().rollback();
                return;
            }
    
    // private static void createEnroll(String studentId, String dormName) {
    //     entityManager.getTransaction().begin();
    //     try {
    //         Student student = entityManager.find(Student.class, studentId);
    //         Dorm dorm = entityManager.find(Dorm.class, dormName);
    //         if (student == null) {
    //             System.err.println("Student with ID " + studentId + " not found.");
    //             entityManager.getTransaction().rollback();
    //             return;
    //         }
    //         if (dorm == null) {
    //             System.err.println("Dorm with name " + dormName + " not found.");
    //             entityManager.getTransaction().rollback();
    //             return;
    //         }
            if (!student.getGender().equalsIgnoreCase(dorm.getGender())) {
                System.err.println("Gender mismatch between student and dorm.");
                entityManager.getTransaction().rollback();
                return;
            }
            if (dorm.getStudents().size() >= dorm.getCapacity()) {
                System.err.println("Dorm " + dormName + " is full.");
                entityManager.getTransaction().rollback();
                return;
            }

            dorm.getStudents().add(student);
            student.setDorm(dorm);
            entityManager.persist(student);
            entityManager.persist(dorm);
            entityManager.getTransaction().commit();
        // proceed with enrollment
    } catch (Exception e) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        e.printStackTrace();
    }
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

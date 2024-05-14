package pbo.f01.model;
import java.util.*;
import javax.persistence.*;

import pbo.f01.Student;

public class drivApp {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static ArrayList<Student> containerStudents = new ArrayList<Student>();
    public static ArrayList<Dorm> containerDorms = new ArrayList<Dorm>();
    

    public static void initializeEntityManager() {
        factory = Persistence.createEntityManagerFactory("dormy_pu");
        entityManager = factory.createEntityManager();
    }

    public static void cleanTableStudent() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Student s").executeUpdate();  
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    
    public static void cleanTableDorm() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Dorm c").executeUpdate();  
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public static void cleanTableEnrollment() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Enrollment e").executeUpdate();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

        // create student
    public static void CreateStudent(String nameStudent, Integer entranceYear, String gender) {
        boolean cek = false;
        for (Student student : containerStudents) {
            if (student.getName_Student().equals(nameStudent)) {
                cek = true;
                break;
            }
        }
        if (cek == false) {
            Student newStudent = new Student(nameStudent, entranceYear, gender);
            containerStudents.add(newStudent);
            entityManager.getTransaction().begin();
            entityManager.persist(newStudent);
            entityManager.flush();
            entityManager.getTransaction().commit();

        }
    }

    public static void CreateDorm(String nameDorm, Integer capacity, String gender) {

        boolean cek = false;
        for (Dorm dorm : containerDorms) {
            if (dorm.getName_Dorm().equals(nameDorm)) {
                cek = true;
                break;
            }
        }
        if (cek == false) {
            Dorm newDorm = new Dorm(nameDorm, capacity, gender);
            containerDorms.add(newDorm);
            entityManager.getTransaction().begin();
            entityManager.persist(newDorm);
            entityManager.flush();
            entityManager.getTransaction().commit();

        }
    }
        // show student
        public static void ShowStudent() {
            String query = "SELECT s FROM Student s ORDER BY s.studentId ASC";
            List<Student> students = entityManager.createQuery(query, Student.class)
                    .getResultList();
    
            for (Student s : students) {
                System.out.println(s.toString());
            }
        }
    
        // show course
        public static void ShowCourse() {
            String query = "SELECT c FROM Course c ORDER BY c.courseId ASC";
            List<Dorm> dorms = entityManager.createQuery(query, Dorm.class)   
                    .getResultList();
    
            for (Dorm c : dorms) {
                System.out.println(c.toString());
            }
        }
    
    
    
    
        public static void createEnroll(String studentName, String nameDorm) {
            boolean studentExists = false;
            boolean dormExists = false;
    
            for (Student student : containerStudents) {
                if (student.getName_Student().equals(studentName)) {
                    studentExists = true;
                    break;
                }
            }
    
            for (Dorm dorm : containerDorms) {
                if (dorm.getName_Dorm().equals(nameDorm)) {
                    dormExists = true;
                    break;
                }
            }
    
            if (studentExists && dormExists) {
                Enrollment newEnroll = new Enrollment(studentName, nameDorm);
                entityManager.getTransaction().begin();
                entityManager.persist(newEnroll);
                entityManager.flush();
                entityManager.getTransaction().commit();
            }
        }
    
        // student-show#12S21001
        public static void ShowStudentDetail(String studentName) {
            String query = "SELECT s FROM Student s WHERE s.studentId = :studentId";
            Student student = entityManager.createQuery(query, Student.class)
                    .setParameter("studentId", studentName)
                    .getSingleResult();
    
            System.out.println(student.toString());
    
            String query_selectCourse_from_enrol = "SELECT e FROM Enrollment e WHERE e.studentId = :studentId";
            List<Enrollment> enrollments = entityManager.createQuery(query_selectCourse_from_enrol, Enrollment.class)
                    .setParameter("studentId", studentName)
                    .getResultList();
    
            List<Dorm> dorms = new ArrayList<>();
            for (Enrollment e : enrollments) {
                String nameDorm = e.getName_Dorm();
                Dorm dorm = entityManager.find(Dorm.class, nameDorm);
                if (dorm != null) {
                    dorms.add(dorm);
                }
            }
    
            // Mengurutkan daftar kursus berdasarkan courseId secara ascending
            dorms.sort(Comparator.comparing(Dorm::getName_Dorm));
    
            for (Dorm c : dorms) {
                System.out.println(c.toString());
            }
        }
    
    }
    

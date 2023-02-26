package com.pavan.spring.data.jpa.repository;

import com.pavan.spring.data.jpa.entity.Guardian;
import com.pavan.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void saveStudent() {
        Student student = Student.builder().emailId("pavan.surve@gmail.com").firstName("Pavan").lastName("Surve")
                // .guardianName("Manik").guardianEmail("manik.surve@gmail.com").guardianMobile("9999999999")
                .build();
        studentRepository.save(student);
    }

    @Test
    void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder().name("Manik").email("manik.surve@gmail.com").mobile("9999999999").build();
        Student student = Student.builder().firstName("Yogesh").emailId("yogesh.surve@gmail.com").lastName("Surve").guardian(guardian).build();
        studentRepository.save(student);
    }

    @Test
    void printAllStudent() {
        List<Student> students = studentRepository.findAll();
        System.out.println("students = " + students);
    }

    @Test
    void printStudentsByFirstName() {
        List<Student> students = studentRepository.findByFirstName("Pavan");
        System.out.println("students = " + students);
    }

    @Test
    void printStudentsByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("sh");
        System.out.println("students = " + students);
    }

    @Test
    void printAllStudentByLastNameNotNull() {
        List<Student> students = studentRepository.findByLastNameNotNull();
        System.out.println("students = " + students);
    }

    @Test
    void printStudentsBasedOnGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Manik");
        System.out.println("students = " + students);
    }

    @Test
    void printStudentsBasedOnFirstNameAndLastName() {
        List<Student> students = studentRepository.findByFirstNameAndLastName("Yogesh", "Surve");
        System.out.println("students = " + students);
    }

    @Test
    void printStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailId("pavan.surve@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    void printStudentFirstNameByEmailAddress() {
        String firstName = studentRepository.getStudentFirstNameByEmailId("pavan.surve@gmail.com");
        System.out.println("firstName = " + firstName);
    }

    @Test
    void printStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailIdNative("yogesh.surve@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    void printStudentByEmailAddressNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailIdNativeNamedParam("pavan.surve@gmail.com");
        System.out.println("student = " + student);
    }

    // @Test
    // this is not working with 3.0.3 for native update and delete queries, will check with new spring boot version later
    // void testUpdateStudentNameByEmailId() {
    // studentRepository.updateStudentNameByEmailId("PAVAN", "pavan.surve@gmail.com");
    // }

}

package com.pavan.spring.data.jpa.repository;

import com.pavan.spring.data.jpa.entity.Course;
import com.pavan.spring.data.jpa.entity.Student;
import com.pavan.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder().firstName("WXY").lastName("RST").build();
        Course course = Course.builder().title("Python").credit(6).teacher(teacher).build();
        courseRepository.save(course);
    }

    @Test
    void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);

        courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();
        totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(1, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("title").descending()
                .and(Sort.by("credit")));
        List<Course> coursesSortByTitle = courseRepository.findAll(sortByTitle).getContent();
        List<Course> coursesSortByCreditDesc = courseRepository.findAll(sortByCreditDesc).getContent();
        List<Course> coursesSortByTitleAndCreditDesc = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        System.out.println("courses sort by title = " + coursesSortByTitle);
        System.out.println("courses sort by credit descending = " + coursesSortByCreditDesc);
        System.out.println("courses sort by title and credit descending = " + coursesSortByTitleAndCreditDesc);
    }

    @Test
    void printFindByTitleContaining() {
        Pageable firstPageTenRecords = PageRequest.of(0, 10);
        List<Course> courses = courseRepository.findByTitleContaining("J", firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    void saveCourseWithStudentsAndTeachers() {
        Teacher teacher = Teacher.builder().firstName("ZYXW").lastName("DCBA").build();
        Student student = Student.builder().firstName("Ram").lastName("Ji").emailId("ram.ji@gmail.com").build();
        Course course = Course.builder().title("AI").credit(12).teacher(teacher).build();
        course.addStudent(student);
        courseRepository.save(course);
    }

}
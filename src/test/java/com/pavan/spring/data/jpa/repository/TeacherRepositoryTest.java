package com.pavan.spring.data.jpa.repository;

import com.pavan.spring.data.jpa.entity.Course;
import com.pavan.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void saveTeacher() {
        Course courseJava = Course.builder().title("Java").credit(9).build();
        Course courseJUnit = Course.builder().title("JUnit").credit(7).build();
        Teacher teacher = Teacher.builder().firstName("ABCD").lastName("PQRS")
                // .courses(List.of(courseJava, courseJUnit))
                .build();
        teacherRepository.save(teacher);
    }

}
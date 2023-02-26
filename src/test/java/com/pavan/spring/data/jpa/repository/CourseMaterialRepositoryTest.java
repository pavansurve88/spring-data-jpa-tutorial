package com.pavan.spring.data.jpa.repository;

import com.pavan.spring.data.jpa.entity.Course;
import com.pavan.spring.data.jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    void saveCourseMaterial() {
        Course course = Course.builder().title("DSA").credit(8).build();
        CourseMaterial courseMaterial = CourseMaterial.builder().url("www.google.com").course(course).build();
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        System.out.println("courseMaterials = " + courseMaterials);
    }

}
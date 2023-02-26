package com.pavan.spring.data.jpa.repository;

import com.pavan.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    // JPQL queries are created based on the classes and attributes you have defined in your code and not on the database tables

    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailId(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailId(String emailId);

    // Native queries are for particular database tables, here PostgreSQL, generally for complex queries we prefer/use native queries

    @Query(value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1", nativeQuery = true)
    Student getStudentByEmailIdNative(String emailId);

    // Native Named Param, instead of using ?1, ?2 and so on we can use native named param

    @Query(value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId", nativeQuery = true)
    Student getStudentByEmailIdNativeNamedParam(@Param("emailId") String emailId);

    // @Modifying annotation is because we are updating the database and generally @Transactional is used on @Service layer method/class level,
    // where multiple database operations performed and at the end transaction is committed otherwise rolled back.

    // @Modifying
    // @Transactional
    // @Query(value = "UPDATE tbl_student SET first_name = ?1 WHERE email_address = ?2", nativeQuery = true)
    // this is not working with 3.0.3 for native update and delete queries, will check with new spring boot version later
    // int updateStudentNameByEmailId(String firstName, String emailId);

}

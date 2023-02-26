package com.pavan.spring.data.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course") // added for lazy fetch to avoid course details in course material
public class CourseMaterial {

    @Id
    @SequenceGenerator(name = "course_material_sequence", sequenceName = "course_material_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;
    // cascading is used to instruct that if course is not available in table then add (cascade) it in table and then add course material
    // eager fetch to get course material details along with course, lazy fetch to get only course material details
    // optional false means whenever you save course material, saving related course in course table is mandatory
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course course;

}

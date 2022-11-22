package com.nhnacademy.edu.jdbc1.service.course;

import java.util.List;

public interface CourseRepository {

    Course getCourse(long courseId);

    List<Course> findAll();

    void insert(long teacherId, long courseId);

    void update(long courseId, long teacherId, long subjectId);

    void delete(long courseId);
}

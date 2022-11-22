package com.nhnacademy.edu.jdbc1.service.course;

import java.util.List;

public interface CourseService {

    Course getOneCourse(long courseId);

    List<Course> getAllCourses();

    void updateCourse(long courseId, long teacherId, long subjectId);

    void insertCourse(long teacherId, long subjectId);

    void deleteCourse(long courseId);
}

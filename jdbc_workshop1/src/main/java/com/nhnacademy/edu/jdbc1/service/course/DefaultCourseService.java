package com.nhnacademy.edu.jdbc1.service.course;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCourseService implements CourseService {

    public final CourseRepository courseRepository;

    public DefaultCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getOneCourse(long courseId) {
        return courseRepository.getCourse(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public void updateCourse(long courseId, long teacherId, long subjectId) {
        courseRepository.update(courseId, teacherId, subjectId);
    }

    @Override
    @Transactional
    public void insertCourse(long teacherId, long subjectId) {
        courseRepository.insert(teacherId, subjectId);
    }

    @Override
    @Transactional
    public void deleteCourse(long courseId) {
        courseRepository.delete(courseId);
    }
}

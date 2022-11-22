package com.nhnacademy.edu.jdbc1.service.teacher;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeacherService implements TeacherService {

    private final TeacherRepository teacherRepository;

    public DefaultTeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

}

package com.nhnacademy.edu.jdbc1.service.subject;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultServiceService implements SubjectService{

    private final SubjectRepository subjectRepository;

    public DefaultServiceService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}

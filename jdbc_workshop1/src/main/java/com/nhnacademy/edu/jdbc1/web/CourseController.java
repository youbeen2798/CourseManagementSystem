package com.nhnacademy.edu.jdbc1.web;

import com.nhnacademy.edu.jdbc1.service.course.Course;
import com.nhnacademy.edu.jdbc1.service.course.CourseService;
import com.nhnacademy.edu.jdbc1.service.subject.Subject;
import com.nhnacademy.edu.jdbc1.service.subject.SubjectService;
import com.nhnacademy.edu.jdbc1.service.teacher.Teacher;
import com.nhnacademy.edu.jdbc1.service.teacher.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;


    public CourseController(CourseService courseService, SubjectService subjectService, TeacherService teacherService) {
        this.courseService = courseService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @GetMapping("/view")
    public String viewCourse(Model model){
        List<Course> courses = courseService.getAllCourses();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();

        model.addAttribute("courses", courses);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);

        return "course";
    }

    @PostMapping("/insert")
    public String insertCourse(@RequestParam("teacherId") long teacherId, @RequestParam("subjectId") long subjectId, Model model){
        courseService.insertCourse(teacherId, subjectId);
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);

        return "redirect:/course/view";
    }

    @GetMapping("/update/{courseId}")
    public String viewUpdateForm(@PathVariable long courseId, ModelMap modelMap){

        Course course = courseService.getOneCourse(courseId); //내가 수정하려는 강의
        List<Subject> subjects = subjectService.getAllSubjects(); //모든 과목
        List<Teacher> teachers = teacherService.getAllTeachers(); //모든 강사님

        modelMap.put("subjects", subjects);
        modelMap.put("teachers", teachers);
        modelMap.put("course", course);

        subjects.size();

        return "courseUpdateForm";
    }

    @PostMapping("/update/{courseId}")
    public String updateCourse(@PathVariable long courseId, @RequestParam("teacherId") long teacherId,
        @RequestParam("subjectId") long subjectId) {
        courseService.updateCourse(courseId, teacherId, subjectId);
        return "redirect:/course/view";
    }

    @GetMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable long courseId){
        courseService.deleteCourse(courseId);

        return "redirect:/course/view";
    }
}

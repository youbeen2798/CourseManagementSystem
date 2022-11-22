package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.service.subject.Subject;
import com.nhnacademy.edu.jdbc1.service.teacher.Teacher;

import com.nhnacademy.edu.jdbc1.service.course.Course;
import com.nhnacademy.edu.jdbc1.service.course.CourseRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCourseRepository implements CourseRepository {

    private final DataSource dataSource; //connection Pool

    public JdbcCourseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Course getCourse(long courseId) { //하나의 강의만 가져오기

        String sql = "SELECT c.id as c_id, c.teacher_id, c.subject_id, c.created_at as c_created_at, \n" +
                " s.id as s_id, s.name as s_name, s.created_at as s_created_at, \n" +
                " t.id as t_id, t.name as t_name, t.created_at as t_created_at \n" +
                "FROM JdbcCourses as c \n" +
                "INNER JOIN JdbcSubjects as s ON c.subject_id = s.id \n" +
                "INNER JOIN JdbcTeachers as t ON c.teacher_id = t.id \n" +
                "WHERE c.id = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setLong(1,courseId);
             ResultSet rs = psmt.executeQuery();

            if(rs.next()){
                Subject subject = new Subject(
                        rs.getLong("s_id"),
                        rs.getString("s_name"),
                        rs.getTimestamp("s_created_at"));

                Teacher teacher = new Teacher(
                        rs.getLong("t_id"),
                        rs.getString("t_name"),
                        rs.getTimestamp("t_created_at"));

                return new Course(
                        rs.getLong("c_id"),
                        subject,
                        teacher,
                        rs.getTimestamp("c_created_at"));
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Course> findAll() { //모든 강의 리턴

        String sql = "SELECT c.id as c_id, c.teacher_id, c.subject_id, c.created_at as c_created_at, \n" +
                " s.id as s_id, s.name as s_name, s.created_at as s_created_at, \n" +
                " t.id as t_id, t.name as t_name, t.created_at as t_created_at \n" +
                "FROM JdbcCourses as c \n" +
                "INNER JOIN JdbcSubjects as s ON c.subject_id = s.id \n" +
                "INNER JOIN JdbcTeachers as t ON c.teacher_id = t.id;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
            List<Course> courses = new ArrayList<>();
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject(
                        rs.getLong("s_id"),
                        rs.getString("s_name"),
                        rs.getTimestamp("s_created_at"));

                Teacher teacher = new Teacher(
                        rs.getLong("t_id"),
                        rs.getString("t_name"),
                        rs.getTimestamp("t_created_at"));

                courses.add(new Course(
                        rs.getLong("c_id"),
                        subject,
                        teacher,
                        rs.getTimestamp("c_created_at")));
            }
            return courses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(long teacherId, long subjectId) {

        String sql = "INSERT INTO JdbcCourses(teacher_id, subject_id, created_at) VALUES(?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setLong(1, teacherId);
            psmt.setLong(2, teacherId);
            psmt.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(long courseId, long teacherId, long subjectId) {

        String sql = "UPDATE JdbcCourses SET teacher_id = ?, subject_id = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setLong(1,teacherId);
            psmt.setLong(2,subjectId);
            psmt.setLong(3,courseId);

            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long courseId){
        String sql = "DELETE FROM JdbcCourses WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setLong(1, courseId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

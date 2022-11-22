package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.service.subject.Subject;
import com.nhnacademy.edu.jdbc1.service.teacher.Teacher;
import com.nhnacademy.edu.jdbc1.service.teacher.TeacherRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTeacherRepository implements TeacherRepository {

    private final DataSource dataSource; //connection Pool

    public JdbcTeacherRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Teacher> findAll() {

        String sql = "SELECT t.id as t_id, t.name as t_name, t.created_at as t_created_at " +
                "FROM JdbcTeachers as t;";

        try (Connection connection = dataSource.getConnection(); PreparedStatement psmt = connection.prepareStatement(sql);) {
            List<Teacher> teachers = new ArrayList<>();

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getLong("t_id"),
                        rs.getString("t_name"),
                        rs.getTimestamp("t_created_at")));
            }
            return teachers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

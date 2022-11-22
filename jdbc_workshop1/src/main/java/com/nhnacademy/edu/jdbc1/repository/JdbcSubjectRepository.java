package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.service.subject.Subject;
import com.nhnacademy.edu.jdbc1.service.subject.SubjectRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcSubjectRepository implements SubjectRepository {

    private final DataSource dataSource; //connection Pool

    public JdbcSubjectRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Subject> findAll() {

        String sql = "SELECT s.id as s_id, s.name as s_name, s.created_at as s_created_at " +
                "FROM JdbcSubjects as s;";
        try (Connection connection = dataSource.getConnection(); PreparedStatement psmt = connection.prepareStatement(sql);) {
            List<Subject> subjects = new ArrayList<>();
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getLong("s_id"),
                        rs.getString("s_name"),
                        rs.getTimestamp("s_created_at")));
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

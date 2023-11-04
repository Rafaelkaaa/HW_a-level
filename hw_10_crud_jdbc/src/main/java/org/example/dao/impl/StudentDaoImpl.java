package org.example.dao.impl;

import org.example.dao.StudentDao;
import org.example.factory.JdbcFactory;
import org.example.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentDaoImpl implements StudentDao {
    private final JdbcFactory sqlConfig = JdbcFactory.getInstance();

    @Override
    public void create(Student value) {
        try (PreparedStatement ps = sqlConfig
                .getConnection()
                .prepareStatement("insert into classes.students values (default, ?,?,?)")) {
            ps.setString(1, value.getFirstName());
            ps.setString(2, value.getLastName());
            ps.setInt(3, value.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student read(String id) {
        try (ResultSet rs = sqlConfig
                .getStatement()
                .executeQuery("SELECT * FROM classes.students where id='" + UUID.fromString(id)+ "'")) {
            rs.next();
            return studentFromRs(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student value) {
        try (PreparedStatement ps = sqlConfig
                .getConnection()
                .prepareStatement("UPDATE classes.students set" +
                        " first_name =?, last_name = ?,age = ? WHERE id = ?")) {
            ps.setString(1, value.getFirstName());
            ps.setString(2, value.getLastName());
            ps.setInt(3, value.getAge());
            ps.setObject(4, UUID.fromString(value.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement ps = sqlConfig
                .getConnection()
                .prepareStatement("DELETE from classes.students CASCADE where id = ?")) {
            ps.setObject(1, UUID.fromString(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        try (ResultSet rs = sqlConfig
                .getStatement()
                .executeQuery("SELECT * from classes.students")) {
            List<Student> students = new ArrayList<>();
            while (rs.next()){
                students.add(studentFromRs(rs));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findByGroupId (String id){
        try (ResultSet rs = sqlConfig
                .getStatement()
                .executeQuery("SELECT *\n" +
                        "FROM classes.stu_gro\n" +
                        "INNER JOIN classes.students " +
                        "ON classes.stu_gro.student_id = classes.students.id\n" +
                        "WHERE classes.stu_gro.group_id ='" + id + "'")) {

            List<Student> students = new ArrayList<>();
            while (rs.next()){
                students.add(studentFromRs(rs));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addStudentToGroup(String studentId, String groupId) {
        try (PreparedStatement ps = sqlConfig
                .getConnection()
                .prepareStatement("insert into classes.stu_gro values (?,?)")) {
            ps.setObject(1, UUID.fromString(studentId));
            ps.setObject(2, UUID.fromString(groupId));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Student studentFromRs(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getObject("id").toString());
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setAge(rs.getInt("age"));
        return student;
    }
}

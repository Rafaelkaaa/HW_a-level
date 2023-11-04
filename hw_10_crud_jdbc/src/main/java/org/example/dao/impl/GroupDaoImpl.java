package org.example.dao.impl;

import org.example.dao.GroupDao;
import org.example.factory.JdbcFactory;
import org.example.model.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupDaoImpl implements GroupDao {
    private final JdbcFactory sqlConfig = JdbcFactory.getInstance();
    @Override
    public void create(Group value) {
        try (PreparedStatement ps = sqlConfig
                .getConnection()
                .prepareStatement("insert into classes.groups values (default, ?)")) {
            ps.setString(1, value.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Group read(String id) {
        try (ResultSet rs = sqlConfig
                .getStatement()
                .executeQuery("SELECT * FROM classes.groups where id='" + UUID.fromString(id)+ "'")) {
            rs.next();
            return groupFromRs(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Group value) {
        try (PreparedStatement ps = sqlConfig
                .getConnection()
                .prepareStatement("UPDATE classes.groups set" +
                        " name =? WHERE id = ?")) {
            ps.setString(1, value.getName());
            ps.setObject(2, UUID.fromString(value.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement ps = sqlConfig
                .getConnection()
                .prepareStatement("DELETE from classes.groups CASCADE where id = ?")) {
            ps.setObject(1, UUID.fromString(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Group> findByStudentId (String id) {
        try (ResultSet rs = sqlConfig
                .getStatement()
                .executeQuery("SELECT *\n" +
                        "FROM classes.stu_gro\n" +
                        "INNER JOIN classes.groups " +
                        "ON classes.stu_gro.group_id = classes.groups.id\n" +
                        "WHERE classes.stu_gro.student_id ='" + id + "'")) {

            List<Group> groups = new ArrayList<>();
            while (rs.next()){
                groups.add(groupFromRs(rs));
            }
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> findAll() {
        try (ResultSet rs = sqlConfig
                .getStatement().executeQuery("SELECT * from classes.groups")) {
            List<Group> groups = new ArrayList<>();
            while (rs.next()){
                groups.add(groupFromRs(rs));
            }
            return groups;
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

    private static Group groupFromRs(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getObject("id").toString());
        group.setName(rs.getString("name"));
        return group;
    }
}

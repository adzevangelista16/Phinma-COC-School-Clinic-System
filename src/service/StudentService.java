/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (student_number, full_name, grade_level, section, gender, birthdate, guardian_name, guardian_contact, allergies) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            bind(ps, s);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET student_number=?, full_name=?, grade_level=?, section=?, gender=?, "
                   + "birthdate=?, guardian_name=?, guardian_contact=?, allergies=? WHERE id=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            bind(ps, s);
            ps.setInt(10, s.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean archiveStudent(int id) {
        String sql = "UPDATE students SET is_archived=1, archived_at=CURRENT_TIMESTAMP WHERE id=? AND is_archived=0";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id=? AND is_archived=0";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE is_archived=0 ORDER BY full_name";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Student> searchStudents(String keyword) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE is_archived=0 "
                    + "AND (full_name LIKE ? OR student_number LIKE ?) ORDER BY full_name";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void bind(PreparedStatement ps, Student s) throws SQLException {
        ps.setString(1, s.getStudentNumber());
        ps.setString(2, s.getFullName());
        ps.setString(3, s.getGradeLevel());
        ps.setString(4, s.getSection());
        ps.setString(5, s.getGender());
        ps.setString(6, s.getBirthdate());
        ps.setString(7, s.getGuardianName());
        ps.setString(8, s.getGuardianContact());
        ps.setString(9, s.getAllergies());
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("student_number"),
                rs.getString("full_name"),
                rs.getString("grade_level"),
                rs.getString("section"),
                rs.getString("gender"),
                rs.getString("birthdate") == null ? "" : rs.getString("birthdate"),
                rs.getString("guardian_name"),
                rs.getString("guardian_contact"),
                rs.getString("allergies")
        );
    }
}

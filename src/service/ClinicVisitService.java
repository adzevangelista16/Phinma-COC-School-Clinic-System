
package service;


import model.ClinicVisit;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicVisitService {
      private final MedicineService medicineService = new MedicineService();

    /** Records a new clinic visit and automatically deducts medicine stock if given. */
    public boolean addVisit(ClinicVisit v) {
        String sql = "INSERT INTO clinic_visits (student_id, complaint, diagnosis, treatment, medicine_given, medicine_id, quantity_given, attended_by, remarks) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, v.getStudentId());
            ps.setString(2, v.getComplaint());
            ps.setString(3, v.getDiagnosis());
            ps.setString(4, v.getTreatment());
            ps.setString(5, v.getMedicineGiven());
            if (v.getMedicineId() != null) ps.setInt(6, v.getMedicineId()); else ps.setNull(6, Types.INTEGER);
            ps.setInt(7, v.getQuantityGiven());
            ps.setString(8, v.getAttendedBy());
            ps.setString(9, v.getRemarks());
            boolean ok = ps.executeUpdate() > 0;

            if (ok && v.getMedicineId() != null && v.getQuantityGiven() > 0) {
                medicineService.deductStock(v.getMedicineId(), v.getQuantityGiven());
            }
            return ok;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean archiveVisit(int id) {
        String sql = "UPDATE clinic_visits SET is_archived=1, archived_at=CURRENT_TIMESTAMP WHERE id=? AND is_archived=0";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ClinicVisit> getVisitsByStudent(int studentId) {
        List<ClinicVisit> list = new ArrayList<>();
        String sql = "SELECT cv.*, s.full_name AS student_name FROM clinic_visits cv "
                   + "JOIN students s ON cv.student_id = s.id "
                   + "WHERE cv.student_id=? AND cv.is_archived=0 ORDER BY cv.visit_date DESC";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ClinicVisit> getAllVisits() {
        List<ClinicVisit> list = new ArrayList<>();
        String sql = "SELECT cv.*, s.full_name AS student_name FROM clinic_visits cv "
                   + "JOIN students s ON cv.student_id = s.id "
                   + "WHERE cv.is_archived=0 ORDER BY cv.visit_date DESC";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ClinicVisit> getVisitsBetween(String startDate, String endDate) {
        List<ClinicVisit> list = new ArrayList<>();
        String sql = "SELECT cv.*, s.full_name AS student_name FROM clinic_visits cv "
                   + "JOIN students s ON cv.student_id = s.id "
                   + "WHERE cv.is_archived=0 AND DATE(cv.visit_date) BETWEEN ? AND ? ORDER BY cv.visit_date DESC";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ClinicVisit mapRow(ResultSet rs) throws SQLException {
        Integer medId = rs.getObject("medicine_id") != null ? rs.getInt("medicine_id") : null;
        return new ClinicVisit(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getString("student_name"),
                rs.getTimestamp("visit_date").toString(),
                rs.getString("complaint"),
                rs.getString("diagnosis"),
                rs.getString("treatment"),
                rs.getString("medicine_given"),
                medId,
                rs.getInt("quantity_given"),
                rs.getString("attended_by"),
                rs.getString("remarks")
        );
    }
}

    

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import util.DBConnection;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportService {
    
    public int getTotalVisits() {
        return singleCount("SELECT COUNT(*) FROM clinic_visits WHERE is_archived=0");
    }

    public int getTotalStudents() {
        return singleCount("SELECT COUNT(*) FROM students WHERE is_archived=0");
    }

    public int getTotalMedicines() {
        return singleCount("SELECT COUNT(*) FROM medicines WHERE is_archived=0");
    }

    public int getLowStockCount() {
        return singleCount("SELECT COUNT(*) FROM medicines WHERE is_archived=0 AND stock_qty <= reorder_level");
    }

    /** Top complaints reported, most frequent first. */
    public Map<String, Integer> getTopComplaints(int limit) {
        Map<String, Integer> result = new LinkedHashMap<>();
        String sql = "SELECT complaint, COUNT(*) AS total FROM clinic_visits "
                   + "WHERE is_archived=0 AND complaint IS NOT NULL AND complaint <> '' "
                   + "GROUP BY complaint ORDER BY total DESC LIMIT ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("complaint"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /** Number of visits per day for the last N days. */
    public Map<String, Integer> getVisitsPerDay(int lastDays) {
        Map<String, Integer> result = new LinkedHashMap<>();
        String sql = "SELECT DATE(visit_date) AS d, COUNT(*) AS total FROM clinic_visits "
                   + "WHERE is_archived=0 AND visit_date >= DATE_SUB(CURDATE(), INTERVAL ? DAY) "
                   + "GROUP BY DATE(visit_date) ORDER BY d";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, lastDays);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("d"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int singleCount(String sql) {
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

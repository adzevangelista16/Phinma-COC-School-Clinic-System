
package service;

import model.Medicine;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MedicineService {
    public boolean addMedicine(Medicine m) {
        String sql = "INSERT INTO medicines (name, category, stock_qty, unit, expiry_date, reorder_level) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            bind(ps, m);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMedicine(Medicine m) {
        String sql = "UPDATE medicines SET name=?, category=?, stock_qty=?, unit=?, expiry_date=?, reorder_level=? WHERE id=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            bind(ps, m);
            ps.setInt(7, m.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean archiveMedicine(int id) {
        String sql = "UPDATE medicines SET is_archived=1, archived_at=CURRENT_TIMESTAMP WHERE id=? AND is_archived=0";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Reduces stock when medicine is dispensed during a clinic visit. */
    public boolean deductStock(int medicineId, int quantity) {
        String sql = "UPDATE medicines SET stock_qty = stock_qty - ? WHERE id=? AND stock_qty >= ? AND is_archived=0";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, medicineId);
            ps.setInt(3, quantity);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Medicine getMedicineById(int id) {
        String sql = "SELECT * FROM medicines WHERE id=? AND is_archived=0";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE is_archived=0 ORDER BY name";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Medicine> getLowStockMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE is_archived=0 AND stock_qty <= reorder_level ORDER BY stock_qty";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void bind(PreparedStatement ps, Medicine m) throws SQLException {
        ps.setString(1, m.getName());
        ps.setString(2, m.getCategory());
        ps.setInt(3, m.getStockQty());
        ps.setString(4, m.getUnit());
        ps.setString(5, m.getExpiryDate());
        ps.setInt(6, m.getReorderLevel());
    }

    private Medicine mapRow(ResultSet rs) throws SQLException {
        return new Medicine(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getInt("stock_qty"),
                rs.getString("unit"),
                rs.getString("expiry_date") == null ? "" : rs.getString("expiry_date"),
                rs.getInt("reorder_level")
        );
    }
}

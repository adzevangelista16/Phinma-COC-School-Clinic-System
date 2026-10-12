package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.ArchiveItem;
import util.DBConnection;

public class ArchiveService {

    public List<ArchiveItem> getArchivedItems() {
        List<ArchiveItem> items = new ArrayList<>();
        String sql = "SELECT archive_type, record_id, description, archived_at FROM ("
                + "SELECT 'Student' AS archive_type, id AS record_id, "
                + "CONCAT(student_number, ' - ', full_name) AS description, archived_at "
                + "FROM students WHERE is_archived=1 "
                + "UNION ALL "
                + "SELECT 'Medicine' AS archive_type, id AS record_id, "
                + "CONCAT(name, ' (', category, ')') AS description, archived_at "
                + "FROM medicines WHERE is_archived=1 "
                + "UNION ALL "
                + "SELECT 'Medical record' AS archive_type, cv.id AS record_id, "
                + "CONCAT(s.full_name, ' | ', cv.complaint) AS description, cv.archived_at "
                + "FROM clinic_visits cv JOIN students s ON cv.student_id=s.id "
                + "WHERE cv.is_archived=1"
                + ") AS archived_items ORDER BY archived_at DESC";

        try (Statement statement = DBConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                items.add(new ArchiveItem(
                        result.getString("archive_type"),
                        result.getInt("record_id"),
                        result.getString("description"),
                        result.getTimestamp("archived_at")
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "The archive tables are not ready. Run database/archive_migration.sql against school_clinic_db.",
                    e
            );
        }
        return items;
    }

    public boolean restore(String type, int id) {
        String table;
        switch (type) {
            case "Student":
                table = "students";
                break;
            case "Medicine":
                table = "medicines";
                break;
            case "Medical record":
                table = "clinic_visits";
                break;
            default:
                return false;
        }

        String sql = "UPDATE " + table
                + " SET is_archived=0, archived_at=NULL WHERE id=? AND is_archived=1";
        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
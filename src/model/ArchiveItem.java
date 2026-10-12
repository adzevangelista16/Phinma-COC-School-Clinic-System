package model;

import java.sql.Timestamp;

public class ArchiveItem {

    private final String type;
    private final int id;
    private final String description;
    private final Timestamp archivedAt;

    public ArchiveItem(String type, int id, String description, Timestamp archivedAt) {
        this.type = type;
        this.id = id;
        this.description = description;
        this.archivedAt = archivedAt;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getArchivedAt() {
        return archivedAt;
    }
}
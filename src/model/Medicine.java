/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Medicine {
     private int id;
    private String name;
    private String category;
    private int stockQty;
    private String unit;
    private String expiryDate; // yyyy-MM-dd
    private int reorderLevel;

    public Medicine() {}

    public Medicine(int id, String name, String category, int stockQty, String unit, String expiryDate, int reorderLevel) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stockQty = stockQty;
        this.unit = unit;
        this.expiryDate = expiryDate;
        this.reorderLevel = reorderLevel;
    }

    public Medicine(String name, String category, int stockQty, String unit, String expiryDate, int reorderLevel) {
        this.name = name;
        this.category = category;
        this.stockQty = stockQty;
        this.unit = unit;
        this.expiryDate = expiryDate;
        this.reorderLevel = reorderLevel;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getStockQty() { return stockQty; }
    public void setStockQty(int stockQty) { this.stockQty = stockQty; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public int getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(int reorderLevel) { this.reorderLevel = reorderLevel; }

    public boolean isLowStock() { return stockQty <= reorderLevel; }

    @Override
    public String toString() {
        return name + " (" + stockQty + " " + unit + ")";
    }
    
}

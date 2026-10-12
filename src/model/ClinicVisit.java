
package model;


public class ClinicVisit {
     private int id;
    private int studentId;
    private String studentName; // convenience field for joined queries/display
    private String visitDate;   // yyyy-MM-dd HH:mm:ss
    private String complaint;
    private String diagnosis;
    private String treatment;
    private String medicineGiven;
    private Integer medicineId;
    private int quantityGiven;
    private String attendedBy;
    private String remarks;
    

public ClinicVisit() {}

    public ClinicVisit(int id, int studentId, String studentName, String visitDate, String complaint,
                        String diagnosis, String treatment, String medicineGiven, Integer medicineId,
                        int quantityGiven, String attendedBy, String remarks) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.visitDate = visitDate;
        this.complaint = complaint;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medicineGiven = medicineGiven;
        this.medicineId = medicineId;
        this.quantityGiven = quantityGiven;
        this.attendedBy = attendedBy;
        this.remarks = remarks;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }

    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) { this.complaint = complaint; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public String getMedicineGiven() { return medicineGiven; }
    public void setMedicineGiven(String medicineGiven) { this.medicineGiven = medicineGiven; }

    public Integer getMedicineId() { return medicineId; }
    public void setMedicineId(Integer medicineId) { this.medicineId = medicineId; }

    public int getQuantityGiven() { return quantityGiven; }
    public void setQuantityGiven(int quantityGiven) { this.quantityGiven = quantityGiven; }

    public String getAttendedBy() { return attendedBy; }
    public void setAttendedBy(String attendedBy) { this.attendedBy = attendedBy; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
}

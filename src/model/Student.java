
package model;

public class Student {
     private int id;
    private String studentNumber;
    private String fullName;
    private String gradeLevel;
    private String section;
    private String gender;
    private String birthdate;      // stored/displayed as yyyy-MM-dd
    private String guardianName;
    private String guardianContact;
    private String allergies;

public Student() {}

    public Student(int id, String studentNumber, String fullName, String gradeLevel, String section,
                   String gender, String birthdate, String guardianName, String guardianContact, String allergies) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.fullName = fullName;
        this.gradeLevel = gradeLevel;
        this.section = section;
        this.gender = gender;
        this.birthdate = birthdate;
        this.guardianName = guardianName;
        this.guardianContact = guardianContact;
        this.allergies = allergies;
    }

    public Student(String studentNumber, String fullName, String gradeLevel, String section,
                   String gender, String birthdate, String guardianName, String guardianContact, String allergies) {
        this.studentNumber = studentNumber;
        this.fullName = fullName;
        this.gradeLevel = gradeLevel;
        this.section = section;
        this.gender = gender;
        this.birthdate = birthdate;
        this.guardianName = guardianName;
        this.guardianContact = guardianContact;
        this.allergies = allergies;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(String gradeLevel) { this.gradeLevel = gradeLevel; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public String getGuardianName() { return guardianName; }
    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }

    public String getGuardianContact() { return guardianContact; }
    public void setGuardianContact(String guardianContact) { this.guardianContact = guardianContact; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    @Override
    public String toString() {
        return studentNumber + " - " + fullName;
    }
    
}

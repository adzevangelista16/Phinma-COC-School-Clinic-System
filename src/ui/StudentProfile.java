
package ui;
import model.ClinicVisit;
import model.Student;
import service.ClinicVisitService;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hp
 */
public class StudentProfile extends javax.swing.JFrame {

    /**
     * Creates new form StudentProfile
     */
    public StudentProfile(Student student) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    
    lblStudentNumber.setText(student.getStudentNumber());
    lblFullName.setText(student.getFullName());
    lblGradeSection.setText(student.getGradeLevel() + " - " + student.getSection());
    lblGender.setText(student.getGender());
    lblBirthdate.setText(student.getBirthdate());
    lblGuardian.setText(student.getGuardianName() + " (" + student.getGuardianContact() + ")");
    lblAllergies.setText(student.getAllergies() == null || student.getAllergies().isEmpty() ? "None reported" : student.getAllergies());

    DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
    model.setColumnIdentifiers(new Object[]{"Date", "Complaint", "Diagnosis", "Treatment", "Medicine Given", "Attended By"});
    ClinicVisitService visitService = new ClinicVisitService();
    for (ClinicVisit v : visitService.getVisitsByStudent(student.getId())) {
        model.addRow(new Object[]{v.getVisitDate(), v.getComplaint(), v.getDiagnosis(), v.getTreatment(), v.getMedicineGiven(), v.getAttendedBy()});
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblStudentNumber = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        lblGradeSection = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblBirthdate = new javax.swing.JLabel();
        lblGuardian = new javax.swing.JLabel();
        lblAllergies = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        jTextField5.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setOpaque(false);

        lblStudentNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStudentNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblStudentNumber.setText("Student Number:");

        lblFullName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(255, 255, 255));
        lblFullName.setText("Full Name:");

        lblGradeSection.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGradeSection.setForeground(new java.awt.Color(255, 255, 255));
        lblGradeSection.setText("Grade & Section:");

        lblGender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGender.setForeground(new java.awt.Color(255, 255, 255));
        lblGender.setText("Gender:");

        lblBirthdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBirthdate.setForeground(new java.awt.Color(255, 255, 255));
        lblBirthdate.setText("Birthdate (yyyy-MM-dd):");

        lblGuardian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGuardian.setForeground(new java.awt.Color(255, 255, 255));
        lblGuardian.setText("Guardian Name:");

        lblAllergies.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAllergies.setForeground(new java.awt.Color(255, 255, 255));
        lblAllergies.setText("Allergies:");

        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblHistory);

        btnBack.setBackground(new java.awt.Color(255, 255, 153));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow-left-from-line.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGuardian)
                    .addComponent(lblStudentNumber)
                    .addComponent(lblFullName)
                    .addComponent(lblGradeSection)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblGender)))
                    .addComponent(lblBirthdate)
                    .addComponent(lblAllergies))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStudentNumber)
                .addGap(13, 13, 13)
                .addComponent(lblFullName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGradeSection)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblGender)
                        .addGap(18, 18, 18)))
                .addComponent(lblBirthdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGuardian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAllergies)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/BG2.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lblAllergies;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBirthdate;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblGradeSection;
    private javax.swing.JLabel lblGuardian;
    private javax.swing.JLabel lblStudentNumber;
    private javax.swing.JTable tblHistory;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aim
 */
public class DaftarBuku extends javax.swing.JDialog {
    private DefaultTableModel daftarBukuDefaultTableModel;
    
    public static String kodeBukuDipilih="";
    public static Object[][] listBuku;

    /**
     * Creates new form DaftarBuku
     */
    public DaftarBuku(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        daftarBukuDefaultTableModel = (DefaultTableModel) daftarBukuTable.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        daftarBukuTable = new javax.swing.JTable();
        pilihToggleButton = new javax.swing.JToggleButton();
        tutupToggleButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Daftar Buku");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        daftarBukuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Judul Buku"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        daftarBukuTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(daftarBukuTable);
        daftarBukuTable.getColumnModel().getColumn(0).setMinWidth(100);
        daftarBukuTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        daftarBukuTable.getColumnModel().getColumn(0).setMaxWidth(100);

        pilihToggleButton.setText("Pilih");
        pilihToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihToggleButtonActionPerformed(evt);
            }
        });

        tutupToggleButton.setText("Tutup");
        tutupToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutupToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pilihToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tutupToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pilihToggleButton)
                    .addComponent(tutupToggleButton))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if (listBuku.length>0){
            daftarBukuDefaultTableModel.setRowCount(0);
            for (int i=0; i<listBuku.length; i++){
                daftarBukuDefaultTableModel.insertRow(daftarBukuDefaultTableModel.getRowCount(),listBuku[i]);
            }
        } else {
            dispose();
        }
    }//GEN-LAST:event_formWindowActivated

    private void tutupToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutupToggleButtonActionPerformed
        // TODO add your handling code here:
        kodeBukuDipilih="";
        dispose();
    }//GEN-LAST:event_tutupToggleButtonActionPerformed

    private void pilihToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihToggleButtonActionPerformed
        // TODO add your handling code here:
        kodeBukuDipilih="";
        if (daftarBukuTable.getSelectedRowCount()>0){
            kodeBukuDipilih = daftarBukuTable.getValueAt(daftarBukuTable.getSelectedRow(), 0).toString();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null,"Belum ada yang dipilih");
        }
    }//GEN-LAST:event_pilihToggleButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DaftarBuku dialog = new DaftarBuku(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable daftarBukuTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton pilihToggleButton;
    private javax.swing.JToggleButton tutupToggleButton;
    // End of variables declaration//GEN-END:variables
}
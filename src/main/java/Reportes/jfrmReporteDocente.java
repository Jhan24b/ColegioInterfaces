/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Reportes;

import Connection.UConnection;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author walter
 */
public class jfrmReporteDocente extends javax.swing.JFrame {

    /**
     * Creates new form jfrmReporteDocente
     */
    public jfrmReporteDocente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnListarDocentes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbtnListarDocentes.setText("Listar docentes");
        jbtnListarDocentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnListarDocentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jbtnListarDocentes)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jbtnListarDocentes)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnListarDocentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnListarDocentesActionPerformed
        // TODO add your handling code here:
        
        try {
            
            
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("cursoId", 1);
            params.put("turno","m");
            params.put("bimestre", 1);
            
            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ListarDocentes.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
            JasperViewer view = new JasperViewer(print,false);
            view.setVisible(true);
            
            
//            String fileJasper=System.getProperty("user.dir")+"\\src\\Reports\\AlumnosNotasPorCursoBimestreTurno.jrxml";
//            JasperDesign jasperDesign= JRXmlLoader.load(fileJasper);
//            JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
//            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,params,con);
//            JasperViewer jasperViewer = new JasperViewer(jasperPrint,true);
//            jasperViewer.setVisible(true);
            
        } catch (Exception e) {
            
             JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jbtnListarDocentesActionPerformed

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
            java.util.logging.Logger.getLogger(jfrmReporteDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfrmReporteDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfrmReporteDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfrmReporteDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrmReporteDocente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnListarDocentes;
    // End of variables declaration//GEN-END:variables
}
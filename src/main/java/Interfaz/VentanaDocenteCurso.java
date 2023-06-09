/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import BusinessLayer.CursoBO;
import BusinessLayer.DocenteBO;
import BusinessLayer.DocenteCursoBO;
import JavaBean.Curso;
import JavaBean.Docente;
import JavaBean.DocenteCurso;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author black
 */
public class VentanaDocenteCurso extends javax.swing.JFrame {

    /**
     * Creates new form VentanaDocenteCurso
     */
    public VentanaDocenteCurso() {
        initComponents();
        
        try {
            docentes = docBO.buscarPorDocente("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        actualizarTablaDocente();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableGestionDocente = new javax.swing.JTable();
        JTextBuscarNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarDocente = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableDocenteCurso = new javax.swing.JTable();
        jTextDocenteId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        JTextDocenteNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonInsertar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Docente"));

        jTableGestionDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Alumno_id", "Apellidos y Nombres", "DNI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGestionDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableGestionDocenteMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableGestionDocente);

        jLabel2.setText("Nombres:");

        btnBuscarDocente.setText("Buscar");
        btnBuscarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDocenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(JTextBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarDocente)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(334, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarDocente)
                    .addComponent(JTextBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Curso por Docente"));

        jTableDocenteCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Apoderado_id", "Apellidos y Nombres", "DNI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDocenteCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDocenteCursoMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTableDocenteCurso);

        jTextDocenteId.setEnabled(false);
        jTextDocenteId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextDocenteIdActionPerformed(evt);
            }
        });

        jLabel9.setText("CODIGO DOCENTE :");

        JTextDocenteNombre.setEnabled(false);
        JTextDocenteNombre.setMaximumSize(new java.awt.Dimension(250, 22));
        JTextDocenteNombre.setMinimumSize(new java.awt.Dimension(250, 22));
        JTextDocenteNombre.setName(""); // NOI18N
        JTextDocenteNombre.setPreferredSize(new java.awt.Dimension(250, 22));

        jLabel1.setText("DOCENTE :");

        jButtonInsertar.setText("Insertar");
        jButtonInsertar.setEnabled(false);
        jButtonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTextDocenteNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextDocenteId)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonInsertar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextDocenteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTextDocenteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsertar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(431, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(479, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 386, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableGestionDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableGestionDocenteMouseClicked
        // TODO add your handling code here:
        int fila = jTableGestionDocente.getSelectedRow();

        if(fila !=-1){
            JTextDocenteNombre.setText(jTableGestionDocente.getValueAt(fila, 1).toString());
            jTextDocenteId.setText(jTableGestionDocente.getValueAt(fila, 0).toString());
            jButtonInsertar.setEnabled(true);
            jButtonEliminar.setEnabled(true);
        }

        try {
            docenteCursos = docenteCursoBO.buscarCursosPorDocenteId(Integer.parseInt(jTableGestionDocente.getValueAt(fila, 0).toString()));
            actualizarTablaDocenteCurso();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jTableGestionDocenteMouseClicked

    private void btnBuscarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDocenteActionPerformed
        try {
            docentes = docBO.buscarPorDocente(JTextBuscarNombre.getText());
            actualizarTablaDocente();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarDocenteActionPerformed

    private void jTableDocenteCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDocenteCursoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDocenteCursoMouseClicked

    private void jTextDocenteIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextDocenteIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDocenteIdActionPerformed

    private void jButtonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarActionPerformed
        // TODO add your handling code here:
        VentanaInsertarDocenteCurso ventanaInsertar = new VentanaInsertarDocenteCurso(Integer.parseInt(jTextDocenteId.getText()),
            JTextDocenteNombre.getText());
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    docenteCursos = docenteCursoBO.buscarCursosPorDocenteId(Integer.parseInt(jTextDocenteId.getText()));
                    actualizarTablaDocenteCurso();
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_jButtonInsertarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        int fila = jTableDocenteCurso.getSelectedRow();

        try {
            System.out.println( "Eliminando AlumnoApoderado con id: "+jTableDocenteCurso.getValueAt(fila, 0) );
            int docente_id = Integer.parseInt(jTextDocenteId.getText().toString());
            int curso_id = Integer.parseInt(jTableDocenteCurso.getValueAt(fila, 0).toString());
            System.out.println("Alumno ID :"+docente_id+ ", Apoderado ID :"+curso_id);
            docenteCursoBO.eliminar(docente_id , curso_id);

            modeloDocenteCurso.removeRow(fila);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    private DefaultTableModel modeloDocente;
    private DocenteBO docBO = new DocenteBO();
    private ArrayList<Docente>  docentes;
    
    private void actualizarTablaDocente(){
        modeloDocente = new DefaultTableModel();
        
        jTableGestionDocente.setModel(modeloDocente);
        
        modeloDocente.addColumn("Docente_id");
        modeloDocente.addColumn("Apellidos y Nombres");
        modeloDocente.addColumn("DNI");
        
        try {
            System.out.println(docentes.size());
            for (int i = 0; i < docentes.size(); i++) {
                Docente docente = docentes.get(i);
                String apellidosNombres = docente.getApellido_paterno()+" "+ docente.getApellido_materno()+", "+ docente.getNombres();
                
                Object [] fila = new Object[] {docente.getDocente_id(), apellidosNombres, docente.getDni()};
                modeloDocente.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloDocenteCurso;
    private ArrayList<DocenteCurso> docenteCursos;
    private DocenteCursoBO docenteCursoBO = new DocenteCursoBO();
    
    private void actualizarTablaDocenteCurso(){
        modeloDocenteCurso = new DefaultTableModel();
        
        jTableDocenteCurso.setModel(modeloDocenteCurso);
        
        modeloDocenteCurso.addColumn("Curso");
        modeloDocenteCurso.addColumn("Nivel");
        modeloDocenteCurso.addColumn("Grado");
        modeloDocenteCurso.addColumn("Turno");
        modeloDocenteCurso.addColumn("Area");
        
        try {
            System.out.println(docenteCursos.size());
            for (int i = 0; i < docenteCursos.size(); i++) {
                DocenteCurso DocCurso = docenteCursos.get(i);
                Curso curso = DocCurso.getCurso();
                String nombre_curso = curso.getNombre()+ " ("+ String.valueOf(curso.getCurso_id())+ ")"; 
                String nombre_area = curso.getArea().getArea_nombre()+ " ("+ String.valueOf(curso.getArea_id())+ ")"; 
                
                Object [] fila = new Object[] {nombre_curso, 
                    curso.getNivel(),curso.getGrado(),
                    DocCurso.getTurno() ,nombre_area};
                modeloDocenteCurso.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
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
            java.util.logging.Logger.getLogger(VentanaDocenteCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaDocenteCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaDocenteCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaDocenteCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaDocenteCurso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextBuscarNombre;
    private javax.swing.JTextField JTextDocenteNombre;
    private javax.swing.JButton btnBuscarDocente;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonInsertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableDocenteCurso;
    private javax.swing.JTable jTableGestionDocente;
    private javax.swing.JTextField jTextDocenteId;
    // End of variables declaration//GEN-END:variables
}

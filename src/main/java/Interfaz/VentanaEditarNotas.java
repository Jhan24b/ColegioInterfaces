/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import BusinessLayer.HistorialNotasBO;
import BusinessLayer.NotasBO;
import JavaBean.HistorialNotas;
import JavaBean.Notas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author black
 */
public class VentanaEditarNotas extends javax.swing.JFrame {

    /**
     * Creates new form VentanaEditarNotas
     */
    private int historial_notas_id;
    
    private int alumno_id;
    
    private int curso_id;
    
    public VentanaEditarNotas(int historial_notas_id, int alumno_id, int curso_id, String alumno, String curso, String nivelGrado, String nota1, String nota2, String nota3, String nota4,
            String nota5, String promedio) {
        initComponents();
        
        this.historial_notas_id = historial_notas_id;
        this.alumno_id = alumno_id;
        this.curso_id = curso_id;
                
        jtxtAlumno.setText(alumno);
        jtxtCurso.setText(curso);
        jtxtNivelGrado.setText(nivelGrado);
        
        jtxtNota1.setText(nota1);
        jtxtNota2.setText(nota2);
        jtxtNota3.setText(nota3);
        jtxtNota4.setText(nota4);
        jtxtNota5.setText(nota5);
        
        jtxtPromedio.setText(promedio);
        
        System.out.println("hn_id:"+historial_notas_id);
        System.out.println("alumno_id:"+alumno_id);
        System.out.println("curso_id:"+curso_id);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTitulo = new javax.swing.JLabel();
        jtxtAlumno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtNota1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtNota2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtNota3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtNota4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtNota5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtxtPromedio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtCurso = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtxtNivelGrado = new javax.swing.JTextField();
        jbEditar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jlNota1 = new javax.swing.JLabel();
        jlNota2 = new javax.swing.JLabel();
        jlNota3 = new javax.swing.JLabel();
        jlNota4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlPromedio = new javax.swing.JLabel();
        jlNota5 = new javax.swing.JLabel();
        jbCalcularPromedi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlTitulo.setBackground(new java.awt.Color(204, 204, 204));
        jlTitulo.setText("   Actualizar Notas del Alumno: ");
        jlTitulo.setOpaque(true);

        jtxtAlumno.setEnabled(false);
        jtxtAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAlumnoActionPerformed(evt);
            }
        });

        jLabel2.setText("Alumno :");

        jLabel3.setText("Nota 1 :");

        jLabel4.setText("Nota 2 :");

        jLabel5.setText("Nota 3 :");

        jLabel6.setText("Nota 4 :");

        jLabel7.setText("Nota 5 :");

        jLabel8.setText("Promedio :");

        jtxtPromedio.setEnabled(false);

        jLabel9.setText("Curso :");

        jtxtCurso.setEnabled(false);
        jtxtCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCursoActionPerformed(evt);
            }
        });

        jLabel10.setText("Nivel/Grado :");

        jtxtNivelGrado.setEnabled(false);
        jtxtNivelGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNivelGradoActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jlNota1.setForeground(new java.awt.Color(255, 51, 51));
        jlNota1.setText(" ");

        jlNota2.setForeground(new java.awt.Color(255, 51, 51));
        jlNota2.setText(" ");

        jlNota3.setForeground(new java.awt.Color(255, 51, 51));
        jlNota3.setText(" ");

        jlNota4.setForeground(new java.awt.Color(255, 51, 51));
        jlNota4.setText(" ");

        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText(" ");

        jlPromedio.setForeground(new java.awt.Color(255, 51, 51));
        jlPromedio.setText(" ");

        jlNota5.setForeground(new java.awt.Color(255, 51, 51));
        jlNota5.setText(" ");

        jbCalcularPromedi.setText("Calcular Promedio");
        jbCalcularPromedi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCalcularPromediActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtNota4)
                                    .addComponent(jtxtNota5)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(22, 22, 22)
                                .addComponent(jtxtNota3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtxtNota1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtNota2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtPromedio))
                            .addComponent(jlNota1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlNota2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlNota3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlNota4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlNota5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCalcularPromedi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtNivelGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 17, Short.MAX_VALUE))
            .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtxtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtxtNivelGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtxtNota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlNota1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtxtNota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlNota2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtxtNota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlNota3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtxtNota4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlNota4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtxtNota5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlNota5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtxtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbCalcularPromedi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAlumnoActionPerformed

    private void jtxtCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCursoActionPerformed

    private void jtxtNivelGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNivelGradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNivelGradoActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        // TODO add your handling code here:
        HistorialNotas hNotas = recibirDatos();
        Notas nota1 = hNotas.getNota();
        double nota_1 = nota1.getNota1();
        double nota_2 = nota1.getNota2();
        double nota_3 = nota1.getNota3();
        double nota_4 = nota1.getNota4();
        double nota_5 = nota1.getNota5();
        
        double promedio = (nota_1 + nota_2 + nota_3 + nota_4 + nota_5)/ 5;
        
        jtxtPromedio.setText(String.valueOf(promedio));
        hNotas.setPromedio(promedio);
        System.out.println("Promedio: "+ promedio);
        
        HistorialNotasBO hNotasBO = new HistorialNotasBO();
        NotasBO notaBO = new NotasBO();
        
        try {
            hNotasBO.actualizar(hNotas);
            
            notaBO.actualizar(nota1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbCalcularPromediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCalcularPromediActionPerformed
        // TODO add your handling code here:
        Notas nota1 = recibirDatos().getNota();
        double nota_1 = nota1.getNota1();
        double nota_2 = nota1.getNota2();
        double nota_3 = nota1.getNota3();
        double nota_4 = nota1.getNota4();
        double nota_5 = nota1.getNota5();
        
        double promedio = (nota_1 + nota_2 + nota_3 + nota_4 + nota_5)/ 5;
        
        jtxtPromedio.setText(String.valueOf(promedio));
        System.out.println("Promedio: "+ promedio);
    }//GEN-LAST:event_jbCalcularPromediActionPerformed

    public HistorialNotas recibirDatos(){
        HistorialNotas hnNotas = new HistorialNotas();
        Notas nota1 = new Notas();

        if (!jtxtNota1.getText().isBlank()) {
            try {
                nota1.setNota1(Double.parseDouble(jtxtNota1.getText()));
            } catch (Exception e) {
                jlNota1.setText("El valor de Nota1 debe ser un número");
            }}
        
        if (!jtxtNota2.getText().isBlank()) {
            try {
                nota1.setNota2(Double.parseDouble(jtxtNota2.getText()));
            } catch (Exception e) {
                jlNota2.setText("El valor de Nota2 debe ser un número");
            }}

        if (!jtxtNota3.getText().isBlank()) {
            try {
                nota1.setNota3(Double.parseDouble(jtxtNota3.getText()));
            } catch (Exception e) {
                jlNota3.setText("El valor de Nota3 debe ser un número");
            }}

        if (!jtxtNota4.getText().isBlank()) {
            try {
                nota1.setNota4(Double.parseDouble(jtxtNota4.getText()));
            } catch (Exception e) {
                jlNota4.setText("El valor de Nota4 debe ser un número");
            }}

        if (!jtxtNota5.getText().isBlank()) {
            try {
                nota1.setNota5(Double.parseDouble(jtxtNota5.getText()));
            } catch (Exception e) {
                jlNota5.setText("El valor de Nota4 debe ser un número");
            }}
        
        nota1.setHistorial_notas_id(historial_notas_id);
        
        hnNotas.setNota(nota1);
        
        hnNotas.setHistorial_id(historial_notas_id);
        hnNotas.setAlumno_id(alumno_id);
        hnNotas.setCurso_id(curso_id);
        
        return hnNotas;
    }
    
    
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
            java.util.logging.Logger.getLogger(VentanaEditarNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEditarNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEditarNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEditarNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
            public void run() {
                new VentanaEditarNotas(-1, -1, -1 ,"", "", "", "","" ,"", "", "","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbCalcularPromedi;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JLabel jlNota1;
    private javax.swing.JLabel jlNota2;
    private javax.swing.JLabel jlNota3;
    private javax.swing.JLabel jlNota4;
    private javax.swing.JLabel jlNota5;
    private javax.swing.JLabel jlPromedio;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JTextField jtxtAlumno;
    private javax.swing.JTextField jtxtCurso;
    private javax.swing.JTextField jtxtNivelGrado;
    private javax.swing.JTextField jtxtNota1;
    private javax.swing.JTextField jtxtNota2;
    private javax.swing.JTextField jtxtNota3;
    private javax.swing.JTextField jtxtNota4;
    private javax.swing.JTextField jtxtNota5;
    private javax.swing.JTextField jtxtPromedio;
    // End of variables declaration//GEN-END:variables
}
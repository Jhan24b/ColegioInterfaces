/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import BusinessLayer.AlumnoBO;
import BusinessLayer.CursoBO;
import BusinessLayer.HistorialNotasBO;
import BusinessLayer.MatriculaBO;
import BusinessLayer.NotasBO;
import JavaBean.Alumno;
import JavaBean.Curso;
import JavaBean.HistorialNotas;
import JavaBean.Matricula;
import JavaBean.Notas;
import Utilities.CorreoElectronico;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author black
 */
public class VentanaMatricula extends javax.swing.JFrame {
    /**
     * Creates new form InsertMatricula
     */
    public VentanaMatricula(Matricula matricula) {
        initComponents();
        jlTitulo.setText("Actualizar Matricula: ");
        jlID.setText(String.valueOf(matricula.getMatricula_id()));
        jbPrincipal.setText("Actualizar");
                
        //Se completa el formulario
        jTextFechaMatricula.setText(matricula.getFecha().toString());
        jTextAlumnoId.setText(String.valueOf(matricula.getAlumno_id()));
        jComboBoxGrado.setSelectedIndex(Character.getNumericValue(matricula.getGrado()));
        
        switch (matricula.getNivel()) {
            case 'I' -> jComboBoxNivel.setSelectedIndex(1);
            case 'P' -> jComboBoxNivel.setSelectedIndex(2);
            case 'S' -> jComboBoxNivel.setSelectedIndex(3);
        }
        
        switch (matricula.getTurno()) {
            case 'D' -> jComboBoxTurno.setSelectedIndex(1);
            case 'T' -> jComboBoxTurno.setSelectedIndex(2);
        }
    }
    
    public VentanaMatricula() {
        initComponents();
        jlTitulo.setText("Insertar Matricula");
        jlID.setText("");
        jbPrincipal.setText("Insertar");
        
        jbPrincipal.setEnabled(false);       
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Insertar Matricula"));
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
        jlID = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextAlumnoId = new javax.swing.JTextField();
        jLabelAlumno_id = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        JTextBuscarNombre = new javax.swing.JTextField();
        btnBuscarAlumno = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        JTextAlumnoNombrePagoInsertar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFechaMatricula = new javax.swing.JTextField();
        jComboBoxNivel = new javax.swing.JComboBox<>();
        jLabelFechaMatricula = new javax.swing.JLabel();
        jLabelNivel = new javax.swing.JLabel();
        jComboBoxGrado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabelGrado = new javax.swing.JLabel();
        jComboBoxTurno = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabelTurno = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jbPrincipal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlTitulo.setText("Actualizar Matricula: ");

        jlID.setForeground(new java.awt.Color(51, 51, 255));
        jlID.setText("-");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Alumno"));

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alumno id", "Apellidos y Nombres", "DNI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlumnosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAlumnos);

        jLabel9.setText("CODIGO ALUMNO:");

        jTextAlumnoId.setEnabled(false);
        jTextAlumnoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextAlumnoIdActionPerformed(evt);
            }
        });

        jLabelAlumno_id.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelAlumno_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jTextAlumnoId)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextAlumnoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAlumno_id, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));

        jLabel2.setText("Nombres:");

        btnBuscarAlumno.setText("Buscar");
        btnBuscarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTextBuscarNombre)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(btnBuscarAlumno)
                .addGap(96, 96, 96))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JTextBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarAlumno))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar Matricula"));
        jPanel3.setToolTipText("");
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setMaximumSize(new java.awt.Dimension(275, 680));
        jPanel3.setMinimumSize(new java.awt.Dimension(275, 680));
        jPanel3.setName(""); // NOI18N

        JTextAlumnoNombrePagoInsertar.setEnabled(false);
        JTextAlumnoNombrePagoInsertar.setMaximumSize(new java.awt.Dimension(250, 22));
        JTextAlumnoNombrePagoInsertar.setMinimumSize(new java.awt.Dimension(250, 22));
        JTextAlumnoNombrePagoInsertar.setName(""); // NOI18N
        JTextAlumnoNombrePagoInsertar.setPreferredSize(new java.awt.Dimension(250, 22));

        jLabel1.setText("Alumno:");

        jLabel5.setText("Nivel :");

        jLabel4.setText("Fecha de matricula :");

        jComboBoxNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Inicial", "Primaria", "Secundaria" }));
        jComboBoxNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNivelActionPerformed(evt);
            }
        });

        jLabelFechaMatricula.setForeground(new java.awt.Color(255, 51, 51));
        jLabelFechaMatricula.setText("-");

        jLabelNivel.setForeground(new java.awt.Color(255, 51, 51));
        jLabelNivel.setText("-");

        jComboBoxGrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "1er° GRADO", "2er° GRADO", "3er° GRADO", "4er° GRADO", "5er° GRADO", "6er° GRADO" }));
        jComboBoxGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGradoActionPerformed(evt);
            }
        });

        jLabel8.setText("Grado :");

        jLabelGrado.setForeground(new java.awt.Color(255, 51, 51));
        jLabelGrado.setText("-");

        jComboBoxTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Dia", "Tarde" }));
        jComboBoxTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTurnoActionPerformed(evt);
            }
        });

        jLabel6.setText("Turno :");

        jLabelTurno.setForeground(new java.awt.Color(255, 51, 51));
        jLabelTurno.setText("-");

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jbPrincipal.setText("Insertar");
        jbPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPrincipalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTextAlumnoNombrePagoInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFechaMatricula)
                                    .addComponent(jComboBoxNivel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxGrado, 0, 150, Short.MAX_VALUE)
                                    .addComponent(jComboBoxTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelGrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelFechaMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTextAlumnoNombrePagoInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFechaMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabelFechaMatricula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTurno)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jlTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTitulo)
                    .addComponent(jlID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextAlumnoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextAlumnoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAlumnoIdActionPerformed

    private void jbPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPrincipalActionPerformed
        // TODO add your handling code here:
        Matricula matricula = RecibirDatos();
        
        if(jbPrincipal.getText().equals("Insertar")){
            Insertar(matricula);
        }
        
        else if(jbPrincipal.getText().equals("Actualizar"))
            Actualizar(matricula);
    }//GEN-LAST:event_jbPrincipalActionPerformed
    
    private Matricula RecibirDatos(){
        Matricula matricula = new Matricula();
        
        if(!(jComboBoxNivel.getSelectedIndex() == 0))
            matricula.setNivel(jComboBoxNivel.getSelectedItem().toString().charAt(0));
        else
            matricula.setNivel(' ');
        
        if(!(jComboBoxGrado.getSelectedIndex() == 0))
            matricula.setGrado(jComboBoxGrado.getSelectedItem().toString().charAt(0));
        else
            matricula.setGrado(' ');
        
        if(!(jComboBoxTurno.getSelectedIndex() == 0))
            matricula.setTurno(jComboBoxTurno.getSelectedItem().toString().charAt(0));
        else 
            matricula.setTurno(' ');
        matricula.setNivel(jComboBoxNivel.getSelectedItem().toString().charAt(0));
        matricula.setGrado(jComboBoxGrado.getSelectedItem().toString().charAt(0));
        matricula.setTurno(jComboBoxTurno.getSelectedItem().toString().charAt(0));


        LimpiarErrores();
        try {
            matricula.setAlumno_id(Integer.parseInt(jTextAlumnoId.getText()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jLabelAlumno_id.setText("Ingresa un número en el campo código alumno");
        }
           
        try {
            if(!jTextFechaMatricula.getText().isEmpty())
                matricula.setFecha(Date.valueOf(jTextFechaMatricula.getText()).toLocalDate());
            else
                matricula.setFecha(null);
        } catch (Exception e) {
            if(e.toString().equals("java.lang.IllegalArgumentException"))
                jLabelFechaMatricula.setText("Error en el formato de la Fecha de Nacimiento");
            
            System.out.println("Error: "+e.toString());
        }
        
        if(!jlID.getText().isEmpty())
            matricula.setMatricula_id(Integer.parseInt(jlID.getText()));
        
        return matricula;
    }
    
    private void Insertar(Matricula matricula){
        MatriculaBO matBO = new MatriculaBO();
        CorreoElectronico correo = new CorreoElectronico();
        AlumnoBO albo = new AlumnoBO();
        
        if(matricula.getErrores().isEmpty()){
            try {
                matBO.insertarMatricula(matricula);
                InsertarHistorialCurso(matricula.getNivel(), matricula.getGrado());
                //Envia correo de verificacion
                correo.enviarCorreo("Matricula registrada", "La matricula fue registrada exitosamente. Bienvenido a la familia UNICOM - LA MOLINA", albo.buscarPorId(matricula.getAlumno_id()));
                dispose();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        else{
            ImprimirErrores(matricula.getErrores());
        }
    }
    
    private void Actualizar(Matricula matricula){
        MatriculaBO matBO = new MatriculaBO();
        
        if(matricula.getErrores().isEmpty()){
            try {
                matBO.actualizarMatricula(matricula);
                dispose();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        else{
            ImprimirErrores(matricula.getErrores());
        }
    }
    private void ImprimirErrores(Map<String, String> errores){    
        for (String error : errores.keySet().toArray(new String[0])) {
            switch (error) {
                case "alumno_id" -> jLabelAlumno_id.setText(errores.get(error));
                case "fecha" -> jLabelFechaMatricula.setText(errores.get(error));
                case "nivel" -> jLabelNivel.setText(errores.get(error));
                case "grado" -> jLabelGrado.setText(errores.get(error));
                case "turno" -> jLabelTurno.setText(errores.get(error));
            }
            System.out.println(error);
        }
    }
    
    private void LimpiarErrores(){
        jLabelAlumno_id.setText("");
        jLabelFechaMatricula.setText("-");
        jLabelNivel.setText("-");
        jLabelGrado.setText("-");
        jLabelTurno.setText("-");
    }
    
     
    private void InsertarHistorialCurso(char nivel, char grado) throws Exception{
        CursoBO cursoBO = new CursoBO();
        HistorialNotasBO hNotasBO = new HistorialNotasBO();
        NotasBO notasBO = new NotasBO();
        ArrayList<HistorialNotas> listaHNotas = new ArrayList<>();
        
        for (Curso curso : cursoBO.buscarPorNivelYGrado(nivel , grado)) {
            System.out.println("Nivel: "+ curso.getGrado() + ", Grado: "+curso.getGrado());
            HistorialNotas hNota = new HistorialNotas();
            
            hNota.setAlumno_id(Integer.parseInt(jTextAlumnoId.getText()));
            hNota.setCurso_id(curso.getCurso_id());
            
            try {
                hNotasBO.insertar(hNota);
                listaHNotas.add(hNota);
            } catch (Exception e) {
                System.out.println("Ventana /InsertarHistorialCurso: "+e);
            }
        }
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBoxGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxGradoActionPerformed

    private void jComboBoxNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNivelActionPerformed

    private void jComboBoxTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTurnoActionPerformed

    private void tblAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlumnosMouseClicked
        // TODO add your handling code here:
        int fila = tblAlumnos.getSelectedRow();

        if(fila !=-1){
            JTextAlumnoNombrePagoInsertar.setText((String) tblAlumnos.getValueAt(fila, 1));
            jTextAlumnoId.setText(tblAlumnos.getValueAt(fila, 0).toString());
            jbPrincipal.setEnabled(true);
        }
    }//GEN-LAST:event_tblAlumnosMouseClicked

    private void btnBuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlumnoActionPerformed
        try {
            alumnos = alumBO.buscarPorAlumno(JTextBuscarNombre.getText());
            actualizarTablaAlumno();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarAlumnoActionPerformed

    private DefaultTableModel modeloAlumnos;
    private AlumnoBO alumBO = new AlumnoBO();
    private ArrayList<Alumno>  alumnos;
    
    private void actualizarTablaAlumno(){
        modeloAlumnos = new DefaultTableModel();
        
        tblAlumnos.setModel(modeloAlumnos);
        
        modeloAlumnos.addColumn("Alumno id");
        modeloAlumnos.addColumn("Apellidos y Nombres");
        modeloAlumnos.addColumn("DNI");
        
        try {
            System.out.println(alumnos.size());
            for (int i = 0; i < alumnos.size(); i++) {
                Alumno alumno = alumnos.get(i);
                String apellidosNombres = alumno.getApellido_paterno()+" "+alumno.getApellido_materno()+" , "+alumno.getNombres();
                
                Object [] fila = new Object[] { alumno.getAlumno_id(), apellidosNombres, alumno.getDni()};
                modeloAlumnos.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(VentanaMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaMatricula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextAlumnoNombrePagoInsertar;
    private javax.swing.JTextField JTextBuscarNombre;
    private javax.swing.JButton btnBuscarAlumno;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxGrado;
    private javax.swing.JComboBox<String> jComboBoxNivel;
    private javax.swing.JComboBox<String> jComboBoxTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlumno_id;
    private javax.swing.JLabel jLabelFechaMatricula;
    private javax.swing.JLabel jLabelGrado;
    private javax.swing.JLabel jLabelNivel;
    private javax.swing.JLabel jLabelTurno;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextAlumnoId;
    private javax.swing.JTextField jTextFechaMatricula;
    private javax.swing.JButton jbPrincipal;
    private javax.swing.JLabel jlID;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JTable tblAlumnos;
    // End of variables declaration//GEN-END:variables
}

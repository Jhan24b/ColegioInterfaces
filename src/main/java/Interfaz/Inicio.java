/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import BusinessLayer.AlumnoBO;
import BusinessLayer.ApoderadoBO;
import BusinessLayer.AreaBO;
import BusinessLayer.AsistenciaDocenteBO;
import BusinessLayer.CursoBO;
import BusinessLayer.DocenteBO;
import BusinessLayer.HistorialNotasBO;
import BusinessLayer.MatriculaBO;
import BusinessLayer.NotasBO;
import BusinessLayer.PagoMatriculaBO;
import BusinessLayer.PagoPensionBO;
import BusinessLayer.PagoVariosBO;
import BusinessLayer.UsuarioBO;
import DataAccessLayer.AlumnoDao;
import DataAccessLayer.ApoderadoDAO;
import DataAccessLayer.DocenteDAO;
import JavaBean.Alumno;
import JavaBean.Apoderado;
import JavaBean.Area;
import JavaBean.AsistenciaDocente;
import JavaBean.Curso;
import JavaBean.Docente;
import JavaBean.HistorialNotas;
import JavaBean.Matricula;
import JavaBean.Notas;
import JavaBean.Usuario;
import JavaBean.pagoMatricula;
import JavaBean.pagoPensiones;
import JavaBean.pagoVarios;
import Utilities.Validator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author xbest
 */
public class Inicio extends javax.swing.JFrame {
    
    /****
     * 
     * VARIABLES GLOBALES
     * 
    ****/
    
    /// Variables globales utiles
    int f = -1;
    
    /// OBJETOS
    private AsistenciaDocente adocente = new AsistenciaDocente();
    private Docente docente = new Docente();
    private Usuario usuario = new Usuario();
    private Notas notas = new Notas();
    
    /// BUSSINESSLAYER
    private AsistenciaDocenteBO asisdocBO = new AsistenciaDocenteBO();
    private DocenteBO docenteBO = new DocenteBO();
    private UsuarioBO usuarioBO = new UsuarioBO();
    private CursoBO cursoBO = new CursoBO();
    private NotasBO notasBO = new NotasBO();
    private HistorialNotasBO hnotasBO = new HistorialNotasBO();
    
    /// ARRAYS
    private ArrayList<AsistenciaDocente> adocentes = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Curso> cursosXNivel = new ArrayList<>();
    private ArrayList<Curso> cursosXGrado = new ArrayList<>();
    public static ArrayList<String> dniUsuarios = new ArrayList<>();
    private ArrayList<HistorialNotas> hnotaslist = new ArrayList<>();
    
    /// MODELOS DE TABLAS
    private DefaultTableModel modeloAdocentes = new DefaultTableModel();
    private DefaultTableModel modeloUsuarios = new DefaultTableModel();
    private DefaultTableModel modeloNotas = new DefaultTableModel();
    
    /****
     * 
     * FIN VARIABLES GLOBALES
     * 
    ****/    
    
    

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        MyInitComponents();   
        actualizarTablaUsuarios();
        ActualizarTablaXTipoPago("Pago Matricula");
                    
        try {
            alumnos = alumBO.buscarPorAlumno("");
            docentes = docBO.buscarPorDocente("");
            apoderados = apoBO.buscarApoderadoPorNombre("");
            
            areas = areaBO.listar();
            cursos = cursoBO.buscarPorCurso("");
            matriculas = matBO.ListarMatriculas();
        } catch (Exception ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarTablaAlumno();
        actualizarTablaDocente();
        actualizarTablaApoderado();
        
        actualizarTablaArea();
        actualizarTablaCurso();
        actualizarTablaMatricula();
    }
    
    private void MyInitComponents(){
        jTableGestionAlumnos.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jHeaderGestionAlumnosMouseClicked(evt);
                        }
        });
        
        jTableGestionUsuarios.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jHeaderGestionUsuariosMouseClicked(evt);
                        }
        });
        
        jTableGestionApoderados.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jHeaderGestionApoderadosMouseClicked(evt);
                        }
        });
    }
    
    
    /*****
     * 
     *  EVENTOS CREADOS
     * 
    *****/
    
    private void jHeaderGestionApoderadosMouseClicked(java.awt.event.MouseEvent evt){
        JTableHeader header = jTableGestionApoderados.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
        System.out.println("Seleccion: " + columnIndex);
        switch (columnIndex) {
            case 1:
                System.out.println("Ordenando por codigo");
                break;
            case 2:
                System.out.println("Ordenando por dni");
                ordenarPorApoderadosDNI(apoderados);
                break;
            case 3:
                System.out.println("Ordenando po apellido paterno");
                ordenarPorApoderadosxApellidoPaterno(apoderados);
                break;
            case 4:
                System.out.println("Ordenando po apellido materno");
                ordenarPorApoderadosxApellidoMaterno(apoderados);
                break;
            case 5:
                System.out.println("Ordenando po Nombre");
                ordenarPorApoderadosxNombre(apoderados);
                break;
            case 6:
                System.out.println("Ordenando por Contacto");
                ordenarPorApoderadosxContacto(apoderados);
                break;
            default:
                throw new AssertionError();
        }
        actualizarTablaApoderado();
    }
    
    private void jHeaderGestionAlumnosMouseClicked(java.awt.event.MouseEvent evt){
        JTableHeader header = jTableGestionAlumnos.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
        System.out.println("Seleccion: " + columnIndex);
        switch (columnIndex) {
            case 1:
                System.out.println("Ordenando por codigo");
                break;
            case 2:
                System.out.println("Ordenando por dni");
                ordenarPorAlumnosDNI(alumnos);
                break;
            case 3:
                System.out.println("Ordenando po apellido paterno");
                ordenarPorAlumnosxApellidoPaterno(alumnos);
                break;
            case 4:
                System.out.println("Ordenando po apellido materno");
                ordenarPorAlumnosxApellidoMaterno(alumnos);
                break;
            case 5:
                System.out.println("Ordenando po Nombre");
                ordenarPorAlumnosxNombre(alumnos);
                break;
            case 6:
                ordenarPorAlumnosxFechaNacimiento(alumnos);
                break;
            default:
                throw new AssertionError();
        }
        actualizarTablaAlumno();
    }
    
    
    private void jHeaderGestionUsuariosMouseClicked(java.awt.event.MouseEvent evt){
        JTableHeader header = jTableGestionUsuarios.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
        System.out.println("Seleccion: " + columnIndex);
        switch (columnIndex) {
            case 0:
                System.out.println("Ordenando por codigo");
                break;
            case 1:
                System.out.println("Ordenando por dni");
                ordenarUsuariosPorDNI(usuarios);
                break;
            case 2:
                System.out.println("Ordenando po apellido paterno");
                ordenarUsuariosPorApellidoPaterno(usuarios);
                break;
            case 3:
                System.out.println("Ordenando po apellido materno");
                ordenarUsuariosPorApellidoMaterno(usuarios);
                break;
            case 4:
                System.out.println("Ordenando po Nombre");
                ordenarUsuariosPorNombre(usuarios);
                break;
            case 6:
                ordenarUsuariosPorRol(usuarios);
                break;
            default:
                throw new AssertionError();
        }
        actualizarTablaAlumno();
    }
    
    
    /*****
     * 
     *  FIN EVENTOS CREADOS
     * 
    *****/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanelDocente = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableGestionDocente = new javax.swing.JTable();
        btnInsertarDocente = new javax.swing.JButton();
        btnEliminarDocente = new javax.swing.JButton();
        btnActualizarDocente = new javax.swing.JButton();
        btnSalirDocente = new javax.swing.JButton();
        cbBuscarDocente = new javax.swing.JComboBox<>();
        txtBuscarDocente = new javax.swing.JTextField();
        btnBuscarDocente = new javax.swing.JButton();
        jPanelAlumno = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableGestionAlumnos = new javax.swing.JTable();
        btnInsertarAlumnos = new javax.swing.JButton();
        btnEliminarAlumnos = new javax.swing.JButton();
        btnEditarAlumnos = new javax.swing.JButton();
        btnSalirAlumnos = new javax.swing.JButton();
        cbOpcionesBuscarAlumnos = new javax.swing.JComboBox<>();
        txtBuscarAlumnos = new javax.swing.JTextField();
        btnBuscarAlumnos = new javax.swing.JButton();
        jPanelApoderado = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableGestionApoderados = new javax.swing.JTable();
        btnInsertarApoderado = new javax.swing.JButton();
        btnEliminarApoderado = new javax.swing.JButton();
        btnEditarApoderado = new javax.swing.JButton();
        btnSalirApoderado = new javax.swing.JButton();
        cbBuscarApoderado = new javax.swing.JComboBox<>();
        txtBuscarApoderado = new javax.swing.JTextField();
        btnBuscarApoderados = new javax.swing.JButton();
        jPanelPagos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePagos = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        cbPagos = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jPanelMatricula = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableGestionMatricula = new javax.swing.JTable();
        jButton28 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jPanelCursoArea = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableGestionCurso = new javax.swing.JTable();
        btnInsertarCurso = new javax.swing.JButton();
        btnActualizarCurso = new javax.swing.JButton();
        btnEliminarCurso = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableGestionArea = new javax.swing.JTable();
        btnInsertarArea = new javax.swing.JButton();
        btnActualizarArea = new javax.swing.JButton();
        btnEliminarArea = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnMarcarTodo = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableAsistenciaDocentes = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnRegistrarIngreso = new javax.swing.JButton();
        btnRegistrarSalida = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtDNIDocente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jPanelUsuarios = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableGestionUsuarios = new javax.swing.JTable();
        btnEditarUsuario = new javax.swing.JButton();
        btnCambiarClave = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnInsertarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        FiltroCbx = new javax.swing.JComboBox<>();
        jPanelNotas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableNotas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnInsertarNota = new javax.swing.JButton();
        cbCursoNota = new javax.swing.JComboBox<>();
        btnEliminarNota = new javax.swing.JButton();
        cbGradoNota = new javax.swing.JComboBox<>();
        cbNivelNota = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtUser = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtRolUser = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("GESTION DOCENTE");

        jTableGestionDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Docente_id", "DNI", "Apellido paterno", "Apellido materno", "Nombres", "Contacto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTableGestionDocente);
        if (jTableGestionDocente.getColumnModel().getColumnCount() > 0) {
            jTableGestionDocente.getColumnModel().getColumn(0).setMinWidth(100);
            jTableGestionDocente.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTableGestionDocente.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        btnInsertarDocente.setText("Insertar");
        btnInsertarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarDocenteActionPerformed(evt);
            }
        });

        btnEliminarDocente.setText("Eliminar");
        btnEliminarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDocenteActionPerformed(evt);
            }
        });

        btnActualizarDocente.setText("Actualizar");
        btnActualizarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarDocenteActionPerformed(evt);
            }
        });

        btnSalirDocente.setText("Salir");
        btnSalirDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirDocenteActionPerformed(evt);
            }
        });

        cbBuscarDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apellidos y Nombres", "DNI" }));
        cbBuscarDocente.setToolTipText("");

        btnBuscarDocente.setText("Buscar");
        btnBuscarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDocenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDocenteLayout = new javax.swing.GroupLayout(jPanelDocente);
        jPanelDocente.setLayout(jPanelDocenteLayout);
        jPanelDocenteLayout.setHorizontalGroup(
            jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDocenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanelDocenteLayout.createSequentialGroup()
                        .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelDocenteLayout.createSequentialGroup()
                                .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDocenteLayout.createSequentialGroup()
                                        .addComponent(btnInsertarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnActualizarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSalirDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelDocenteLayout.createSequentialGroup()
                                        .addComponent(cbBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 27, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanelDocenteLayout.setVerticalGroup(
            jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDocenteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addGap(15, 15, 15)
                .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarDocente))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarDocente)
                    .addComponent(btnActualizarDocente)
                    .addComponent(btnInsertarDocente)
                    .addComponent(btnSalirDocente))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Docente", jPanelDocente);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GESTION ALUMNOS");

        jTableGestionAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Alumnos id", "DNI", "Apellido paterno", "Apellido materno", "Nombres", "Fecha de nacimiento", "Correo electronico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTableGestionAlumnos);

        btnInsertarAlumnos.setText("Insertar");
        btnInsertarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarAlumnosActionPerformed(evt);
            }
        });

        btnEliminarAlumnos.setText("Eliminar");
        btnEliminarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAlumnosActionPerformed(evt);
            }
        });

        btnEditarAlumnos.setText("Editar");
        btnEditarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAlumnosActionPerformed(evt);
            }
        });

        btnSalirAlumnos.setText("Salir");
        btnSalirAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirAlumnosActionPerformed(evt);
            }
        });

        cbOpcionesBuscarAlumnos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "Apellidos y Nombres" }));
        cbOpcionesBuscarAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbOpcionesBuscarAlumnosMouseExited(evt);
            }
        });

        btnBuscarAlumnos.setText("Buscar");
        btnBuscarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlumnosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlumnoLayout = new javax.swing.GroupLayout(jPanelAlumno);
        jPanelAlumno.setLayout(jPanelAlumnoLayout);
        jPanelAlumnoLayout.setHorizontalGroup(
            jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlumnoLayout.createSequentialGroup()
                        .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7))
                        .addContainerGap())
                    .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                        .addComponent(cbOpcionesBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, Short.MAX_VALUE))
                    .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                        .addComponent(btnInsertarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalirAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelAlumnoLayout.setVerticalGroup(
            jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbOpcionesBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarAlumnos))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalirAlumnos)
                    .addComponent(btnEliminarAlumnos)
                    .addComponent(btnEditarAlumnos)
                    .addComponent(btnInsertarAlumnos))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Alumnos", jPanelAlumno);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("GESTION APODERADOS");

        jTableGestionApoderados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Apoderado_id", "DNI", "Apellido paterno", "Apellido materno", "Nombres", "Contacto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableGestionApoderados);

        btnInsertarApoderado.setText("Insertar");
        btnInsertarApoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarApoderadoActionPerformed(evt);
            }
        });

        btnEliminarApoderado.setText("Eliminar");
        btnEliminarApoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarApoderadoActionPerformed(evt);
            }
        });

        btnEditarApoderado.setText("Editar");
        btnEditarApoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarApoderadoActionPerformed(evt);
            }
        });

        btnSalirApoderado.setText("Salir");
        btnSalirApoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirApoderadoActionPerformed(evt);
            }
        });

        cbBuscarApoderado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apellidos y Nombres", "DNI", "Correo Electronico" }));

        btnBuscarApoderados.setText("Buscar");
        btnBuscarApoderados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarApoderadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelApoderadoLayout = new javax.swing.GroupLayout(jPanelApoderado);
        jPanelApoderado.setLayout(jPanelApoderadoLayout);
        jPanelApoderadoLayout.setHorizontalGroup(
            jPanelApoderadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                .addGroup(jPanelApoderadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanelApoderadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                                .addComponent(btnInsertarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalirApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                                .addComponent(cbBuscarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarApoderados, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanelApoderadoLayout.setVerticalGroup(
            jPanelApoderadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelApoderadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBuscarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarApoderados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelApoderadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarApoderado)
                    .addComponent(btnEditarApoderado)
                    .addComponent(btnEliminarApoderado)
                    .addComponent(btnSalirApoderado))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Apoderado", jPanelApoderado);

        jTablePagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Pagos ID", "Alumno ID", "Monto", "Observacion", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTablePagos);

        jButton6.setText("Editar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Salir");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("GESTION PAGOS");

        jButton8.setText("Insertar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        cbPagos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pago Matricula", "Pago Pensiones", "Pago Varios" }));
        cbPagos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbPagosPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPagosActionPerformed(evt);
            }
        });

        jButton9.setText("Eliminar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPagosLayout = new javax.swing.GroupLayout(jPanelPagos);
        jPanelPagos.setLayout(jPanelPagosLayout);
        jPanelPagosLayout.setHorizontalGroup(
            jPanelPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPagos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelPagosLayout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 446, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelPagosLayout.setVerticalGroup(
            jPanelPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPagosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton7))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Pagos", jPanelPagos);

        jTableGestionMatricula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Matricula ID", "Fecha", "Grado", "Nivel", "Turno", "Alumno ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTableGestionMatricula);

        jButton28.setText("Editar");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GESTION MATRICULA");

        jButton30.setText("Insertar");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setText("Eliminar");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setText("Salir");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMatriculaLayout = new javax.swing.GroupLayout(jPanelMatricula);
        jPanelMatricula.setLayout(jPanelMatriculaLayout);
        jPanelMatriculaLayout.setHorizontalGroup(
            jPanelMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatriculaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(jPanelMatriculaLayout.createSequentialGroup()
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelMatriculaLayout.setVerticalGroup(
            jPanelMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatriculaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanelMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton30)
                    .addComponent(jButton28)
                    .addComponent(jButton31)
                    .addComponent(jButton32))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Matricula", jPanelMatricula);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Curso\n")));

        jTableGestionCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CURSO ID", "NOMBRE", "GRADO", "NIVEL", "AREA ID"
            }
        ));
        jTableGestionCurso.setCellSelectionEnabled(true);
        jScrollPane10.setViewportView(jTableGestionCurso);
        jTableGestionCurso.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnInsertarCurso.setText("INSERTAR");
        btnInsertarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarCursoActionPerformed(evt);
            }
        });

        btnActualizarCurso.setText("ACTUALIZAR");
        btnActualizarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCursoActionPerformed(evt);
            }
        });

        btnEliminarCurso.setText("ELIMINAR");
        btnEliminarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnInsertarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(btnActualizarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarCurso)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarCurso)
                    .addComponent(btnActualizarCurso)
                    .addComponent(btnInsertarCurso))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Area\n"));

        jTableGestionArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "NOMBRE AREA"
            }
        ));
        jScrollPane9.setViewportView(jTableGestionArea);

        btnInsertarArea.setText("INSERTAR");
        btnInsertarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarAreaActionPerformed(evt);
            }
        });

        btnActualizarArea.setText("ACTUALIZAR");
        btnActualizarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarAreaActionPerformed(evt);
            }
        });

        btnEliminarArea.setText("ELIMINAR");
        btnEliminarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAreaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(9, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnInsertarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnEliminarArea)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarArea)
                    .addComponent(btnActualizarArea)
                    .addComponent(btnInsertarArea))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelCursoAreaLayout = new javax.swing.GroupLayout(jPanelCursoArea);
        jPanelCursoArea.setLayout(jPanelCursoAreaLayout);
        jPanelCursoAreaLayout.setHorizontalGroup(
            jPanelCursoAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCursoAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanelCursoAreaLayout.setVerticalGroup(
            jPanelCursoAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCursoAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCursoAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Curso y Area", jPanelCursoArea);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"12345678", "Michael Cordova", "14/03/2023",  new Boolean(true)},
                {"91516498", "Miguel Lozano", "14/03/2023",  new Boolean(true)},
                {null, null, "", null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "Nombres y Apellidos", "Fecha", "Asistencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(jTable4);

        jLabel11.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Asistencia Alumnos");

        btnMarcarTodo.setText("Marcar todos");
        btnMarcarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarTodoActionPerformed(evt);
            }
        });

        jButton39.setText("Guardar");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salón 01", "Salón 02", "Salón 03", "Salón 04", "Salón 05", "Salón 06", "Salón 07", "Salón 08", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jTextField1.setText("*Ingresar nombre del alumno*");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Filtrar por: (Alumno)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 59, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))
                                    .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))))
        );

        jTabbedPane2.addTab("Asistencias Alumnos", jPanel1);

        jPanel4.setToolTipText("");

        tableAsistenciaDocentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"12345678", "Jose Velasquez", null, null},
                {"91516498", "Guillermo Meza", null, null},
                {"72757984", "Profe Uriel", null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "Nombres y Apellidos", "Hora de ingreso", "Hora de salida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tableAsistenciaDocentes);

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Asistencia Docentes");

        jButton40.setText("Guardar");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matemáticas", "Comunicación", "Ciencia", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jTextField2.setText("*Ingresar DNI del docente*");

        jLabel14.setText("Filtrar por: (Docente)");

        jLabel15.setText("Elija el área:");

        btnRegistrarIngreso.setText("Registrar entrada");
        btnRegistrarIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarIngresoActionPerformed(evt);
            }
        });

        btnRegistrarSalida.setText("Registrar salida");

        jLabel16.setText("DNI: ");

        txtDNIDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIDocenteActionPerformed(evt);
            }
        });

        jLabel17.setText("Clave: ");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "de A - Z", "de Z - A", "ninguno" }));

        jLabel18.setText("Ordenar por: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 90, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDNIDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnRegistrarIngreso)
                                .addGap(28, 28, 28)
                                .addComponent(btnRegistrarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarIngreso)
                    .addComponent(btnRegistrarSalida)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtDNIDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(213, 213, 213)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        jTabbedPane2.addTab("Asistencias Docente", jPanel4);

        jTableGestionUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Usuario ID", "DNI", "Apellido paterno", "Apellido materno", "Nombre", "Clave", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableGestionUsuarios);

        btnEditarUsuario.setText("Editar");
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        btnCambiarClave.setText("Cambiar Clave");
        btnCambiarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarClaveActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("GESTION USUARIOS");

        btnInsertarUsuario.setText("Insertar");
        btnInsertarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        FiltroCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apellidos y Nombres", "DNI", "Correo Electronico" }));

        javax.swing.GroupLayout jPanelUsuariosLayout = new javax.swing.GroupLayout(jPanelUsuarios);
        jPanelUsuarios.setLayout(jPanelUsuariosLayout);
        jPanelUsuariosLayout.setHorizontalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                        .addComponent(btnInsertarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCambiarClave)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                        .addComponent(FiltroCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField7)))
                .addContainerGap())
        );
        jPanelUsuariosLayout.setVerticalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FiltroCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarUsuario)
                    .addComponent(btnEditarUsuario)
                    .addComponent(btnEliminarUsuario)
                    .addComponent(btnCambiarClave))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Usuarios", jPanelUsuarios);

        jTableNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Alumno", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Nota 5", "Promedio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableNotas);
        if (jTableNotas.getColumnModel().getColumnCount() > 0) {
            jTableNotas.getColumnModel().getColumn(0).setMinWidth(300);
            jTableNotas.getColumnModel().getColumn(0).setMaxWidth(300);
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("GESTION NOTAS");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnInsertarNota.setText("Insertar");
        btnInsertarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarNotaActionPerformed(evt);
            }
        });

        cbCursoNota.setEnabled(false);
        cbCursoNota.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCursoNotaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        btnEliminarNota.setText("Eliminar");
        btnEliminarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNotaActionPerformed(evt);
            }
        });

        cbGradoNota.setEnabled(false);
        cbGradoNota.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbGradoNotaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbNivelNota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inicial", "Primaria", "Secundaria" }));
        cbNivelNota.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbNivelNotaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel21.setText("Nivel:");

        jLabel22.setText("Curso:");

        jLabel23.setText("Grado:");

        javax.swing.GroupLayout jPanelNotasLayout = new javax.swing.GroupLayout(jPanelNotas);
        jPanelNotas.setLayout(jPanelNotasLayout);
        jPanelNotasLayout.setHorizontalGroup(
            jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNotasLayout.createSequentialGroup()
                        .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelNotasLayout.createSequentialGroup()
                                .addComponent(btnInsertarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanelNotasLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNivelNota, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGradoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCursoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        jPanelNotasLayout.setVerticalGroup(
            jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNotasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCursoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGradoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNivelNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarNota)
                    .addComponent(btnEliminarNota))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Notas", jPanelNotas);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("I.E.P La Molina");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));

        btnCerrarSesion.setText("Cerrar Sesion");

        jLabel19.setText("Usuario:");

        jLabel20.setText("Rol: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRolUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(25, 25, 25))
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(342, 342, 342)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRolUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirAlumnosActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirAlumnosActionPerformed

    private void btnEditarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAlumnosActionPerformed
        int fila = jTableGestionAlumnos.getSelectedRow();
        Alumno alumno = alumnos.get(fila); 
        
        VentanaAlumno ventanaInsertar = new VentanaAlumno(alumno);
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    alumnos = alumBO.buscarPorAlumno("");
                    actualizarTablaAlumno();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnEditarAlumnosActionPerformed

    private void btnEliminarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAlumnosActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int fila = jTableGestionAlumnos.getSelectedRow();

        try {
            System.out.println( "Eliminando Apoderado con id: "+jTableGestionAlumnos.getValueAt(fila, 1) );
            int id = (int)jTableGestionAlumnos.getValueAt(fila, 1);
            System.out.println("ID :"+id);
            alumBO.eliminar(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloAlumnos.removeRow(fila);
    }//GEN-LAST:event_btnEliminarAlumnosActionPerformed

    private void btnInsertarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarAlumnosActionPerformed
        // TODO add your handling code here:
        VentanaAlumno ventanaInsertar = new VentanaAlumno();
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    alumnos = alumBO.buscarPorAlumno("");
                    actualizarTablaAlumno();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnInsertarAlumnosActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        int fila = jTablePagos.getSelectedRow();
        String tipoPago = cbPagos.getSelectedItem().toString();

        try {
            System.out.println( "Eliminando "+tipoPago+" con id: "+jTablePagos.getValueAt(fila, 1) );
            int id = (int) jTablePagos.getValueAt(fila, 1);
            System.out.println("ID :"+id);
            switch (tipoPago) {
            case "Pago Matricula" -> {
                modeloPagoMatricula.removeRow(fila);
                pagoMatBO.eliminarPagoMatriculaPorId(id);
            }
            case "Pago Pensiones" -> {
                modeloPagoPension.removeRow(fila);
                pagoPenBO.eliminarPagoPensionPorId(id);
            }
            case "Pago Varios" -> {
                modeloPagoVarios.removeRow(fila);
                pagoVarBO.eliminarPagoVariosPorId(id);
            }
        }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void cbPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPagosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPagosActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String tipoPago = String.valueOf(cbPagos.getSelectedItem());
        
        VentanaPagos ventanaInsertar = ventanaInsertar = new VentanaPagos(tipoPago);
        
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    ActualizarTablaXTipoPago(tipoPago);
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String tipoPago = String.valueOf(cbPagos.getSelectedItem());
        int fila = jTablePagos.getSelectedRow();
        
        VentanaPagos ventanaInsertar;
        
        switch (tipoPago) {
            case "Pago Matricula" -> {
                pagoMatricula newPago = pagosMatricula.get(fila);
                ventanaInsertar = new VentanaPagos(tipoPago, newPago);
            }
            case "Pago Pensiones" -> {
                pagoPensiones newPago = pagosPensiones.get(fila);
                ventanaInsertar = new VentanaPagos(tipoPago, newPago);
                }
            case "Pago Varios" -> {
                pagoVarios newPago = pagosVarios.get(fila);
                ventanaInsertar = new VentanaPagos(tipoPago, newPago);
                }
            default -> {
                ventanaInsertar = new VentanaPagos(tipoPago);
            }
        }
        
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    ActualizarTablaXTipoPago(tipoPago);
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnSalirApoderadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirApoderadoActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirApoderadoActionPerformed

    private void btnEditarApoderadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarApoderadoActionPerformed
        int fila = jTableGestionApoderados.getSelectedRow();
        Apoderado apoderado = apoderados.get(fila);

        VentanaApoderado ventanaInsertar = new VentanaApoderado(apoderado);
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    apoderados = apoBO.buscarApoderadoPorNombre("");
                    actualizarTablaApoderado();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnEditarApoderadoActionPerformed

    private void btnEliminarApoderadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarApoderadoActionPerformed
        // TODO add your handling code here:

        int fila = jTableGestionApoderados.getSelectedRow();

        try {
            System.out.println( "Eliminando Apoderado con id: "+jTableGestionApoderados.getValueAt(fila, 1) );
            int id = (int)jTableGestionApoderados.getValueAt(fila, 1);
            System.out.println("ID :"+id);
            apoBO.eliminarApoderado(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloApoderado.removeRow(fila);
    }//GEN-LAST:event_btnEliminarApoderadoActionPerformed

    private void btnInsertarApoderadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarApoderadoActionPerformed
        VentanaApoderado ventanaInsertar = new VentanaApoderado();
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    apoderados = apoBO.buscarApoderadoPorNombre("");
                    actualizarTablaApoderado();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnInsertarApoderadoActionPerformed

    private void btnSalirDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirDocenteActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirDocenteActionPerformed

    private void btnActualizarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarDocenteActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionDocente.getSelectedRow();
        Docente docente1 = docentes.get(fila);

        VentanaDocente ventanaInsertar = new VentanaDocente(docente1);
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    docentes = docBO.buscarPorDocente("");
                    actualizarTablaDocente();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnActualizarDocenteActionPerformed

    private void btnEliminarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDocenteActionPerformed
        // TODO add your handling code here:
    
        int fila = jTableGestionDocente.getSelectedRow();

        try {
            System.out.println( "Eliminando Apoderado con id: "+jTableGestionDocente.getValueAt(fila, 1) );
            int id = (int)jTableGestionDocente.getValueAt(fila, 1);
            System.out.println("ID :"+id);
            docBO.eliminar(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloDocente.removeRow(fila);
    }//GEN-LAST:event_btnEliminarDocenteActionPerformed

    private void btnInsertarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarDocenteActionPerformed
        // TODO add your handling code here:
        VentanaApoderado ventanaInsertar = new VentanaApoderado();
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    docentes = docBO.buscarPorDocente("");
                    actualizarTablaDocente();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnInsertarDocenteActionPerformed

    private void btnEliminarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNotaActionPerformed
        // TODO add your handling code here:
        if(jTableNotas.getSelectedRow()!=-1){
            JOptionPane.showMessageDialog(null, "FUNCIONA");
            try {
                int idNotaEliminar = hnotaslist.get(jTableNotas.getSelectedRow()).getHistorial_id();
                System.out.println(idNotaEliminar + " ++++++=======SE ELIMINARA ESTO");
                notasBO.eliminar(idNotaEliminar);
            } catch (Exception ex) {
                System.out.println(ex);
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro a eliminar");
        }
    }//GEN-LAST:event_btnEliminarNotaActionPerformed

    private void btnInsertarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarNotaActionPerformed
        // TODO add your handling code here:
        InsertNotas insNotas = new InsertNotas();
        insNotas.setVisible(true);
    }//GEN-LAST:event_btnInsertarNotaActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionMatricula.getSelectedRow();
        Matricula matricula = matriculas.get(fila);
        
        VentanaMatricula ventanaInsertar = new VentanaMatricula(matricula);
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    matriculas = matBO.ListarMatriculas();
                    actualizarTablaMatricula();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        VentanaMatricula ventanaInsertar = new VentanaMatricula();
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    matriculas = matBO.ListarMatriculas();
                    actualizarTablaMatricula();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionMatricula.getSelectedRow();

        try {
            System.out.println( "Eliminando Matricula con id: "+jTableGestionMatricula.getValueAt(fila, 1) );
            int id = (int) jTableGestionMatricula.getValueAt(fila, 1);
            System.out.println("ID :"+id);
            matBO.eliminarMatricula(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloMatricula.removeRow(fila);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void btnMarcarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarTodoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnMarcarTodoActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void cbOpcionesBuscarAlumnosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbOpcionesBuscarAlumnosMouseExited
        // TODO add your handling code here:
        System.out.println(cbOpcionesBuscarAlumnos.getSelectedItem()+"  saliendo");  
        //jComboBox4.s
    }//GEN-LAST:event_cbOpcionesBuscarAlumnosMouseExited

    private void txtDNIDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIDocenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIDocenteActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void btnRegistrarIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarIngresoActionPerformed
        // TODO add your handling code here:
        UsuarioBO ubo = new UsuarioBO();
        Usuario user = new Usuario();
        if(txtDNIDocente.getText().isBlank() || txtContrasena.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }else{
           if(Validator.isDNI(txtDNIDocente.getText())){ //FALTA QUE VERIFIQUE QUE NO ESTE YA REGISTRADO
               try {
                   System.out.println(txtContrasena.getText());
                   System.out.println(Time.valueOf(LocalTime.now()));
                   user.setDni(txtDNIDocente.getText());
                   docente = docenteBO.buscarPorDNI(txtDNIDocente.getText());
                   docente.setApellidosNombres2();
                   user.setClave(txtContrasena.getText());
                   try {
                       ubo.loguin(user);
                       adocentes.add(new AsistenciaDocente(txtDNIDocente.getText(), Time.valueOf(LocalTime.now())));
                       actualizarTablaAsistenciaDocentes();
                   } catch (Exception ex) {
                       Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                       JOptionPane.showMessageDialog(null, "No se pudo registrar el ingreso", "Credenciales incorrectas", 1);
                   }
               } catch (Exception ex) {
                   Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
    }//GEN-LAST:event_btnRegistrarIngresoActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionUsuarios.getSelectedRow();

        try {
            int id = (int)jTableGestionUsuarios.getValueAt(fila, 0);
            System.out.println("ID :"+id);
            usuarioBO.eliminar(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        actualizarTablaUsuarios();
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnInsertarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarUsuarioActionPerformed
        // TODO add your handling code here:
        InsertUsuario insUsu = new InsertUsuario();
        for (Usuario usdni : usuarios) {
            dniUsuarios.add(usdni.getDni());
        }
        insUsu.setVisible(true);
        insUsu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    usuarios = usuarioBO.buscarPorUsuario("");
                    actualizarTablaUsuarios();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//GEN-LAST:event_btnInsertarUsuarioActionPerformed

    private void btnCambiarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarClaveActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionUsuarios.getSelectedRow();
        Usuario usuarioL = usuarios.get(fila);
        
        VentanaUsuarioCambiarClave ventanaUsuarioCambiarClave = new VentanaUsuarioCambiarClave(usuarioL);
        // Agregar el WindowListener a la ventana
        ventanaUsuarioCambiarClave.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    usuarios = usuarioBO.buscarPorUsuario("");
                    actualizarTablaUsuarios();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        if(!(usuarios.isEmpty())){
            if(fila>=0){
                ventanaUsuarioCambiarClave.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario para editar");
            }
        }else{
            JOptionPane.showMessageDialog(null, "La tabla usuarios esta vacia");
        }
    }//GEN-LAST:event_btnCambiarClaveActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        // TODO add your handling code here:
//        int fila = jTableGestionUsuarios.getSelectedRow();
//        int columna = jTableGestionUsuarios.getSelectedColumn();
//
//        try {
//            String datoNuevo = JOptionPane.showInputDialog("Ingrese el valor con la que desee actualizar");;
//
//            Usuario interno = usuarios.get(fila);
//
//            switch (columna) {
//                case 1 -> interno.setDni(datoNuevo);
//                case 2 -> interno.setApellido_paterno(datoNuevo);
//                case 3 -> interno.setApellido_materno(datoNuevo);
//                case 4 -> interno.setNombres(datoNuevo);
//                case 5 -> interno.setClave(datoNuevo );
//                case 6 -> interno.setRol(datoNuevo);
//                default -> {
//                }
//            }
//            
//            System.out.println(datoNuevo + " - " + interno.getUsuario_id());
//            usuarioBO.actualizar(interno);
//
//            actualizarTablaUsuarios();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        int fila = jTableGestionUsuarios.getSelectedRow();
        Usuario usuarioL = new Usuario();
        if(fila != -1){
            usuarioL = usuarios.get(fila);
            for (Usuario usdni : usuarios) {
                if(!(usdni.equals(usuarios.get(fila)))){
                    dniUsuarios.add(usdni.getDni());
                }
            }
        }
        
        VentanaUsuario ventanaActualizar = new VentanaUsuario(usuarioL);
        // Agregar el WindowListener a la ventana
        ventanaActualizar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    usuarios = usuarioBO.buscarPorUsuario("");
                    actualizarTablaUsuarios();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        if(!(usuarios.isEmpty())){
            if(fila>=0){
                ventanaActualizar.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario para editar");
            }
        }else{
            JOptionPane.showMessageDialog(null, "La tabla usuarios esta vacia");
        }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void btnInsertarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarCursoActionPerformed
        VentanaCurso ventanaInsertar = new VentanaCurso();
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    cursos = cursoBO.buscarPorCurso("");
                    actualizarTablaCurso();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnInsertarCursoActionPerformed

    private void btnInsertarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarAreaActionPerformed
        // TODO add your handling code here:
        VentanaArea ventanaInsertar = new VentanaArea();
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    areas = areaBO.listar();
                    actualizarTablaArea();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnInsertarAreaActionPerformed

    private void btnActualizarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarAreaActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionArea.getSelectedRow();
        Area area = areas.get(fila);
        
        VentanaArea ventanaInsertar = new VentanaArea(area);
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    areas = areaBO.listar();
                    actualizarTablaArea();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnActualizarAreaActionPerformed

    private void btnEliminarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAreaActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionArea.getSelectedRow();

        try {
            System.out.println( "Eliminando Apoderado con id: "+jTableGestionArea.getValueAt(fila, 1) );
            int id = (int)jTableGestionArea.getValueAt(fila, 0);
            System.out.println("ID :"+id);
            areaBO.eliminar(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloArea.removeRow(fila);
    }//GEN-LAST:event_btnEliminarAreaActionPerformed

    private void btnActualizarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCursoActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionCurso.getSelectedRow();
        Curso curso = cursos.get(fila);
        
        VentanaCurso ventanaInsertar = new VentanaCurso(curso);
        // Agregar el WindowListener a la ventana
        ventanaInsertar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    cursos = cursoBO.buscarPorCurso("");
                    actualizarTablaCurso();
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Mostrar la ventana
        ventanaInsertar.setVisible(true);
    }//GEN-LAST:event_btnActualizarCursoActionPerformed

    private void btnEliminarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCursoActionPerformed
        // TODO add your handling code here:
        int fila = jTableGestionCurso.getSelectedRow();

        try {
            System.out.println( "Eliminando Apoderado con id: "+jTableGestionCurso.getValueAt(fila, 1) );
            int id = (int)jTableGestionCurso.getValueAt(fila, 0);
            System.out.println("ID :"+id);
            cursoBO.eliminar(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloCurso.removeRow(fila);
    }//GEN-LAST:event_btnEliminarCursoActionPerformed

    private void btnBuscarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlumnosActionPerformed
        // TODO add your handling code here:
        int opcion = cbOpcionesBuscarAlumnos.getSelectedIndex();
        String buscar = txtBuscarAlumnos.getText();
        
        switch (opcion) {
            case 0:
                {
                    alumnos.clear();
                    try {
                        alumnos.add(alumBO.buscarPorDNI(buscar));
                    } catch (Exception ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case 1:
                {
                    try {
                        alumnos=alumBO.buscarPorAlumno(buscar);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            default:
                throw new AssertionError();
        }
        actualizarTablaAlumno();
    }//GEN-LAST:event_btnBuscarAlumnosActionPerformed

    private void btnBuscarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDocenteActionPerformed
        // TODO add your handling code here:
        int opcion = cbBuscarDocente.getSelectedIndex();
        String buscar = txtBuscarDocente.getText();
        
        switch (opcion) {
            case 0:
                {
                    docentes.clear();
                    try {
                        docentes.add(docenteBO.buscarPorDNI(buscar));
                    } catch (Exception ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case 1:
                {
                    try {
                        docentes=docenteBO.buscarPorDocente(buscar);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            default:
                throw new AssertionError();
        }
        actualizarTablaDocente();
    }//GEN-LAST:event_btnBuscarDocenteActionPerformed

    private void btnBuscarApoderadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarApoderadosActionPerformed
        // TODO add your handling code here:
        int opcion = cbBuscarApoderado.getSelectedIndex();
        String buscar = txtBuscarApoderado.getText();
        
        switch (opcion) {
            case 0:
                {
                    apoderados.clear();
                    try {
                        apoderados=apoBO.buscarApoderadoPorNombre(buscar);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case 1:
                {
                    try {
                        apoderados.add(apoBO.buscarApoderadoPorDNI(buscar)); 
                    } catch (Exception ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            default:
                throw new AssertionError();
        }
        actualizarTablaApoderado();
    }//GEN-LAST:event_btnBuscarApoderadosActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton32ActionPerformed

    private void cbNivelNotaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbNivelNotaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        int fila = cbNivelNota.getSelectedIndex();
        switch (fila) {
            case 0 -> {
                try {
                    cursosXNivel = cursoBO.buscarPorNivel("I");
                } catch (Exception ex) {
                    System.out.println(ex);
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case 1 -> {
                try {
                    cursosXNivel = cursoBO.buscarPorNivel("P");
                } catch (Exception ex) {
                    System.out.println(ex);
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case 2 -> {
                try {
                    cursosXNivel = cursoBO.buscarPorNivel("S");
                } catch (Exception ex) {
                    System.out.println(ex);
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            default -> throw new AssertionError();
        }
        ArrayList<Character> gr = new ArrayList<>();
        for(Curso c : cursosXNivel){
            gr.add(c.getGrado());
        }
        HashSet<Character> hashset = new HashSet<>();
        hashset.addAll(gr);
        gr = new ArrayList<>(hashset);
        Collections.sort(gr);
        cbGradoNota.removeAllItems();
        for(Character ch : gr){
            cbGradoNota.addItem(String.valueOf(ch)+"' grado");
        }
        cbGradoNota.setEnabled(true);
    }//GEN-LAST:event_cbNivelNotaPopupMenuWillBecomeInvisible

    private void cbGradoNotaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbGradoNotaPopupMenuWillBecomeInvisible
        try {
            // TODO add your handling code here:
            char val = String.valueOf(cbGradoNota.getSelectedItem()).charAt(0);
            cursosXGrado = cursoBO.buscarPorGrado(String.valueOf(cursosXNivel.get(0).getNivel()), String.valueOf(val));
        } catch (Exception ex) {
            System.out.println("no se sabe");
            System.out.println(ex);
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbCursoNota.removeAllItems();
        for(Curso cg: cursosXGrado){
            cbCursoNota.addItem(cg.getNombre());
        }
        cbCursoNota.setEnabled(true);
    }//GEN-LAST:event_cbGradoNotaPopupMenuWillBecomeInvisible

    private void cbCursoNotaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCursoNotaPopupMenuWillBecomeInvisible
        try {
            // TODO add your handling code here:
            String name=cursosXGrado.get(0).getNombre();
            String grado = String.valueOf(cursosXGrado.get(0).getGrado());
            String nivel = String.valueOf(cbNivelNota.getItemAt(cbNivelNota.getSelectedIndex()).charAt(0));
            hnotaslist = hnotasBO.buscarPorCurso(name,grado,nivel);
            mostrarTablaNotas(hnotaslist);
        } catch (Exception ex) {
            System.out.println("Fallo en Interface");
            System.out.println(ex);
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbCursoNotaPopupMenuWillBecomeInvisible

    private void cbPagosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbPagosPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        ActualizarTablaXTipoPago((String) cbPagos.getSelectedItem());
    }//GEN-LAST:event_cbPagosPopupMenuWillBecomeInvisible
    
    private DefaultTableModel modeloAlumnos;
    private AlumnoBO alumBO = new AlumnoBO();
    private ArrayList<Alumno>  alumnos;
    
    private void actualizarTablaAlumno(){
        modeloAlumnos = new DefaultTableModel();
        
        jTableGestionAlumnos.setModel(modeloAlumnos);
        
        modeloAlumnos.addColumn("#");
        modeloAlumnos.addColumn("Alumno id");
        modeloAlumnos.addColumn("DNI");
        modeloAlumnos.addColumn("Apellido paterno");
        modeloAlumnos.addColumn("Apellido materno");
        modeloAlumnos.addColumn("Nombres");
        modeloAlumnos.addColumn("Fecha de nacimiento");
        modeloAlumnos.addColumn("Correo electronico");
        
        try {
            System.out.println(alumnos.size());
            for (int i = 0; i < alumnos.size(); i++) {
                Alumno alumno = alumnos.get(i);
                System.out.println(alumno.getCorreo_electrico());
                Object [] fila = new Object[] { (i+1) , alumno.getAlumno_id(), alumno.getDni(), 
                    alumno.getApellido_paterno(), alumno.getApellido_materno(),
                    alumno.getNombres(), alumno.getFecha_nacimiento() , alumno.getCorreo_electrico()};
                modeloAlumnos.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloDocente;
    private DocenteBO docBO = new DocenteBO();
    private ArrayList<Docente>  docentes;
    
    private void actualizarTablaDocente(){
        modeloDocente = new DefaultTableModel();
        
        jTableGestionDocente.setModel(modeloDocente);
        
        modeloDocente.addColumn("#");
        modeloDocente.addColumn("docente_id");
        modeloDocente.addColumn("dni");
        modeloDocente.addColumn("apellido_paterno");
        modeloDocente.addColumn("apellido_materno");
        modeloDocente.addColumn("nombres");
        modeloDocente.addColumn("contacto");
        
        try {
            System.out.println(docentes.size());
            for (int i = 0; i < docentes.size(); i++) {
                Docente docente = docentes.get(i);
                
                Object [] fila = new Object[] { (i+1) , docente.getDocente_id(), docente.getDni(), 
                    docente.getApellido_paterno(), docente.getApellido_materno(),
                    docente.getNombres(),docente.getContacto()};
                modeloDocente.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloApoderado;
    private ArrayList<Apoderado> apoderados;
    private ApoderadoBO apoBO = new ApoderadoBO();
    
    private void actualizarTablaApoderado(){
        modeloApoderado = new DefaultTableModel();
        
        jTableGestionApoderados.setModel(modeloApoderado);
        
        modeloApoderado.addColumn("#");
        modeloApoderado.addColumn("apoderado_id");
        modeloApoderado.addColumn("dni");
        modeloApoderado.addColumn("apellido_paterno");
        modeloApoderado.addColumn("apellido_materno");
        modeloApoderado.addColumn("nombres");
        modeloApoderado.addColumn("contacto");
        
        try {
            for (int i = 0; i < apoderados.size(); i++) {
                Apoderado apoderado = apoderados.get(i);
                
                Object [] fila = new Object[] { (i+1) , apoderado.getApoderado_id(), apoderado.getDni(), 
                    apoderado.getApellido_paterno(), apoderado.getApellido_materno(),
                    apoderado.getNombres(),apoderado.getContacto()};
                modeloApoderado.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloMatricula;
    private MatriculaBO matBO = new MatriculaBO();
    private ArrayList<Matricula>  matriculas;
    
    private void actualizarTablaMatricula(){
        modeloMatricula = new DefaultTableModel();
        
        jTableGestionMatricula.setModel(modeloMatricula);
        
        modeloMatricula.addColumn("#");
        modeloMatricula.addColumn("matricula_id");
        modeloMatricula.addColumn("fecha");
        modeloMatricula.addColumn("grado");
        modeloMatricula.addColumn("nivel");
        modeloMatricula.addColumn("turno");
        modeloMatricula.addColumn("alumno_id");
        
        try {
            System.out.println(matriculas.size());
            
            for (int i = 0; i < matriculas.size(); i++) {
                Matricula matricula = matriculas.get(i);
                
                Object [] fila = new Object[] { (i+1) , matricula.getMatricula_id(), matricula.getFecha(), 
                    matricula.getGrado(), matricula.getNivel(),
                    matricula.getTurno(),matricula.getAlumno_id()};
                modeloMatricula.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloArea;
    private AreaBO areaBO = new AreaBO();
    private ArrayList<Area>  areas;
    
    private void actualizarTablaArea(){
        modeloArea = new DefaultTableModel();
        
        jTableGestionArea.setModel(modeloArea);
        
        modeloArea.addColumn("Area_id");
        modeloArea.addColumn("Nombre");
        
        
        try {
            System.out.println(areas.size());
            
            for (int i = 0; i < areas.size(); i++) {
                Area area = areas.get(i);
                
                Object [] fila = new Object[] { area.getArea_id(), area.getArea_nombre()};
                modeloArea.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloCurso;
    private ArrayList<Curso> cursos;
    //private CursoBO cursoBO = new CursoBO();
    
    private void actualizarTablaCurso(){
        modeloCurso = new DefaultTableModel();
        
        jTableGestionCurso.setModel(modeloCurso);
        
        modeloCurso.addColumn("curso_id");
        modeloCurso.addColumn("curso_nombre");
        modeloCurso.addColumn("grado");
        modeloCurso.addColumn("nivel");
        modeloCurso.addColumn("area_id");
        
        try {
            System.out.println(cursos.size());
            for (int i = 0; i < cursos.size(); i++) {
                Curso curso = cursos.get(i);
                
                Object [] fila = new Object[] {curso.getCurso_id(), curso.getNombre(), 
                    curso.getGrado(), curso.getNivel(),
                    curso.getArea_id()};
                modeloCurso.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloPagoMatricula;
    private PagoMatriculaBO pagoMatBO = new PagoMatriculaBO();
    private ArrayList<pagoMatricula>  pagosMatricula;
    
    private void actualizarTablaPagoMatricula(){
        modeloPagoMatricula = new DefaultTableModel();
        
        jTablePagos.setModel(modeloPagoMatricula);
        
        modeloPagoMatricula.addColumn("#");
        modeloPagoMatricula.addColumn("Pago Matricula ID");
        modeloPagoMatricula.addColumn("Fecha");
        modeloPagoMatricula.addColumn("Monto");
        modeloPagoMatricula.addColumn("Observacion");
        modeloPagoMatricula.addColumn("Alumno ID");
        
        try {
            System.out.println(pagosMatricula.size());
            for (int i = 0; i < pagosMatricula.size(); i++) {
                pagoMatricula pagoMat = pagosMatricula.get(i);
                
                Object [] fila = new Object[] { (i+1) , pagoMat.getPago_matricula_id(), pagoMat.getFecha(), 
                    pagoMat.getMonto(), pagoMat.getObservacion(), pagoMat.getAlumno_id()};
                modeloPagoMatricula.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloPagoPension;
    private PagoPensionBO pagoPenBO = new PagoPensionBO();
    private ArrayList<pagoPensiones>  pagosPensiones;
    
    private void actualizarTablaPagoPensiones(){
        modeloPagoPension = new DefaultTableModel();
        
        jTablePagos.setModel(modeloPagoPension);
        
        modeloPagoPension.addColumn("#");
        modeloPagoPension.addColumn("Pago Pension ID");
        modeloPagoPension.addColumn("Fecha");
        modeloPagoPension.addColumn("Monto");
        modeloPagoPension.addColumn("Observacion");
        modeloPagoPension.addColumn("Alumno ID");
        
        try {
            System.out.println(pagosPensiones.size());
            for (int i = 0; i < pagosPensiones.size(); i++) {
                pagoPensiones pagoPen = pagosPensiones.get(i);
                
                Object [] fila = new Object[] { (i+1) , pagoPen.getPago_pensiones_id(), pagoPen.getFecha(), 
                    pagoPen.getMonto(), pagoPen.getObservacion(), pagoPen.getAlumno_id()};
                modeloPagoPension.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private DefaultTableModel modeloPagoVarios;
    private PagoVariosBO pagoVarBO = new PagoVariosBO();
    private ArrayList<pagoVarios>  pagosVarios;
    
    private void actualizarTablaPagoVarios(){
        modeloPagoVarios = new DefaultTableModel();
        
        jTablePagos.setModel(modeloPagoVarios);
        
        modeloPagoVarios.addColumn("#");
        modeloPagoVarios.addColumn("Pago Varios ID");
        modeloPagoVarios.addColumn("Fecha");
        modeloPagoVarios.addColumn("Monto");
        modeloPagoVarios.addColumn("Descripción");
        modeloPagoVarios.addColumn("Alumno ID");
        
        try {
            System.out.println(pagosVarios.size());
            for (int i = 0; i < pagosVarios.size(); i++) {
                pagoVarios pagoVar = pagosVarios.get(i);
                
                Object [] fila = new Object[] { (i+1) , pagoVar.getPago_varios_id(), pagoVar.getFecha(), 
                    pagoVar.getMonto(), pagoVar.getObservacion(), pagoVar.getAlumno_id()};
                modeloPagoVarios.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*****
    *
    * FUNCIONES
    *
    *****/
    
    //Actualizar Tabla por Tipo de Pago
    public void ActualizarTablaXTipoPago(String tipo){
        try {
            switch (tipo) {
                case "Pago Matricula":
                    pagosMatricula = pagoMatBO.listarPagoMatricula();
                    actualizarTablaPagoMatricula();
                    //PAGO MATRICULA
                    break;
                case "Pago Varios":
                    pagosVarios = pagoVarBO.listarPagoVarios();
                    actualizarTablaPagoVarios();
                    //PAGO VARIOS
                    break;
                case "Pago Pensiones":
                    pagosPensiones = pagoPenBO.listarPagoPension();
                    actualizarTablaPagoPensiones();
                    //PAGO PENSIONES
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Funcion para ordenar a los alumnos de acuerdo a su DNI
    public ArrayList<Alumno> ordenarPorAlumnosDNI(ArrayList<Alumno> alumnos){
        alumnos.sort((p1,p2)->String.valueOf(p1.getDni()).compareTo(String.valueOf(p2.getDni())));;
        return alumnos;
    }
    
    //Funcion para ordenar a los alumnos de acuerdo a su apellido paterno
    public ArrayList<Alumno> ordenarPorAlumnosxApellidoPaterno(ArrayList<Alumno> alumnos){
        alumnos.sort((p1,p2)->String.valueOf(p1.getApellido_paterno()).compareTo(String.valueOf(p2.getApellido_paterno())));;
        return alumnos;
    }
    
    //Funcion para ordenar a los alumnos de acuerdo a su apellido materno
    public ArrayList<Alumno> ordenarPorAlumnosxApellidoMaterno(ArrayList<Alumno> alumnos){
        alumnos.sort((p1,p2)->String.valueOf(p1.getApellido_materno()).compareTo(String.valueOf(p2.getApellido_materno())));;
        return alumnos;
    }
    
    //Funcion para ordenar a los alumnos de acuerdo a su nombre
    public ArrayList<Alumno> ordenarPorAlumnosxNombre(ArrayList<Alumno> alumnos){
        alumnos.sort((p1,p2)->String.valueOf(p1.getNombres()).compareTo(String.valueOf(p2.getNombres())));;
        return alumnos;
    }
    
    //Funcion para ordenar a los alumnos de acuerdo a su fecha de nacimientos
    public ArrayList<Alumno> ordenarPorAlumnosxFechaNacimiento(ArrayList<Alumno> alumnos){
        alumnos.sort((p1,p2)->String.valueOf(p1.getFecha_nacimiento()).compareTo(String.valueOf(p2.getFecha_nacimiento())));;
        return alumnos;
    }
    
    
    //Funcion para ordenar a los usuarios de acuerdo a su dni
    public ArrayList<Usuario> ordenarUsuariosPorDNI(ArrayList<Usuario> usuarios){
        usuarios.sort((p1,p2)->String.valueOf(p1.getDni()).compareTo(String.valueOf(p2.getDni())));;
        return usuarios;
    }
    
    //Funcion para ordenar a los usuarios de acuerdo a su apellido paterno
    public ArrayList<Usuario> ordenarUsuariosPorApellidoPaterno(ArrayList<Usuario> usuarios){
        usuarios.sort((p1,p2)->String.valueOf(p1.getApellido_paterno()).compareTo(String.valueOf(p2.getApellido_paterno())));;
        return usuarios;
    }
    
    //Funcion para ordenar a los usuarios de acuerdo a su apellido materno
    public ArrayList<Usuario> ordenarUsuariosPorApellidoMaterno(ArrayList<Usuario> usuarios){
        usuarios.sort((p1,p2)->String.valueOf(p1.getApellido_materno()).compareTo(String.valueOf(p2.getApellido_materno())));;
        return usuarios;
    }
    
    //Funcion para ordenar a los usuarios de acuerdo a su nombre
    public ArrayList<Usuario> ordenarUsuariosPorNombre(ArrayList<Usuario> usuarios){
        usuarios.sort((p1,p2)->String.valueOf(p1.getNombres()).compareTo(String.valueOf(p2.getNombres())));;
        return usuarios;
    }
    
    //Funcion para ordenar a los usuarios de acuerdo a su nombre
    public ArrayList<Usuario> ordenarUsuariosPorRol(ArrayList<Usuario> usuarios){
        usuarios.sort((p1,p2)->String.valueOf(p1.getRol()).compareTo(String.valueOf(p2.getRol())));;
        return usuarios;
    }
    
    
    //Funcion para ordenar a los apoderados de acuerdo a su DNI
    public ArrayList<Apoderado> ordenarPorApoderadosDNI(ArrayList<Apoderado> apoderados){
        apoderados.sort((p1,p2)->String.valueOf(p1.getDni()).compareTo(String.valueOf(p2.getDni())));;
        return apoderados;
    }
    //Funcion para ordenar a los apoderados de acuerdo a su apellido paterno
    public ArrayList<Apoderado> ordenarPorApoderadosxApellidoPaterno(ArrayList<Apoderado> apoderados){
        apoderados.sort((p1,p2)->String.valueOf(p1.getApellido_paterno()).compareTo(String.valueOf(p2.getApellido_paterno())));;
        return apoderados;
    }  
    //Funcion para ordenar a los apoderados de acuerdo a su apellido materno
    public ArrayList<Apoderado> ordenarPorApoderadosxApellidoMaterno(ArrayList<Apoderado> apoderados){
        apoderados.sort((p1,p2)->String.valueOf(p1.getApellido_materno()).compareTo(String.valueOf(p2.getApellido_materno())));;
        return apoderados;
    }
    //Funcion para ordenar a los apoderados de acuerdo a su nombre
    public ArrayList<Apoderado> ordenarPorApoderadosxNombre(ArrayList<Apoderado> apoderados){
        apoderados.sort((p1,p2)->String.valueOf(p1.getNombres()).compareTo(String.valueOf(p2.getNombres())));;
        return apoderados;
    }
    //Funcion para ordenar a los apoderados de acuerdo a su fecha de nacimientos
    public ArrayList<Apoderado> ordenarPorApoderadosxContacto(ArrayList<Apoderado> apoderados){
        apoderados.sort((p1,p2)->String.valueOf(p1.getContacto()).compareTo(String.valueOf(p2.getContacto())));;
        return apoderados;
    }
    
    //Funcion para ordenar a los docentes de acuerdo a su DNI
    public ArrayList<Docente> ordenarPorDocenteDNI(ArrayList<Docente> docentes){
        docentes.sort((p1,p2)->String.valueOf(p1.getDni()).compareTo(String.valueOf(p2.getDni())));;
        return docentes;
    }
    //Funcion para ordenar a los apoderados de acuerdo a su apellido paterno
    public ArrayList<Docente> ordenarPorDocentesxApellidoPaterno(ArrayList<Docente> docentes){
        docentes.sort((p1,p2)->String.valueOf(p1.getApellido_paterno()).compareTo(String.valueOf(p2.getApellido_paterno())));;
        return docentes;
    }  
    //Funcion para ordenar a los apoderados de acuerdo a su apellido materno
    public ArrayList<Docente> ordenarPorDocentesxApellidoMaterno(ArrayList<Docente> docentes){
        docentes.sort((p1,p2)->String.valueOf(p1.getApellido_materno()).compareTo(String.valueOf(p2.getApellido_materno())));;
        return docentes;
    }
    //Funcion para ordenar a los apoderados de acuerdo a su nombre
    public ArrayList<Docente> ordenarPorDocentesxNombre(ArrayList<Docente> docentes){
        docentes.sort((p1,p2)->String.valueOf(p1.getNombres()).compareTo(String.valueOf(p2.getNombres())));;
        return docentes;
    }
    //Funcion para ordenar a los apoderados de acuerdo a su fecha de nacimientos
    public ArrayList<Docente> ordenarPorDocentesxContacto(ArrayList<Docente> docentes){
        docentes.sort((p1,p2)->String.valueOf(p1.getContacto()).compareTo(String.valueOf(p2.getContacto())));;
        return docentes;
    }
    
    
    private void actualizarTablaAsistenciaDocentes(){
        modeloAdocentes = new DefaultTableModel();
        
        tableAsistenciaDocentes.setModel(modeloAdocentes);
        
        try {
            for (int i = 0; i < adocentes.size(); i++) {
                adocente = adocentes.get(i);
                
                Object [] fila = new Object[] { adocente.getAsistencia_id(), docente.getApellidosNombres() , adocente.getHora_ingreso(), adocente.getHora_salida()};
                modeloAdocentes.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void actualizarTablaUsuarios(){
        modeloUsuarios = (DefaultTableModel) jTableGestionUsuarios.getModel();
        modeloUsuarios.setRowCount(0);
        try {
            usuarios = usuarioBO.buscarPorUsuario("");
        } catch (Exception ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(usuarios.size());
        try {
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                System.out.println(usuario.getDni());
                System.out.println(usuario.getApellido_paterno());
                System.out.println(usuario.getClave());
                Object [] fila = new Object[] {usuario.getUsuario_id(), usuario.getDni(), usuario.getApellido_paterno(), usuario.getApellido_materno(), usuario.getNombres(), usuario.getClave(), usuario.getRol()};
                modeloUsuarios.addRow(fila);
            }
            jTableGestionUsuarios.setModel(modeloUsuarios);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void mostrarTablaNotas(ArrayList<HistorialNotas> hnl){
        modeloNotas = (DefaultTableModel) jTableNotas.getModel();
        modeloNotas.setRowCount(0);
        
        System.out.println(hnl.size());
        try {
            for (HistorialNotas hhh : hnl) {
                Notas n = hhh.getNota();
                Object [] fila = new Object[] {hhh.getAlumno().getApellidosNombres(),
                n.getNota1(),
                n.getNota2(),
                n.getNota3(),
                n.getNota4(),
                n.getNota5(),
                };
                modeloNotas.addRow(fila);
            }
            jTableNotas.setModel(modeloNotas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*****
    *
    * FIN FUNCIONES
    *
    *****/
    
    
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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> FiltroCbx;
    private javax.swing.JButton btnActualizarArea;
    private javax.swing.JButton btnActualizarCurso;
    private javax.swing.JButton btnActualizarDocente;
    private javax.swing.JButton btnBuscarAlumnos;
    private javax.swing.JButton btnBuscarApoderados;
    private javax.swing.JButton btnBuscarDocente;
    private javax.swing.JButton btnCambiarClave;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEditarAlumnos;
    private javax.swing.JButton btnEditarApoderado;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEliminarAlumnos;
    private javax.swing.JButton btnEliminarApoderado;
    private javax.swing.JButton btnEliminarArea;
    private javax.swing.JButton btnEliminarCurso;
    private javax.swing.JButton btnEliminarDocente;
    private javax.swing.JButton btnEliminarNota;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnInsertarAlumnos;
    private javax.swing.JButton btnInsertarApoderado;
    private javax.swing.JButton btnInsertarArea;
    private javax.swing.JButton btnInsertarCurso;
    private javax.swing.JButton btnInsertarDocente;
    private javax.swing.JButton btnInsertarNota;
    private javax.swing.JButton btnInsertarUsuario;
    private javax.swing.JButton btnMarcarTodo;
    private javax.swing.JButton btnRegistrarIngreso;
    private javax.swing.JButton btnRegistrarSalida;
    private javax.swing.JButton btnSalirAlumnos;
    private javax.swing.JButton btnSalirApoderado;
    private javax.swing.JButton btnSalirDocente;
    private javax.swing.JComboBox<String> cbBuscarApoderado;
    private javax.swing.JComboBox<String> cbBuscarDocente;
    private javax.swing.JComboBox<String> cbCursoNota;
    private javax.swing.JComboBox<String> cbGradoNota;
    private javax.swing.JComboBox<String> cbNivelNota;
    private javax.swing.JComboBox<String> cbOpcionesBuscarAlumnos;
    private javax.swing.JComboBox<String> cbPagos;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelAlumno;
    private javax.swing.JPanel jPanelApoderado;
    private javax.swing.JPanel jPanelCursoArea;
    private javax.swing.JPanel jPanelDocente;
    private javax.swing.JPanel jPanelMatricula;
    private javax.swing.JPanel jPanelNotas;
    private javax.swing.JPanel jPanelPagos;
    private javax.swing.JPanel jPanelUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTableGestionAlumnos;
    private javax.swing.JTable jTableGestionApoderados;
    private javax.swing.JTable jTableGestionArea;
    private javax.swing.JTable jTableGestionCurso;
    private javax.swing.JTable jTableGestionDocente;
    private javax.swing.JTable jTableGestionMatricula;
    private javax.swing.JTable jTableGestionUsuarios;
    private javax.swing.JTable jTableNotas;
    private javax.swing.JTable jTablePagos;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTable tableAsistenciaDocentes;
    private javax.swing.JTextField txtBuscarAlumnos;
    private javax.swing.JTextField txtBuscarApoderado;
    private javax.swing.JTextField txtBuscarDocente;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtDNIDocente;
    private javax.swing.JLabel txtRolUser;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}

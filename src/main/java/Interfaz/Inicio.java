/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
    
import BusinessLayer.AlumnoBO;
import BusinessLayer.ApoderadoBO;
import BusinessLayer.AreaBO;
import BusinessLayer.AsistenciaAlumnoBO;
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
import JavaBean.AsistenciaAlumno;
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
import Utilities.Encriptador;
import Utilities.Validator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import Connection.UConnection;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JTable;
/**
 *
 * @author xbest
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * **
     *
     * VARIABLES GLOBALES
     *
     ***
     */
    /// Variables globales utiles
    int f = -1;
    String usuarioSistema = "";
    String rolUsuarioSistema = "";
    
    /// OBJETOS
    private AsistenciaDocente adocente = new AsistenciaDocente();
    private Docente docente = new Docente();
    private Usuario usuario = new Usuario();
    private Notas notas = new Notas();
    private AsistenciaAlumno asisalumno = new AsistenciaAlumno();
    private AsistenciaDocente asistenciaDocente = new AsistenciaDocente();
    

    /// BUSSINESSLAYER
    private AsistenciaDocenteBO asisdocBO = new AsistenciaDocenteBO();
    private DocenteBO docenteBO = new DocenteBO();
    private UsuarioBO usuarioBO = new UsuarioBO();
    private UsuarioBO ubo = new UsuarioBO();
    private CursoBO cursoBO = new CursoBO();
    private NotasBO notasBO = new NotasBO();
    private HistorialNotasBO hnotasBO = new HistorialNotasBO();
    private AsistenciaAlumnoBO asisalumBO = new AsistenciaAlumnoBO();

    /// ARRAYS
    private ArrayList<AsistenciaDocente> adocentes = new ArrayList<>();
    private ArrayList<AsistenciaAlumno> aalumnos = new ArrayList<>();
    private ArrayList<Alumno> asalonasistencia = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Curso> cursosXNivel = new ArrayList<>();
    private ArrayList<Curso> cursosXGrado = new ArrayList<>();
    public static ArrayList<String> dniUsuarios = new ArrayList<>();
    private ArrayList<HistorialNotas> hnotaslist = new ArrayList<>();

    /// MODELOS DE TABLAS
    private DefaultTableModel modeloAdocentes = new DefaultTableModel();
    private DefaultTableModel modeloUsuarios = new DefaultTableModel();
    private DefaultTableModel modeloNotas = new DefaultTableModel();
    private DefaultTableModel modeloAsistenciaAlumnos = new DefaultTableModel();

    /**
     * **
     *
     * FIN VARIABLES GLOBALES
     *
     ***
     */
    /**
     * Creates new form Inicio
     */
    public Inicio() {
//        btnCerrarSesion.setMnemonic(KeyEvent.VK_X);
        initComponents();
        MyInitComponents();
        actualizarTablaUsuarios();
        
        ActualizarTablaXTipoPago("Pago Matricula");
        actualizarTablaAsistenciaDocentes();

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

    private void MyInitComponents() {
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
        
        jTableGestionDocente.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHeaderGestionDocenteMouseClicked(evt);
            }
        });
        
        jTablePagos.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHeaderPagosMouseClicked(evt);
            }
        });
        
        jTableGestionMatricula.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHeaderGestionMatriculaMouseClicked(evt);
            }
        });
        
        jTableGestionCurso.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHeaderGestionCursoMouseClicked(evt);
            }
        });
        
        jTableGestionArea.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHeaderGestionAreaMouseClicked(evt);
            }
        });
    }

    /**
     * ***
     *
     * EVENTOS CREADOS
     *
     ****
     */
    
    private String tablaOrdenada = ""; 
    private boolean asc = true;
    private int columnOrdenada = -1;
    
    private void jHeaderGestionAreaMouseClicked (java.awt.event.MouseEvent evt){
        JTableHeader header = jTableGestionArea.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTableGestionArea.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTableGestionArea.getName();
        }
        
        ordenarTabla(jTableGestionArea, columnIndex, asc, areas);
    }
    
    private void jHeaderGestionCursoMouseClicked (java.awt.event.MouseEvent evt){
        JTableHeader header = jTableGestionCurso.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTableGestionCurso.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTableGestionCurso.getName();
        }
        
        ordenarTabla(jTableGestionCurso, columnIndex, asc, cursos);
    }
    
    private void jHeaderGestionMatriculaMouseClicked (java.awt.event.MouseEvent evt){
        JTableHeader header = jTableGestionMatricula.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTableGestionMatricula.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTableGestionMatricula.getName();
        }
        
        ordenarTabla(jTableGestionMatricula, columnIndex, asc, matriculas);
    }
    
    private void jHeaderPagosMouseClicked (java.awt.event.MouseEvent evt){
        JTableHeader header = jTablePagos.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTablePagos.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTablePagos.getName();
        }
        
        switch ((String) cbPagos.getSelectedItem()) {
            case "Pago Matricula":
                ordenarTabla(jTablePagos, columnIndex, asc, pagosMatricula);
                //PAGO MATRICULA
                break;
            case "Pago Varios":
                ordenarTabla(jTablePagos, columnIndex, asc, pagosVarios);
                //PAGO VARIOS
                break;
            case "Pago Pensiones":
                ordenarTabla(jTablePagos, columnIndex, asc, pagosPensiones);
                //PAGO PENSIONES
                break;
        }
    }
    
    private void jHeaderGestionDocenteMouseClicked(java.awt.event.MouseEvent evt){
        JTableHeader header = jTableGestionDocente.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTableGestionDocente.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTableGestionDocente.getName();
        }
        
        ordenarTabla(jTableGestionDocente, columnIndex, asc, docentes);
    }
    
    private void jHeaderGestionApoderadosMouseClicked(java.awt.event.MouseEvent evt) {
        JTableHeader header = jTableGestionApoderados.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTableGestionApoderados.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTableGestionApoderados.getName();
        }
        
        ordenarTabla(jTableGestionApoderados, columnIndex, asc, apoderados);
    }

    private void jHeaderGestionAlumnosMouseClicked(java.awt.event.MouseEvent evt) {
        JTableHeader header = jTableGestionAlumnos.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTableGestionAlumnos.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTableGestionAlumnos.getName();
        }
        
        ordenarTabla(jTableGestionAlumnos, columnIndex, asc, alumnos);
    }

    private void jHeaderGestionUsuariosMouseClicked(java.awt.event.MouseEvent evt) {
        JTableHeader header = jTableGestionUsuarios.getTableHeader();
        int columnIndex = header.columnAtPoint(evt.getPoint());
       
        System.out.println("Seleccion: " + columnIndex);
        
        if ( columnIndex == columnOrdenada  && jTableGestionUsuarios.getName().equals(tablaOrdenada)) {
            asc = !asc;
        } else {
            columnOrdenada = columnIndex;
            asc = true;
            
            tablaOrdenada = jTableGestionUsuarios.getName();
        }
        
        ordenarTabla(jTableGestionUsuarios, columnIndex, asc, usuarios);
    }

    /**
     * ***
     *
     * FIN EVENTOS CREADOS
     *
     ****
     */
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
        cbBuscarDocente = new javax.swing.JComboBox<>();
        txtBuscarDocente = new javax.swing.JTextField();
        btnBuscarDocente = new javax.swing.JButton();
        btnReporteDocente = new javax.swing.JButton();
        btnEliminarApoderado2 = new javax.swing.JButton();
        jPanelApoderado = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableGestionApoderados = new javax.swing.JTable();
        btnInsertarApoderado = new javax.swing.JButton();
        btnEliminarApoderado = new javax.swing.JButton();
        btnEditarApoderado = new javax.swing.JButton();
        cbBuscarApoderado = new javax.swing.JComboBox<>();
        txtBuscarApoderado = new javax.swing.JTextField();
        btnBuscarApoderados = new javax.swing.JButton();
        btnEliminarApoderado1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanelPagos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePagos = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
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
        btnReporteCurso = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableGestionArea = new javax.swing.JTable();
        btnInsertarArea = new javax.swing.JButton();
        btnActualizarArea = new javax.swing.JButton();
        btnEliminarArea = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtblAsistenciaAlumnos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnMarcarTodo = new javax.swing.JButton();
        btnRegistrarSalon = new javax.swing.JButton();
        cbNivelAsis = new javax.swing.JComboBox<>();
        txtAlumnoAsistencia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbGradoAsis = new javax.swing.JComboBox<>();
        btnMostrarHistorial = new javax.swing.JButton();
        btnReporteHoy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableAsistenciaDocentes = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        btnRegistrarIngreso = new javax.swing.JButton();
        btnRegistrarSalida = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtDNIDocente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
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
        btnEliminarNota1 = new javax.swing.JButton();
        jPanelUsuarios = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableGestionUsuarios = new javax.swing.JTable();
        btnEditarUsuario = new javax.swing.JButton();
        btnCambiarClave = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnInsertarUsuario = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        FiltroCbx = new javax.swing.JComboBox<>();
        btnReporteUsuarios = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnReporteCurso4 = new javax.swing.JButton();
        jPanelAlumno = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableGestionAlumnos = new javax.swing.JTable();
        btnInsertarAlumnos = new javax.swing.JButton();
        btnEliminarAlumnos = new javax.swing.JButton();
        btnEditarAlumnos = new javax.swing.JButton();
        cbOpcionesBuscarAlumnos = new javax.swing.JComboBox<>();
        txtBuscarAlumnos = new javax.swing.JTextField();
        btnBuscarAlumnos = new javax.swing.JButton();
        btnReporteAlumnos = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtUser = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtRolUser = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

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
        setBackground(new java.awt.Color(248, 248, 248));
        setLocationByPlatform(true);
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
                "ID", "DNI", "Apellido Paterno", "Apellido Materno", "Nombres", "Contacto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGestionDocente.setName("tablaDocente"); // NOI18N
        jScrollPane6.setViewportView(jTableGestionDocente);
        if (jTableGestionDocente.getColumnModel().getColumnCount() > 0) {
            jTableGestionDocente.getColumnModel().getColumn(0).setResizable(false);
            jTableGestionDocente.getColumnModel().getColumn(0).setPreferredWidth(45);
            jTableGestionDocente.getColumnModel().getColumn(1).setResizable(false);
            jTableGestionDocente.getColumnModel().getColumn(1).setPreferredWidth(70);
            jTableGestionDocente.getColumnModel().getColumn(2).setResizable(false);
            jTableGestionDocente.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableGestionDocente.getColumnModel().getColumn(3).setResizable(false);
            jTableGestionDocente.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableGestionDocente.getColumnModel().getColumn(4).setResizable(false);
            jTableGestionDocente.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableGestionDocente.getColumnModel().getColumn(5).setResizable(false);
            jTableGestionDocente.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        btnInsertarDocente.setMnemonic('i');
        btnInsertarDocente.setText("Insertar");
        btnInsertarDocente.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
        btnInsertarDocente.setFocusable(false);
        btnInsertarDocente.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnInsertarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarDocenteActionPerformed(evt);
            }
        });

        btnEliminarDocente.setMnemonic('e');
        btnEliminarDocente.setText("Eliminar");
        btnEliminarDocente.setToolTipText("");
        btnEliminarDocente.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
        btnEliminarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDocenteActionPerformed(evt);
            }
        });

        btnActualizarDocente.setMnemonic('a');
        btnActualizarDocente.setText("Actualizar");
        btnActualizarDocente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[alt+a]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnActualizarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarDocenteActionPerformed(evt);
            }
        });

        cbBuscarDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apellidos y Nombres", "DNI" }));
        cbBuscarDocente.setToolTipText("");

        btnBuscarDocente.setMnemonic('b');
        btnBuscarDocente.setText("Buscar");
        btnBuscarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDocenteActionPerformed(evt);
            }
        });

        btnReporteDocente.setMnemonic('r');
        btnReporteDocente.setText("Reporte Docente");
        btnReporteDocente.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+r]"));
        btnReporteDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteDocenteActionPerformed(evt);
            }
        });

        btnEliminarApoderado2.setMnemonic('q');
        btnEliminarApoderado2.setText("Asignar Cursos a los Docentes");
        btnEliminarApoderado2.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+q]"));
        btnEliminarApoderado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarApoderado2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDocenteLayout = new javax.swing.GroupLayout(jPanelDocente);
        jPanelDocente.setLayout(jPanelDocenteLayout);
        jPanelDocenteLayout.setHorizontalGroup(
            jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDocenteLayout.createSequentialGroup()
                .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDocenteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelDocenteLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(cbBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelDocenteLayout.createSequentialGroup()
                        .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDocenteLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(btnInsertarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnReporteDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnEliminarApoderado2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDocenteLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelDocenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActualizarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReporteDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnInsertarDocente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarApoderado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Docente", jPanelDocente);

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
        jTableGestionApoderados.setName("tablaApoderado"); // NOI18N
        jScrollPane5.setViewportView(jTableGestionApoderados);

        btnInsertarApoderado.setMnemonic('i');
        btnInsertarApoderado.setText("Insertar");
        btnInsertarApoderado.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
        btnInsertarApoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarApoderadoActionPerformed(evt);
            }
        });

        btnEliminarApoderado.setMnemonic('e');
        btnEliminarApoderado.setText("Eliminar");
        btnEliminarApoderado.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
        btnEliminarApoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarApoderadoActionPerformed(evt);
            }
        });

        btnEditarApoderado.setMnemonic('a');
        btnEditarApoderado.setText("Actualizar");
        btnEditarApoderado.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        btnEditarApoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarApoderadoActionPerformed(evt);
            }
        });

        cbBuscarApoderado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apellidos y Nombres", "DNI", "Correo Electronico" }));

        btnBuscarApoderados.setText("Buscar");
        btnBuscarApoderados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarApoderadosActionPerformed(evt);
            }
        });

        btnEliminarApoderado1.setMnemonic('q');
        btnEliminarApoderado1.setText("Asignar Apoderados a los Alumnos");
        btnEliminarApoderado1.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+q]"));
        btnEliminarApoderado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarApoderado1ActionPerformed(evt);
            }
        });

        jButton2.setMnemonic('r');
        jButton2.setText("Reporte");
        jButton2.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+r]"));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addGap(61, 61, 61)
                        .addComponent(cbBuscarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarApoderados, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnInsertarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarApoderado1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelApoderadoLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelApoderadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarApoderado1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
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
        jTablePagos.setName("tablaPagos"); // NOI18N
        jScrollPane3.setViewportView(jTablePagos);

        jButton6.setMnemonic('a');
        jButton6.setText("Actualizar");
        jButton6.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("GESTION PAGOS");

        jButton8.setMnemonic('i');
        jButton8.setText("Insertar");
        jButton8.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
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

        jButton9.setMnemonic('e');
        jButton9.setText("Eliminar");
        jButton9.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
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
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPagosLayout.createSequentialGroup()
                        .addGap(0, 37, Short.MAX_VALUE)
                        .addGroup(jPanelPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelPagosLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(cbPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))
                    .addGroup(jPanelPagosLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
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
                "ID", "Fecha", "Grado", "Nivel", "Turno", "Alumno ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableGestionMatricula.setName("tablaMatricula"); // NOI18N
        jScrollPane8.setViewportView(jTableGestionMatricula);
        if (jTableGestionMatricula.getColumnModel().getColumnCount() > 0) {
            jTableGestionMatricula.getColumnModel().getColumn(0).setResizable(false);
            jTableGestionMatricula.getColumnModel().getColumn(0).setPreferredWidth(45);
            jTableGestionMatricula.getColumnModel().getColumn(1).setResizable(false);
            jTableGestionMatricula.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableGestionMatricula.getColumnModel().getColumn(2).setResizable(false);
            jTableGestionMatricula.getColumnModel().getColumn(2).setPreferredWidth(45);
            jTableGestionMatricula.getColumnModel().getColumn(3).setResizable(false);
            jTableGestionMatricula.getColumnModel().getColumn(3).setPreferredWidth(45);
            jTableGestionMatricula.getColumnModel().getColumn(4).setResizable(false);
            jTableGestionMatricula.getColumnModel().getColumn(4).setPreferredWidth(45);
            jTableGestionMatricula.getColumnModel().getColumn(5).setResizable(false);
            jTableGestionMatricula.getColumnModel().getColumn(5).setPreferredWidth(45);
        }

        jButton28.setMnemonic('a');
        jButton28.setText("Editar");
        jButton28.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GESTION MATRICULA");

        jButton30.setMnemonic('i');
        jButton30.setText("Insertar");
        jButton30.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setMnemonic('e');
        jButton31.setText("Eliminar");
        jButton31.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setMnemonic('r');
        jButton32.setText("Reporte");
        jButton32.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+r]"));
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
                    .addGroup(jPanelMatriculaLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelMatriculaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanelMatriculaLayout.setVerticalGroup(
            jPanelMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatriculaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Matricula", jPanelMatricula);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Curso\n")));
        jPanel2.setPreferredSize(new java.awt.Dimension(412, 600));

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
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableGestionCurso.setCellSelectionEnabled(true);
        jTableGestionCurso.setName("tablaCurso"); // NOI18N
        jScrollPane10.setViewportView(jTableGestionCurso);
        jTableGestionCurso.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTableGestionCurso.getColumnModel().getColumnCount() > 0) {
            jTableGestionCurso.getColumnModel().getColumn(0).setResizable(false);
            jTableGestionCurso.getColumnModel().getColumn(0).setPreferredWidth(45);
            jTableGestionCurso.getColumnModel().getColumn(1).setResizable(false);
            jTableGestionCurso.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableGestionCurso.getColumnModel().getColumn(2).setResizable(false);
            jTableGestionCurso.getColumnModel().getColumn(2).setPreferredWidth(45);
            jTableGestionCurso.getColumnModel().getColumn(3).setResizable(false);
            jTableGestionCurso.getColumnModel().getColumn(3).setPreferredWidth(45);
            jTableGestionCurso.getColumnModel().getColumn(4).setResizable(false);
            jTableGestionCurso.getColumnModel().getColumn(4).setPreferredWidth(45);
        }
        jTableGestionCurso.getAccessibleContext().setAccessibleName("");

        btnInsertarCurso.setMnemonic('i');
        btnInsertarCurso.setText("INSERTAR");
        btnInsertarCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
        btnInsertarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarCursoActionPerformed(evt);
            }
        });

        btnActualizarCurso.setMnemonic('a');
        btnActualizarCurso.setText("ACTUALIZAR");
        btnActualizarCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        btnActualizarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCursoActionPerformed(evt);
            }
        });

        btnEliminarCurso.setMnemonic('e');
        btnEliminarCurso.setText("ELIMINAR");
        btnEliminarCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
        btnEliminarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCursoActionPerformed(evt);
            }
        });

        btnReporteCurso.setMnemonic('r');
        btnReporteCurso.setText("Reporte Curso");
        btnReporteCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+r]"));
        btnReporteCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteCursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnInsertarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReporteCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteCurso))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Area\n"));

        jTableGestionArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE AREA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableGestionArea.setName("tablaArea"); // NOI18N
        jScrollPane9.setViewportView(jTableGestionArea);
        if (jTableGestionArea.getColumnModel().getColumnCount() > 0) {
            jTableGestionArea.getColumnModel().getColumn(0).setResizable(false);
            jTableGestionArea.getColumnModel().getColumn(0).setPreferredWidth(45);
            jTableGestionArea.getColumnModel().getColumn(1).setResizable(false);
            jTableGestionArea.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        btnInsertarArea.setMnemonic('n');
        btnInsertarArea.setText("INSERTAR");
        btnInsertarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarAreaActionPerformed(evt);
            }
        });

        btnActualizarArea.setMnemonic('u');
        btnActualizarArea.setText("ACTUALIZAR");
        btnActualizarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarAreaActionPerformed(evt);
            }
        });

        btnEliminarArea.setMnemonic('d');
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
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnInsertarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarArea))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarArea)
                    .addComponent(btnActualizarArea)
                    .addComponent(btnEliminarArea))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelCursoAreaLayout = new javax.swing.GroupLayout(jPanelCursoArea);
        jPanelCursoArea.setLayout(jPanelCursoAreaLayout);
        jPanelCursoAreaLayout.setHorizontalGroup(
            jPanelCursoAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCursoAreaLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanelCursoAreaLayout.setVerticalGroup(
            jPanelCursoAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCursoAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCursoAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Curso y Area", jPanelCursoArea);

        jtblAsistenciaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombres y Apellidos", "Asistencia", "Tardanza"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(jtblAsistenciaAlumnos);
        if (jtblAsistenciaAlumnos.getColumnModel().getColumnCount() > 0) {
            jtblAsistenciaAlumnos.getColumnModel().getColumn(0).setResizable(false);
            jtblAsistenciaAlumnos.getColumnModel().getColumn(0).setPreferredWidth(90);
            jtblAsistenciaAlumnos.getColumnModel().getColumn(1).setResizable(false);
            jtblAsistenciaAlumnos.getColumnModel().getColumn(1).setPreferredWidth(300);
            jtblAsistenciaAlumnos.getColumnModel().getColumn(2).setResizable(false);
            jtblAsistenciaAlumnos.getColumnModel().getColumn(2).setPreferredWidth(45);
            jtblAsistenciaAlumnos.getColumnModel().getColumn(3).setResizable(false);
            jtblAsistenciaAlumnos.getColumnModel().getColumn(3).setPreferredWidth(45);
        }

        jLabel11.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Registrar Asistencia Alumnos");

        btnMarcarTodo.setMnemonic('a');
        btnMarcarTodo.setText("Marcar todos");
        btnMarcarTodo.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        btnMarcarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarTodoActionPerformed(evt);
            }
        });

        btnRegistrarSalon.setMnemonic('g');
        btnRegistrarSalon.setText("Guardar");
        btnRegistrarSalon.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+g]"));
        btnRegistrarSalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarSalonActionPerformed(evt);
            }
        });

        cbNivelAsis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inicial", "Primaria", "Secundaria" }));
        cbNivelAsis.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbNivelAsisPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbNivelAsis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNivelAsisActionPerformed(evt);
            }
        });

        txtAlumnoAsistencia.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        txtAlumnoAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlumnoAsistenciaActionPerformed(evt);
            }
        });

        jLabel12.setText("Filtrar por Nombres");

        jLabel24.setText("Nivel:");

        jLabel25.setText("Grado:");

        cbGradoAsis.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbGradoAsisPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbGradoAsis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGradoAsisActionPerformed(evt);
            }
        });

        btnMostrarHistorial.setMnemonic('h');
        btnMostrarHistorial.setText("Historial");
        btnMostrarHistorial.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+h]"));
        btnMostrarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarHistorialActionPerformed(evt);
            }
        });

        btnReporteHoy.setMnemonic('r');
        btnReporteHoy.setText("Reporte de Hoy");
        btnReporteHoy.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+r]"));
        btnReporteHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteHoyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtAlumnoAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(cbNivelAsis, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cbGradoAsis, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnReporteHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRegistrarSalon, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(btnMarcarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(btnMostrarHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(37, 37, 37)))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAlumnoAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNivelAsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGradoAsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnMostrarHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReporteHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Asistencias Alumnos", jPanel1);

        jPanel4.setToolTipText("");

        tableAsistenciaDocentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombres y Apellidos", "Hora de ingreso", "Hora de salida"
            }
        ));
        jScrollPane12.setViewportView(tableAsistenciaDocentes);
        if (tableAsistenciaDocentes.getColumnModel().getColumnCount() > 0) {
            tableAsistenciaDocentes.getColumnModel().getColumn(0).setResizable(false);
            tableAsistenciaDocentes.getColumnModel().getColumn(0).setPreferredWidth(90);
            tableAsistenciaDocentes.getColumnModel().getColumn(1).setResizable(false);
            tableAsistenciaDocentes.getColumnModel().getColumn(1).setPreferredWidth(360);
            tableAsistenciaDocentes.getColumnModel().getColumn(2).setResizable(false);
            tableAsistenciaDocentes.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableAsistenciaDocentes.getColumnModel().getColumn(3).setResizable(false);
            tableAsistenciaDocentes.getColumnModel().getColumn(3).setPreferredWidth(120);
        }

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Asistencia Docentes");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matemáticas", "Comunicación", "Ciencia", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Elija el área:");

        btnRegistrarIngreso.setMnemonic('e');
        btnRegistrarIngreso.setText("Registrar entrada");
        btnRegistrarIngreso.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
        btnRegistrarIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarIngresoActionPerformed(evt);
            }
        });

        btnRegistrarSalida.setMnemonic('s');
        btnRegistrarSalida.setText("Registrar salida");
        btnRegistrarSalida.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+s]"));
        btnRegistrarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarSalidaActionPerformed(evt);
            }
        });

        jLabel16.setText("DNI: ");

        txtDNIDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIDocenteActionPerformed(evt);
            }
        });

        jLabel17.setText("Clave: ");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "de A - Z", "de Z - A", "ninguno" }));
        jComboBox3.setPreferredSize(new java.awt.Dimension(100, 45));

        jLabel18.setText("Ordenar por: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(txtDNIDocente, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(btnRegistrarIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrarSalida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(69, 69, 69))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDNIDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnRegistrarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Asistencias Docente", jPanel4);

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

        btnInsertarNota.setMnemonic('i');
        btnInsertarNota.setText("Insertar");
        btnInsertarNota.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
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

        btnEliminarNota.setMnemonic('e');
        btnEliminarNota.setText("Eliminar");
        btnEliminarNota.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
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

        btnEliminarNota1.setMnemonic('a');
        btnEliminarNota1.setText("Editar");
        btnEliminarNota1.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        btnEliminarNota1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNota1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNotasLayout = new javax.swing.GroupLayout(jPanelNotas);
        jPanelNotas.setLayout(jPanelNotasLayout);
        jPanelNotasLayout.setHorizontalGroup(
            jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNotasLayout.createSequentialGroup()
                        .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelNotasLayout.createSequentialGroup()
                                .addComponent(btnInsertarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarNota1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarNota1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInsertarNota)
                        .addComponent(btnEliminarNota)))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Notas", jPanelNotas);

        jTableGestionUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "DNI", "Apellido paterno", "Apellido materno", "Nombre", "Clave", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableGestionUsuarios.setName("tablaUsuarios"); // NOI18N
        jScrollPane4.setViewportView(jTableGestionUsuarios);
        if (jTableGestionUsuarios.getColumnModel().getColumnCount() > 0) {
            jTableGestionUsuarios.getColumnModel().getColumn(0).setResizable(false);
            jTableGestionUsuarios.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableGestionUsuarios.getColumnModel().getColumn(1).setResizable(false);
            jTableGestionUsuarios.getColumnModel().getColumn(1).setPreferredWidth(45);
            jTableGestionUsuarios.getColumnModel().getColumn(2).setResizable(false);
            jTableGestionUsuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableGestionUsuarios.getColumnModel().getColumn(3).setResizable(false);
            jTableGestionUsuarios.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableGestionUsuarios.getColumnModel().getColumn(4).setResizable(false);
            jTableGestionUsuarios.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableGestionUsuarios.getColumnModel().getColumn(5).setResizable(false);
            jTableGestionUsuarios.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTableGestionUsuarios.getColumnModel().getColumn(6).setResizable(false);
            jTableGestionUsuarios.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        btnEditarUsuario.setMnemonic('A');
        btnEditarUsuario.setText("Editar");
        btnEditarUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        btnCambiarClave.setMnemonic('c');
        btnCambiarClave.setText("Cambiar Clave");
        btnCambiarClave.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+c]"));
        btnCambiarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarClaveActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("GESTION USUARIOS");

        btnInsertarUsuario.setMnemonic('i');
        btnInsertarUsuario.setText("Insertar");
        btnInsertarUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
        btnInsertarUsuario.setMaximumSize(new java.awt.Dimension(49, 45));
        btnInsertarUsuario.setMinimumSize(new java.awt.Dimension(49, 45));
        btnInsertarUsuario.setPreferredSize(new java.awt.Dimension(49, 45));
        btnInsertarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarUsuarioActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        FiltroCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apellidos y Nombres", "DNI", "Correo Electronico" }));

        btnReporteUsuarios.setText("Reporte Usuarios");
        btnReporteUsuarios.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+r]"));
        btnReporteUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteUsuariosActionPerformed(evt);
            }
        });

        jButton1.setText("Filtrar");

        btnReporteCurso4.setText("Eliminar");
        btnReporteCurso4.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
        btnReporteCurso4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteCurso4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUsuariosLayout = new javax.swing.GroupLayout(jPanelUsuarios);
        jPanelUsuarios.setLayout(jPanelUsuariosLayout);
        jPanelUsuariosLayout.setHorizontalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(FiltroCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                                .addComponent(btnInsertarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReporteCurso4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCambiarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReporteUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanelUsuariosLayout.setVerticalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FiltroCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteCurso4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jTabbedPane2.addTab("Usuarios", jPanelUsuarios);

        jPanelAlumno.setPreferredSize(new java.awt.Dimension(900, 600));

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
                "#", "ID", "DNI", "Apellido Paterno", "Apellido materno", "Nombres", "Fecha de nacimiento", "Correo electronico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGestionAlumnos.setName("tablaAlumnos"); // NOI18N
        jScrollPane7.setViewportView(jTableGestionAlumnos);
        if (jTableGestionAlumnos.getColumnModel().getColumnCount() > 0) {
            jTableGestionAlumnos.getColumnModel().getColumn(0).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableGestionAlumnos.getColumnModel().getColumn(1).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableGestionAlumnos.getColumnModel().getColumn(2).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTableGestionAlumnos.getColumnModel().getColumn(3).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableGestionAlumnos.getColumnModel().getColumn(4).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableGestionAlumnos.getColumnModel().getColumn(5).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTableGestionAlumnos.getColumnModel().getColumn(6).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTableGestionAlumnos.getColumnModel().getColumn(7).setResizable(false);
            jTableGestionAlumnos.getColumnModel().getColumn(7).setPreferredWidth(120);
        }

        btnInsertarAlumnos.setMnemonic('i');
        btnInsertarAlumnos.setText("Insertar");
        btnInsertarAlumnos.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+i]"));
        btnInsertarAlumnos.setMaximumSize(new java.awt.Dimension(49, 45));
        btnInsertarAlumnos.setMinimumSize(new java.awt.Dimension(49, 45));
        btnInsertarAlumnos.setPreferredSize(new java.awt.Dimension(49, 45));
        btnInsertarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarAlumnosActionPerformed(evt);
            }
        });

        btnEliminarAlumnos.setMnemonic('e');
        btnEliminarAlumnos.setText("Eliminar");
        btnEliminarAlumnos.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+e]"));
        btnEliminarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAlumnosActionPerformed(evt);
            }
        });

        btnEditarAlumnos.setMnemonic('a');
        btnEditarAlumnos.setText("Actualizar");
        btnEditarAlumnos.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+a]"));
        btnEditarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAlumnosActionPerformed(evt);
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

        btnReporteAlumnos.setMnemonic('r');
        btnReporteAlumnos.setText("Reporte Alumnos");
        btnReporteAlumnos.setBorder(javax.swing.BorderFactory.createTitledBorder("[alt+r]"));
        btnReporteAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteAlumnosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlumnoLayout = new javax.swing.GroupLayout(jPanelAlumno);
        jPanelAlumno.setLayout(jPanelAlumnoLayout);
        jPanelAlumnoLayout.setHorizontalGroup(
            jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(cbOpcionesBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                        .addComponent(btnInsertarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReporteAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                        .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 106, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanelAlumnoLayout.setVerticalGroup(
            jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlumnoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbOpcionesBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarAlumnos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanelAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Alumnos", jPanelAlumno);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));

        btnCerrarSesion.setMnemonic('x');
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[alt+x]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        btnCerrarSesion.setMaximumSize(new java.awt.Dimension(80, 45));
        btnCerrarSesion.setMinimumSize(new java.awt.Dimension(80, 45));
        btnCerrarSesion.setPreferredSize(new java.awt.Dimension(80, 45));
        btnCerrarSesion.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        jLabel19.setText("Usuario:");

        jLabel20.setText("Rol: ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("I.E.P La Molina");

        jLabel27.setText("LOGO");

        jLabel28.setText("IMG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtRolUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtRolUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20))))))
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            System.out.println("Eliminando Apoderado con id: " + jTableGestionAlumnos.getValueAt(fila, 1));
            int id = (int) jTableGestionAlumnos.getValueAt(fila, 1);
            System.out.println("ID :" + id);
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
            System.out.println("Eliminando " + tipoPago + " con id: " + jTablePagos.getValueAt(fila, 1));
            int id = (int) jTablePagos.getValueAt(fila, 1);
            System.out.println("ID :" + id);
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
            System.out.println("Eliminando Apoderado con id: " + jTableGestionApoderados.getValueAt(fila, 1));
            int id = (int) jTableGestionApoderados.getValueAt(fila, 1);
            System.out.println("ID :" + id);
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
            System.out.println("Eliminando Apoderado con id: " + jTableGestionDocente.getValueAt(fila, 1));
            int id = (int) jTableGestionDocente.getValueAt(fila, 1);
            System.out.println("ID :" + id);
            docBO.eliminar(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloDocente.removeRow(fila);
    }//GEN-LAST:event_btnEliminarDocenteActionPerformed

    private void btnInsertarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarDocenteActionPerformed
        // TODO add your handling code here:
        VentanaDocente ventanaInsertar = new VentanaDocente();
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
        if (jTableNotas.getSelectedRow() != -1) {
            JOptionPane.showMessageDialog(null, "FUNCIONA");
            try {
                int idNotaEliminar = hnotaslist.get(jTableNotas.getSelectedRow()).getHistorial_id();
                System.out.println(idNotaEliminar + " =======SE ELIMINARA ESTO");
                notasBO.eliminar(idNotaEliminar);
            } catch (Exception ex) {
                System.out.println(ex);
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro a eliminar");
        }
    }//GEN-LAST:event_btnEliminarNotaActionPerformed

    private void btnInsertarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarNotaActionPerformed
        // TODO add your handling code here:
        InsertNotas ventanaInsertar = new InsertNotas();
                // Agregar el WindowListener a la ventana
                ventanaInsertar.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            String name = cursosXGrado.get(0).getNombre();
                            String grado = String.valueOf(cursosXGrado.get(0).getGrado());
                            String nivel = String.valueOf(cbNivelNota.getItemAt(cbNivelNota.getSelectedIndex()).charAt(0));
                            hnotaslist = hnotasBO.buscarPorCurso(name, grado, nivel);
                            mostrarTablaNotas(hnotaslist);
                        } catch (Exception ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
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
            System.out.println("Eliminando Matricula con id: " + jTableGestionMatricula.getValueAt(fila, 1));
            int id = (int) jTableGestionMatricula.getValueAt(fila, 1);
            System.out.println("ID :" + id);
            matBO.eliminarMatricula(id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        modeloMatricula.removeRow(fila);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void btnMarcarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarTodoActionPerformed
        // TODO add your handling code here:
        String gradoAs = String.valueOf(cbGradoAsis.getItemAt(cbGradoAsis.getSelectedIndex()).charAt(0));
        String nivelAs = String.valueOf(cbNivelAsis.getItemAt(cbNivelAsis.getSelectedIndex()).charAt(0));
        actualizarTablaAsistenciaAlumnos(nivelAs, gradoAs);
    }//GEN-LAST:event_btnMarcarTodoActionPerformed

    private void btnRegistrarSalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSalonActionPerformed
        //needs improve it with an exhaustive filter
        //also need ad more reports
        for (int i = 0; i < asalonasistencia.size(); i++) {
            asisalumno = new AsistenciaAlumno();
            Boolean asistenciadia = (Boolean) (jtblAsistenciaAlumnos.getValueAt(i, 2));
            System.out.println("Asistio " + asistenciadia);
            if (asistenciadia) {
                asisalumno.setAlumno_id(asalonasistencia.get(i).getAlumno_id());
                Boolean observacion = (Boolean) (jtblAsistenciaAlumnos.getValueAt(i, 3));
                System.out.println("OBSERvAcion " + observacion);

                if (observacion) {
                    asisalumno.setObservacion("Tardanza");
                } else {
                    asisalumno.setObservacion("");
                }

                
            }else{
                asisalumno.setAlumno_id(asalonasistencia.get(i).getAlumno_id());
                asisalumno.setObservacion("Falto");
            }
            
            aalumnos.add(asisalumno);

        }
        if (!aalumnos.isEmpty()) {
            try {
                System.out.println(aalumnos.size());
                asisalumBO.insertarAsistenciaGrupo(aalumnos);
                JOptionPane.showMessageDialog(null, "Se registro la asistencia Correctamente");
                aalumnos.clear();
            } catch (Exception ex) {
                System.out.println(ex);
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tal parece que no asistio ningun alumno");
        }
    }//GEN-LAST:event_btnRegistrarSalonActionPerformed

    private void cbNivelAsisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNivelAsisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNivelAsisActionPerformed

    private void txtAlumnoAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlumnoAsistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlumnoAsistenciaActionPerformed

    private void cbOpcionesBuscarAlumnosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbOpcionesBuscarAlumnosMouseExited
        // TODO add your handling code here:
        System.out.println(cbOpcionesBuscarAlumnos.getSelectedItem() + "  saliendo");
        //jComboBox4.s
    }//GEN-LAST:event_cbOpcionesBuscarAlumnosMouseExited

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
        if (!(usuarios.isEmpty())) {
            if (fila >= 0) {
                ventanaUsuarioCambiarClave.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario para editar");
            }
        } else {
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
        if (fila != -1) {
            usuarioL = usuarios.get(fila);
            for (Usuario usdni : usuarios) {
                if (!(usdni.equals(usuarios.get(fila)))) {
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
        if (!(usuarios.isEmpty())) {
            if (fila >= 0) {
                ventanaActualizar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario para editar");
            }
        } else {
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
            System.out.println("Eliminando Apoderado con id: " + jTableGestionArea.getValueAt(fila, 1));
            int id = (int) jTableGestionArea.getValueAt(fila, 0);
            System.out.println("ID :" + id);
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
            System.out.println("Eliminando Apoderado con id: " + jTableGestionCurso.getValueAt(fila, 1));
            int id = (int) jTableGestionCurso.getValueAt(fila, 0);
            System.out.println("ID :" + id);
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
            case 0: {
                alumnos.clear();
                try {
                    alumnos.add(alumBO.buscarPorDNI(buscar));
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 1: {
                try {
                    alumnos = alumBO.buscarPorAlumno(buscar);

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
            case 0: {
                docentes.clear();
                try {
                    docentes.add(docenteBO.buscarPorDNI(buscar));
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 1: {
                try {
                    docentes = docenteBO.buscarPorDocente(buscar);

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
            case 0: {
                apoderados.clear();
                try {
                    apoderados = apoBO.buscarApoderadoPorNombre(buscar);

                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 1: {
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

            default ->
                throw new AssertionError();
        }
        ArrayList<Character> gr = new ArrayList<>();
        for (Curso c : cursosXNivel) {
            gr.add(c.getGrado());
        }
        HashSet<Character> hashset = new HashSet<>();
        hashset.addAll(gr);
        gr = new ArrayList<>(hashset);
        Collections.sort(gr);
        cbGradoNota.removeAllItems();
        for (Character ch : gr) {
            cbGradoNota.addItem(String.valueOf(ch) + "' grado");
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
        for (Curso cg : cursosXGrado) {
            cbCursoNota.addItem(cg.getNombre());
        }
        cbCursoNota.setEnabled(true);
    }//GEN-LAST:event_cbGradoNotaPopupMenuWillBecomeInvisible

    private void cbCursoNotaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCursoNotaPopupMenuWillBecomeInvisible
        try {
            // TODO add your handling code here:
            String name = cursosXGrado.get(0).getNombre();
            String grado = String.valueOf(cursosXGrado.get(0).getGrado());
            String nivel = String.valueOf(cbNivelNota.getItemAt(cbNivelNota.getSelectedIndex()).charAt(0));
            hnotaslist = hnotasBO.buscarPorCurso(name, grado, nivel);
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

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void cbGradoAsisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGradoAsisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGradoAsisActionPerformed

    private void btnMostrarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarHistorialActionPerformed
        // TODO add your handling code here:
        VentanaHistorialAsistenciaAlumnos vhaa = new VentanaHistorialAsistenciaAlumnos();
        vhaa.setVisible(true);
    }//GEN-LAST:event_btnMostrarHistorialActionPerformed

    private void cbNivelAsisPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbNivelAsisPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        int op = cbNivelAsis.getSelectedIndex();
        if (op != -1) {
            cbGradoAsis.setEnabled(true);
            switch (op) {
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

                default ->
                    throw new AssertionError();
            }
            ArrayList<Character> gr = new ArrayList<>();
            for (Curso c : cursosXNivel) {
                gr.add(c.getGrado());
            }
            HashSet<Character> hashset = new HashSet<>();
            hashset.addAll(gr);
            gr = new ArrayList<>(hashset);
            Collections.sort(gr);
            cbGradoAsis.removeAllItems();
            for (Character ch : gr) {
                cbGradoAsis.addItem(String.valueOf(ch) + "' grado");
            }
        }
    }//GEN-LAST:event_cbNivelAsisPopupMenuWillBecomeInvisible

    private void cbGradoAsisPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbGradoAsisPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String gradoAs = String.valueOf(cbGradoAsis.getItemAt(cbGradoAsis.getSelectedIndex()).charAt(0));
        String nivelAs = String.valueOf(cbNivelAsis.getItemAt(cbNivelAsis.getSelectedIndex()).charAt(0));
        actualizarTablaAsistenciaAlumnos(nivelAs, gradoAs);
    }//GEN-LAST:event_cbGradoAsisPopupMenuWillBecomeInvisible

    private void btnReporteCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteCursoActionPerformed
        // TODO add your handling code here:
        try {
            
            
//            Map<String, Object> params = new HashMap<String, Object>();

//            params.put("cursoId", 1);
//            params.put("turno","m");
//            params.put("bimestre", 1);

            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ReporteCurso.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
//            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,null,con);
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
    }//GEN-LAST:event_btnReporteCursoActionPerformed

    private void btnReporteAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteAlumnosActionPerformed
        // TODO add your handling code here:
        try {
            
            
//            Map<String, Object> params = new HashMap<String, Object>();

//            params.put("cursoId", 1);
//            params.put("turno","m");
//            params.put("bimestre", 1);

            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ReportesAlumno.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
//            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,null,con);
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
    }//GEN-LAST:event_btnReporteAlumnosActionPerformed

    private void btnReporteDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteDocenteActionPerformed
        // TODO add your handling code here:
        try {
            
            
            Map<String, Object> params = new HashMap<String, Object>();
            String rutaimg = System.getProperty("user.dir")+"/src/main/java/Interfaz/logoHD.jpg";
            params.put("url", rutaimg);
//            params.put("AnioMatricula", "2023");
//            params.put("turno","m");
//            params.put("bimestre", 1);

            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ReportesAlumno.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
//            JasperPrint print= JasperFillManager.fillReport(jasperReport,null,con);
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
    }//GEN-LAST:event_btnReporteDocenteActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void btnReporteUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteUsuariosActionPerformed
        // TODO add your handling code here:
        try {
            
            
            Map<String, Object> params = new HashMap<String, Object>();
            String rutaimg = System.getProperty("user.dir")+"/src/main/java/Interfaz/logoHD.jpg";
            params.put("url", rutaimg);
//            params.put("AnioMatricula", "2023");
//            params.put("turno","m");
//            params.put("bimestre", 1);

            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ReporteUsuarios.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
//            JasperPrint print= JasperFillManager.fillReport(jasperReport,null,con);
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
    }//GEN-LAST:event_btnReporteUsuariosActionPerformed

    private void btnReporteCurso4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteCurso4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteCurso4ActionPerformed

    private void txtDNIDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIDocenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIDocenteActionPerformed

    private void btnRegistrarIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarIngresoActionPerformed
        // TODO add your handling code here:

        Usuario userDigitado = new Usuario();
        Usuario userBase = new Usuario();
        if (txtDNIDocente.getText().isBlank() || txtContrasena.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        } else {
            //usuario = 70705050, contrasena=12345678
            if (Validator.isDNI(txtDNIDocente.getText())) { //FALTA QUE VERIFIQUE QUE NO ESTE YA REGISTRADO
                try {
                    System.out.println(txtContrasena.getText());

                    System.out.println("dni doc== " + txtDNIDocente.getText());

                    docente = docenteBO.buscarPorDNI(txtDNIDocente.getText());
                    System.out.println("aqui doc _ " + docente.getDni());
                    //                  docente.setApellidosNombres2();  
                    if (docente != (null)) {
                        
                        if(asisdocBO.buscarPorFecha(docente.getDocente_id()).getDocente_id() == docente.getDocente_id()){
//                        if(buscar(docente.getDocente_id(),adocentes,0)){// AQUI SE VERIFICA SI YA FUE REGISTRADO EL INGRESO
                            JOptionPane.showMessageDialog(null, "El docente ya registro su hora de ingreso");
                        }else{
                            userDigitado.setDni(txtDNIDocente.getText());
                            userDigitado.setClave(txtContrasena.getText());
                            userBase = ubo.buscarPorDNI(txtDNIDocente.getText());

                            try {
                                Encriptador encry = new Encriptador();
                                System.out.println("digitado " + userDigitado.getClave() + "encriptado===" + encry.Encriptar(userDigitado.getClave()));
                                System.out.println("Clave bd +++ " + userBase.getClave());
                                if (encry.VerificarCredenciales(userDigitado.getClave(), userBase.getClave())) {
                                    System.out.println("ingreso credenciales bien");
                                    //                                actualizarTablaAsistenciaDocentes();
                                    LocalDate fechita = LocalDate.now();
                                    Time timecito = Time.valueOf(LocalTime.now());
                                    
                                    System.out.println("esto se ingresa: " + String.valueOf(timecito));
                                    System.out.println("esto se ingresa2: " + String.valueOf(fechita));
                                    asistenciaDocente = new AsistenciaDocente(docente.getDocente_id(), timecito, fechita);
                                    //                                asistenciaDocente = new AsistenciaDocente();
                                    asistenciaDocente.setDocente_id(docente.getDocente_id());
                                    System.out.println("ESTE DOCENTE ENTRA:::: " + docente.getDocente_id());
                                    try {
                                        asisdocBO.insertarIngreso(asistenciaDocente);
                                        actualizarTablaAsistenciaDocentes();
                                    } catch (Exception e) {
                                        System.out.println("problema aqui F");
                                        System.out.println(e);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pudo registrar el ingreso", "Credenciales incorrectas", 1);
                                }

                            } catch (Exception ex) {
                                System.out.println("ERROR EN REGISTRAR");
                                System.out.println(ex);
                                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(null, "No se pudo registrar el ingreso", "Credenciales incorrectas", 1);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No existen las credenciales ingresadas, intente nuevamente");
                    }
                } catch (Exception ex) {
                    System.out.println("ERROR DESPUES INTENTANDO VALIDATOR");
                    System.out.println(ex);
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un dni correcto");
            }
        }
    }//GEN-LAST:event_btnRegistrarIngresoActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void btnRegistrarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSalidaActionPerformed
        // TODO add your handling code here:
        
        Usuario userDigitado = new Usuario();
        Usuario userBase = new Usuario();
        if (txtDNIDocente.getText().isBlank() || txtContrasena.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        } else {
            if (Validator.isDNI(txtDNIDocente.getText())) { //FALTA QUE VERIFIQUE QUE NO ESTE YA REGISTRADO
                try {
                    System.out.println(txtContrasena.getText());

                    System.out.println("dni doc== " + txtDNIDocente.getText());

                    docente = docenteBO.buscarPorDNI(txtDNIDocente.getText());
                    System.out.println("aqui doc _ " + docente.getDni());
                    //                  docente.setApellidosNombres2();  
                    if (docente != (null)) {
                        System.out.println(asisdocBO.buscarPorFecha(docente.getDocente_id()).getDocente_id());
                        if(asisdocBO.buscarPorFecha(docente.getDocente_id()).getDocente_id() == docente.getDocente_id()){
//                            if(String.valueOf(asisdocBO.buscarPorFecha(docente.getDocente_id()).getHora_salida()).isBlank()){
                            if(asisdocBO.buscarPorFecha(docente.getDocente_id()).getHora_salida()==null){
                                userDigitado.setDni(txtDNIDocente.getText());
                                userDigitado.setClave(txtContrasena.getText());
                                userBase = ubo.buscarPorDNI(txtDNIDocente.getText());
                                try {
                                    Encriptador encry = new Encriptador();
                                    System.out.println("digitado " + userDigitado.getClave() + "encriptado===" + encry.Encriptar(userDigitado.getClave()));
                                    System.out.println("Clave bd +++ " + userBase.getClave());
                                    if (encry.VerificarCredenciales(userDigitado.getClave(), userBase.getClave())) {
                                        System.out.println("ingreso credenciales bien");
                                        //                                actualizarTablaAsistenciaDocentes();
                                        Time timecito = Time.valueOf(LocalTime.now());
                                        LocalDate fechita = LocalDate.now();
                                        System.out.println("esto se ingresa: " + String.valueOf(timecito));
                                        System.out.println("esto se ingresa2: " + String.valueOf(fechita));
                                        System.out.println("ESTE DOCENTE ENTRA:::: " + docente.getDocente_id());
                                        try {
                                            asisdocBO.insertarSalida(docente.getDocente_id(), fechita, timecito);
                                            actualizarTablaAsistenciaDocentes();
                                        } catch (Exception e) {
                                            System.out.println("problema aqui F");
                                            System.out.println(e);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo registrar el ingreso", "Credenciales incorrectas", 1);
                                    }

                                } catch (Exception ex) {
                                    System.out.println("ERROR EN REGISTRAR");
                                    System.out.println(ex);
                                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(null, "No se pudo registrar el ingreso", "Credenciales incorrectas", 1);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "El docente ya registro la hora de su salida");
                            }
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "El docente no registro su hora de ingreso, por tanto, no puede registrar su hora de salida");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No existen las credenciales ingresadas, intente nuevamente");
                    }
                } catch (Exception ex) {
                    System.out.println("ERROR DESPUES INTENTANDO VALIDATOR");
                    System.out.println(ex);
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un dni correcto");
            }
        }
    }//GEN-LAST:event_btnRegistrarSalidaActionPerformed

    private void btnEliminarApoderado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarApoderado1ActionPerformed
        // TODO add your handling code here:
        VentanaAlumnoApoderado ventanaInsertar = new VentanaAlumnoApoderado();
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
    }//GEN-LAST:event_btnEliminarApoderado1ActionPerformed

    private void btnEliminarApoderado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarApoderado2ActionPerformed
        // TODO add your handling code here:
        VentanaDocenteCurso ventanaInsertar = new VentanaDocenteCurso();
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
    }//GEN-LAST:event_btnEliminarApoderado2ActionPerformed

    private void btnEliminarNota1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNota1ActionPerformed
        // TODO add your handling code here:
        if (jTableNotas.getSelectedRow() != -1) {
            try {
                int fila_seleccionada = jTableNotas.getSelectedRow();
                
                int idHNotaEditar = hnotaslist.get(fila_seleccionada).getHistorial_id();
                int idAlumnoEditar = hnotaslist.get(fila_seleccionada).getAlumno_id();
                int idCursoEditar = hnotaslist.get(fila_seleccionada).getCurso_id();
                
                System.out.println(idHNotaEditar + " =======SE EDITARA ESTO");
                
                String nombre_alumno = jTableNotas.getValueAt(fila_seleccionada, 0).toString();
                String nombre_curso = cbCursoNota.getSelectedItem().toString();
                String nivel_grado = cbNivelNota.getSelectedItem().toString()+"/ "+ cbGradoNota.getSelectedItem().toString();
                
                String nota1 = "", nota2 = "", nota3 = "", nota4 = "", nota5 = "", promedio = "";
                
                if(!(jTableNotas.getValueAt(fila_seleccionada, 1) == null))
                    nota1 = jTableNotas.getValueAt(fila_seleccionada, 1).toString();
                    
                if(!(jTableNotas.getValueAt(fila_seleccionada, 2) == null))
                    nota2 = jTableNotas.getValueAt(fila_seleccionada, 2).toString();
                
                if(!(jTableNotas.getValueAt(fila_seleccionada, 3) == null))
                    nota3 = jTableNotas.getValueAt(fila_seleccionada, 3).toString();
                
                if(!(jTableNotas.getValueAt(fila_seleccionada, 4) == null))
                    nota4 = jTableNotas.getValueAt(fila_seleccionada, 4).toString();
                
                if(!(jTableNotas.getValueAt(fila_seleccionada, 5) == null))
                    nota5 = jTableNotas.getValueAt(fila_seleccionada, 5).toString();
                
                if(!(jTableNotas.getValueAt(fila_seleccionada, 6) == null))
                    promedio = jTableNotas.getValueAt(fila_seleccionada, 6).toString();
                
                
                VentanaEditarNotas ventanaInsertar = new VentanaEditarNotas(idHNotaEditar, idAlumnoEditar, idCursoEditar, nombre_alumno, nombre_curso, nivel_grado, nota1, nota2, nota3, nota4, nota5, promedio);
                // Agregar el WindowListener a la ventana
                ventanaInsertar.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            String name = cursosXGrado.get(0).getNombre();
                            String grado = String.valueOf(cursosXGrado.get(0).getGrado());
                            String nivel = String.valueOf(cbNivelNota.getItemAt(cbNivelNota.getSelectedIndex()).charAt(0));
                            hnotaslist = hnotasBO.buscarPorCurso(name, grado, nivel);
                            mostrarTablaNotas(hnotaslist);
                        } catch (Exception ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                // Mostrar la ventana
                ventanaInsertar.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro a editar");
        }
    }//GEN-LAST:event_btnEliminarNota1ActionPerformed

    private void btnReporteHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteHoyActionPerformed
        // TODO add your handling code here:
        try {
            
            
            Map<String, Object> params = new HashMap<String, Object>();
            String rutaimg = System.getProperty("user.dir")+"/src/main/java/Interfaz/logoHD.jpg";
            params.put("url", rutaimg);
//            params.put("AnioMatricula", "2023");
//            params.put("turno","m");
//            params.put("bimestre", 1);

            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ReporteAsistenciaAlumnosHoy.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
//            JasperPrint print= JasperFillManager.fillReport(jasperReport,null,con);
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
    }//GEN-LAST:event_btnReporteHoyActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            
            
            Map<String, Object> params = new HashMap<String, Object>();
            String rutaimg = System.getProperty("user.dir")+"/src/main/java/Interfaz/logoHD.jpg";
            params.put("url", rutaimg);
//            params.put("AnioMatricula", "2023");
//            params.put("turno","m");
//            params.put("bimestre", 1);

            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ReporteApoderado.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
//            JasperPrint print= JasperFillManager.fillReport(jasperReport,null,con);
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        try {
            
            
            Map<String, Object> params = new HashMap<String, Object>();
            String rutaimg = System.getProperty("user.dir")+"/src/main/java/Interfaz/logoHD.jpg";
            params.put("url", rutaimg);
//            params.put("AnioMatricula", "2023");
//            params.put("turno","m");
//            params.put("bimestre", 1);

            Connection con=UConnection.getConnection();
            
            String rutaReporte=System.getProperty("user.dir")+"/src/main/java/Reportes/ReporteMatricula.jasper";
            
            JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint print= JasperFillManager.fillReport(jasperReport,params,con);
//            JasperPrint print= JasperFillManager.fillReport(jasperReport,null,con);
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
    }//GEN-LAST:event_jButton32ActionPerformed

    private DefaultTableModel modeloAlumnos;
    private AlumnoBO alumBO = new AlumnoBO();
    private ArrayList<Alumno> alumnos;

    private void actualizarTablaAlumno() {
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
                Object[] fila = new Object[]{(i + 1), alumno.getAlumno_id(), alumno.getDni(),
                    alumno.getApellido_paterno(), alumno.getApellido_materno(),
                    alumno.getNombres(), alumno.getFecha_nacimiento(), alumno.getCorreo_electrico()};
                modeloAlumnos.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DefaultTableModel modeloDocente;
    private DocenteBO docBO = new DocenteBO();
    private ArrayList<Docente> docentes;

    private void actualizarTablaDocente() {
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

                Object[] fila = new Object[]{(i + 1), docente.getDocente_id(), docente.getDni(),
                    docente.getApellido_paterno(), docente.getApellido_materno(),
                    docente.getNombres(), docente.getContacto()};
                modeloDocente.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DefaultTableModel modeloApoderado;
    private ArrayList<Apoderado> apoderados;
    private ApoderadoBO apoBO = new ApoderadoBO();

    private void actualizarTablaApoderado() {
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

                Object[] fila = new Object[]{(i + 1), apoderado.getApoderado_id(), apoderado.getDni(),
                    apoderado.getApellido_paterno(), apoderado.getApellido_materno(),
                    apoderado.getNombres(), apoderado.getContacto()};
                modeloApoderado.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DefaultTableModel modeloMatricula;
    private MatriculaBO matBO = new MatriculaBO();
    private ArrayList<Matricula> matriculas;

    private void actualizarTablaMatricula() {
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

                Object[] fila = new Object[]{(i + 1), matricula.getMatricula_id(), matricula.getFecha(),
                    matricula.getGrado(), matricula.getNivel(),
                    matricula.getTurno(), matricula.getAlumno_id()};
                modeloMatricula.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DefaultTableModel modeloArea;
    private AreaBO areaBO = new AreaBO();
    private ArrayList<Area> areas;

    private void actualizarTablaArea() {
        modeloArea = new DefaultTableModel();

        jTableGestionArea.setModel(modeloArea);

        modeloArea.addColumn("Area_id");
        modeloArea.addColumn("Nombre");

        try {
            System.out.println(areas.size());

            for (int i = 0; i < areas.size(); i++) {
                Area area = areas.get(i);

                Object[] fila = new Object[]{area.getArea_id(), area.getArea_nombre()};
                modeloArea.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DefaultTableModel modeloCurso;
    private ArrayList<Curso> cursos;
    //private CursoBO cursoBO = new CursoBO();

    private void actualizarTablaCurso() {
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

                Object[] fila = new Object[]{curso.getCurso_id(), curso.getNombre(),
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
    private ArrayList<pagoMatricula> pagosMatricula;

    private void actualizarTablaPagoMatricula() {
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

                Object[] fila = new Object[]{(i + 1), pagoMat.getPago_matricula_id(), pagoMat.getFecha(),
                    pagoMat.getMonto(), pagoMat.getObservacion(), pagoMat.getAlumno_id()};
                modeloPagoMatricula.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DefaultTableModel modeloPagoPension;
    private PagoPensionBO pagoPenBO = new PagoPensionBO();
    private ArrayList<pagoPensiones> pagosPensiones;

    private void actualizarTablaPagoPensiones() {
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

                Object[] fila = new Object[]{(i + 1), pagoPen.getPago_pensiones_id(), pagoPen.getFecha(),
                    pagoPen.getMonto(), pagoPen.getObservacion(), pagoPen.getAlumno_id()};
                modeloPagoPension.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DefaultTableModel modeloPagoVarios;
    private PagoVariosBO pagoVarBO = new PagoVariosBO();
    private ArrayList<pagoVarios> pagosVarios;

    private void actualizarTablaPagoVarios() {
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

                Object[] fila = new Object[]{(i + 1), pagoVar.getPago_varios_id(), pagoVar.getFecha(),
                    pagoVar.getMonto(), pagoVar.getObservacion(), pagoVar.getAlumno_id()};
                modeloPagoVarios.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * ***
     *
     * FUNCIONES
     *
     ****
     */
    //Actualizar Tabla por Tipo de Pago
    public void ActualizarTablaXTipoPago(String tipo) {
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
    
    //funcion que ordena un Jtable de forma ascendente o descente, ordena según el número de columna seleccionado
    private static void ordenarTabla(JTable table, int columna, boolean ascendente, ArrayList lista_1) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        
        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < rowCount - i - 1; j++) {
                Object value1 = model.getValueAt(j, columna);
                Object value2 = model.getValueAt(j + 1, columna);
                
                boolean swap = false;
                
                if (ascendente) {
                    if (((Comparable) value1).compareTo(value2) > 0) {
                        swap = true;
                    }
                } else {
                    if (((Comparable) value1).compareTo(value2) < 0) {
                        swap = true;
                    }
                }
                
                if (swap) {
                    swapRows(model, j, j + 1, lista_1);
                }
            }
        }
    }
    
    private static void swapRows(DefaultTableModel model, int row1, int row2, ArrayList lista_1) {
        Object[] rowData1 = new Object[model.getColumnCount()];
        Object[] rowData2 = new Object[model.getColumnCount()];
        
        for (int i = 0; i < model.getColumnCount(); i++) {
            rowData1[i] = model.getValueAt(row1, i);
            rowData2[i] = model.getValueAt(row2, i);
        }
        
        for (int i = 0; i < model.getColumnCount(); i++) {
            model.setValueAt(rowData2[i], row1, i);
            model.setValueAt(rowData1[i], row2, i);
        }
        
        Object temp = lista_1.get(row1);
        lista_1.set(row1, lista_1.get(row2));
        lista_1.set(row2, temp);
    }
    
    //Funcion para actualizar la tabla Asistencia Docente
    private void actualizarTablaAsistenciaDocentes() {
        try {
            modeloAdocentes = (DefaultTableModel) tableAsistenciaDocentes.getModel();
            modeloAdocentes.setRowCount(0);
            adocentes = asisdocBO.asistenciaDocenteListarPorHoy();
            System.out.println("tamndangao" + adocentes.size());
            try {
                for (int i = 0; i < adocentes.size(); i++) {
                    adocente = adocentes.get(i);
                    docente = docBO.buscarPorId(adocente.getDocente_id());
                    Object[] fila = new Object[]{docente.getDni() ,docente.getApellidosNombres(), adocente.getHora_ingreso(), adocente.getHora_salida()};
                    modeloAdocentes.addRow(fila);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            tableAsistenciaDocentes.setModel(modeloAdocentes);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarTablaUsuarios() {
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
                Object[] fila = new Object[]{usuario.getUsuario_id(), usuario.getDni(), usuario.getApellido_paterno(), usuario.getApellido_materno(), usuario.getNombres(), usuario.getClave(), usuario.getRol()};
                modeloUsuarios.addRow(fila);
            }
            jTableGestionUsuarios.setModel(modeloUsuarios);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarTablaNotas(ArrayList<HistorialNotas> hnl) {
        modeloNotas = (DefaultTableModel) jTableNotas.getModel();
        modeloNotas.setRowCount(0);

        System.out.println(hnl.size());
        try {
            for (HistorialNotas hhh : hnl) {
                Notas n = hhh.getNota();
                Object[] fila = new Object[]{hhh.getAlumno().getApellidosNombres(),
                    n.getNota1(),
                    n.getNota2(),
                    n.getNota3(),
                    n.getNota4(),
                    n.getNota5(),
                    hhh.getPromedio()};
                modeloNotas.addRow(fila);
            }
            jTableNotas.setModel(modeloNotas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarTablaAsistenciaAlumnos(String nivel, String grado) {
        modeloAsistenciaAlumnos = (DefaultTableModel) jtblAsistenciaAlumnos.getModel();
        modeloAsistenciaAlumnos.setRowCount(0);
        try {
            asalonasistencia = alumBO.buscarPorAlumnoxSalon(grado, nivel);
        } catch (Exception ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int cont = 0;
            for (Alumno aaa : asalonasistencia) {

                Object[] fila = new Object[]{aaa.getDni(), aaa.getApellidosNombres(), true, false};
                modeloAsistenciaAlumnos.addRow(fila);
                jtblAsistenciaAlumnos.setValueAt(true, cont, 2);
                jtblAsistenciaAlumnos.setValueAt(false, cont, 3);
                cont++;
            }
            jtblAsistenciaAlumnos.setModel(modeloAsistenciaAlumnos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * ***
     *
     * FIN FUNCIONES
     *
     ****
     */
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
    private javax.swing.JButton btnEliminarApoderado1;
    private javax.swing.JButton btnEliminarApoderado2;
    private javax.swing.JButton btnEliminarArea;
    private javax.swing.JButton btnEliminarCurso;
    private javax.swing.JButton btnEliminarDocente;
    private javax.swing.JButton btnEliminarNota;
    private javax.swing.JButton btnEliminarNota1;
    private javax.swing.JButton btnInsertarAlumnos;
    private javax.swing.JButton btnInsertarApoderado;
    private javax.swing.JButton btnInsertarArea;
    private javax.swing.JButton btnInsertarCurso;
    private javax.swing.JButton btnInsertarDocente;
    private javax.swing.JButton btnInsertarNota;
    private javax.swing.JButton btnInsertarUsuario;
    private javax.swing.JButton btnMarcarTodo;
    private javax.swing.JButton btnMostrarHistorial;
    private javax.swing.JButton btnRegistrarIngreso;
    private javax.swing.JButton btnRegistrarSalida;
    private javax.swing.JButton btnRegistrarSalon;
    private javax.swing.JButton btnReporteAlumnos;
    private javax.swing.JButton btnReporteCurso;
    private javax.swing.JButton btnReporteCurso4;
    private javax.swing.JButton btnReporteDocente;
    private javax.swing.JButton btnReporteHoy;
    private javax.swing.JButton btnReporteUsuarios;
    private javax.swing.JComboBox<String> cbBuscarApoderado;
    private javax.swing.JComboBox<String> cbBuscarDocente;
    private javax.swing.JComboBox<String> cbCursoNota;
    private javax.swing.JComboBox<String> cbGradoAsis;
    private javax.swing.JComboBox<String> cbGradoNota;
    private javax.swing.JComboBox<String> cbNivelAsis;
    private javax.swing.JComboBox<String> cbNivelNota;
    private javax.swing.JComboBox<String> cbOpcionesBuscarAlumnos;
    private javax.swing.JComboBox<String> cbPagos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JTable jTableGestionAlumnos;
    private javax.swing.JTable jTableGestionApoderados;
    private javax.swing.JTable jTableGestionArea;
    private javax.swing.JTable jTableGestionCurso;
    private javax.swing.JTable jTableGestionDocente;
    private javax.swing.JTable jTableGestionMatricula;
    private javax.swing.JTable jTableGestionUsuarios;
    private javax.swing.JTable jTableNotas;
    private javax.swing.JTable jTablePagos;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTable jtblAsistenciaAlumnos;
    private javax.swing.JTable tableAsistenciaDocentes;
    private javax.swing.JTextField txtAlumnoAsistencia;
    private javax.swing.JTextField txtBuscarAlumnos;
    private javax.swing.JTextField txtBuscarApoderado;
    private javax.swing.JTextField txtBuscarDocente;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtDNIDocente;
    private javax.swing.JLabel txtRolUser;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer;

import DataAccessLayer.AsistenciaAlumnoDAO;
import JavaBean.AsistenciaAlumno;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class AsistenciaAlumnoBO {
    public void insertar(AsistenciaAlumno asistenciaalumno) throws Exception{
        try {
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            asistenciaalumnoDAO.insertarAsistenciaAlumno(asistenciaalumno);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void insertarAsistenciaGrupo(ArrayList<AsistenciaAlumno> asistenciaalumnos) throws Exception{
        try {
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            asistenciaalumnoDAO.insertarAsistenciaGrupo(asistenciaalumnos);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar(AsistenciaAlumno asistenciaalumno) throws Exception{
        try{
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            asistenciaalumnoDAO.actualizarAsistenciaAlumno(asistenciaalumno);
        }catch (Exception e) {
            throw e;
        }
        
    }
    
    public void eliminar(int i) throws Exception{
        try {
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            asistenciaalumnoDAO.eliminarAsistenciaAlumno(i);
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public ArrayList<AsistenciaAlumno> buscarAsistenciaAlumnoPorAlumnoId(int id) throws Exception{
        try {
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            return asistenciaalumnoDAO.buscarAsistenciaAlumnoPorAlumnoId(id);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public ArrayList<AsistenciaAlumno> listarAsistenciaAlumno() throws Exception{
        try {
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            return asistenciaalumnoDAO.listarAsistenciaAlumno();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public ArrayList<AsistenciaAlumno> listarAsistenciaGrupo(String nivel, String grado) throws Exception{
        try {
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            return asistenciaalumnoDAO.listarAsistenciaGrupo(nivel, grado);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public ArrayList<AsistenciaAlumno> listarAsistenciaGrupoFecha(Date fecha, String nivel, String grado) throws Exception{
        try {
            AsistenciaAlumnoDAO asistenciaalumnoDAO = new AsistenciaAlumnoDAO();
            return asistenciaalumnoDAO.listarAsistenciaGrupoFecha(fecha, nivel, grado);
        } catch (Exception e) {
            throw e;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.AsistenciaAlumno;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author black
 */
public class AsistenciaAlumnoDAO {
    private Connection con;
    CallableStatement cstm = null; 
    
    public void insertarAsistenciaAlumno(AsistenciaAlumno asistencia) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_asistenciaAlumno_insertar (?,?,?)";
            cstm = con.prepareCall(sql);
            cstm.setDate(1, Date.valueOf(asistencia.getFecha()));
            if( asistencia.getObservacion().isEmpty() ) cstm.setString(2, asistencia.getObservacion());
            else cstm.setString(2, asistencia.getObservacion());
            
            cstm.setInt(3, asistencia.getAlumno_id());
            
            cstm.execute();
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
               if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }       
        }
    }
    
    public void insertarAsistenciaGrupo(ArrayList<AsistenciaAlumno> asistencia) throws Exception{
        LocalDate fecha = LocalDate.now();
        try {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            
            String sql = "call sp_asistenciaAlumno_insertar (?,?,?)";
            cstm = con.prepareCall(sql);
            
            for (AsistenciaAlumno aa: asistencia){
                cstm.setDate(1, Date.valueOf(fecha));
                if( aa.getObservacion().isEmpty() ) cstm.setString(2, "");
                else cstm.setString(2, aa.getObservacion());
            
                cstm.setInt(3, aa.getAlumno_id());
                cstm.addBatch();
            }
            
            cstm.executeBatch();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
               if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }       
        }
    }
    
    public void actualizarAsistenciaAlumno(AsistenciaAlumno asistencia) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_asistenciaAlumno_actualizar (?,?,?,?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, asistencia.getAsistencia_alumno_id());
            cstm.setDate(2, Date.valueOf(asistencia.getFecha()));
            if( asistencia.getObservacion().isEmpty() ) 
                cstm.setNull(3, java.sql.Types.VARCHAR);
            else 
                cstm.setString(3, asistencia.getObservacion());
            
            cstm.setInt(4, asistencia.getAlumno_id());
            
            cstm.executeUpdate();
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
               if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }       
        }
    }
    
    public void eliminarAsistenciaAlumno(int id) throws Exception{     
        try {
            con=UConnection.getConnection();
            String sql = "call sp_asistenciaAlumno_eliminar (?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, id);
         
            cstm.executeUpdate();
        }catch (Exception e) {           
            Bitacora.registrar(e);            
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
            
        }finally{
            try {
                if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }        
        }        
    }

    public ArrayList<AsistenciaAlumno> buscarAsistenciaAlumnoPorAlumnoId(int id) throws Exception{
        AsistenciaAlumno asistencia = null;
        ResultSet rs = null;
        
        ArrayList<AsistenciaAlumno> asistencias  = new ArrayList<>();
        
        try {
            con = UConnection.getConnection();
            String sql = "call sp_asistenciaAlumno_por_alumno_id (?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            while(rs.next()){
                asistencia = new AsistenciaAlumno();
                asistencia.setAsistencia_alumno_id(rs.getInt("asistencia_alumno_id"));  
                asistencia.setFecha(rs.getDate("fecha").toLocalDate());
                
                if (!rs.getString("observacion").isEmpty()) 
                    asistencia.setObservacion(rs.getString("observacion"));  
                
                asistencias.add(asistencia);
            }
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }    
        }
        return asistencias;
    }
    
    public ArrayList<AsistenciaAlumno> listarAsistenciaAlumno() throws Exception{
        AsistenciaAlumno asistencia = new AsistenciaAlumno();
        ResultSet rs = null;
        
        ArrayList<AsistenciaAlumno> asistencias  = new ArrayList<>();
        
        try {
            con = UConnection.getConnection();
            String sql = "call sp_asistenciaAlumno_listar";
            cstm = con.prepareCall(sql);
            
            rs = cstm.executeQuery();
            while(rs.next()){
                asistencia = new AsistenciaAlumno();
                asistencia.setAlumno_id(rs.getInt("alumno_id")); 
                asistencia.setAsistencia_alumno_id(rs.getInt("asistencia_alumno_id")); 
                System.out.println("BD== " + rs.getInt("asistencia_alumno_id"));
                asistencia.setFecha(rs.getDate("fecha").toLocalDate());
                
                if (!rs.getString("observacion").isEmpty()) 
                    asistencia.setObservacion(rs.getString("observacion"));  
                else
                    asistencia.setObservacion("");
                asistencias.add(asistencia);
                System.out.println("VALOR:: "+asistencia.getAlumno_id());
            }
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }    
        }
        return asistencias;
    }
    
    public ArrayList<AsistenciaAlumno> listarAsistenciaGrupo(String nivel, String grado) throws Exception{
        AsistenciaAlumno asistencia = new AsistenciaAlumno();
        ResultSet rs = null;
        
        ArrayList<AsistenciaAlumno> asistencias  = new ArrayList<>();
        
        try {
            con = UConnection.getConnection();
            String sql = "call sp_asistenciaAlumno_listarPorGrupo(?,?)";
            cstm = con.prepareCall(sql);
            cstm.setString(1, nivel);
            cstm.setString(2, grado);
            
            rs = cstm.executeQuery();
            while(rs.next()){
                asistencia = new AsistenciaAlumno();
                asistencia.setAlumno_id(rs.getInt("alumno_id")); 
                asistencia.setAsistencia_alumno_id(rs.getInt("asistencia_alumno_id")); 
                System.out.println("BD== " + rs.getInt("asistencia_alumno_id"));
                asistencia.setFecha(rs.getDate("fecha").toLocalDate());
                
                if (!rs.getString("observacion").isEmpty()) 
                    asistencia.setObservacion(rs.getString("observacion"));  
                else
                    asistencia.setObservacion("");
                asistencias.add(asistencia);
                System.out.println("VALOR:: "+asistencia.getAlumno_id());
            }
        } catch (Exception e) {
            System.out.println(e);
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }    
        }
        return asistencias;
    }
    
    
    
    public ArrayList<AsistenciaAlumno> listarAsistenciaGrupoFecha(Date fecha, String nivel, String grado) throws Exception{
        AsistenciaAlumno asistencia = new AsistenciaAlumno();
        ResultSet rs = null;
        
        ArrayList<AsistenciaAlumno> asistencias  = new ArrayList<>();
        
        try {
            con = UConnection.getConnection();
            String sql = "call sp_asistenciaAlumno_listarPorGrupoFecha(?,?,?)";
            cstm = con.prepareCall(sql);
            cstm.setDate(1,fecha);
            cstm.setString(2, nivel);
            cstm.setString(3, grado);
            
            rs = cstm.executeQuery();
            while(rs.next()){
                asistencia = new AsistenciaAlumno();
                asistencia.setAlumno_id(rs.getInt("alumno_id")); 
                asistencia.setAsistencia_alumno_id(rs.getInt("asistencia_alumno_id")); 
                System.out.println("BD== " + rs.getInt("asistencia_alumno_id"));
                asistencia.setFecha(rs.getDate("fecha").toLocalDate());
                
                if (!rs.getString("observacion").isEmpty()) 
                    asistencia.setObservacion(rs.getString("observacion"));  
                else
                    asistencia.setObservacion("");
                asistencias.add(asistencia);
                System.out.println("VALOR:: "+asistencia.getAlumno_id());
            }
        } catch (Exception e) {
            System.out.println(e);
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }    
        }
        return asistencias;
    }
}

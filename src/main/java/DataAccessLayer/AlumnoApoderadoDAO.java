/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.AsistenciaAlumno;
import JavaBean.alumnoApoderado;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author black
 */
public class AlumnoApoderadoDAO {
    private Connection con;
    CallableStatement cstm = null; 
    
    public void insertar(alumnoApoderado alumnoApo) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_alumno_apoderado_insertar (?,?,?)";
            cstm = con.prepareCall(sql);

            cstm.setInt(1, alumnoApo.getAlumno_id());
            
            cstm.setInt(2, alumnoApo.getApoderado_id());
            
            if(alumnoApo.getParentesco().isEmpty()) cstm.setNull(3, java.sql.Types.VARCHAR);
            else cstm.setString(3, alumnoApo.getParentesco());
            
            cstm.execute();
        } catch (Exception e) {
            if(e.getMessage().contains("Duplicate entry"))
                throw new Exception("El apoderado ya está asignado al alumno seleccionado");
            
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
    
    public void actualizar(alumnoApoderado alumnoApo) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_alumno_apoderado_actualizar (?,?,?)";
            cstm = con.prepareCall(sql);
            
            cstm.setInt(1, alumnoApo.getAlumno_id());
            
            cstm.setInt(2, alumnoApo.getApoderado_id());
            
            if(alumnoApo.getParentesco().isEmpty()) cstm.setNull(3, java.sql.Types.VARCHAR);
            else cstm.setString(3, alumnoApo.getParentesco());
            
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
    
    public void eliminar(int alumno_id, int apoderado_id) throws Exception{     
        try {
            con = UConnection.getConnection();
            String sql = "call sp_alumno_apoderado_eliminar (?, ?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, alumno_id);
            cstm.setInt(2, apoderado_id);
         
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

    public alumnoApoderado buscarPorAlumnoId(int id) throws Exception{
        alumnoApoderado alumnoApo = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_alumno_apoderado_por_alumno_id(?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            while(rs.next()){
                alumnoApo = new alumnoApoderado();
                
                alumnoApo.setAlumno_id(rs.getInt("alumno_id"));  
                alumnoApo.setApoderado_id(rs.getInt("apoderado_id"));
                if(!rs.getString("parentesco").isEmpty()) 
                    alumnoApo.setParentesco(rs.getString("parentesco"));   
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
        return alumnoApo;
    }
    
    public ArrayList<alumnoApoderado> listar() throws Exception{
        ArrayList<alumnoApoderado> alumnoApos = new ArrayList<>();
        alumnoApoderado alumnoApo = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_alumno_apoderado_listar()";
            cstm = con.prepareCall(sql);
            
            rs = cstm.executeQuery();
            while(rs.next()){
                alumnoApo = new alumnoApoderado();
                
                alumnoApo.setAlumno_id(rs.getInt("alumno_id"));  
                alumnoApo.setApoderado_id(rs.getInt("apoderado_id"));
                
                if(!rs.getString("parentesco").isEmpty()) 
                    alumnoApo.setParentesco(rs.getString("parentesco"));  
                
                alumnoApos.add(alumnoApo);
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
        return alumnoApos;
    }
}

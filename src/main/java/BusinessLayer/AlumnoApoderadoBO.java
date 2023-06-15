/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import DataAccessLayer.AlumnoApoderadoDAO;
import JavaBean.alumnoApoderado;
import java.util.ArrayList;

public class AlumnoApoderadoBO {
    
    public void insertar(alumnoApoderado alumnoApo) throws Exception{
        try {
            AlumnoApoderadoDAO AlumnoApoderadoDAO = new AlumnoApoderadoDAO();
            AlumnoApoderadoDAO.insertar(alumnoApo);
        } catch (Exception e) {
            throw e;
        }
    }
    public void actualizar(alumnoApoderado alumnoApo) throws Exception{
        try {
            AlumnoApoderadoDAO AlumnoApoderadoDAO = new AlumnoApoderadoDAO();
            AlumnoApoderadoDAO.actualizar(alumnoApo);
        } catch (Exception e) {
            throw e;
        }
    }
    public void eliminar(int alumno_id, int apoderado_id) throws Exception{
        try {
            AlumnoApoderadoDAO AlumnoApoderadoDAO = new AlumnoApoderadoDAO();
            AlumnoApoderadoDAO.eliminar( alumno_id , apoderado_id);
        } catch (Exception e) {
            throw e;
        }
    }
    public alumnoApoderado buscarPorAlumnoId(int id) throws Exception{
        try {
            AlumnoApoderadoDAO alumnoApoderadoDAO = new AlumnoApoderadoDAO();
            return alumnoApoderadoDAO.buscarPorAlumnoId(id);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public ArrayList<alumnoApoderado> listar() throws Exception{
        try {
            AlumnoApoderadoDAO alumnoApoderadoDAO = new AlumnoApoderadoDAO();
            return alumnoApoderadoDAO.listar();
        } catch (Exception e) {
            throw e;
        }
    }
}

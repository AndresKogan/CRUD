/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Modelo.ModeloProfesor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class ProfesorDAO extends SQLQuery {
    private ArrayList<ModeloProfesor> profesores;
    private ModeloProfesor profesor;

    public ArrayList<ModeloProfesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<ModeloProfesor> profesores) {
        this.profesores = profesores;
    }
    
     public boolean cargaDatosDAO(ModeloProfesor profesor) {
        try {
            
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO profesor  (prof_dni, prof_nombre, prof_apellido, prof_fec_nac, prof_domicilio, prof_telefono) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setLong(1, profesor.getDni());
            preparedStmt.setString(2, profesor.getNombre());
            preparedStmt.setString(3, profesor.getApellido());
            preparedStmt.setDate(4, profesor.getFechaNac());
            preparedStmt.setString(6, profesor.getTelefono());
            preparedStmt.setString(5, profesor.getDomicilio());
            

            preparedStmt.execute();
            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<ModeloProfesor> traerDatosDAO() {
        profesores = new ArrayList();
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from profesor");
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                profesor = new ModeloProfesor();
                profesor.setDni(resultados.getLong(1));
                profesor.setNombre(resultados.getString(2));
                profesor.setApellido(resultados.getString(3));
                profesor.setFechaNac(resultados.getDate(4));
                profesor.setDomicilio(resultados.getString(5));
                profesor.setTelefono(resultados.getString(6));
               
                profesores.add(profesor);

            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return profesores;

    }

    public boolean bajaDAO(JTable tablaProfesores) {
        int seleccion;
        profesores = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM profesor WHERE prof_dni = ?");
            seleccion = tablaProfesores.getSelectedRow();
            this.consulta.setLong(1, profesores.get(seleccion).getDni());
            consulta.executeUpdate();

            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(ModeloProfesor Profesor) {    
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            java.sql.PreparedStatement preparedStmt = (java.sql.PreparedStatement) this.conn.prepareStatement("UPDATE profesor SET prof_nombre=?, prof_apellido=?, prof_fec_nac=?, prof_domicilio=?, prof_telefono=? WHERE prof_dni=?");
            preparedStmt.setString(1, Profesor.getNombre());
            preparedStmt.setString(2, Profesor.getApellido());
            preparedStmt.setDate(3, Profesor.getFechaNac());
            preparedStmt.setString(4, Profesor.getDomicilio());
            preparedStmt.setString(5, Profesor.getTelefono());
           
            preparedStmt.setLong(6, Profesor.getDni());

            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
    public boolean dniRepetidoDAO(ModeloProfesor profesor){
        
        try{
           this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from profesor where prof_dni=?");
            this.consulta.setLong(1, profesor.getDni());
            ResultSet hojadeResultados = consulta.executeQuery();
            if(hojadeResultados.next()){
                return true;              
            }
            this.desconectar();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
   }
}
    

